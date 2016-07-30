package com.zhiyin.ranker.api.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhiyin.ranker.api.common.ranker.ContentListenNumRankData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.hq.rank.core.RankData;
import org.hq.rank.service.IRankService;
import org.hq.rank.service.RankService;

import java.util.List;

/**
 * Created by hg on 2016/7/27.
 */
@Slf4j
public class RankServerFactory {
    public static IRankService rankService = new RankService();

    // 按照收听内容量排名
    public static final String ContentListenNumRank = "ContentListenNumRank";

    // 初始化
    public static void build(){
        rankService.deleteRank( ContentListenNumRank );
        rankService.createRank(ContentListenNumRank ,1);
//        return this;
    }

    public static  void add(ContentListenNumRankData tmp){

            rankService.put(ContentListenNumRank, tmp.getUserGid(),tmp.getLisNum());

    }

    public static  void add(List<ContentListenNumRankData> list){
        for (int i=0 ;i< list.size();i++) {
            add(list.get(i));
        }
    }



    public static void main(String[] args){

        List<ContentListenNumRankData> listenNumRankDatas = Lists.newArrayList();

        for(int i = 0; i<10;i++){
            listenNumRankDatas.add(new ContentListenNumRankData(i, RandomUtils.nextInt()));
        }
        RankServerFactory.build();
        RankServerFactory.add(listenNumRankDatas);

        List<RankData> res = RankServerFactory.rankService.getRankDatasAroundId(ContentListenNumRank, 0, 2, 2);
        log.info(JSON.toJSONString(res));


        for(int i = 0; i<10;i++){
            listenNumRankDatas.add(new ContentListenNumRankData(i, i));
        }
        RankServerFactory.build();
        RankServerFactory.add(listenNumRankDatas);

        res = RankServerFactory.rankService.getRankDatasAroundId(ContentListenNumRank, 0, 2, 2);
        log.info(JSON.toJSONString(res));

        System.out.println("succ end.");
    }

}
