package com.zhiyin.dbs.common.service;

import com.zhiyin.dbs.common.entity.DialogInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;

/**
 * Created by hg on 2016/7/11.
 */
public interface IDialogInfoService extends IBaseService<DialogInfo> {

    Integer insertDialog(DialogInfo dialogInfo);

}

