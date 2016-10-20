package com.zhiyin.user.dbs.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.user.dbs.entity.DeviceFixInfo;
import com.zhiyin.user.dbs.mapper.DeviceFixInfoMapper;
import com.zhiyin.user.dbs.service.IDeviceFixInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/10/8.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DeviceFixInfoServiceImpl extends BaseService<DeviceFixInfo> implements IDeviceFixInfoService {

    @Autowired
    DeviceFixInfoMapper deviceFixInfoMapper;

    @Override
    public BaseMapper<DeviceFixInfo> getBaseMapper() {
        return deviceFixInfoMapper;
    }

    @Override
    public DeviceFixInfo selectByUk(DeviceFixInfo fixInfo){

        return deviceFixInfoMapper.selectByUk(fixInfo);

    }
}
