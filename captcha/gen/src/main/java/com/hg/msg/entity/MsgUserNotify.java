package com.hg.msg.entity;

import java.util.Date;

public class MsgUserNotify {
    private Long id;

    private Integer isread;

    private Long user;

    private Long notify;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getNotify() {
        return notify;
    }

    public void setNotify(Long notify) {
        this.notify = notify;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}