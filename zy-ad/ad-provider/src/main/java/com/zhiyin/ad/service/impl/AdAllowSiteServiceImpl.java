package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.entity.AdAllowSite;
import com.zhiyin.ad.mapper.AdAllowSiteMapper;
import com.zhiyin.ad.service.IAdAllowSiteService;
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
public class AdAllowSiteServiceImpl extends BaseService<AdAllowSite> implements IAdAllowSiteService {

    @Resource
    private AdAllowSiteMapper adAllowSiteMapper;

    @Override
    public BaseMapper<AdAllowSite> getBaseMapper() {
        return adAllowSiteMapper;
    }

    @Override
    public List<AdAllowSite> selectByAd(Long adId){
        return adAllowSiteMapper.selectByAd(adId);
    }
}
