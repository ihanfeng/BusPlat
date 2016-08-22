package com.zhiyin.frame.api;


import com.zhiyin.ese.api.client.SearchContentRestApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class EseRestController {

//    @Autowired
//    private EseWebApiService notificationService;

    @Autowired
    private SearchContentRestApiService searchContentRestApiService;


    @RequestMapping("/ese-hello")
    public String version() {
        log.info("call ese");
        return
                searchContentRestApiService.hello("admin");
    }

}