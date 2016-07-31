package com.zhiyin.ranker.api.vo;

import com.zhiyin.ranker.api.web.S2cObj;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hg on 2016/7/30.
 */
@Getter
@Setter

public class RankDataS2c extends S2cObj {

    private Long userId;
    private Integer userGid;
    private Integer rankNum;

    // 由于使用排名是从0开始，所以需要加1
    public void setRankNum(Integer rankNum){
        this.rankNum = rankNum +1 ;
    }
}
