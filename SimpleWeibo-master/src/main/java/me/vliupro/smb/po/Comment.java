package me.vliupro.smb.po;

import java.util.Date;

/**
 * Created by vliupro on 16-5-22.
 */
public class Comment {

    private int commentId; //主键ID
    private String cContent; //评论主体内容
    private int userId; //评论者ID
    private int weiboId; //评论微博ID
    private Date cCtime; //评论时间

    public Comment() {

    }

    public Comment(String cContent, int userId, int weiboId) {
        this.cContent = cContent;
        this.userId = userId;
        this.weiboId = weiboId;
        this.cCtime = new Date();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(int weiboId) {
        this.weiboId = weiboId;
    }

    public Date getcCtime() {
        return cCtime;
    }

    public void setcCtime(Date cCtime) {
        this.cCtime = cCtime;
    }
}
