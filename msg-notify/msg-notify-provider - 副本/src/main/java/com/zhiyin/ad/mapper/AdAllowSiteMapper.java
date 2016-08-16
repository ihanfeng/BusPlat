package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAllowSite;

public interface AdAllowSiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdAllowSite record);

    int insertSelective(AdAllowSite record);

    AdAllowSite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdAllowSite record);

    int updateByPrimaryKey(AdAllowSite record);
}