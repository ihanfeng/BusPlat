package com.zhiyin.ourchat.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.ourchat.entity.DialogLatest;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogLatestService extends IBaseService<DialogLatest> {

    List<DialogLatest> selectByUid(Long userId);


    PageInfo<DialogLatest> selectByUid(Long userId, PageInfo pageInfo);

    List<DialogLatest> selectLatest(Long userId, Long partnerId);

    public Integer deleteByUid(Long userId);

    public Integer deleteByPartner(Long userId, Long partnerId);

//    public Long deleteIn

}

