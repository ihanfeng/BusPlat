package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.entity.AdCgMap;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdCgMapMapper
        extends BaseMapper<AdCgMap> {
    List<AdCgMap> selectByAd(@Param("adId") Long adId);
}