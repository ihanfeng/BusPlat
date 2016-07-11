package me.vliupro.smb.dao;

import me.vliupro.smb.po.Follow;

import java.util.List;

/**
 * Created by vliupro on 16-5-23.
 */
public interface FollowDao {

    /**
     * 添加关注
     * @param follow
     * @return
     */
    boolean addFollow(Follow follow);

    /**
     * 取消关注
     * @param followId
     * @return
     */
    boolean deleteFollow(int followId);

    /**
     * 删除关注（By follower and followed）
     * @param followerId
     * @param followedId
     * @return
     */
    boolean deleteFollowByUser(int followerId, int followedId);

    /**
     * 获得follower关注的所有ID
     * @param followerId
     * @return
     */
    List<Integer> getFollowsByFollower(int followerId);

    /**
     * 获取所有关注followed的用户ID
     * @param followedId
     * @return
     */
    List<Integer> getFollowsByFollowed(int followedId);

    /**
     * 获取follower关注的用户数
     * @param followerId
     * @return
     */
    int getFollowerNum(int followerId);

    /**
     * 获取关注followed的用户数
     * @param followedId
     * @return
     */
    int getFollowedNum(int followedId);

    boolean checkFollow(Follow follow);

}
