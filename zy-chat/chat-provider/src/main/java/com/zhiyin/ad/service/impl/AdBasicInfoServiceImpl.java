package com.zhiyin.ad.service.impl;

import com.zhiyin.ad.config.AdShelfStatus;
import com.zhiyin.ad.config.AdTimerConfig;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.mapper.AdBasicInfoMapper;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdBasicInfoServiceImpl extends BaseService<AdBasicInfo> implements IAdBasicInfoService {

    @Resource
    private AdBasicInfoMapper adBasicInfoMapper;

    @Override
    public BaseMapper<AdBasicInfo> getBaseMapper() {
        return adBasicInfoMapper;
    }


    /**
     * 更新上架状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Integer updateShelfStatus(Long id, Integer status){
        AdBasicInfo ad = new AdBasicInfo();
        ad.setId(id);
        ad.setShelfStatus(status);
        return this.updateByPrimaryKeySelective(ad);
    }

    /**
     * 查询即将上架的广告
     * 状态为:not
     *
     * @return
     */
    @Override
    public List<AdBasicInfo> selectWillShelfOn( ){


        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now().plusMinutes(AdTimerConfig.TimerInterval);

        log.info("sel will shelf on, start:{},end:{}",start.toString("YYYMMDDHHmmss"),end);
        List<AdBasicInfo> list = adBasicInfoMapper.selectWillShelfOn(start.toDate(), end.toDate(), AdShelfStatus.ShelfNot);
        return list;
    }


    /**
     * 查询即将下架的广告
     *
     * @return
     */
    @Override
    public List<AdBasicInfo> selectWillShelfOff(){

        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerInterval + AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now();
        log.info("sel will shelf off, start:{},end:{}",start,end);
        List<AdBasicInfo> list = adBasicInfoMapper.selectWillShelfOff(start.toDate(), end.toDate(), AdShelfStatus.ShelfOn);
        return list;
    }

}
