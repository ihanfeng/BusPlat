package com.patterncat.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Supplier;

/**
 * 1,存储登录的用户
 * 2,存储最近登录的用户列表
 * 3,存储用户最近浏览的项目
 * 4,存储用户session的购物车
 * 5,缓存数据行
 * Created by patterncat on 2016-02-11.
 */
@Component
public class ShoppingService {

    /**
     * 登录用户
     * 数据结构 -- map
     * key -- loginMap
     * value -- k:token v:user
     */
    public static final String KEY_LOGIN_USER = "loginMap";

    /**
     * 最近登录用户
     * 数据结构 -- zset
     * key -- recentSet
     * value -- v:token score:timestamp
     */
    public static final String KEY_RECENT_USER = "recentSet";

    /**
     * 项目浏览计数
     */
    public static final String KEY_ITEM_VIEW_COUNT = "itemViewedZSet";

    /**
     * 存储用户最近浏览的项目
     * 数据结构 -- zset
     * key -- viewZset:token
     * value -- v:item score:timestamp
     */
    public static final String KEY_USER_VIEW_PREFIX = "viewZset:";

    /**
     * 用户购物车
     * 数据结构 -- map
     * key -- cartMap:session
     * value -- k:item v:count
     */
    public static final String KEY_USER_CART_PREFIX = "cartMap:";

    /**
     * 请求的缓存
     * 数据结构 -- string
     * key -- cache:hashcode
     * value -- string
     */
    public static final String KEY_CACHE_PREFIX = "cache:";

    /**
     * 缓存库存信息
     * key -- inventory:rowId
     * value -- json
     */
    public static final String KEY_INVENTORY_PREFIX = "inventory:";

    /**
     * 调度ZSet
     * key -- scheduleZSet
     * value -- k:rowId v:timestamp
     */
    public static final String KEY_SCHEDULE = "scheduleZSet";

    /**
     * 到期ZSet
     * key -- delayZSet
     * value -- k:rowId v:timestamp
     */
    public static final String KEY_DELAY = "delayZSet";

    public String formUserViewKey(String token){
        return KEY_USER_VIEW_PREFIX + token;
    }

    public String formUserCartKey(String session){
        return KEY_USER_CART_PREFIX + session;
    }

    public String formCacheKey(String request){
        return KEY_CACHE_PREFIX + request.hashCode();
    }

    public String formInventoryKey(String rowId){
        return KEY_INVENTORY_PREFIX + rowId;
    }

    @Autowired
    RedisTemplate redisTemplate;

    public void startCleanSessionTask() throws InterruptedException {
        CleanSessionTask thread = new CleanSessionTask();
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        Thread.sleep(2000);
        if (thread.isAlive()){
            throw new RuntimeException("The clean sessions thread is still alive?!?");
        }
    }

    public void startCacheRowTask(){
        new CacheRowsTask().start();
    }

    /**
     * 根据token获取登录的用户
     * @param token
     * @return
     */
    public String getLgoinUserByToken(String token) {
        return (String) redisTemplate.opsForHash().get(KEY_LOGIN_USER, token);
    }

    /**
     * 用户浏览项目
     * @param token
     * @param user
     * @param item
     */
    public void viewItem(String token,String user,String item){
        long timestamp = System.currentTimeMillis()/1000;
        //模拟登录下
        redisTemplate.opsForHash().put(KEY_LOGIN_USER, token, user);
        //更新最近登录
        redisTemplate.boundZSetOps(KEY_RECENT_USER).add(token,timestamp);

        if(item == null){
            return ;
        }

        String userViewKey = formUserViewKey(token);
        //添加最近浏览记录
        redisTemplate.boundZSetOps(userViewKey).add(item,timestamp);
        //缩减下最近浏览记录,保持在25条
        redisTemplate.boundZSetOps(userViewKey).removeRange(0,-26);
        //对项目浏览得分-1,最后升序排
        redisTemplate.boundZSetOps(KEY_ITEM_VIEW_COUNT).incrementScore(item,-1);
    }

    /**
     * 添加到购物车
     * @param session
     * @param item
     * @param count
     */
    public void addToCart(String session,String item,int count){
        String cartKey = formUserCartKey(session);
        if(count <= 0){
            redisTemplate.opsForHash().delete(cartKey,item);
        }else{
            redisTemplate.opsForHash().put(cartKey, item, String.valueOf(count));
        }
    }

    /**
     * 只保留最新100个会话
     */
    class CleanSessionTask extends Thread{

        private volatile boolean stop = false;

        private int limit = 0;

        public void quit(){
            stop = true;
        }

