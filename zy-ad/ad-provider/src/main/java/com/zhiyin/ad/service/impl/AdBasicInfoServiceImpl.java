package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.config.AdShelfStatus;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.mapper.AdBasicInfoMapper;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/7/12.
 */

public class AdBasicInfoServiceImpl extends BaseService<AdBasicInfo> implements IAdBasicInfoService {

    @Resource
    private AdBasicInfoMapper adBasicInfoMapper;

    @Override
    public BaseMapper<AdBasicInfo> getBaseMapper() {
        return null;
    }


    public Integer shelfOn(Long id){

        AdBasicInfo ad = new AdBasicInfo();
        ad.setId(id);
//        ad.setShelfStatus(AdShelfStatus.ShelfOn);

    }

}
