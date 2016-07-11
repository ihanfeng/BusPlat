package com.hg.msg.mapper;

import com.hg.msg.entity.MsgSaveRemind;
import com.hg.msg.util.MyMapper;

public interface MsgSaveRemindMapper extends MyMapper<MsgSaveRemind> {
    int deleteByPrimaryKey(Long id);

    int insert(MsgSaveRemind record);

    int insertSelective(MsgSaveRemind record);

    MsgSaveRemind selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgSaveRemind record);

    int updateByPrimaryKey(MsgSaveRemind record);
}