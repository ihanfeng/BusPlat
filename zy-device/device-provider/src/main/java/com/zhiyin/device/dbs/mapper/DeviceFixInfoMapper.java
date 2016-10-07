package com.zhiyin.device.dbs.mapper;

import com.zhiyin.device.dbs.entity.DeviceFixInfo;

public interface DeviceFixInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceFixInfo record);

    int insertSelective(DeviceFixInfo record);

    DeviceFixInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceFixInfo record);

    int updateByPrimaryKey(DeviceFixInfo record);
}