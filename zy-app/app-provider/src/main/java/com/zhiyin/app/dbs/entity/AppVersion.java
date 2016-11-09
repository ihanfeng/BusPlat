package com.zhiyin.app.dbs.entity;

import java.util.Date;

public class AppVersion {
    private Long id;

    private Long appId;

    private String title;

    private String content;

    private String version;

    private String clientOs;

    private String clientOsVersion;

    private Integer forceUpgrade;

    private Integer isLatest;

    private Long issueUid;

    private Long developUid;

    private Date createTime;

    private Date updateTime;

    private Integer delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs == null ? null : clientOs.trim();
    }

    public String getClientOsVersion() {
        return clientOsVersion;
    }

    public void setClientOsVersion(String clientOsVersion) {
        this.clientOsVersion = clientOsVersion == null ? null : clientOsVersion.trim();
    }

    public Integer getForceUpgrade() {
        return forceUpgrade;
    }

    public void setForceUpgrade(Integer forceUpgrade) {
        this.forceUpgrade = forceUpgrade;
    }

    public Integer getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Integer isLatest) {
        this.isLatest = isLatest;
    }

    public Long getIssueUid() {
        return issueUid;
    }

    public void setIssueUid(Long issueUid) {
        this.issueUid = issueUid;
    }

    public Long getDevelopUid() {
        return developUid;
    }

    public void setDevelopUid(Long developUid) {
        this.developUid = developUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}