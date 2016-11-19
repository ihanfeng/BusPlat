package com.zhiyin.app.dbs.service.impl;

import com.zhiyin.app.dbs.entity.AppInfo;
import com.zhiyin.app.dbs.mapper.AppInfoMapper;
import com.zhiyin.app.dbs.service.IAppInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
//@CacheConfig(cacheNames = {RedisCacheName.UserCacheName}) // 默认cache名称
public class AppInfoServiceImpl extends BaseService<AppInfo> implements IAppInfoService {

    @Autowired
    AppInfoMapper appInfoMapper;

    @Override
    public BaseMapper<AppInfo> getBaseMapper() {
        return appInfoMapper;
    }

}