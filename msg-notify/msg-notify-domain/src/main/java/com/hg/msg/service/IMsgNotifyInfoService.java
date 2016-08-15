package com.hg.msg.service;

import com.hg.msg.entity.MsgNotify;

import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/4/7.
 */
public interface IMsgNotifyInfoService {

    public Long insertSelective(MsgNotify msgNotify);

    List<MsgNotify> selectNewByType(Integer type, Date createTime);

    List<MsgNotify> selectSubNotifyAfter(Long target, String targetType, String action, Date createTime);
}
