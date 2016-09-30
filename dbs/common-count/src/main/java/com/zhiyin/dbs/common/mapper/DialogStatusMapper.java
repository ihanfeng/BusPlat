package com.zhiyin.dbs.common.mapper;

import com.zhiyin.dbs.common.entity.DialogStatus;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface DialogStatusMapper extends BaseMapper<DialogStatus> {


    Integer deleteByUid(Long userId);

    DialogStatus selectByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);

    int updateReaded(DialogStatus dialogStatus);

//    int updateReadNum(@Param("userId") Long userId, @Param("partnerId") Long partnerId);.
int updateReadNum( DialogStatus dialogStatus);

}