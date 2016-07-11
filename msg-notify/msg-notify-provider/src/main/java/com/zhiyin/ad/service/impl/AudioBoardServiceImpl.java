package com.zhiyin.ad.service.impl;

import com.hg.msg.mapper.MsgNotifyMapper;
import com.zhiyin.ad.config.AdTimerConfig;
import com.zhiyin.ad.entity.AudioBoard;
import com.zhiyin.ad.mapper.AudioBoardMapper;
import com.zhiyin.ad.service.IAudioBoardService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@Service
public class AudioBoardServiceImpl implements IAudioBoardService {

    @Autowired
    AudioBoardMapper audioBoardMapper;


    /**
     * 查询上架的
     * @return
     */
    @Override
    public List<AudioBoard> selectShelfOn(){

        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now().plusMinutes(AdTimerConfig.TimerIntervalTolerant);

//         audioBoardMapper.selectShelfOn(startTime,endTime);
        return null;
    }


    /**
     * 查询上架的
     * @return
     */
    @Override
    public List<AudioBoard> selectShelfOff(){
        DateTime start = DateTime.now().minusMinutes(AdTimerConfig.TimerIntervalTolerant);
        DateTime end = DateTime.now().plusMinutes(AdTimerConfig.TimerIntervalTolerant);

//        audioBoardMapper.selectShelfOff(startTime,endTime);
        return null;

    }


}
