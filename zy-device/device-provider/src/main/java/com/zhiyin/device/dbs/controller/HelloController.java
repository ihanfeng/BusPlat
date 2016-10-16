package com.zhiyin.device.dbs.controller;



import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(method = RequestMethod.POST, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String helloPost(@RequestBody User user) {
        log.info("hello" + user.getName());
        return "hello " + user.getName() +"!";
    }

    @RequestMapping(value = "/ok")
    public String ok() {
        return "ok";
    }


    public static void main(String[] args) {

        User user = new User();
        user.setName("admin");
        log.info(JSON.toJSONString(user) );
    }

}
