package me.vliupro.smb.dao;

import me.vliupro.smb.po.Thumb;

/**
 * Created by vliupro on 16-6-2.
 */
public interface ThumbDao {

    /**
     * 增加点赞
     * @param thumb
     * @return
     */
    boolean addThumb(Thumb thumb);

    /**
     * 取消点赞
     * @param userId
     * @param weiboId
     * @return
     */
    boolean deleteThumb(int userId, int weiboId);

    /**
     * 获得微博的点赞数量
     * @param weiboId
     * @return
     */
    int getNumOfWeibo(int weiboId);

    /**
     * 根据userId, weiboId获取Thumb对象
     * @param userId
     * @return
     */
    Thumb getThumb(int userId, int weiboId);
}
