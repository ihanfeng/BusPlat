package com.zhiyin.j2cache;

import com.google.common.collect.Lists;
import com.zhiyin.j2cache.spring.J2cacheCache;
import com.zhiyin.j2cache.spring.J2cacheCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@ComponentScan("com.zhiyin.j2cache")
@EnableCaching(proxyTargetClass = true)
public class J2cacheCacheConfig extends CachingConfigurerSupport {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CacheManager cacheManager() {

        J2cacheCacheManager cacheManager = new J2cacheCacheManager();

        List<Cache> cacheList = Lists.newArrayList();
        J2cacheCache c = new J2cacheCache();
        c.setName("test");
        cacheList.add(c);


//        J2cacheCache c2 = new J2cacheCache();
//        c2.setName("accountCache");
//        cacheList.add(c2);


        cacheManager.setCaches(cacheList);

        return cacheManager;
    }



}