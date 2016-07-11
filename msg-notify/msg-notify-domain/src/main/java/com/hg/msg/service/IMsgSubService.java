package com.hg.msg.service;

import com.hg.msg.entity.MsgSubscription;

/**
 * Created by wangqinghui on 2016/3/22.
 */
public interface IMsgSubService {
    MsgSubscription selectUserSub(Long target, String targetType, String action, Long userId);

    Long insertSelective(Long target, String targetType, String action, Long userId);

    int deleteTargetSub(Long userId, Long target, String targetType);
}
