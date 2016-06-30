package com.hg.msg.entity;

public class MsgSubscriptionConfig {
    private String action;

    private Long user;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}