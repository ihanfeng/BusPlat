package com.zhiyin.ranker.api.vo;

import com.zhiyin.ranker.api.web.C2sObj;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hg on 2016/7/28.
 */
@Getter
@Setter
public class BaseAroundC2s extends C2sObj {
    private Long userId;
    private Integer around; // 周围数量
}
