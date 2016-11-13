package com.zhiyin.device.dbs.consumer.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhiyin.dbs.module.common.service.IServerStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class ScheduledTasks {


    @com.alibaba.dubbo.config.annotation.Reference
//    @Qualifier("deviceServerStatusService")
    private IServerStatusService deviceServerStatusService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

//        log.info( deviceServerStatusService.info() );

    }

}