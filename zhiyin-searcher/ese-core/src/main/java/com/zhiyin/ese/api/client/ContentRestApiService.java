package com.zhiyin.ese.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = SearcherClientApiInfo.WebProj)
public interface ContentRestApiService {

	@RequestMapping(method = RequestMethod.GET, value = "/"+SearcherClientApiInfo.WebProj+"/hello/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello(@PathVariable("name") String name);

}