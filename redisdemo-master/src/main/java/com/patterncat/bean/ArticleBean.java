package com.patterncat.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by patterncat on 2016-02-11.
 */
public class ArticleBean {

    private Long id;

    private String title;

    private String link;

    private String user;

    private Long now;

    private Integer votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
