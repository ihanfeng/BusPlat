package com.zhiyin.ranker.api.web;

/**
 * Created by hg on 2016/2/6.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class C2sObj  {

    String access_token;
    String subjectId; // 用户id
    int subjectType; //用户类型


}
