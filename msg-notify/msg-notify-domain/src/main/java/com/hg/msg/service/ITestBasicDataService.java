package com.hg.msg.service;

import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * Created by hg on 2016/4/7.
 */
public interface ITestBasicDataService {

    public PageInfo selectInc(Date fromTime, Date toTime);



    PageInfo selectIncByTest(Long fromTime, Long toTime);
}
