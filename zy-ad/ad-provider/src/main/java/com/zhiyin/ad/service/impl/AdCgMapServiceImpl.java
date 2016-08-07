package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.entity.AdCgMap;
import com.zhiyin.ad.mapper.AdCgMapMapper;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.ad.service.IAdCgMapService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/19.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdCgMapServiceImpl  extends BaseService<AdCgMap> implements IAdCgMapService {

    @Resource
    private AdCgMapMapper adCgMapMapper;

    @Override
    public BaseMapper<AdCgMap> getBaseMapper() {
        return adCgMapMapper;
    }

    /**
     * 根据广告编号查询广告映射
     * @param adId
     * @return
     */
    @Override
    public List<AdCgMap> selectByAd(Long adId){

        return adCgMapMapper.selectByAd( adId);
    }
}
