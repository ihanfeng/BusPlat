package com.zhiyin.user.dbs.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.user.dbs.entity.DeviceVarInfo;
import com.zhiyin.user.dbs.mapper.DeviceVarInfoMapper;
import com.zhiyin.user.dbs.service.IDeviceVarInfoService;
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
