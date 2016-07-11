package com.zhiyin.ourchat.service;

import com.zhiyin.ourchat.entity.DialogRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogRecordService {

    Integer insertSelective(DialogRecord record);

    List<DialogRecord> selectByPartner(Long userId, Long partnerId);

    Integer deleteByPrimaryKey(Long id);

    Integer deleteByUid(@Param("userId") Long userId);

}
