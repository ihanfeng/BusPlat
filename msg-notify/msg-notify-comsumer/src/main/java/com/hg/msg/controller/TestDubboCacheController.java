package com.hg.msg.controller;

import com.hg.msg.service.ITestCacheService1;
import com.hg.msg.service.ITestCacheService2;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
@RequestMapping("/cache")
public class TestDubboCacheController {

    @com.alibaba.dubbo.config.annotation.Reference
    private ITestCacheService1 testCacheService1;

    @com.alibaba.dubbo.config.annotation.Reference
    private ITestCacheService2 testCacheService2;

    @RequestMapping(method = RequestMethod.GET, path = "/testok", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {

        log.info("call test dubbo cache.");
        testCacheService1.selectById(1L);
        testCacheService1.selectById(1L);
        testCacheService1.selectById(2L);


        testCacheService2.selectById(1L);
        testCacheService2.selectById(1L);

        return "";
    }

}
