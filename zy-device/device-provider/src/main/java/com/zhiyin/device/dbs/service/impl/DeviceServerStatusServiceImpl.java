package com.zhiyin.device.dbs.service.impl;

import com.zhiyin.dbs.module.common.service.IServerStatusService;
import com.zhiyin.dbs.module.common.service.impl.ServerStatusServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/10/20.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class DeviceServerStatusServiceImpl
        implements IServerStatusService {



    public String hello(String name) {
        return "I\'m server, I\'m OK!";
    }

    public String info() {
        return "I\'m live.";
    }
}
