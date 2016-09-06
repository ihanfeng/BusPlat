package com.patterncat.service;

import com.patterncat.bean.ArticleBean;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 0,文章 : map
 * 1,对文章投票 : map中的votes字段incr
 * 2,每个用户只能投一次 : 文章用户投票set
 * 3,文章按投票和时间排行 : 投票,时间 与 文章 set
 * 4,文章打标签
 * Created by patterncat on 2016-02-10.
 */
@Component
public class VoteService {

    /**
     * incr,获取文章的id
     */
    public static final String KEY_ARTICLE_ID_SEQ = "articleIdSeq";

    /**
     * 数据结构:SET
     * key -- voted:articleId
     * value -- user
     * 记录每篇文章的投票用户
     */
    public static final String KEY_VOTE_ARTICLE_PREFIX = "votedSet:";

    /**
     * 数据结构:map
     * key -- articleMap:id
     * value --  article map
     */
    public static final String KEY_ARTICLE_PREFIX = "articleMap:";

    /**
     * 数据结构:zset
     * key -- scoreZSet
     * value -- 得分 articleMap:id
     */
    public static final String KEY_SCORE = "scoreZSet";

    /**
     * 数据结构:zset
     * key -- timeZSet
     * value -- 得分 articleMap:id
     */
    public static final String KEY_TIME = "timeZSet";

    /**
     * 文章标签前缀
     * 数据结构 set
     * key -- tagSet:tag
     * value -- articleMap:id
     */
    public static final String KEY_TAG_PREFIX = "tagSet:";

    /**
     * 文章用户投票记录的过期时间
     */
    public static final int ARTICLE_USER_VOTE_EXPIRE_DAY = 7;

    /**
     * 文章用户投票记录的过期时间
     * 秒为单位
     */
    public static final int ONE_WEEK_IN_SECONDS = 7 * 86400;

    /**
     * 投票得分
     */
    public static final int VOTE_SCORE = 432;

    /**
     * 每页多少条记录
     */
    public static final int ARTICLES_PER_PAGE = 25;


    @Autowired
    RedisTemplate redisTemplate;

    public String formArticleKey(Long id){
        return KEY_ARTICLE_PREFIX + id;
    }

    public String formVoteKey(Long id){
        return KEY_VOTE_ARTICLE_PREFIX + id;
    }

    public String formTagKey(String tag){
        return KEY_TAG_PREFIX + tag;
    }

    public String formScoreTagKey(String scorePrefix,String tag){
        return scorePrefix + ":" + tag;
    }

    /**
     * 添加文章
     * @param user
     * @param title
     * @param link
     * @return 文章的自增id
     */
    public Long postArticle(String user,String title,String link){
        ValueOperations<String, Long> intOps = redisTemplate.opsForValue();
        Long id = intOps.increment(KEY_ARTICLE_ID_SEQ,1L);

        //本人发布的文章,自动添加到已投票set中
        voteInternal(id,user);

        Map<String,Object> articleData = new HashMap<String,Object>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        long nowInSecs = System.currentTimeMillis() / 1000;
        articleData.put("now", String.valueOf(nowInSecs));
        articleData.put("votes", "1"); //主要投票数的计数器

        //保存文章
        String articleKey = formArticleKey(id);
        redisTemplate.opsForHash().putAll(articleKey, articleData);

        //记录得分和时间
        redisTemplate.boundZSetOps(KEY_SCORE).add(articleKey,nowInSecs + VOTE_SCORE);
        redisTemplate.boundZSetOps(KEY_TIME).add(articleKey,nowInSecs);

        return id;
    }

    public void delete(Long id){
        String articleKey = formArticleKey(id);
        redisTemplate.delete(articleKey);
        redisTemplate.boundZSetOps(KEY_SCORE).remove(articleKey);
        redisTemplate.boundZSetOps(KEY_TIME).remove(articleKey);
        String votedKey = formVoteKey(id);
        redisTemplate.delete(votedKey);
        //delete tag interset
    }

