package com.zhiyin.device.dbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.device.dbs.config.RedisCacheName;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.mapper.DeviceFixInfoMapper;
import com.zhiyin.device.dbs.service.IDeviceFixInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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

    @Cacheable
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

    public int deleteByPrimaryKey(Long id) {
        return this.getBaseMapper().deleteByPrimaryKey(id);
    }

}
