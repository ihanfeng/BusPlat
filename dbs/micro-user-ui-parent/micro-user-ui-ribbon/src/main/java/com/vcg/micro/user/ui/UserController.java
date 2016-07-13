package com.vcg.micro.user.ui;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vcg.micro.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuyu on 2016/7/5.
 */
@RestController
@RequestMapping("/ribbon")
public class UserController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User selectByPrimaryKey(@PathVariable("id") Integer id) {
        return restTemplate.getForObject("http://micro-user/user/" + id, User.class);
    }



}
