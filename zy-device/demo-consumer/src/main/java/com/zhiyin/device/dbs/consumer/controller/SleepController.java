package com.zhiyin.device.dbs.consumer.controller;

import com.zhiyin.device.dbs.consumer.service.impl.SleepServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/10/25.
 */

@Slf4j
@RestController
public class SleepController {

    @Autowired
    SleepServiceImpl sleepService;

    @RequestMapping(method = RequestMethod.GET, path = "/sleep", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        int sleep = RandomUtils.nextInt(2100, 2200);
        log.info("sleep:{}",sleep);
       return sleepService.sleep(sleep);
    }

}
