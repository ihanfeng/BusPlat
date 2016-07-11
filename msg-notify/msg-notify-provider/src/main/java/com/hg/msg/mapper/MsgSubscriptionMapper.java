package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSubscription;
import com.hg.msg.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsgSubscriptionMapper extends MyMapper<MsgSubscription> {
    int deleteByPrimaryKey(Long id);

    int insert(MsgSubscription record);

    int insertSelective(MsgSubscription record);

    MsgSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgSubscription record);

    int updateByPrimaryKey(MsgSubscription record);


    // 查询用户的订阅
    public MsgSubscription selectUserSub(@Param("target") Long target, @Param("targetType") String targetType, @Param("action") String action, @Param("userId") Long userId);

    List<MsgSubscription> selectByUserId(@Param("userId") Long userId);

    int deleteTargetSub(Long userId, Long target, String targetType);
}