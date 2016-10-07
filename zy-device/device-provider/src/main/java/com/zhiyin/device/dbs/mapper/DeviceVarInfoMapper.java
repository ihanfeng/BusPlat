package com.zhiyin.device.dbs.mapper;

import com.zhiyin.device.dbs.entity.DeviceVarInfo;

public interface DeviceVarInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceVarInfo record);

    int insertSelective(DeviceVarInfo record);

    DeviceVarInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceVarInfo record);

    int updateByPrimaryKey(DeviceVarInfo record);
}