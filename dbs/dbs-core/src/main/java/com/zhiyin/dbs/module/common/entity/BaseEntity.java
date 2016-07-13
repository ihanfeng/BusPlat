package com.zhiyin.dbs.module.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hg on 2016/3/6.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date createTime;

    private Date updateTime;

    private Integer delStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
