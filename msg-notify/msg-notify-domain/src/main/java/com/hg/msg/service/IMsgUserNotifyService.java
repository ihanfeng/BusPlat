package com.hg.msg.service;

import com.hg.msg.entity.MsgUserNotify;

import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/3/22.
 */
public interface IMsgUserNotifyService {

    public Long insertSelective(MsgUserNotify msgUserNotify);

    Long insertSelective(Long userId, Long notifyId, Date notifyTime);

    List<MsgUserNotify> selectByUid(Long userId);
}
