package com.zhiyin.ad.task;

import com.alibaba.fastjson.JSON;
import com.zhiyin.ad.config.AdTimerConfig;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.service.IAdBasicInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private IAdBasicInfoService adBasicInfoService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = AdTimerConfig.TimerInterval * 1000 * 60)
    public void reportCurrentTime() {

        List<AdBasicInfo> list = adBasicInfoService.selectWillShelfOn();
        for(AdBasicInfo tmp : list){
            log.info("process ad:{}", JSON.toJSONString(tmp));


        }

//        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }

}
