package com.zhiyin.user.dbs.mapper;

import com.zhiyin.user.dbs.entity.UserLoginLog;

public interface UserLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}