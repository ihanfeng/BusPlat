package com.zhiyin.ourchat.service;

import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.ourchat.entity.DialogInfo;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogInfoService extends IBaseService<DialogInfo> {

    Integer insertDialog(DialogInfo dialogInfo);

}

