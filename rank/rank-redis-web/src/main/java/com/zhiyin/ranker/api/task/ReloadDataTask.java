package com.zhiyin.ranker.api.task;

import com.zhiyin.ranker.api.common.RankServerFactory;
import com.zhiyin.ranker.api.service.ILoadRankDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ILoadRankDataService loadRankDataService;

//    @Scheduled(fixedRate=1000 * 60 * 60 * 3 ,initialDelay=1000 * 20)
    @Scheduled(fixedRate=1000 * 60 ,initialDelay=1000 * 20)
    public void timer() {
        loadRankDataService.loadContentStat();
    }

}
