package com.zhiyin.ad.service;

import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;

import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
public interface IAdBasicInfoService extends IBaseService<AdBasicInfo> {
    Integer updateShelfStatus(Long id, Integer status);

    List<AdBasicInfo> selectWillShelfOn();

    List<AdBasicInfo> selectWillShelfOn(Date startDate, Date endDate);

    List<AdBasicInfo> selectWillShelfOff();

    List<AdBasicInfo> selectWillShelfOff(Date startDate, Date endDate);
}
