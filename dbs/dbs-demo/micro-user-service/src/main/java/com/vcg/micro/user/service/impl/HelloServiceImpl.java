package com.vcg.micro.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.vcg.micro.user.service.IHelloService;
//import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/7/13.
 */
//@org.springframework.stereotype.Service
@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + "!";
    }

}
