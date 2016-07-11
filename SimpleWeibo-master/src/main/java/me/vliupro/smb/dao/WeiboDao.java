package me.vliupro.smb.dao;

import me.vliupro.smb.po.Weibo;

import java.util.List;

/**
 * Created by vliupro on 16-5-23.
 */
public interface WeiboDao {

    /**
     * 添加微博(原创)
     * @param weibo
     * @return
     */
    boolean addWeibo(Weibo weibo);

    /**
     * 添加微博(转发)
     * @param weibo
     * @return
     */
    boolean addForwardWeibo(Weibo weibo);

    /**
     * 删除微博
     * @param weiboId
     * @return
     */
    boolean deleteWeibo(int weiboId);

    /**
     * 根据ID获取微博
     * @param weiboId
     * @return
     */
    Weibo getWeiboById(int weiboId);

    /**
     * 最新微博，实现分页
     * @param begin
     * @param total
     * @return
     */
    List<Weibo> getWeibosLimited(int begin, int total);

    /**
     * 根据用户ID查找所有微博
     * @param userId
     * @return
     */
    List<Weibo> getWeibosByUserId(int userId);

    /**
     * 查找weibo的数量
     * @return
     */
    int getTotalNum();

    /**
     * 查看发表微博数
     * @param userId
     * @return
     */
    int getUserWeiboNum(int userId);

    /**
     * 查找转发微博weiboId数
     * @return
     */
    int getForwardWeiboNum(int weiboId);

    List<Weibo> searchWeibos(String content, int begin, int total);
}
