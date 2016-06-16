package com.zhiyin.frame.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class EseRestController {

    @Autowired
    private EseWebApiService notificationService;

    @RequestMapping("/ese-hello")
    public String version() {
        log.info("call ese");
        return notificationService.helloEse();
    }

}