package com.hg.msg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.vcg.micro.user.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class HelloController {


//    @Reference(version = "1.0.0")
    @com.alibaba.dubbo.config.annotation.Reference
    private IHelloService helloService;

    @RequestMapping(method = RequestMethod.GET, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        return helloService.sayHello("admin");
    }

}
