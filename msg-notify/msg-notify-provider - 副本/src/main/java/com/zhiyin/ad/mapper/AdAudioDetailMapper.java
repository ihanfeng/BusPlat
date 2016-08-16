package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAudioDetail;

public interface AdAudioDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdAudioDetail record);

    int insertSelective(AdAudioDetail record);

    AdAudioDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdAudioDetail record);

    int updateByPrimaryKey(AdAudioDetail record);
}