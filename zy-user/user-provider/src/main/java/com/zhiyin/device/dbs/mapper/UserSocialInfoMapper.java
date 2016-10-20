package com.zhiyin.device.dbs.mapper;

import com.zhiyin.user.dbs.entity.UserSocialInfo;

public interface UserSocialInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSocialInfo record);

    int insertSelective(UserSocialInfo record);

    UserSocialInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSocialInfo record);

    int updateByPrimaryKey(UserSocialInfo record);
}