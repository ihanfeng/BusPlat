package com.zhiyin.dbs.common.controller;

import com.zhiyin.dbs.common.entity.DialogInfo;
import com.zhiyin.dbs.common.entity.DialogLatest;
import com.zhiyin.dbs.common.service.IDialogInfoService;
import com.zhiyin.dbs.common.service.IDialogLatestService;
import com.zhiyin.dbs.common.service.IDialogRecordService;
import com.zhiyin.dbs.common.service.IDialogStatusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/15.
 */
@RestController
public class DialogController {


    @Resource
    IDialogInfoService dialogInfoService;

    @Resource
    IDialogRecordService dialogRecordService;

    @Resource
    IDialogLatestService dialogLatestService;

    @Resource
    IDialogStatusService dialogStatusService;

    @RequestMapping(value = "/test")
    public String test(){
        DialogInfo info = new DialogInfo();

        info.setContent("hello, user1 talk to user2.");
        info.setSender(10L);
        info.setReceiver(20L);

        dialogRecordService.deleteByUid(info.getReceiver());
        dialogRecordService.deleteByUid(info.getSender());

        // 清除列表
        dialogLatestService.deleteByUid(10L);

        // 插入记录
        dialogInfoService.insertDialog(info);

        // 查询聊天列表
        List<DialogLatest> list = dialogLatestService.selectByUid(info.getSender());

        return "ok";
    }
}
