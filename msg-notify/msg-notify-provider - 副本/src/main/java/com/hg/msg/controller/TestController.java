package com.hg.msg.controller;

import com.alibaba.fastjson.JSON;
import com.hg.msg.service.IMsgNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private IMsgNotifyService msgNotifyService;

    @RequestMapping("/testok")
    public String hello() {
        msgNotifyService.getUserNotify(1L);
        return "hello";
    }

    @RequestMapping(value = "/testpost" , method = RequestMethod.POST)
    public String hello(TestC2s c2s) {

        log.info(JSON.toJSONString(c2s));

        return JSON.toJSONString(c2s);
    }

    class TestC2s {
        String id ;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
