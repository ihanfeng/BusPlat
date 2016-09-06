package com.patterncat.service;

import com.patterncat.bean.ReportBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by patterncat on 2016-01-30.
 */
@Component
public class DemoService {

    /**
     * Using SpEL for conditional caching - only cache method executions when
     * the name is equal to "Joshua"
     */
    @Cacheable(value="messageCache", condition="'patterncat'.equals(#name)")
    public String getMessage(String name) {
        System.out.println("Executing DemoService" +
                ".getHelloMessage(\"" + name + "\")");

        return "Hello " + name + "!";
    }

    @Cacheable(value = "reportcache",keyGenerator = "myKeyGenerator")
    public ReportBean getReport(Long id, String date, String content, String title) {
        System.out.println("无缓存的时候调用这里---数据库查询");
        return new ReportBean(id, date, content, title);
    }
}
