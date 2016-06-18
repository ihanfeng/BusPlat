package com.zhiyin.fms.module.sms.controller;

import com.zhiyin.fms.module.common.controller.BaseController;
import com.zhiyin.fms.web.S2cObj;
import com.zhiyin.fms.web.WebResp;
import com.zhiyin.sms.sender.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsSenderController extends BaseController {

    @RequestMapping(value = "/sender",method = RequestMethod.GET)
    public WebResp<S2cObj> sender(
            @RequestParam(value = "tel", required = true) String tel,
            @RequestParam(value = "msg", required = true) String msg) {
        SmsSender.send(tel, msg);
        return succRet();
    }

}
