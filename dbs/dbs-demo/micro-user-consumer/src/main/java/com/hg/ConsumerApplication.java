package com.hg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@EnableAutoConfiguration
//@EnableDiscoveryClient
@SpringBootApplication
//@EnableHystrix
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class ConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringApplication.class);
    }

}
