package com.zhiyin.ad.service;

import com.zhiyin.ad.entity.AdAllowSite;
import com.zhiyin.dbs.module.common.service.IBaseService;

import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
public interface IAdAllowSiteService extends IBaseService<AdAllowSite> {
    List<AdAllowSite> selectByAd(Long adId);
}
