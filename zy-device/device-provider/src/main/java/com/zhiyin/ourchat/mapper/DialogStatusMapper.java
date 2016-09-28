package com.zhiyin.ourchat.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.entity.DialogStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialogStatusMapper extends BaseMapper<DialogStatus> {


    Integer deleteByUid(Long userId);

    DialogStatus selectByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);

    int updateReaded(DialogStatus dialogStatus);

//    int updateReadNum(@Param("userId") Long userId, @Param("partnerId") Long partnerId);.
int updateReadNum( DialogStatus dialogStatus);

}