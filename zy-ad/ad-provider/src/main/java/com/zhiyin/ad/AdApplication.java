package com.zhiyin.ad;

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
public class AdApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }

}
