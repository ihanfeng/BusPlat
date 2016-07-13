package com.vcg.micro;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.vcg.micro.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyu on 2016/7/6.
 */
@SpringBootApplication
@EnableEurekaClient
public class DubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }


    @Bean
    public ReferenceBean<UserService> userService(){
        return ref(UserService.class);
    }

    public <T> ReferenceBean<T> ref(Class<T> iface) {
        ReferenceBean<T> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(iface);
        //指定要消费的协议,
        referenceBean.setProtocol("dubbo");
        return referenceBean;
    }
}
