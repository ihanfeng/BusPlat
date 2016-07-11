package me.vliupro.smb.po;

/**
 * Created by vliupro on 16-5-22.
 */
public class Follow {

    private int followId;
    private int followerId;
    private int followedId;

    public Follow() {

    }

    public Follow(int followerId, int followedId) {
        this.followedId = followedId;
        this.followerId = followerId;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }
}
