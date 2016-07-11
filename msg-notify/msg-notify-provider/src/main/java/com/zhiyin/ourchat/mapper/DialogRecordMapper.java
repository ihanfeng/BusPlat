package com.zhiyin.ourchat.mapper;

import com.zhiyin.ourchat.entity.DialogRecord;

import java.util.List;

public interface DialogRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DialogRecord record);

    int insertSelective(DialogRecord record);

    DialogRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DialogRecord record);

    int updateByPrimaryKey(DialogRecord record);

    Integer deleteByUid(Long userId);

    List<DialogRecord> selectByPartner(Long userId, Long partnerId);
}