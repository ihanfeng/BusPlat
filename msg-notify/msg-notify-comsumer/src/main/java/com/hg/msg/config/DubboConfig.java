package com.hg.msg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:context/applicationContext-dubbo.xml")
public class DubboConfig {

}
