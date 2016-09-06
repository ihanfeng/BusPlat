package com.hg.msg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

//@Profile("product")
@Configuration
@PropertySource("classpath:config/dubbo-config.properties")
@ImportResource("classpath:applicationContext-dubbo.xml")
public class DubboConfig {

}
