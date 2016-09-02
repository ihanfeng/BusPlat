package com.patterncat.mq;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicLong;

/**
 * https://dzone.com/articles/redis-pubsub-using-spring
 * Created by patterncat on 2016-02-16.
 */
public class MyPublisher {

    private final RedisTemplate< String, Object > template;
    private final ChannelTopic topic;
    private final AtomicLong counter = new AtomicLong( 0 );

    public MyPublisher( final RedisTemplate< String, Object > template,
                               final ChannelTopic topic ) {
        this.template = template;
        this.topic = topic;
    }

    @Scheduled( fixedDelay = 100 )
    public void publish() {
        template.convertAndSend( topic.getTopic(), "Message " + counter.incrementAndGet() +
                ", " + Thread.currentThread().getName() );
    }
}
