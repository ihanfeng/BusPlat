package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdBasicInfo;

public interface AdBasicInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdBasicInfo record);

    int insertSelective(AdBasicInfo record);

    AdBasicInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdBasicInfo record);

    int updateByPrimaryKey(AdBasicInfo record);
}