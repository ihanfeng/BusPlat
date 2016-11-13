package com.zhiyin.app.dbs.mapper;

import com.zhiyin.app.dbs.entity.DeviceFixInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;

public interface DeviceFixInfoMapper extends BaseMapper<DeviceFixInfo> {

    DeviceFixInfo selectByUk(DeviceFixInfo fixInfo);

}