package com.hg.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hg.msg.common.NotifyType;
import com.hg.msg.entity.MsgNotify;
import com.hg.msg.entity.MsgSubscription;
import com.hg.msg.entity.MsgSubscriptionConfig;
import com.hg.msg.entity.MsgUserNotify;
import com.hg.msg.mapper.MsgSubscriptionConfigMapper;
import com.hg.msg.mapper.MsgSubscriptionMapper;
import com.hg.msg.mapper.MsgUserNotifyMapper;
import com.hg.msg.service.IMsgNotifyInfoService;
import com.hg.msg.service.IMsgNotifyService;
import com.hg.msg.service.IMsgSubService;
import com.hg.msg.service.IMsgUserNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/3/22.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = {"dubbo"})
public class MsgNotifyServiceImpl implements IMsgNotifyService {

    @Autowired
    private IMsgUserNotifyService msgUserNotifyService;
    @Autowired
    private IMsgSubService msgSubService;
    @Autowired
    private IMsgNotifyInfoService msgNotifyInfoService;

    @Autowired
    private MsgUserNotifyMapper msgUserNotifyMapper;

    @Autowired
    private MsgSubscriptionMapper msgSubscriptionMapper;

    @Autowired
    private MsgSubscriptionConfigMapper msgSubscriptionConfigMapper;

    @Override
    public String testok(String name) {
        return "hello " + name;
    }

    /**
     * 测试事务
     *
     * @return
     */
    @Override
//    @Transactional
    public String testTrans() {
        MsgNotify notify = new MsgNotify();
        msgNotifyInfoService.insertSelective(notify);
        if (true) {
            throw new RuntimeException("throw");
        }
        msgNotifyInfoService.insertSelective(notify);
        return null;
    }


    @Override
    public Long createAnnounce(String content, Long sender) {
        MsgNotify msgNotify = new MsgNotify();
        msgNotify.setContent(content);
        msgNotify.setSender(sender);
        msgNotify.setType(NotifyType.Announce);

        return msgNotifyInfoService.insertSelective(msgNotify);

    }

    @Override
    public List<MsgUserNotify> pullAnnounce(Long userId) {

        // 查询最新的公告
        MsgUserNotify latestAnnounce = msgUserNotifyMapper.selectLatestNotify(userId, NotifyType.Announce);

        Date latestTime = null;
        if (latestAnnounce == null || latestAnnounce.getNotifyTime() == null) {
            // 如果为null,说明用户没有公告信息，用户可能为新用户
            // 拉取公告的起始时间应为为用户注册日
            //TODO 公告数量可能很大，比如：用户注册后没有登录过，一年以后登录。
//            latestTime = DateTime.parse("2016-03-31").toDate();
            latestTime = DateTime.now().minusDays(10).toDate();
        } else {
            latestTime = latestAnnounce.getNotifyTime();
        }

//        用lastTime作为过滤条件，查询Notify的公告信息
        List<MsgNotify> annList = msgNotifyInfoService.selectNewByType(NotifyType.Announce, latestTime);

        for (MsgNotify ann : annList) {
            msgUserNotifyService.insertSelective(userId, ann.getId(), ann.getCreateTime());
        }

        // TODO add return
        return null;
    }


    @Override
    public Long create(String content, Long sender) {
        return null;
    }


    @Transactional
    @Override
    public Long createMessage(String content, Long sender, Long receiver) {

        MsgNotify notify = new MsgNotify();
        notify.setContent(content);
        notify.setSender(sender);
        notify.setTarget(receiver);

        notify.setType(NotifyType.Message);

        // TODO why
//        notify.setTargetType("3");

        Long notifyId = msgNotifyInfoService.insertSelective(notify);


        MsgUserNotify userNotify = new MsgUserNotify();

        userNotify.setUserId(receiver);
        userNotify.setNotifyId(notifyId);
        userNotify.setIsRead(0);
        msgUserNotifyService.insertSelective(userNotify);

        if (notifyId > 0)
            throw new RuntimeException("new");
        return userNotify.getId();
    }


