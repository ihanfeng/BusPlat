package com.zhiyin.user.dbs.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.user.dbs.entity.DeviceMovInfo;
import com.zhiyin.user.dbs.mapper.DeviceMovInfoMapper;
import com.zhiyin.user.dbs.service.IDeviceMovInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/10/8.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DeviceMovInfoServiceImpl extends BaseService<DeviceMovInfo> implements IDeviceMovInfoService {

    @Autowired
    DeviceMovInfoMapper deviceMovInfoMapper;

    @Override
    public BaseMapper<DeviceMovInfo> getBaseMapper() {
        return deviceMovInfoMapper;
    }
}
