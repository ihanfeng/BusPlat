package com.zhiyin.device.dbs.service.impl;

import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.entity.DeviceInfo;
import com.zhiyin.device.dbs.service.IDeviceFixInfoService;
import com.zhiyin.device.dbs.service.IDeviceInfoService;
import com.zhiyin.device.dbs.service.IDeviceMovInfoService;
import com.zhiyin.device.dbs.service.IDeviceVarInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/10/8.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DeviceInfoServiceImpl implements IDeviceInfoService {


    @Resource
    private IDeviceFixInfoService fixInfoService;

    @Resource
    private IDeviceMovInfoService movInfoService;

    @Resource
    private IDeviceVarInfoService varInfoService;


    @Override
    public int insertFix(DeviceFixInfo fixInfo){
        fixInfoService.insertSelectiveGet(fixInfo);
        return 1;
    }

    @Override
    public DeviceFixInfo selectDevice(DeviceFixInfo fixInfo){

        return fixInfoService.selectByUk(fixInfo);

    }



}
