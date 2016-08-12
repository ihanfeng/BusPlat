package com.zhiyin.ad.entity;

import com.zhiyin.dbs.module.common.entity.BaseEntity;

import java.util.Date;

public class AdCgMap  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long adAudioId;

    private Long adId;

    private Long cgId;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Integer delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdAudioId() {
        return adAudioId;
    }

    public void setAdAudioId(Long adAudioId) {
        this.adAudioId = adAudioId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getCgId() {
        return cgId;
    }

    public void setCgId(Long cgId) {
        this.cgId = cgId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}