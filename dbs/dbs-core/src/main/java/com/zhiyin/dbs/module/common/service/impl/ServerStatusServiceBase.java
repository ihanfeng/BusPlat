package com.zhiyin.dbs.module.common.service.impl;

import com.zhiyin.dbs.module.common.service.IServerStatusService;

/**
 * Created by hg on 2016/10/20.
 */
public class ServerStatusServiceBase implements IServerStatusService {


    @Override
    public String hello(String name) {
        return "I'm server, I'm OK!";
    }

    @Override
    public String info() {
        return "I'm live.";
    }
}
