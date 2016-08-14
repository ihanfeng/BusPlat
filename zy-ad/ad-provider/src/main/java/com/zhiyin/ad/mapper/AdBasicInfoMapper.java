package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface AdBasicInfoMapper  extends BaseMapper<AdBasicInfo> {

    @Update("set names utf8mb4")
    public void setCharsetToUtf8mb4();

    List<AdBasicInfo> selectWillShelfOn(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("shelfStatus") Integer shelfStatus);

    List<AdBasicInfo> selectWillShelfOff(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("shelfStatus") Integer shelfStatus);

}