package com.zhiyin.app.dbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableEurekaClient
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
public class AppDbsProviderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppDbsProviderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppDbsProviderApplication.class, args);
    }

}

