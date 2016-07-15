package com.zhiyin.ourchat.service;

import com.zhiyin.ourchat.entity.DialogLatest;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogLatestService {

    List<DialogLatest> selectByUid(Long userId);

    List<DialogLatest> selectLatest(Long userId, Long partnerId);

    public  Long insertSelective(DialogLatest record);


    public  Integer deleteByUid(Long userId);

    public  Integer deleteByPartner(Long userId, Long partnerId);

//    public Long deleteIn

}

