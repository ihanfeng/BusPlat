package com.zhiyin.device.dbs.mapper;

import com.zhiyin.device.dbs.entity.DialogRecord;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialogRecordMapper extends BaseMapper<DialogRecord>{


    Integer deleteByUid(Long userId);

    List<DialogRecord> selectByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);
}