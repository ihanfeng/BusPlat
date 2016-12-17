package com.zhiyin.app.dbs.service.impl;

import com.zhiyin.app.dbs.config.RedisCacheName;
import com.zhiyin.app.dbs.entity.AppInfo;
import com.zhiyin.app.dbs.mapper.AppInfoMapper;
import com.zhiyin.app.dbs.service.IAppInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper2;
import com.zhiyin.dbs.module.common.service.impl.BaseService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
@CacheConfig(cacheNames = {RedisCacheName.UserCacheName}) // 默认cache名称
public class AppInfoServiceImpl extends BaseService2<Long,AppInfo> implements IAppInfoService {

    @Autowired
    AppInfoMapper appInfoMapper;

    @Override
    public BaseMapper2<Long,AppInfo> getBaseMapper() {
        return appInfoMapper;
    }

//    @Cacheable
@Cacheable(key = "#root.caches[0].name+'.id.'+#id")
public AppInfo selectById(Long id){
        return super.selectById(id);
    }


    @CacheEvict(key = "#root.caches[0].name+'.id.'+#id")
    @Cacheable
    public int updateByPrimaryKeySelective(AppInfo bo) {
        return super.updateByPrimaryKeySelective(bo);
    }

    @CacheEvict(key = "#root.caches[0].name+'.id.'+#id")
    public int deleteByPrimaryKey(Long id){
        return super.deleteByPrimaryKey(id);
    }



}