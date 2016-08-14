package com.zhiyin.ourchat.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "zhiyin-chat")
public interface ChatRestApiService {

	@RequestMapping(method = RequestMethod.GET, value = "/zhiyin-chat/hello/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String hello(@PathVariable("name") String name);

}