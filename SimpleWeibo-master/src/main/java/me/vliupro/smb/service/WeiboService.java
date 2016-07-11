package me.vliupro.smb.service;

import me.vliupro.smb.po.Weibo;
import me.vliupro.smb.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-5-31.
 */
public interface WeiboService {

    /**
     * 发表微博
     * @param weibo
     * @return
     */
    boolean publish(Weibo weibo);

    /**
     * 删除微博
     * @param weiboId
     * @return
     */
    boolean deleteById(int weiboId);

    /**
     * 根据weiboId获取微博
     * @param weiboId
     * @return
     */
    Weibo getWeiboById(int weiboId);

    /**
     * 获取最新微博，分页
     * @param pageNum
     * @param total
     * @return
     */
    Page<Weibo> getWeibosByPage(int pageNum, int total);

    /**
     * 根据用户ID获取微博List
     * @param userId
     * @return
     */
    List<Weibo> getWeibosByUserId(int userId);

    /**
     * 根据用户ID<List>获取微博，分页
     * @param userIds
     * @param pageNum
     * @param total
     * @return
     */
    Page<Weibo> getWeibosByListUserIds(List<Integer> userIds, int pageNum, int total);

    /**
     * 查看userId发表微博数
     * @param userId
     * @return
     */
    int getNumIfUserWeibo(int userId);

    /**
     * 获取weiboId微博的转发数量
     * @param weiboIds
     * @return
     */
    Map<String, Integer> getNumOfForwardWeibo(List<Integer> weiboIds);

    Page<Weibo> searchWeibos(String content, int pageNum, int total);
}
