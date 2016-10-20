package com.zhiyin.user.dbs.service.impl;

import com.zhiyin.user.dbs.entity.DeviceFixInfo;
import com.zhiyin.user.dbs.service.IDeviceFixInfoService;
import com.zhiyin.user.dbs.service.IDeviceInfoService;
import com.zhiyin.user.dbs.service.IDeviceMovInfoService;
import com.zhiyin.user.dbs.service.IDeviceVarInfoService;
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
