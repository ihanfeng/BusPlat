package com.hg.captcha.api.core.controller;



import com.hg.captcha.api.core.entity.CaptchaInfo;
import com.hg.captcha.api.core.service.ICatptchaInfoService;
import com.hg.captcha.core.CaptchaBase64;
import com.hg.captcha.core.CaptchaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CaptchaInfoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    ICatptchaInfoService catptchaInfoService;


    /**
     * 获取验证码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/cap", produces = "application/json")
    public String greeting() {

        CaptchaBase64 base64 = CaptchaFactory.genBase64();
        CaptchaInfo c = new CaptchaInfo();
        c.setValue(base64.getValue());
        c.setModule("findpass");
        c.setToken(UUID.randomUUID().toString());

        catptchaInfoService.insertSelective(c);
        return base64.getBase64();
    }

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/cap", produces = "application/json")
    public String valid() {

        CaptchaBase64 base64 = CaptchaFactory.genBase64();

        CaptchaInfo c = new CaptchaInfo();
        c.setValue(base64.getValue());
        c.setModule("findpass");
        catptchaInfoService.insertSelective(c);
        return base64.getBase64();
    }

}
