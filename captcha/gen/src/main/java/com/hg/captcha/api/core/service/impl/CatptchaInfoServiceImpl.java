package com.hg.captcha.api.core.service.impl;



import com.hg.captcha.api.core.entity.CaptchaInfo;
import com.hg.captcha.api.core.mapper.CaptchaInfoMapper;
import com.hg.captcha.api.core.service.ICatptchaInfoService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service("catptchaInfoService")
public class CatptchaInfoServiceImpl implements ICatptchaInfoService {

    @Autowired
    public CaptchaInfoMapper catptchaInfoMapper;


    @Override
    public CaptchaInfo selectByPrimaryKey(Long id) {
        return catptchaInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CaptchaInfo catptchaInfo) {
        catptchaInfo.setId(DateTime.now().getMillis());

        return catptchaInfoMapper.insertSelective(catptchaInfo);
    }






}
