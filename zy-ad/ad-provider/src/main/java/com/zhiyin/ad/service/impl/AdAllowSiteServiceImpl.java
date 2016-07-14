package com.zhiyin.ad.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhiyin.ad.entity.AdAllowSite;
import com.zhiyin.ad.service.IAdAllowSiteService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;

/**
 * Created by hg on 2016/7/12.
 */
@Service
public class AdAllowSiteServiceImpl extends BaseService<AdAllowSite> implements IAdAllowSiteService {
    @Override
    public BaseMapper<AdAllowSite> getBaseMapper() {
        return null;
    }
}
