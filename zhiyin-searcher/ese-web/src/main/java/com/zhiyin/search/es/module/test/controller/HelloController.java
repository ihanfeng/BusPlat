package com.zhiyin.search.es.module.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/6/15.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String test() {
//        String hello = "hello you!";
//        log.info( hello );
//        return hello;
//    }

}