        @Override
        public void run() {
            while(!stop){
                Long size = redisTemplate.boundZSetOps(KEY_RECENT_USER).zCard();
                if(size <= limit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                long endIndex = Math.min(size,limit);
                BoundZSetOperations<String,String> ops = redisTemplate.boundZSetOps(KEY_RECENT_USER);
                Set<String> tokens = ops.range(0, endIndex - 1);
                List<String> sessionKeys = new ArrayList<>();
                tokens.stream().forEach(session -> {
                    sessionKeys.add(formUserCartKey(session));
                    sessionKeys.add(formUserViewKey(session));
                });
                //清理购物车及用户最近浏览记录
                redisTemplate.delete(sessionKeys);
                //清理登陆用户
                redisTemplate.opsForHash().delete(KEY_LOGIN_USER, tokens.toArray(new String[tokens.size()]));
                //清理最近登陆的用户
                redisTemplate.boundZSetOps(KEY_RECENT_USER).remove(tokens.toArray(new String[tokens.size()]));
            }
        }
    }

    /**
     * 缓存请求
     * @param request
     * @param supplier
     * @return
     */
    public String cacheRequest(String request,Supplier<String> supplier){
        if(!canCache(request)){
            //不走缓存
            return supplier.get();
        }

        String pageKey = formCacheKey(request);
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String content = ops.get(pageKey);
        if(content == null && supplier != null){
            //缓存不存在
            content = supplier.get();
            redisTemplate.opsForValue().setIfAbsent(pageKey,content);
        }
        return content;
    }

    /**
     * 判断需不需要缓存该请求
     * 浏览量上w的才请求
     * @param request
     * @return
     */
    public boolean canCache(String request){
        try {
            URL url = new URL(request);
            Map<String,String> params = new HashMap<String,String>();
            if (url.getQuery() != null){
                for (String param : url.getQuery().split("&")){
                    String[] pair = param.split("=", 2);
                    params.put(pair[0], pair.length == 2 ? pair[1] : null);
                }
            }

            String itemId = params.get("item");
            if (itemId == null || params.containsKey("_")) {
                return false;
            }
            Long rank = redisTemplate.boundZSetOps(KEY_ITEM_VIEW_COUNT).rank(itemId);
            return rank != null && rank < 10000;
        }catch(MalformedURLException mue){
            return false;
        }
    }

    /**
     * 缓存数据行
     * 1,取出schedule到期的数据项
     * 2,取出该数据项的过期时间
     * 3,更新该数据项的过期时间
     */
    class CacheRowsTask extends Thread{

        private volatile boolean stop = false;

        public void quit(){
            stop = true;
        }

        @Override
        public void run() {
            Gson gson = new Gson();
            while (!stop){
                //取第一个出来
                Set<ZSetOperations.TypedTuple> range = redisTemplate.boundZSetOps(KEY_SCHEDULE).rangeWithScores(0,0);
                ZSetOperations.TypedTuple next = range.size() > 0 ? range.iterator().next() : null;
                long now = System.currentTimeMillis() / 1000;
                if (next == null || next.getScore() > now){
                    try {
                        sleep(50);
                    }catch(InterruptedException ie){
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                String rowId = (String) next.getValue();
                double delay = redisTemplate.boundZSetOps(KEY_DELAY).score(rowId);
                if (delay <= 0) {
                    redisTemplate.boundZSetOps(KEY_DELAY).remove(rowId);
                    redisTemplate.boundZSetOps(KEY_SCHEDULE).remove(rowId);
                    redisTemplate.delete(formInventoryKey(rowId));
                    continue;
                }

                Inventory row = Inventory.get(rowId);
                redisTemplate.opsForZSet().add(KEY_SCHEDULE, rowId, now + delay);
                redisTemplate.opsForValue().set(formInventoryKey(rowId), gson.toJson(row));
            }
        }
    }

    /**
     * 被缓存的项
     */
    static class Inventory {
        private String id;
        private String data;
        private long time;

        private Inventory (String id) {
            this.id = id;
            this.data = "data to cache...";
            this.time = System.currentTimeMillis() / 1000;
        }

        public static Inventory get(String id) {
            return new Inventory(id);
        }
    }

    /**
     * 初始化缓存调度
     * @param rowId
     * @param delay
     */
    public void scheduleRowCache(String rowId, int delay) {
        redisTemplate.opsForZSet().add(KEY_DELAY,rowId,delay);
        redisTemplate.opsForZSet().add(KEY_SCHEDULE,rowId,System.currentTimeMillis() / 1000);
    }
}
