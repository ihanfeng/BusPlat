package com.zhiyin.device.dbs.mapper;

import com.zhiyin.user.dbs.entity.UserLoginLog;

public interface UserLoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}