package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSaveRemind;

public interface MsgSaveRemindMapper {
    int insert(MsgSaveRemind record);

    int insertSelective(MsgSaveRemind record);
}