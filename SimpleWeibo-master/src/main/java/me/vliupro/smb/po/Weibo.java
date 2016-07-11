package me.vliupro.smb.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by vliupro on 16-5-22.
 */
public class Weibo implements Comparable<Weibo> {

    private int weiboId; //主键ID
    private String wContent; //微博主体内容
    private int userId; //发表微博用户
    private Date wCtime; //发表微博时间
    private boolean isOriginal; //是否原创
    private String remark = ""; //转发评论
    private Date wFtime; //转发时间
    private int originId = -1; //原微博ID

    public Weibo() {
        remark = "转发微博";
        wFtime = new Date();
    }

    public Weibo(String wContent, int userId, boolean isOriginal) {
        this.wContent = wContent;
        this.userId = userId;
        this.wCtime = new Date();
        this.isOriginal = isOriginal;
    }

    public Weibo(String wContent, int userId, boolean isOriginal, String remark, int originId) {
        this.wContent = wContent;
        this.userId = userId;
        this.wCtime = new Date();
        this.isOriginal = isOriginal;
        this.remark = remark;
        this.wFtime = new Date();
        this.originId = originId;
    }

    public int getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(int weiboId) {
        this.weiboId = weiboId;
    }

    public String getwContent() {
        return wContent;
    }

    public void setwContent(String wContent) {
        this.wContent = wContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getwCtime() {
        return wCtime;
    }

    public void setwCtime(Date wCtime) {
        this.wCtime = wCtime;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getwFtime() {
        return wFtime;
    }

    public void setwFtime(Date wFtime) {
        this.wFtime = wFtime;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "weiboId=" + weiboId +
                ", wContent='" + wContent + '\'' +
                ", userId=" + userId +
                ", wCtime=" + wCtime +
                ", isOriginal=" + isOriginal +
                ", remark='" + remark + '\'' +
                ", forwardTime=" + wFtime +
                ", originId=" + originId +
                '}';
    }

    //倒序排列
    @Override
    public int compareTo(Weibo weibo) {
        if (this.isOriginal() && weibo.isOriginal()) {
            return this.getwCtime().before(weibo.getwCtime()) ? 1 : -1;
        } else if (!this.isOriginal() && weibo.isOriginal()) {
            return this.getwFtime().before(weibo.getwCtime()) ? 1 : -1;
        } else if (this.isOriginal() && !weibo.isOriginal()) {
            return this.getwCtime().before(weibo.getwFtime()) ? 1 : -1;
        } else {
            return this.getwFtime().before(weibo.getwFtime()) ? 1 : -1;
        }
    }
}
