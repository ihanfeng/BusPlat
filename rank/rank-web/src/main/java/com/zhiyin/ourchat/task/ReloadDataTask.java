package com.zhiyin.ourchat.task;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zhiyin.dbs.module.content.entity.SubjectAddrListenStat;
import com.zhiyin.dbs.module.content.service.ISubjectAddrListenStatService;
import com.zhiyin.dbs.module.user.entity.UserInfo;
import com.zhiyin.dbs.module.user.service.IUserInfoService;
import com.zhiyin.ourchat.common.RankServerFactory;
import com.zhiyin.ourchat.common.ranker.ContentListenNumRankData;
import com.zhiyin.ourchat.service.ILoadRankDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hg on 2016/7/30.
 */
@Slf4j
@Lazy(false)
@Component()
public class ReloadDataTask {

    @Autowired
    private ILoadRankDataService loadRankDataService;

    @Scheduled(fixedRate=1000 * 60 * 60 * 3 ,initialDelay=1000 * 20)
    public void timer() {
        loadRankDataService.loadContentStat();
    }

}
