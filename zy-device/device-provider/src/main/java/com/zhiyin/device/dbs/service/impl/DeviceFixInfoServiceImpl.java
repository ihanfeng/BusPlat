package com.zhiyin.device.dbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.device.dbs.config.RedisCacheName;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.mapper.DeviceFixInfoMapper;
import com.zhiyin.device.dbs.service.IDeviceFixInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/10/8.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
@CacheConfig(cacheNames = {RedisCacheName.UserCacheName}) // 默认cache名称
public class DeviceFixInfoServiceImpl extends BaseService<DeviceFixInfo> implements IDeviceFixInfoService {

    @Autowired
    DeviceFixInfoMapper deviceFixInfoMapper;

    @Override
    public BaseMapper<DeviceFixInfo> getBaseMapper() {
        return deviceFixInfoMapper;
    }

    /**
     * 根据联合主键查询*/
    @Override
    public DeviceFixInfo selectByUk(DeviceFixInfo fixInfo){
        return deviceFixInfoMapper.selectByUk(fixInfo);
    }

    @Override
    public DeviceFixInfo selectByKey(DeviceFixInfo fixInfo){

        if(fixInfo == null){
            throw new RuntimeException("查询数据为空");
        }

        if(Strings.isNullOrEmpty(fixInfo.getImei()) && Strings.isNullOrEmpty(fixInfo.getUuid()) && Strings.isNullOrEmpty(fixInfo.getSerialno())){
            throw new RuntimeException("查询数据不规范");
        }

        return deviceFixInfoMapper.selectByKey(fixInfo);
    }

    @Cacheable()
    public DeviceFixInfo selectById(Long id) {
        return this.getBaseMapper().selectByPrimaryKey(id);
    }

    @Cacheable
    public PageInfo selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List list = this.getBaseMapper().selectAll();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Cacheable
    public List<DeviceFixInfo> selectAll() {
        return this.getBaseMapper().selectAll();
    }

//    @CacheEvict(allEntries = true)
    public int deleteByPrimaryKey(Long id) {
        return this.getBaseMapper().deleteByPrimaryKey(id);
    }

}
