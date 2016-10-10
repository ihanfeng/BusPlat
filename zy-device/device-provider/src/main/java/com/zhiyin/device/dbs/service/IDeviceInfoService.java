package com.zhiyin.device.dbs.service;

import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;

/**
 * Created by hg on 2016/10/8.
 */
public interface IDeviceInfoService {
    int insertFix(DeviceFixInfo fixInfo);

    DeviceFixInfo selectDevice(DeviceFixInfo fixInfo);
}
