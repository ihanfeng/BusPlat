package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;

/**
 * Created by hg on 2016/7/12.
 */

public class AdBasicInfoServiceImpl extends BaseService<AdBasicInfo> implements IAdBasicInfoService {


    @Override
    public BaseMapper<AdBasicInfo> getBaseMapper() {
        return null;
    }
}
