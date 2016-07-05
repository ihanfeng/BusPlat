package com.hg.msg.mapper;

import com.hg.msg.entity.MsgUserNotify;

public interface MsgUserNotifyMapper {
    int insert(MsgUserNotify record);

    int insertSelective(MsgUserNotify record);

    MsgUserNotify selectLatestAnnounce(Long userId);
}