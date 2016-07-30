package com.zhiyin.ourchat.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.content.entity.SubjectAddrListenStat;
import com.zhiyin.dbs.module.content.service.ISubjectAddrListenStatService;
import com.zhiyin.dbs.module.user.entity.UserInfo;
import com.zhiyin.dbs.module.user.service.IUserInfoService;
import com.zhiyin.ourchat.common.RankServerFactory;
import com.zhiyin.ourchat.common.ranker.ContentListenNumRankData;
import com.zhiyin.ourchat.service.ILoadRankDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/7/30.
 */
@Slf4j
@Service
public class LoadRankDataServiceImpl implements ILoadRankDataService{
    @com.alibaba.dubbo.config.annotation.Reference
    ISubjectAddrListenStatService subjectAddrListenStatService;

    @com.alibaba.dubbo.config.annotation.Reference
    IUserInfoService userInfoService;


    @Override
    public void loadContentStat() {
        log.warn("schedule load data, need to improve.");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(200);

        // 初始化
        RankServerFactory.build();

        while(true){
            PageInfo<SubjectAddrListenStat> sel = subjectAddrListenStatService.selectByAddrRole(0L, 0L, pageInfo);

            for(SubjectAddrListenStat tmp : sel.getList()){
                UserInfo user = userInfoService.selectById(tmp.getUserId());
                if(user == null){
                    log.error("user should exist.{}",tmp.getUserId());
                    continue;
                }
                ContentListenNumRankData data = new ContentListenNumRankData( user.getGid(),tmp.getContentNum());
                RankServerFactory.add(data);
            }

            if(sel.isIsLastPage() ){
                break;
            }
            pageInfo.setPageNum(pageInfo.getPageNum()+1);
        }
        log.info("load data job finish.");
    }
}
