package com.github.q120011676.spring.j2cache.server;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by say on 3/31/16.
 */
@Component
@CacheConfig(cacheNames = {"spring_j22"}, keyGenerator = "smallHump")
public class TUtil {

    @CachePut(key = "#name")
    public String setN(String name) {
        return name;
    }

    @Cacheable(key = "#name")
    public String getN(String name) {
        System.out.println("getN");
        return null;
    }
}
