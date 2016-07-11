package me.vliupro.smb.service;

import me.vliupro.smb.dao.FollowDao;
import me.vliupro.smb.dao.FollowDaoImpl;
import me.vliupro.smb.po.Follow;

import java.util.List;

/**
 * Created by vliupro on 16-6-1.
 */
public class FollowServiceImpl implements FollowService {

    private FollowDao fd = new FollowDaoImpl();

    @Override
    public boolean follow(Follow follow) {
        return fd.addFollow(follow);
    }

    @Override
    public boolean unFollow(Follow follow) {
        return fd.deleteFollowByUser(follow.getFollowerId(), follow.getFollowedId());
    }

    @Override
    public List<Integer> getFollowsByFollowerId(int followerId) {
        return fd.getFollowsByFollower(followerId);
    }

    @Override
    public List<Integer> getFollowsByFollowedId(int followedId) {
        return fd.getFollowsByFollowed(followedId);
    }

    @Override
    public int getFollowerTotal(int followerId) {
        return fd.getFollowerNum(followerId);
    }

    @Override
    public int getFollowedTotal(int followedId) {
        return fd.getFollowedNum(followedId);
    }

    @Override
    public boolean checkFollow(int followingId, int followedId) {
        return fd.checkFollow(new Follow(followingId, followedId));
    }
}
