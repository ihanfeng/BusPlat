package com.zhiyin.ranker.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/dubbo-config.properties")
@ImportResource("classpath:applicationContext-dubbo-consumer.xml")
public class DubboConsumerConfig {


//    @Be

}
