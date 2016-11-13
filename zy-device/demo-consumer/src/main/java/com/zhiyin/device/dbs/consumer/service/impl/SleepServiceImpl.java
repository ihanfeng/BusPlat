package com.zhiyin.device.dbs.consumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@Slf4j
@Service
public class SleepServiceImpl {

    @HystrixCommand(fallbackMethod = "fallback", commandKey = "hello", groupKey = "hello")
    public String sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok.";
    }

    public String fallback(int sleepTime,Throwable e) {
        log.info("fallback",e);
        return "Fallback call, seems employee service is down";
    }

}
