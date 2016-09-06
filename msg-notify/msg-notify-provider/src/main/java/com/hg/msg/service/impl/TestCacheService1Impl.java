package com.hg.msg.service.impl;

import com.hg.msg.service.ITestCacheService1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by wangqinghui on 2016/8/26.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = {"dubbo"})
public class TestCacheService1Impl implements ITestCacheService1 {

    @Override
    public String selectById(Long id) {
        log.info("call test1 sel method, id:{}",id);
        return String.valueOf(id);
    }

    @Override
    public Integer addInfo(String name) {
        log.info("call test1 sel method, id:{}",name);
        return 1;
    }
}
