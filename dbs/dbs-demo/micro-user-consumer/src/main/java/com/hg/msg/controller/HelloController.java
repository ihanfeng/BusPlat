package com.hg.msg.controller;

import com.vcg.micro.user.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
