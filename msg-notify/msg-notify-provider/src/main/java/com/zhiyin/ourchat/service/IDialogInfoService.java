package com.zhiyin.ourchat.service;

import com.zhiyin.ad.entity.AudioBoard;
import com.zhiyin.ourchat.entity.DialogInfo;
import com.zhiyin.ourchat.entity.DialogLatest;

import java.util.List;

/**
 * Created by wangqinghui on 2016/7/11.
 */
public interface IDialogInfoService {


//    public Long insertSelective(DialogInfo record) ;

    List<AudioBoard> insertDialog(DialogInfo dialogInfo);
}

