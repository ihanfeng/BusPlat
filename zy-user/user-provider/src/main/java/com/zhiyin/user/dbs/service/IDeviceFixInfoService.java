package com.zhiyin.user.dbs.service;

import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.user.dbs.entity.DeviceFixInfo;

/**
 * Created by hg on 2016/10/8.
 */
public interface IDeviceFixInfoService extends IBaseService<DeviceFixInfo> {
    DeviceFixInfo selectByUk(DeviceFixInfo fixInfo);
}
