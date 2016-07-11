package com.hg.msg.service;

import com.hg.msg.entity.MsgSubscriptionConfig;
import com.hg.msg.entity.MsgUserNotify;

import java.util.List;

/**
 * Created by wangqinghui on 2016/3/22.
 */
public interface IMsgNotifyService {

    String testok(String name);

    String testTrans();

    /**
     * 往Notify表中插入一条公告记录
     */
    public Long createAnnounce(String content, Long sender);


    /**
     * 从UserNotify中获取最近的一条公告信息的创建时间: lastTime
     * 用lastTime作为过滤条件，查询Notify的公告信息
     * 新建UserNotify并关联查询出来的公告信息
     *
     * @param userId
     * @return
     */
    public List<MsgUserNotify> pullAnnounce(Long userId);


//    public List<MsgNotify> selectAnnounce(Date lastDate);


    public Long create(String content, Long sender);


    /**
     * 往Notify表中插入一条提醒记录
     */
    public Long createRemind(Long target, String targetType, String action, Long sender, String content);

    /**
     * 往Notify表中插入一条信息记录
     * 往UserNotify表中插入一条记录，并关联新建的Notify
     */
    public Long createMessage(String content, Long sender, Long receiver);


    /**
     * 从UserNotify中获取最近的一条公告信息的创建时间: lastTime
     * <p/>
     * 用lastTime作为过滤条件，查询Notify的公告信息
     * 新建UserNotify并关联查询出来的公告信息
     **/
    public Long pullRemind(Long user);


    /**
     * 查询用户的订阅表，得到用户的一系列订阅记录
     * 通过每一条的订阅记录的target、targetType、action、createdAt去查询Notify表，获取订阅的Notify记录。（注意订阅时间必须早于提醒创建时间）
     * 查询用户的配置文件SubscriptionConfig，如果没有则使用默认的配置DefaultSubscriptionConfig
     * 使用订阅配置，过滤查询出来的Notify
     * 使用过滤好的Notify作为关联新建UserNotify
     **/
    public void subscribe(Long userId, Long targetId, String targetType, String reason);


    /**
     * 通过reason，查询NotifyConfig，获取对应的动作组:actions
     * <p/>
     * 遍历动作组，每一个动作新建一则Subscription记录
     **/
    public void cancelSubscription(Long userId, Long target, String targetType);

    //
    MsgSubscriptionConfig getSubConfig(Long userId);

    /**
     * 删除user、target、targetType对应的一则或多则记录
     */
//    public void getSubscriptionConfig(userID)

//    查询SubscriptionConfig表，获取用户的订阅配置

    //
    List<String> getSubscriptionConfig(Long userId);

    /**
     * 更新用户的SubscriptionConfig记录
     */
    public Integer updateSubscriptionConfig(Long userID);


    /**
     * 获取用户的消息列表
     */
    public List<MsgUserNotify> getUserNotify(Long uid);


    /**
     * 更新指定的notify，把isRead属性设置为true
     */
    public Integer read(Long uid, Long notifyId);


}
