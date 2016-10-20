package com.zhiyin.user.dbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableEurekaClient
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DubboAutoConfiguration.class})
public class UserDbsProviderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserDbsProviderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserDbsProviderApplication.class, args);
    }

}

