package com.zhiyin.ranker.api.vo;

import com.zhiyin.ranker.api.web.S2cObj;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 排行榜Top
 * Created by hg on 2016/7/28.
 */
@Getter
@Setter
public class ContentListenNumTopS2c extends S2cObj {
    private List<RankDataS2c> list;
}
