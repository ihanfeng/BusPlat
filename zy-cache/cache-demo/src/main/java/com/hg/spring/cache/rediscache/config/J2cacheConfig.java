package com.hg.spring.cache.rediscache.config;

import com.github.q120011676.spring.j2cache.J2cacheCacheManager;
import com.google.common.collect.Maps;
import com.hg.spring.cache.rediscache.cache.LoggingRedisTemplate;
import com.hg.spring.cache.rediscache.cache.RedisCacheErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.lang.reflect.Method;
import java.util.Map;

@Configuration
@EnableCaching(proxyTargetClass = true)
//public class RedisCacheConfig implements CachingConfigurer {
public class J2cacheConfig extends CachingConfigurerSupport {

	@Bean
	public CacheManager cacheManager() {
		J2cacheCacheManager cacheManager = new J2cacheCacheManager();
		return cacheManager;
	}

	// redis 错误处理方式
	public CacheErrorHandler errorHandler() {
		return new RedisCacheErrorHandler();
	}


}