package com.hg.msg.service;

import com.hg.msg.entity.MsgNotify;
import com.zhiyin.dbs.module.common.service.impl.BaseService2;

import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/4/7.
 */
public interface IMsgNotifyInfoService extends BaseService2<Long,MsgNotify> {

    public Long insertSelective(MsgNotify msgNotify);

    List<MsgNotify> selectNewByType(Integer type, Date createTime);

    List<MsgNotify> selectSubNotifyAfter(Long target, String targetType, String action, Date createTime);
}
