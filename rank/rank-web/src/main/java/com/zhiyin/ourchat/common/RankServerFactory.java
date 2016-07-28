package com.zhiyin.ourchat.common;

import org.hq.rank.core.RankData;
import org.hq.rank.service.IRankService;
import org.hq.rank.service.RankService;

import java.util.List;

/**
 * Created by hg on 2016/7/27.
 */
public class RankServerFactory {
    public static IRankService rankService = new RankService();

    public static final String ContentListenRank = "ContentListenRank";
    public static void build(){
        rankService.deleteRank( ContentListenRank );
        rankService.createRank(ContentListenRank ,1);
    }

    public static  void add(List<ContentListenRankData> list){
        for (int i=0 ;i< list.size();i++) {
            rankService.put(ContentListenRank, list.get(i));
        }
        int testId=30;
        RankData rankData1 = rankService.getRankDataById("rank_a", testId);
        rankService.put("rank_a", testId, 6,6,6);
        RankData rankData2 = rankService.getRankDataById("rank_a", testId);
        rankService.putByField("rank_a", testId, 1, 8);
        RankData rankData3 = rankService.getRankDataById("rank_a", testId);
    }

}
