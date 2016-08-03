package com.zhiyin.ourchat.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class DemoAppService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoAppService.class).web(true).run(args);

//        SpringApplication notificationMicroService = new SpringApplication(DemoAppService.class);
//        notificationMicroService.addListeners(new ApplicationPidFileWriter("notification-micro-service.pid"));
//        notificationMicroService.run(args);
    }

}
