package com.zhiyin.app.dbs.controller;

import com.zhiyin.app.dbs.entity.DeviceFixInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/10/10.
 */
@Slf4j
@RestController
public class DeviceInfoController {

//    @Resource
//    private IDeviceInfoService deviceInfoService;
//
//    @RequestMapping(value = "/device/record", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String record(@RequestBody DeviceFixInfo fixInfo) {
//        deviceInfoService.insertFix( fixInfo );
//        return "ok";
//    }
//
//    @RequestMapping(value = "/device/get", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String get(@RequestBody DeviceFixInfo fixInfo) {
//        deviceInfoService.selectDevice(fixInfo);
//        return "ok";
//    }



}
