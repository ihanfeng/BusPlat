package com.zhiyin.app.dbs.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyin.app.dbs.service.IAppInfoService;
import com.zhiyin.app.dbs.service.impl.AppInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/10/10.
 */
@Slf4j
@RestController
public class DeviceInfoController {

//    @Resource
    private IAppInfoService appInfoService;

    @RequestMapping(value = "/app/infoall", method = RequestMethod.GET  )
    public String record( ) {
         return JSON.toJSONString(appInfoService.selectAll());
    }

    @RequestMapping(value = "/app/info/{id}", method = RequestMethod.GET  )
    public String recor44d(@PathVariable("id") Long id) {
        return JSON.toJSONString(appInfoService.selectById(id));
    }

//    @RequestMapping(value = "/device/get", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String get(@RequestBody DeviceFixInfo fixInfo) {
//        deviceInfoService.selectDevice(fixInfo);
//        return "ok";
//    }



}
