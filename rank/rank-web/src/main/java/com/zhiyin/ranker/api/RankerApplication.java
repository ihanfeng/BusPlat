package com.zhiyin.ranker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
@SpringBootApplication
public class RankerApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RankerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RankerApplication.class);
    }

}
