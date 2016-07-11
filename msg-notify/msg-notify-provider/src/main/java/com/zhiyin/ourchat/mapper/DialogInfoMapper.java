package com.zhiyin.ourchat.mapper;

import com.zhiyin.ourchat.entity.DialogInfo;

public interface DialogInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DialogInfo record);

    int insertSelective(DialogInfo record);

    DialogInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DialogInfo record);

    int updateByPrimaryKey(DialogInfo record);
}