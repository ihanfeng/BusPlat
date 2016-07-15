package com.zhiyin.ourchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
public class OurChatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OurChatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OurChatApplication.class, args);
    }

}
