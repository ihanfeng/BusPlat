package com.zhiyin.fms.module.common.controller;

import com.zhiyin.fms.web.S2cObj;
import com.zhiyin.fms.web.WebResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/")
public class BaseController {


    public static WebResp<S2cObj> failRet(String failInfo) {
        return new WebResp<>(
                HttpStatus.BAD_REQUEST.value(), failInfo, null);
    }

    public static WebResp<S2cObj> failReqRet() {
        return new WebResp<>(
                HttpStatus.BAD_REQUEST.value(), "请求参数错误", null);
    }

    public static WebResp<S2cObj> failRet(int code, String failInfo) {
        return new WebResp<>(
                code, failInfo, null);
    }

    public static WebResp<S2cObj> succRet(S2cObj succInfo) {
        return new WebResp<>(
                HttpStatus.OK.value(), "", succInfo);
    }

    public static WebResp<S2cObj> succRet() {
        return new WebResp<>(
                HttpStatus.OK.value(), "", null);
    }

}
