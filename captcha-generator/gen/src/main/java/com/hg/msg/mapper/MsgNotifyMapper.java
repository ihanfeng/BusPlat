package com.hg.msg.mapper;

import com.hg.msg.entity.MsgNotify;

import java.util.Date;
import java.util.List;

public interface MsgNotifyMapper {
    int insert(MsgNotify record);

    int insertSelective(MsgNotify record);

    List<MsgNotify> selectLatestByType(int type,Date date);
}