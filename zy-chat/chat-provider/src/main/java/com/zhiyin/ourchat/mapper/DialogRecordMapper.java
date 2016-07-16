package com.zhiyin.ourchat.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.ourchat.entity.DialogRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialogRecordMapper extends BaseMapper<DialogRecord>{


    Integer deleteByUid(Long userId);

    List<DialogRecord> selectByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);
}