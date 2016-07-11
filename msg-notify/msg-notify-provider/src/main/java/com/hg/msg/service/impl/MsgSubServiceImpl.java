package com.hg.msg.service.impl;

import com.hg.msg.entity.MsgSubscription;
import com.hg.msg.mapper.MsgSubscriptionMapper;
import com.hg.msg.service.IMsgSubService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangqinghui on 2016/3/31.
 */
@Service
public class MsgSubServiceImpl implements IMsgSubService {

    @Autowired
    private MsgSubscriptionMapper msgSubscriptionMapper;


    @Override
    public MsgSubscription selectUserSub(Long target, String targetType, String action, Long userId) {
        return msgSubscriptionMapper.selectUserSub(target, targetType, action, userId);
    }


    @Override
    public Long insertSelective(Long target, String targetType, String action, Long userId) {

        MsgSubscription sub = msgSubscriptionMapper.selectUserSub(target, targetType, action, userId);

        // 如果已经订阅，进行更新
        if (sub != null) {
            sub.setUpdateTime(DateTime.now().toDate());
            return sub.getId();
        }


        // 插入新数据
        sub = new MsgSubscription();


        sub.setId(IdGen.gen());
        sub.setUpdateTime(DateTime.now().toDate());
        sub.setCreateTime(DateTime.now().toDate());

        sub.setDelStatus(0);


        sub.setTarget(target);
        sub.setTargetType(targetType);
        sub.setAction(
                action
        );
        sub.setUserId(userId);


        msgSubscriptionMapper.insertSelective(sub);
        return sub.getId();
    }

    @Override
    public int deleteTargetSub(Long userId, Long target, String targetType) {
        return msgSubscriptionMapper.deleteTargetSub(userId, target, targetType);
    }

}
