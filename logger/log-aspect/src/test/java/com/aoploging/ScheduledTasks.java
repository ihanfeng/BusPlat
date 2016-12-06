package com.aoploging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private DemoService demoService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        demoService.processPaymentContract("ss");
        demoService.demo2("ss");

    }

}