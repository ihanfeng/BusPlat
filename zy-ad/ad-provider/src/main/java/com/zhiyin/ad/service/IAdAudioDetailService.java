package com.zhiyin.ad.service;

import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.dbs.module.common.service.IBaseService;

import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
public interface IAdAudioDetailService extends IBaseService<AdAudioDetail>{
    List<AdAudioDetail> selectByAd(Long adId);
}
