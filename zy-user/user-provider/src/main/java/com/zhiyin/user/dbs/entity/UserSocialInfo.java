package com.zhiyin.user.dbs.entity;

import java.util.Date;

public class UserSocialInfo {
    private Integer id;

    private Integer userId;

    private String openId;

    private Boolean platformId;

    private Boolean channelId;

    private Date createTime;

    private Date updateTime;

    private Integer delStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Boolean getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Boolean platformId) {
        this.platformId = platformId;
    }

    public Boolean getChannelId() {
        return channelId;
    }

    public void setChannelId(Boolean channelId) {
        this.channelId = channelId;
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