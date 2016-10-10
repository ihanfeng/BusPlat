package com.zhiyin.device.dbs.controller;

import com.zhiyin.device.dbs.service.IDeviceInfoService;
import lombok.extern.slf4j.Slf4j;
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



}
