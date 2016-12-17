package com.zhiyin.device.dbs.mapper;

import com.zhiyin.dbs.common.base.BaseMapper;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.entity.DeviceFixInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceFixInfoMapper extends BaseMapper<DeviceFixInfo, DeviceFixInfoExample, Long> {
    DeviceFixInfo selectByKey(DeviceFixInfo fixInfo);
}