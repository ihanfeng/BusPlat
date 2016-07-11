package me.vliupro.smb.service;

import me.vliupro.smb.dao.ThumbDao;
import me.vliupro.smb.dao.ThumbDaoImpl;
import me.vliupro.smb.po.Thumb;

/**
 * Created by vliupro on 16-6-2.
 */
public class ThumbServiceImpl implements ThumbService {

    private ThumbDao td;

    public ThumbServiceImpl() {
        td = new ThumbDaoImpl();
    }

    @Override
    public boolean thumbing(Thumb thumb) {
        if (td.getThumb(thumb.getUserId(), thumb.getWeiboId()) != null) {
            return false;
        } else {
            return td.addThumb(thumb);
        }

    }

    @Override
    public boolean unThumb(int user_id, int weibo_id) {
        return td.deleteThumb(user_id, weibo_id);
    }

    @Override
    public int thumbNumOfWeibo(int weiboId) {
        return td.getNumOfWeibo(weiboId);
    }

    @Override
    public boolean isThumbed(int userId, int weiboId) {
        if (td.getThumb(userId, weiboId) != null) {
            return true;
        } else {
            return false;
        }
    }
}
