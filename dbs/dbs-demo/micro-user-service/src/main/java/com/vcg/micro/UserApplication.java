package com.vcg.micro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@EnableAutoConfiguration
@ComponentScan("com.vcg.micro.user")
@MapperScan("com.vcg.micro.user.dao")
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@EnableEurekaClient
//@EnableDiscoveryClient
//@EnableSwagger2//see http://localhost:8080/swagger-ui.html
public class UserApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(UserApplication.class, args);
    }

}
