package com.zhiyin.search.es.web;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class BasicContentS2c extends S2cObj {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String adminDescription;
    private Integer gid;
    private String title;
    private int size = 1;
    private Integer source;
    private Float duration;
    private Long roleId;
    private String keyword;
    private Integer playPriority;
    private Integer difficultyDegree;
    private String description;
    private String tag;
    private String savePath;
    private Integer fsType;
    private Integer chapterNum;
    private Long articleId;
    private Date shelveOnTime;
    private Date shelveOffTime;
    private Long createUserId;
    private Long updateUserId;
    private Date createTime;
    private Date expireTime;
    private String expireReason;
    private Long expireUserId;
    private Integer delStatus;
}