    @Override
    public Long createRemind(Long target, String targetType, String action, Long sender, String content) {

        MsgNotify msgNotify = new MsgNotify();
        msgNotify.setType(NotifyType.Remind);
        msgNotify.setTarget(target);
        msgNotify.setTargetType(targetType);
        msgNotify.setAction(action);
        msgNotify.setSender(sender);
        msgNotify.setContent(content);
        return msgNotifyInfoService.insertSelective(msgNotify);
    }

    @Override
    public Long pullRemind(Long userId) {

//        查询用户的订阅表，得到用户的一系列订阅记录
        List<MsgSubscription> subList = msgSubscriptionMapper.selectByUserId(userId);

//        通过每一条的订阅记录的target、targetType、action、createdAt去查询Notify表，获取订阅的Notify记录。（注意订阅时间必须早于提醒创建时间）
        List<MsgNotify> notifyList = Lists.newArrayList();
        for (MsgSubscription sub : subList) {
            List<MsgNotify> notify = msgNotifyInfoService.selectSubNotifyAfter(sub.getTarget(), sub.getTargetType(), sub.getAction(), sub.getCreateTime());
            if (notify != null) {
                notifyList.addAll(notify);
            }
        }

//        查询用户的配置文件SubscriptionConfig，如果没有则使用默认的配置DefaultSubscriptionConfig
        List<String> actionList = getSubscriptionConfig(userId);


//        使用订阅配置，过滤查询出来的Notify
        List<MsgNotify> filtedNotify = Lists.newArrayList();
        for (MsgNotify notify : notifyList) {
            for (String action : actionList) {
                if (action.equals(notify.getAction())) {
                    filtedNotify.add(notify);
                }
            }
        }


//                使用过滤好的Notify作为关联新建UserNotify
        for (MsgNotify notify : filtedNotify) {
            MsgUserNotify tmp = new MsgUserNotify();
            tmp.setNotifyId(notify.getId());
            tmp.setNotifyType(NotifyType.Remind);
            tmp.setUserId(userId);
            tmp.setNotifyTime(notify.getCreateTime());
            msgUserNotifyService.insertSelective(tmp);
        }

        return null;
    }

//    public MsgNotify selectSubNotifyAfter(Long target,String targetType,String action,Date createTime){
//        MsgNotify msgNotify = new MsgNotify();
//        msgNotify.setTarget(target);
//        msgNotify.setTargetType(targetType);
//        msgNotify.setAction(action);
//        msgNotify.setCreateTime(createTime);
//
//
//        return msgNotifyMapper.selectSubNotify(msgNotify);
//    }

    @Override
    public void subscribe(Long userId, Long targetId, String targetType, String reason) {

        List<String> actionList = NotifyConfig.action(reason);
        for (String action : actionList) {

            msgSubService.insertSelective(targetId, targetType, action, userId);
        }
    }

    @Override
    public void cancelSubscription(Long userId, Long target, String targetType) {

        MsgSubscription sub = new MsgSubscription();
        sub.setUserId(userId);
        sub.setTarget(target);
        sub.setTargetType(targetType);

        msgSubService.deleteTargetSub(userId, target, targetType);
    }

    //
    @Override
    public MsgSubscriptionConfig getSubConfig(Long userId) {

        MsgSubscriptionConfig sel = msgSubscriptionConfigMapper.selectByUserId(userId);

        if (sel == null || Strings.isNullOrEmpty(sel.getAction())) {
            sel = NotifyUtil.defaultSubConfig();
        }

        return sel;
    }

    @Override
    public List<String> getSubscriptionConfig(Long userId) {


        MsgSubscriptionConfig sel = getSubConfig(userId);

        return NotifyUtil.getSubAction(sel.getAction());

    }

    @Override
    public Integer updateSubscriptionConfig(Long userID) {
        return null;
    }

    @Override
    public List<MsgUserNotify> getUserNotify(Long uid) {

        List<MsgUserNotify> list = msgUserNotifyService.selectByUid(uid);

        log.info(JSON.toJSONString(list));
        return list;

    }

//    @Override
//    public List<MsgNotify> getUserNotify(Long uid) {
//        return msgUserNotifyMapper
//    }

    @Override
    public Integer read(Long uid, Long notifyId) {
        return 0;
    }
}
