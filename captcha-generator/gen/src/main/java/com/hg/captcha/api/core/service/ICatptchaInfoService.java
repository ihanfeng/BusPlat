package com.hg.captcha.api.core.service;


import com.hg.captcha.api.core.entity.CaptchaInfo;

public interface ICatptchaInfoService {

    public CaptchaInfo selectByPrimaryKey(Long id);


    public int insertSelective(CaptchaInfo captchaInfo);




}
