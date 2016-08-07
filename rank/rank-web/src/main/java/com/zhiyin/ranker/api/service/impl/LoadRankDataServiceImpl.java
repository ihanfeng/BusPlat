//package com.zhiyin.ranker.api.service.impl;
//
//import com.github.pagehelper.PageInfo;
//import com.zhiyin.dbs.module.content.entity.SubjectAddrListenStat;
//import com.zhiyin.dbs.module.content.service.ISubjectAddrListenStatService;
//import com.zhiyin.dbs.module.user.entity.UserInfo;
//import com.zhiyin.dbs.module.user.service.IUserInfoService;
//import com.zhiyin.ranker.api.common.RankServerFactory;
//import com.zhiyin.ranker.api.common.ranker.ContentListenNumRankData;
//import com.zhiyin.ranker.api.service.ILoadRankDataService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * Created by hg on 2016/7/30.
// */
//@Slf4j
//@Service
//public class LoadRankDataServiceImpl implements ILoadRankDataService {
//
//    @com.alibaba.dubbo.config.annotation.Reference
//    ISubjectAddrListenStatService subjectAddrListenStatService;
//
//    @com.alibaba.dubbo.config.annotation.Reference
//    IUserInfoService userInfoService;
//
//
//    @Override
//    public void loadContentStat() {
//        log.warn("schedule load data, need to improve.");
//        PageInfo pageInfo = new PageInfo();
//        pageInfo.setPageNum(1);
//        pageInfo.setPageSize(200);
//
//        // 初始化
//        RankServerFactory.build();
//
//        while(true){
//            PageInfo<SubjectAddrListenStat> sel = subjectAddrListenStatService.selectByAddrRole(0L, 0L, pageInfo);
//
//            log.info("content list num {}, process page {}.",sel.getSize(),sel.getPageNum());
//            for(SubjectAddrListenStat tmp : sel.getList()){
//                UserInfo user = userInfoService.selectById(tmp.getUserId());
//                if(user == null){
//                    log.error("user should exist.{}",tmp.getUserId());
//                    continue;
//                }
//                ContentListenNumRankData data = new ContentListenNumRankData( user.getGid(),tmp.getContentNum());
//                log.info("rank data info:{} {}",user.getGid(), tmp.getContentNum() );
//                RankServerFactory.add(data);
//            }
//
//            if(sel.isIsLastPage() ){
//                break;
//            }
//            pageInfo.setPageNum(pageInfo.getPageNum()+1);
//        }
//        log.info("load data job finish.");
//    }
//}
