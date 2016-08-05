package com.zhiyin.ranker.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient( "zhiyin-ranker")
public interface ContentListenClient {


    final static String prefix = "/zhiyin-ranker";

    @RequestMapping(method = RequestMethod.GET, value = prefix +"/hello/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello(@PathVariable("name") String name);


	@RequestMapping(method = RequestMethod.GET, value = prefix +"/contents/rank/top/{top}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String top(@PathVariable("top") Integer top);

}