package com.zhiyin.ourchat.demo;

import com.alibaba.boot.dubbo.DubboAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
@SpringBootApplication
public class OurChatDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OurChatDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OurChatDemoApplication.class);
    }

}
