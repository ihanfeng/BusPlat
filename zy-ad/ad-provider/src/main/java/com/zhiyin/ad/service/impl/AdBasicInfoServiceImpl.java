package com.zhiyin.ad.service.impl;

import com.google.common.base.Optional;
import com.zhiyin.ad.config.AdShelfStatus;
import com.zhiyin.ad.config.AdTimerConfig;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.mapper.AdBasicInfoMapper;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hg on 2016/7/12.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdBasicInfoServiceImpl extends BaseService<AdBasicInfo> implements IAdBasicInfoService {

    SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//等价于now.toLocaleString()


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
     * 查询即将上架的广告，默认为当前时间
     * 状态为:not
     *
     * @return
     */
    @Override
    public List<AdBasicInfo> selectWillShelfOn( ){

        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now().plusMinutes(AdTimerConfig.TimerInterval);

        return selectWillShelfOn(start.toDate(), end.toDate() );
    }

    @Override
    public List<AdBasicInfo> selectWillShelfOn( Date startDate,Date endDate ){

        log.info("sel will shelf on, start:{},end:{}",myFmt2.format(startDate), myFmt2.format(endDate));
        List<AdBasicInfo> list = adBasicInfoMapper.selectWillShelfOn(startDate, endDate, AdShelfStatus.ShelfNot);
        list = Optional.fromNullable(list).or(new ArrayList<AdBasicInfo>());
        return list;
    }


    /**
     * 查询即将下架的广告，为了效率，限定一下时间
     *
     * @return
     */
    @Override
    public List<AdBasicInfo> selectWillShelfOff(){

        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerInterval + AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now();

        return selectWillShelfOff( start.toDate(), end.toDate() );
    }

    @Override
    public List<AdBasicInfo> selectWillShelfOff(Date startDate, Date endDate){
        log.info("sel will shelf off, start:{},end:{}", myFmt2.format(startDate), myFmt2.format(endDate));
        List<AdBasicInfo> list = adBasicInfoMapper.selectWillShelfOff(startDate , endDate, AdShelfStatus.ShelfOn);
        list = Optional.fromNullable(list).or(new ArrayList<AdBasicInfo>());
        return list;
    }



}