    private Long voteInternal(Long id,String user){
        String votedKey = formVoteKey(id);
        Long rs = redisTemplate.opsForSet().add(votedKey,user);
        if(rs == 1){
            redisTemplate.expire(votedKey, ARTICLE_USER_VOTE_EXPIRE_DAY, TimeUnit.DAYS);
        }
        return rs;
    }

    /**
     * 根据id获取文章内容
     * @param id
     * @return
     */
    public ArticleBean getArticleById(Long id){
        Map<String,Object> data = redisTemplate.opsForHash().entries(formArticleKey(id));
        ArticleBean bean = new ArticleBean();
        bean.setId(id);
        try {
            BeanUtils.populate(bean,data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 用户给文章投票
     * 严格一点需要加事务
     * @param user
     * @param id
     */
    public void voteArticle(String user,Long id){
        long now = System.currentTimeMillis() / 1000;
        String articleKey = formArticleKey(id);
        Double score = redisTemplate.boundZSetOps(KEY_SCORE).score(articleKey);
        //文章发布超过一周,不能投票
        if(score.longValue() + ONE_WEEK_IN_SECONDS < now){
            return;
        }

        //判断用户是否可以投票
        //若已经投票则返回,未投票则投票,并增加投票记录
        Long tryVote = voteInternal(id,user);
        if(tryVote == 0){
            return;
        }

        //给文章增加投票
        redisTemplate.opsForHash().increment(articleKey,"votes",1L);
        //增加文章的得分
        redisTemplate.boundZSetOps(KEY_SCORE).incrementScore(articleKey,VOTE_SCORE);
    }

    /**
     * 获取文章的得票数
     * @param id
     * @return
     */
    public Long getArticleVote(Long id){
        HashOperations<String,String,Long> ops = redisTemplate.opsForHash();
        return ops.get(formArticleKey(id),"votes");
    }

    /**
     * 根据排序及页数获取文章
     * @param page
     * @param zrangeKey
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private List<ArticleBean> getArticleRank(int page,String zrangeKey){
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        BoundZSetOperations<String,String> ops = redisTemplate.boundZSetOps(zrangeKey);
        //根据得分从大到小
        Set<String> ids = ops.reverseRange(start, end);
        List<ArticleBean> rs = ids.stream().map(new Function<String, ArticleBean>() {
            @Override
            public ArticleBean apply(String id) {
                HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
                Map<String, String> map = hashOps.entries(id);
                ArticleBean bean = new ArticleBean();
                bean.setId(Long.valueOf(id.substring(id.indexOf(':') + 1)));
                try {
                    BeanUtils.populate(bean, map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return bean;
            }
        }).collect(Collectors.toList());
        return rs;
    }

    /**
     * 给文章打标签
     * @param id
     * @param tags
     */
    public void tagArticle(Long id,String[] tags){
        String articleKey = formArticleKey(id);
        for(String tag:tags){
            String tagKey = formTagKey(tag);
            redisTemplate.opsForSet().add(tagKey,articleKey);
        }
    }

    /**
     * 根据标签获取文章排名
     * @param page
     * @param zrangeKey
     * @param tag
     * @return
     */
    public List<ArticleBean> getArticleRankByTag(int page,String zrangeKey,String tag){
        String scoreTagKey = formScoreTagKey(zrangeKey, tag);
        Boolean exist = redisTemplate.hasKey(scoreTagKey);
        if(!exist){
            Long rs = redisTemplate.opsForZSet().intersectAndStore(formTagKey(tag),zrangeKey,scoreTagKey);
            if(rs == 1){
                redisTemplate.expire(scoreTagKey,1,TimeUnit.MINUTES);
            }
        }
        return getArticleRank(page,scoreTagKey);
    }

    /**
     * 根据得分排序
     * @param page
     * @return
     */
    public List<ArticleBean> getArticleRankByScore(int page){
        return getArticleRank(page,KEY_SCORE);
    }

    /**
     * 根据时间排序
     * @param page
     * @return
     */
    public List<ArticleBean> getArticleRankByTime(int page){
        return getArticleRank(page,KEY_TIME);
    }
}
