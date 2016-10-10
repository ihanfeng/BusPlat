package com.zhiyin.device.dbs.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;

public interface DeviceFixInfoMapper extends BaseMapper<DeviceFixInfo> {


    DeviceFixInfo selectByUk(DeviceFixInfo fixInfo);
}