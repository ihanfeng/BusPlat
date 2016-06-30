package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSubscription;

public interface MsgSubscriptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgSubscription record);

    int insertSelective(MsgSubscription record);

    MsgSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgSubscription record);

    int updateByPrimaryKey(MsgSubscription record);
}