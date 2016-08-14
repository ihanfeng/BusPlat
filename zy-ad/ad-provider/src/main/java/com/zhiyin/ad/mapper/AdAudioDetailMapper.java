package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AdAllowSite;
import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdAudioDetailMapper  extends BaseMapper<AdAudioDetail> {



    List<AdAudioDetail> selectByAd(Long adId);
}