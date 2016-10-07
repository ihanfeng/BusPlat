package com.zhiyin.device.dbs.mapper;

import com.zhiyin.device.dbs.entity.DeviceMovInfo;

public interface DeviceMovInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceMovInfo record);

    int insertSelective(DeviceMovInfo record);

    DeviceMovInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceMovInfo record);

    int updateByPrimaryKey(DeviceMovInfo record);
}