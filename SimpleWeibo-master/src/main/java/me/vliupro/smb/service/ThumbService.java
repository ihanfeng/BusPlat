package me.vliupro.smb.service;

import me.vliupro.smb.po.Thumb;

/**
 * Created by vliupro on 16-6-2.
 */
public interface ThumbService {

    /**
     * 点赞
     *
     * @param thumb
     * @return
     */
    boolean thumbing(Thumb thumb);

    /**
     * 取消点赞
     *
     * @param user_id
     * @param weibo_id
     * @return
     */
    boolean unThumb(int user_id, int weibo_id);

    /**
     * 微博下的点赞数量
     *
     * @param weiboId
     * @return
     */
    int thumbNumOfWeibo(int weiboId);

    /**
     * 判断是否已点过赞
     *
     * @param userId
     * @param weiboId
     * @return
     */
    boolean isThumbed(int userId, int weiboId);
}
