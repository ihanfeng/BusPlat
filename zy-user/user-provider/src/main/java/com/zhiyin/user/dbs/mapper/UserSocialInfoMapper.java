package com.zhiyin.user.dbs.mapper;

import com.zhiyin.user.dbs.entity.UserSocialInfo;

public interface UserSocialInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSocialInfo record);

    int insertSelective(UserSocialInfo record);

    UserSocialInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSocialInfo record);

    int updateByPrimaryKey(UserSocialInfo record);
}