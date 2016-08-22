package com.zhiyin.ourchat.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.entity.DialogStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangqinghui on 2016/8/22.
 */
public interface IDialogStatusService extends IBaseService<DialogStatus> {

    Boolean updateReadNum(Long userId, Long partnerId);

    Boolean updateReaded(Long userId, Long partnerId);

    DialogStatus selectByPartner(Long userId, Long partnerId);

    Integer deleteByUid(@Param("userId") Long userId);

}
