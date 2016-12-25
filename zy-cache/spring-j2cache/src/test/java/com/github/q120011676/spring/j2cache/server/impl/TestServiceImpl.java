package com.github.q120011676.spring.j2cache.server.impl;

import com.github.q120011676.spring.j2cache.server.TUtil;
import com.github.q120011676.spring.j2cache.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by say on 3/21/16.
 */
@Service("ts")
@CacheConfig(cacheNames = {"spring_j2"}, keyGenerator = "smallHump")
public class TestServiceImpl implements TestService {

    @Autowired
    private TUtil t;

    @Override
    @Cacheable(key = "name")
    public String getName() {
        return "A";
    }

    @Override
    @CachePut(key = "name")
    public String setName(String name) {
        return name;
    }

    @Override
    @CacheEvict(key = "name")
    public void cleanName() {
    }

    @Override
    public void setN(String name) {
        t.setN(name);
    }

    @Override
    public void setB(String name) {
        this.setName(name);
    }

    @Override
    public void setC(String name) {
        this.setN(name);
    }

    @Override
    public String getC() {
        return this.getName();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clean() {
    }
}
