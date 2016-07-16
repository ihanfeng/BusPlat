package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.ad.mapper.AdAudioDetailMapper;
import com.zhiyin.ad.service.IAdAudioDetailService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdAudioDetailServiceImpl extends BaseService<AdAudioDetail> implements IAdAudioDetailService {

    @Resource
    private AdAudioDetailMapper audioDetailMapper;

    @Override
    public BaseMapper<AdAudioDetail> getBaseMapper() {
        return audioDetailMapper;
    }

    @Override
    public List<AdAudioDetail> selectByAd(Long adId){
        return audioDetailMapper.selectByAd(adId);
    }


}
