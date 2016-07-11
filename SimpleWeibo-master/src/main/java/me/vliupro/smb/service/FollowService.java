package me.vliupro.smb.service;

import me.vliupro.smb.po.Follow;

import java.util.List;

/**
 * Created by vliupro on 16-6-1.
 */
public interface FollowService {

    /**
     * 添加关注
     * @param follow
     * @return
     */
    boolean follow(Follow follow);

    /**
     * 取消关注
     * @param follow
     * @return
     */
    boolean unFollow(Follow follow);

    /**
     * 获得follower关注的所有ID
     * @param followerId
     * @return
     */
    List<Integer> getFollowsByFollowerId(int followerId);

    /**
     * 获取所有关注followed的用户ID
     * @param followedId
     * @return
     */
    List<Integer> getFollowsByFollowedId(int followedId);

    /**
     * 获取follower关注的用户数
     * @param followerId
     * @return
     */
    int getFollowerTotal(int followerId);

    /**
     * 获取关注followed的用户数
     * @param followedId
     * @return
     */
    int getFollowedTotal(int followedId);

    /**
     * 查询是否已关注
     * @param followingId
     * @param followedId
     * @return
     */
    boolean checkFollow(int followingId, int followedId);
}
