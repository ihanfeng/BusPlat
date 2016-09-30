package com.zhiyin.dbs.common.service;

import com.zhiyin.dbs.common.entity.DialogStatus;
import com.zhiyin.dbs.module.common.service.IBaseService;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wangqinghui on 2016/8/22.
 */
public interface IDialogStatusService extends IBaseService<DialogStatus> {

    Boolean updateReadNum(Long userId, Long partnerId);

    Boolean updateReaded(Long userId, Long partnerId);

    DialogStatus selectByPartner(Long userId, Long partnerId);

    Integer deleteByUid(@Param("userId") Long userId);

}
