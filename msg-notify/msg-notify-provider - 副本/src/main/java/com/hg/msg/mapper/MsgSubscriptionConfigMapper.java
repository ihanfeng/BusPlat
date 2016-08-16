package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSubscriptionConfig;
import com.hg.msg.util.MyMapper;

public interface MsgSubscriptionConfigMapper extends MyMapper<MsgSubscriptionConfig> {
    int deleteByPrimaryKey(Long id);

    int insert(MsgSubscriptionConfig record);

    int insertSelective(MsgSubscriptionConfig record);

    MsgSubscriptionConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgSubscriptionConfig record);

    int updateByPrimaryKey(MsgSubscriptionConfig record);

    MsgSubscriptionConfig selectByUserId(Long userId);
}