package com.zhiyin.search.es.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCommonC2s {

    private Long id;

    private Long roleId;
    private String title;
    private Float duration;// 时长
    private String document;
    private String description;// 描述
    private String tag;// 内容标签
    private String savePath;// 保存路径


}
