package com.zhiyin.ourchat.service;

import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.ourchat.entity.DialogRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogRecordService extends IBaseService<DialogRecord> {

    List<DialogRecord> selectByPartner(Long userId, Long partnerId);

    Integer deleteByUid(@Param("userId") Long userId);

}
