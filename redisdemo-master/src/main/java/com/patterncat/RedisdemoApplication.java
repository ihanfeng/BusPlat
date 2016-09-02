package com.patterncat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterncat.mq.MyMessageListener;
import com.patterncat.mq.MyPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class RedisdemoApplication {

	/**
	 * 设置缓存对象的序列化方式,不设置会报错
	 * 另外对于json序列化,对象要提供默认空构造器
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(300);
		return cacheManager;
	}

	@Bean
	public RedisTemplate redisTemplate(
			RedisConnectionFactory factory) {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
//		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setValueSerializer( new GenericToStringSerializer<Object>( Object.class));
		template.afterPropertiesSet();
		template.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
//		template.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
//		template.setHashValueSerializer(new StringRedisSerializer(StandardCharsets.UTF_8));

		template.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));

		return template;
	}

	/**
	 * 自定义key的生成策略
	 * @return
	 */
	@Bean
	public KeyGenerator myKeyGenerator(){
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	//publish
	@Bean
	MyPublisher redisPublisher(RedisConnectionFactory factory) {
		return new MyPublisher( redisTemplate(factory), topic() );
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic( "pubsub:queue" );
	}

    //subscribe
	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter( new MyMessageListener() );
	}

	@Bean
	RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory) {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.addMessageListener(messageListener(), topic());
		return container;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisdemoApplication.class, args);
	}
}
