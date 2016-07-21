package com.zhiyin.ourchat;

import com.alibaba.boot.dubbo.DubboAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
@SpringBootApplication
public class OurChatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OurChatApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OurChatApplication.class);
    }

}
