package com.zhiyin.ad.service;

import com.zhiyin.ad.entity.AudioBoard;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IAudioBoardService {
    List<AudioBoard> selectShelfOn();

    List<AudioBoard> selectShelfOff();
}
