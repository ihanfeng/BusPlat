package com.zhiyin.dbs.common.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hg on 2016/3/6.
 */
public interface BaseEntity extends Serializable {

    public Long getId();

    public void setId(Long id);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public Date getUpdateTime();

    public void setUpdateTime(Date updateTime);

    public Integer getDelStatus();

    public void setDelStatus(Integer delStatus);


}
