package com.patterncat.mq;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Created by patterncat on 2016-02-16.
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage( final Message message, final byte[] pattern ) {
        System.out.println( "Message received: " + message.toString() );
    }
}
