package com.zhiyin.j2cache.spring;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * 功能说明：memcachedCacheManager
 *
 */
public class J2cacheCacheManager extends AbstractCacheManager {

    private Collection<Cache> caches;
    private boolean dynamic = true;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

    public void setCaches(Collection<Cache> caches) {
        this.caches = caches;
    }




    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null && this.dynamic) {
            return createAndAddCache(name);
        }

        return cache;
    }


    protected Cache createAndAddCache(String cacheName) {
        addCache(createCache(cacheName));
        return super.getCache(cacheName);
    }

    protected J2cacheCache createCache(String cacheName) {
        J2cacheCache c = new J2cacheCache();
        c.setName(cacheName);
        return c;
    }

}
