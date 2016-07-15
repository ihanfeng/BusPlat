package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdBasicInfoMapper  extends BaseMapper<AdBasicInfo> {

    List<AdBasicInfo> selectWillShelfOn(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("shelfStatus") Integer shelfStatus);

    List<AdBasicInfo> selectWillShelfOff(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("shelfStatus") Integer shelfStatus);

}