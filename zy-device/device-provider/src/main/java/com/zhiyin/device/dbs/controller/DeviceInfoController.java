package com.zhiyin.device.dbs.controller;

import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.service.IDeviceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/10/10.
 */
@Slf4j
@RestController
public class DeviceInfoController {

    @Resource
    private IDeviceInfoService deviceInfoService;

    @RequestMapping(value = "/test/device/record")
    public String record(@RequestBody DeviceFixInfo fixInfo) {
        deviceInfoService.insertFix( fixInfo );
        return "ok";
    }

    @RequestMapping(value = "/test/device/get")
    public String get(@RequestBody DeviceFixInfo fixInfo) {
        deviceInfoService.selectDevice(fixInfo);
        return "ok";
    }



}
