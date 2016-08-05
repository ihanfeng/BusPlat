package com.zhiyin.ourchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
public class OurChatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OurChatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OurChatApplication.class, args);
    }

}

