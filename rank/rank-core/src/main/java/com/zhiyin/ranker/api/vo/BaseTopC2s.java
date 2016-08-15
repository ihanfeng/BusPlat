package com.zhiyin.ranker.api.vo;

import com.zhiyin.ranker.api.web.C2sObj;
import lombok.Getter;
import lombok.Setter;

/**
 * 排行榜Top
 * Created by hg on 2016/7/28.
 */
@Getter
@Setter
public class BaseTopC2s extends C2sObj {
    private Integer top; // 排行榜数量
}
