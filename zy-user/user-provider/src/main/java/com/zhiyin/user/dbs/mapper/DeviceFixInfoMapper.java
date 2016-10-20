package com.zhiyin.user.dbs.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.user.dbs.entity.DeviceFixInfo;

public interface DeviceFixInfoMapper extends BaseMapper<DeviceFixInfo> {

    DeviceFixInfo selectByUk(DeviceFixInfo fixInfo);

}