package com.zhiyin.ad.mapper;

import com.zhiyin.ad.entity.AudioBoard;

public interface AudioBoardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AudioBoard record);

    int insertSelective(AudioBoard record);

    AudioBoard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AudioBoard record);

    int updateByPrimaryKey(AudioBoard record);
}