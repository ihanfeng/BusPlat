package com.zhiyin.app.dbs;

import com.zhiyin.boot.dbs.DruidAutoConfiguration;
import com.zhiyin.boot.dbs.EnableDruidAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableEurekaClient
@EnableDruidAutoConfiguration
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

