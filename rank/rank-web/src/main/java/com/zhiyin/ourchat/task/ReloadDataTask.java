package com.zhiyin.ourchat.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2016/7/30.
 */
@Slf4j
@Lazy(false)
@Component()
public class ReloadDataTask {

    @com.alibaba.dubbo.config.annotation.Reference
    ICustomAddressService customAddressService;

    //	@Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(fixedRate=1000 * 60 * 60 ,initialDelay=1000 * 20)
    public void loadAllAddress() {
        logger.info("reload CustomAddressInfo start");
        // customAddressService.selectAllByByPage(pageNum, pageSize)

        job();
        logger.info("reload CustomAddressInfo end");

    }
}
