package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSubscriptionConfig;

public interface MsgSubscriptionConfigMapper {
    int insert(MsgSubscriptionConfig record);

    int insertSelective(MsgSubscriptionConfig record);
}