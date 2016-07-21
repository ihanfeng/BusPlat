package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAllowSite;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;

import java.util.List;

public interface AdAllowSiteMapper extends BaseMapper<AdAllowSite> {

    List<AdAllowSite> selectByAd(Long adId);

}