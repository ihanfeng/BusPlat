package me.vliupro.smb.po;

/**
 * Created by vliupro on 16-6-2.
 */
public class Thumb {

    private int thumbId;
    private int userId;
    private int weiboId;

    public Thumb() {
    }

    public Thumb(int userId, int weiboId) {
        this.userId = userId;
        this.weiboId = weiboId;
    }

    public int getThumbId() {
        return thumbId;
    }

    public void setThumbId(int thumbId) {
        this.thumbId = thumbId;
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
}
