package com.zhiyin.device.dbs.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello" + name;
    }

    @RequestMapping(value = "/ok")
    public String ok() {
        return "ok";
    }

}
