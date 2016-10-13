package com.zhiyin.device.dbs.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @RequestMapping(method = RequestMethod.POST, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String helloPost(@RequestBody String user) {
        log.info("acc:" + user);

        return restApiService.hello(user);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/record", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String record(@RequestBody String user) {
        return restApiService.record(user);
    }




    @RequestMapping(method = RequestMethod.POST, path = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get(@RequestBody String user) {
        return restApiService.get(user);
    }




}
