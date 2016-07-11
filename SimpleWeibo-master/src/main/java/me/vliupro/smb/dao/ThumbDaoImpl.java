package me.vliupro.smb.dao;

import me.vliupro.smb.po.Thumb;

import java.util.Map;

/**
 * Created by vliupro on 16-6-2.
 */
public class ThumbDaoImpl extends BaseImpl implements ThumbDao {

    @Override
    public boolean addThumb(Thumb thumb) {
        String sql = "insert into db_thumb (user_id, weibo_id) values(?,?)";
        int count = db.update(sql, thumb.getUserId(), thumb.getWeiboId());
        return count > 0;
    }

    @Override
    public boolean deleteThumb(int userId, int weiboId) {
        String sql = "delete from db_thumb where user_id=? and weibo_id=?";
        int count = db.update(sql, userId, weiboId);
        return count > 0;
    }

    @Override
    public int getNumOfWeibo(int weiboId) {
        String sql = "select count(*) as num from db_thumb where weibo_id=?";
        Map<String, Object> map = db.query(sql, weiboId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public Thumb getThumb(int userId, int weiboId) {
        String sql = "select * from db_thumb where user_id=? and weibo_id=?";
        Map<String, Object> map = db.query(sql, userId, weiboId);
        if (map != null) {
            return (Thumb) this.generate(map);
        } else {
            return null;
        }
    }

    @Override
    protected Object generate(Map<String, Object> map) {
        Thumb thumb = new Thumb();
        thumb.setThumbId(Integer.parseInt(map.get("id").toString()));
        thumb.setUserId(Integer.parseInt(map.get("user_id").toString()));
        thumb.setUserId(Integer.parseInt(map.get("weibo_id").toString()));
        return thumb;
    }
}
