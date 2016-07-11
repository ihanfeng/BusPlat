package me.vliupro.smb.dao;

import me.vliupro.smb.po.Follow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-5-23.
 */
public class FollowDaoImpl extends BaseImpl implements FollowDao {

    public boolean addFollow(Follow follow) {
        String sql = "insert into db_follow (follower_id, followed_id) values(?,?)";
        int count = this.db.update(sql, follow.getFollowerId(), follow.getFollowedId());
        return count > 0;
    }

    public boolean deleteFollow(int followId) {
        String sql = "delete from db_follow where id=?";
        int count = this.db.update(sql, followId);
        return count > 0;
    }

    public boolean deleteFollowByUser(int followerId, int followedId) {
        String sql = "delete from db_follow where follower_id=? and followed_id=?";
        int count = this.db.update(sql, followerId, followedId);
        return count > 0;
    }

    public List<Integer> getFollowsByFollower(int followerId) {
        List<Integer> followedIds = new ArrayList<Integer>();
        String sql = "select followed_id from db_follow where follower_id=?";
        List<Map<String, Object>> followMaps = this.db.queryList(sql, followerId);
        for (Map<String, Object> map : followMaps) {
            followedIds.add(Integer.parseInt(map.get("followed_id").toString()));
        }
        return followedIds;
    }

    public List<Integer> getFollowsByFollowed(int followedId) {
        List<Integer> followerIds = new ArrayList<Integer>();
        String sql = "select follower_id from db_follow where followed_id=?";
        List<Map<String, Object>> followMaps = this.db.queryList(sql, followedId);
        for (Map<String, Object> map : followMaps) {
            followerIds.add(Integer.parseInt(map.get("follower_id").toString()));
        }
        return followerIds;
    }

    @Override
    public int getFollowerNum(int followerId) {
        String sql = "select count(*) as num from db_follow where follower_id=?";
        Map<String, Object> map = db.query(sql, followerId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public int getFollowedNum(int followedId) {
        String sql = "select count(*) as num from db_follow where followed_id=?";
        Map<String, Object> map = db.query(sql, followedId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public boolean checkFollow(Follow follow) {
        String sql = "select count(*) as num from db_follow where follower_id=? and followed_id=?";
        Map<String, Object> map = db.query(sql, follow.getFollowerId(), follow.getFollowedId());
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num) > 0;
        } else {
            return false;
        }
    }

    @Override
    protected Object generate(Map<String, Object> map) {
        Follow follow = new Follow();
        follow.setFollowId(Integer.parseInt(map.get("id").toString()));
        follow.setFollowerId(Integer.parseInt(map.get("follower_id").toString()));
        follow.setFollowedId(Integer.parseInt(map.get("followed_id").toString()));
        return follow;
    }
}
