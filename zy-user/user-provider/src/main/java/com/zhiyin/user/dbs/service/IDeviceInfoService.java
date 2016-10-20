package com.zhiyin.user.dbs.service;

import com.zhiyin.user.dbs.entity.DeviceFixInfo;

/**
 * Created by hg on 2016/10/8.
 */
public interface IDeviceInfoService {
    int insertFix(DeviceFixInfo fixInfo);

    DeviceFixInfo selectDevice(DeviceFixInfo fixInfo);
}
