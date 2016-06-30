package com.hg.captcha.api.core.mapper;

import com.hg.captcha.api.core.entity.CaptchaInfo;

public interface CaptchaInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaptchaInfo record);

    int insertSelective(CaptchaInfo record);

    CaptchaInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaptchaInfo record);

    int updateByPrimaryKey(CaptchaInfo record);
}