package com.zhiyin.device.dbs.mapper;

import com.zhiyin.device.dbs.entity.DialogStatus;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface DialogStatusMapper extends BaseMapper<DialogStatus> {


    Integer deleteByUid(Long userId);

    DialogStatus selectByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);

    int updateReaded(DialogStatus dialogStatus);

//    int updateReadNum(@Param("userId") Long userId, @Param("partnerId") Long partnerId);.
int updateReadNum( DialogStatus dialogStatus);

}