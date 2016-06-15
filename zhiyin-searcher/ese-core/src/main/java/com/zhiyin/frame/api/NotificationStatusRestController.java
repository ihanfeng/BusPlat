package com.zhiyin.frame.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/")
public class NotificationStatusRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/version")
    public String version() {
        log.info("call version");

        return notificationService.version();
    }

}