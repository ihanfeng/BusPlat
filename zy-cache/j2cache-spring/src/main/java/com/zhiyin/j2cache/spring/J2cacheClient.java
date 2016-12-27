package com.zhiyin.j2cache.spring;


import net.oschina.j2cache.CacheObject;

import java.util.List;

/**
 * 缓存对外开发接口
 *
 */
public class J2cacheClient extends CacheAbstractTemplate {

    @Override
    public Object get(String region, String key) {
        CacheObject co = new CacheObject();
        co.setRegion(region);
        co.setKey(key);

        CacheObject cacheObj = cache.get(region, key);

        return cacheObj.getValue();
    }

    @Override
    public void set(String region, String key, Object value) {
        set(region, key, value, 0);
    }

    @Override
    public void set(String region, String key, Object value, Integer expired) {

        cache.set(region,key,value);
    }

    @Override
    public void evict(String region, String key) {
        cache.evict(region,key);
    }

    @Override
    public void batchEvict(String region, List keys) {

        cache.batchEvict(region,keys);
    }

    @Override
    public void clear(String region) {

        cache.clear(region);
    }

    @Override
    public List keys(String region) {
        return cache.keys(region);
    }

}
