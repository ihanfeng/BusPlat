package com.zhiyin.search.es.module.test.controller;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * just for test server.
 * Created by hg on 2016/6/15.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello, I'm ESE-WEB.");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello " + name +", I'm ESE-WEB.";
    }

}
