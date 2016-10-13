package com.zhiyin.device.dbs.consumer.controller;

import java.net.URI;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Slf4j
@Service
public class RestApiService {

    @Value("#{'${dbs.service.url}'}")
    private String employeeServiceUrl;


    @HystrixCommand(fallbackMethod = "fallback", commandKey = "hello", groupKey = "hello")
    public String hello(String req) {
//		RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(employeeServiceUrl + "/hello");
//		return restTemplate.postForObject(uri,req,String.class);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type",Arrays.asList("application/json;charset=UTF-8"));
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> entity = new HttpEntity<String>(req, headers);

        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return resp.getBody();
    }

    public String fallback(String req,Throwable e) {

        log.info("fallback",e);
        return "Fallback call, seems employee service is down";
    }

    @HystrixCommand(fallbackMethod = "fallback", commandKey = "record", groupKey = "record")
    public String record(String req) {
        URI uri = URI.create(employeeServiceUrl + "/device/record");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type",Arrays.asList("application/json;charset=UTF-8"));
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> entity = new HttpEntity<String>(req, headers);

        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return resp.getBody();
    }

    @HystrixCommand(fallbackMethod = "fallback", commandKey = "get", groupKey = "get")
    public String get(String req) {
        URI uri = URI.create(employeeServiceUrl + "/device/get");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type",Arrays.asList("application/json;charset=UTF-8"));
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> entity = new HttpEntity<String>(req, headers);

        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return resp.getBody();
    }



}
