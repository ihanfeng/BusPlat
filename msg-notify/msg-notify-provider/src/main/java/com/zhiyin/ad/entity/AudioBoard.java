package com.zhiyin.ad.entity;

import java.util.Date;

public class AudioBoard {
    private Long id;

    private Long companyId;

    private String addrIds;

    private String title;

    private Date shelfOnTime;

    private Date shelfOffTime;

    private Date createTime;

    private Date updateTime;

    private Integer shelfStatus;

    private Integer delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAddrIds() {
        return addrIds;
    }

    public void setAddrIds(String addrIds) {
        this.addrIds = addrIds == null ? null : addrIds.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getShelfOnTime() {
        return shelfOnTime;
    }

    public void setShelfOnTime(Date shelfOnTime) {
        this.shelfOnTime = shelfOnTime;
    }

    public Date getShelfOffTime() {
        return shelfOffTime;
    }

    public void setShelfOffTime(Date shelfOffTime) {
        this.shelfOffTime = shelfOffTime;
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

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}