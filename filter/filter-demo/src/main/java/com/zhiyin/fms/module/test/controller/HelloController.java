package com.zhiyin.fms.module.test.controller;

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
    public ResponseEntity<String> hello(@RequestParam(value = "name", required = false) String name) {
        name = Optional.fromNullable(name).or("default");
        return ResponseEntity.ok("hello " + name + ", I'm FILTER-DEMO-WEB.");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ResponseEntity<String> helloPost(@RequestBody String req) {
        req = Optional.fromNullable(req).or("default");
        return ResponseEntity.ok("hello " + req + ".");
    }

}
