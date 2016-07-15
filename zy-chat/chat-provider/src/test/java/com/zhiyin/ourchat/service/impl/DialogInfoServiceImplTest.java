package com.zhiyin.ourchat.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhiyin.ourchat.OurChatApplication;
import com.zhiyin.ourchat.entity.DialogInfo;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.service.IDialogInfoService;
import com.zhiyin.ourchat.service.IDialogLatestService;
import com.zhiyin.ourchat.service.IDialogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {OurChatApplication.class})
@WebAppConfiguration
public class DialogInfoServiceImplTest {

    @Resource
    IDialogInfoService dialogInfoService;

    @Resource
    IDialogRecordService dialogRecordService;

    @Resource
    IDialogLatestService dialogLatestService;


    @Test
    public void testInsertDialog() throws Exception {


        DialogInfo info = new DialogInfo();

        info.setContent("hello, user1 talk to user2.");
        info.setSender(1L);
        info.setReceiver(2L);

        DialogInfo info2 = new DialogInfo();
        info2.setSender(1L);
        info2.setReceiver(3L);
        info2.setContent("hello, user1 talk to user3.");

        DialogInfo info3 = new DialogInfo();
        info3.setSender(3L);
        info3.setReceiver(1L);
        info3.setContent("hello, user3 talk to user1.");

        // 清除旧的记录
        dialogRecordService.deleteByUid(info.getReceiver());
        dialogRecordService.deleteByUid(info.getSender());
        dialogRecordService.deleteByUid(info2.getReceiver());

        // 清除列表
        dialogLatestService.deleteByUid(1L);

        // 插入记录
        dialogInfoService.insertDialog(info);
        dialogInfoService.insertDialog(info2);
        dialogInfoService.insertDialog(info3);

        // 查询聊天列表
        List<DialogLatest> list = dialogLatestService.selectByUid(info.getSender());
        log.info("chat list:");
        log.info(JSON.toJSONString(list));
        Assert.assertTrue(list.size() == 2);

        // 查询聊天记录
        List<DialogRecord> records = dialogRecordService.selectByPartner(1L, 3L);
        log.info("chat dialog:");
        log.info(JSON.toJSONString(records));
        Assert.assertTrue(records.size() == 2);

//        dialogRecordService.selectByPartner()


    }

}