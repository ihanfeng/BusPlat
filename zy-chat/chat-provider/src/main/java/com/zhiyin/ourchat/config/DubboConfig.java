package com.zhiyin.ourchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Profile("product")
@Configuration
@ImportResource("classpath:context/applicationContext-dubbo.xml")
public class DubboConfig {

}
