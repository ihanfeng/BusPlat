package com.github.q120011676.spring.j2cache;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by say on 3/21/16.
 */
public class J2cacheCacheManager extends AbstractTransactionSupportingCacheManager {
    private Collection<Cache> caches;
    private boolean dynamic = true;

    public void setCaches(Collection<Cache> caches) {
        this.caches = caches;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        if (this.caches == null && this.dynamic) {
            this.caches = new HashSet<Cache>();
        }
        Assert.notNull(this.caches, "A redis template is required in order to interact with data store");
        return this.caches;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null && this.dynamic) {
            this.addCache(new J2cacheCache(name));
        }
        return super.getCache(name);
    }
}
