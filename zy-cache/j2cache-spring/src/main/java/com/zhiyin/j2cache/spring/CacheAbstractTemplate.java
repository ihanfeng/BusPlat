package com.zhiyin.j2cache.spring;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <p>缓存方法调用入口</p>
 * <p>注：需设置JVM禁用IPv6服务，编译时添加参数：-Djava.net.preferIPv4Stack=true</p>
 *
 * @author FY
 */
public abstract class CacheAbstractTemplate {


    CacheChannel cache = J2Cache.getChannel();


    // ----------------------- Cache method ----------------------------

    /**
     * <b>缓存中取数据</b>
     * @param region 缓存region name
     * @param key 缓存业务key
     * @return {@link CacheObject}
     */
    public abstract Object get(String region, String key);

    /**
     * 写入缓存 - 不带过期时间
     * @param region 缓存region name
     * @param key 缓存key
     * @param value 缓存数据
     */
    public abstract void set(String region, String key, Object value);

    /**
     * 写入缓存 - 带过期时间;
     * <b>不建议使用该方法，当前缓存的实现架构下，有效期不能同时设置，ehcache在配置文件中，而redis可以在代码上设置，所以不是很合理</b>
     * @param region 缓存region name
     * @param key 缓存key
     * @param value 缓存数据
     * @param expired 过期时间
     */
    public abstract void set(String region, String key, Object value, Integer expired);


    /**
     * 删除缓存
     * @param region 缓存region name
     * @param key 缓存key
     */
    public abstract void evict(String region, String key);

    /**
     * 删除缓存 - 批量
     * @param region 缓存region name
     * @param keys 缓存key集合
     */
    public abstract void batchEvict(String region, List keys);

    /**
     * 清除缓存
     * @param region 缓存region name
     */
    public abstract void clear(String region);

    /**
     * 获得一个region下面所有的cache key
     * @param region 缓存region name
     * @return {@link List} key list
     */
    public abstract List keys(String region);




}
