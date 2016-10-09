package com.zhiyin.device.dbs.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.entity.DeviceVarInfo;
import com.zhiyin.device.dbs.mapper.DeviceFixInfoMapper;
import com.zhiyin.device.dbs.mapper.DeviceVarInfoMapper;
import com.zhiyin.device.dbs.service.IDeviceFixInfoService;
import com.zhiyin.device.dbs.service.IDeviceVarInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/10/8.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DeviceVarInfoServiceImpl extends BaseService<DeviceVarInfo> implements IDeviceVarInfoService {

    @Autowired
    DeviceVarInfoMapper deviceVarInfoMapper;

    @Override
    public BaseMapper<DeviceVarInfo> getBaseMapper() {
        return deviceVarInfoMapper;
    }
}
