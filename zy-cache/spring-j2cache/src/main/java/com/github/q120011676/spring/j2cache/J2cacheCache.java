package com.github.q120011676.spring.j2cache;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheException;
import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.List;

/**
 * Created by say on 3/21/16.
 */
public class J2cacheCache implements Cache {
    private CacheChannel cacheChannel = J2Cache.getChannel();
    private String name;
    private static final Logger LOGGER = LoggerFactory.getLogger(J2cacheCache.class);

    public J2cacheCache() {
        this.setName("default");
    }

    public J2cacheCache(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.cacheChannel;
    }

    @Override
    public ValueWrapper get(Object o) {
        CacheObject co = this.cacheChannel.get(this.getName(), o);
        return co.getValue() != null ? new SimpleValueWrapper(co.getValue()) : null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        CacheObject co = this.cacheChannel.get(this.getName(), o);
        return co.getValue() != null ? (T) co.getValue() : null;
    }

    @Override
    public void put(Object o, Object o1) {
        this.cacheChannel.set(this.getName(), o, o1);
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        CacheObject co = this.cacheChannel.get(this.getName(), o);
        if (co.getValue() != null) {
            this.put(o, o1);
            return null;
        }
        return new SimpleValueWrapper(co.getValue());
    }

    @Override
    public void evict(Object o) {
        if (this.get(o) != null) {
            try {
                this.cacheChannel.evict(this.getName(), o);
            } catch (CacheException e) {
                LOGGER.warn(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void clear() {
        List keys = this.cacheChannel.keys(this.getName());
        if (keys != null && keys.size() > 0) {
            try {
                this.cacheChannel.clear(this.getName());
            } catch (CacheException e) {
                LOGGER.warn(e.getLocalizedMessage());
            }
        }
    }
}
