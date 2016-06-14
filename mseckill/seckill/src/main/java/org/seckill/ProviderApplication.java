package org.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

//@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
@ImportResource("classpath*:spring/spring*.xml")
public class ProviderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProviderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
