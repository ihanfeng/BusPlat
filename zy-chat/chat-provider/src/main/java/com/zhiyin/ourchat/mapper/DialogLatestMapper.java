package com.zhiyin.ourchat.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.ourchat.entity.DialogLatest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialogLatestMapper extends BaseMapper<DialogLatest> {

    List<DialogLatest> selectByUid(@Param("userId") Long userId);

    List<DialogLatest> selectLatest(@Param("userId") Long userId, @Param("partnerId") Long partnerId);


//    int deleteOldLatest(@Param("userId") Long userId, @Param("partnerId") Long partnerId);


    Integer deleteByPartner(@Param("userId") Long userId, @Param("partnerId") Long partnerId);


    Integer deleteByUid(Long userId);
}