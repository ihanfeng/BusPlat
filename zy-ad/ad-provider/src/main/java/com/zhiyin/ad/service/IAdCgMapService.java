package com.zhiyin.ad.service;

import com.zhiyin.ad.entity.AdCgMap;
import com.zhiyin.dbs.module.common.service.IBaseService;

import java.util.List;

/**
 * Created by hg on 2016/7/19.
 */
public interface IAdCgMapService extends IBaseService<AdCgMap> {
    List<AdCgMap> selectByAd(Long adId);
}
