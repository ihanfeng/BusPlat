package com.zhiyin.device.dbs.consumer;

import com.zhiyin.device.dbs.consumer.listener.DemoServletContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients
@EnableHystrixDashboard
@ServletComponentScan
public class ConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConsumerApplication.class);
    }

  /*  @Bean
    public DemoServletContextListener executorListener() {
        return new DemoServletContextListener();
    }*/

}
