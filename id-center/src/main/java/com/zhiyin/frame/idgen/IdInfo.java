package com.zhiyin.frame.idgen;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangqinghui on 2016/4/28.
 */
@Getter
@Setter
public class IdInfo {

    private Long val;
    private Integer realLen;
    private Integer finalLen;
    private String timestamp;
    private String datacenter;
    private String sequence;

}
