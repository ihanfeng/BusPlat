package com.hg.msg.service.impl;

import com.hg.msg.entity.MsgNotify;
import com.hg.msg.entity.MsgUserNotify;
import com.hg.msg.mapper.MsgNotifyMapper;
import com.hg.msg.mapper.MsgUserNotifyMapper;
import com.hg.msg.service.IMsgNotifyService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by wangqinghui on 2016/3/22.
 */
public class MsgNotifyService implements IMsgNotifyService {

    @Autowired
    private MsgNotifyMapper msgNotifyMapper;

    @Autowired
    private MsgUserNotifyMapper msgUserNotifyMapper;
    @Override
    public Long createAnnounce(String content, Long sender) {
        MsgNotify msgNotify = new MsgNotify();
        msgNotify.setContent(content);
        msgNotify.setSender(sender);
        msgNotify.setId(DateTime.now().getMillis());

        msgNotifyMapper.insertSelective(msgNotify);

        return msgNotify.getId();
    }

    @Override
    public List<MsgUserNotify> pullAnnounce(Long userId) {

        MsgUserNotify latestAnnounce = msgUserNotifyMapper.selectLatestAnnounce(userId);


        List<MsgNotify> annList = selectAnnounce(latestAnnounce.getCreatetime());

        for(MsgNotify msgNotify : annList){
            MsgUserNotify msgUserNotify = new MsgUserNotify();
            msgUserNotify.setId( DateTime.now().getMillis());
            msgUserNotify.setNotify(msgNotify.getId());
            msgUserNotify.setCreatetime( DateTime.now().toDate() );
            msgUserNotify.setUser(userId);
        }

        return null;
    }

    public List<MsgNotify> selectAnnounce(Date lastDate){

        return msgNotifyMapper.selectLatestByType(1,lastDate);

    }

    @Override
    public Long create(String content, Long sender) {
        return null;
    }

    @Override
    public MsgNotify createRemind(Long target, Integer targetType, Integer action, Long sender, String content) {
        return null;
    }

    @Override
    public Long createMessage(String content, Long sender, Long receiver) {

        MsgNotify notify = new MsgNotify();
        notify.setContent(content);
        notify.setSender(sender);
        notify.setTarget( receiver.intValue() );

        notify.setTargettype("3");

        msgNotifyMapper.insertSelective(notify);


        MsgUserNotify userNotify = new MsgUserNotify();
        userNotify.setCreatetime(DateTime.now().toDate());
        userNotify.setId( IdGen.gen() );
        userNotify.setUser(sender);
        userNotify.setNotify(notify.getId() );
        userNotify.setIsread(0);
//        userNotify.set
        msgUserNotifyMapper.insertSelective(userNotify);

        return userNotify.getId() ;
    }

    @Override
    public Long pullRemind(Long user) {
        return null;
    }

    @Override
    public void subscribe(Long userId, Long targetId,String targetType,String reason){

        MsgNotify msgNotify = new MsgNotify();
        msgNotify.setId( DateTime.now().getMillis() );
        msgNotify.setContent(reason);
        msgNotify.setSender(userId);
        msgNotify.setTarget(targetId.intValue());
        msgNotify.setTargettype(targetType);
        msgNotifyMapper.insertSelective(msgNotify);


    }

    @Override
    public void cancelSubscription() {

    }

    @Override
    public void getSubscriptionConfig() {

    }

    @Override
    public Integer updateSubscriptionConfig(Long userID) {
        return null;
    }

    @Override
    public List<MsgNotify> getUserNotify(Long uid) {
        return msgUserNotifyMapper
    }

    @Override
    public Integer read(Long uid, Long notifyId) {
        return null;
    }
}
