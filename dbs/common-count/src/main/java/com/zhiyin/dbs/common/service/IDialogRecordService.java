package com.zhiyin.dbs.common.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.common.entity.DialogRecord;
import com.zhiyin.dbs.module.common.service.IBaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogRecordService extends IBaseService<DialogRecord> {

    List<DialogRecord> selectByPartner(Long userId, Long partnerId);

    PageInfo<DialogRecord> selectByPartner(Long userId, Long partnerId, Integer pageNum, Integer pageSize);

    Integer deleteByUid(@Param("userId") Long userId);

}
