package com.zhiyin.frame.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class EseWebApiService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://ZHIYIN-SEARCHER/zhiyin-searcher";

    @HystrixCommand
    public String helloEse( ) {

        String hello = null;
        try {
            hello = restTemplate.getForObject(serviceUrl
                    + "/hello", String.class );
        } catch (HttpClientErrorException e) {
            log.error("call ese-web by eureak error.",e);
        }

        return hello;
    }

}
