package com.zhiyin.ourchat.service.impl;

import com.zhiyin.ourchat.OurChatApplication;
import com.zhiyin.ourchat.entity.DialogInfo;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.service.IDialogInfoService;
import com.zhiyin.ourchat.service.IDialogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

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


    @Test
    public void testInsertDialog() throws Exception {


        DialogInfo info = new DialogInfo();

        info.setContent("hello, user1 talk to user2.");
        info.setSender(1L);
        info.setReceiver(2L);

        dialogRecordService.deleteByUid(info.getReceiver());
        dialogRecordService.deleteByUid(info.getSender() );

        dialogInfoService.insertDialog(info);

    }

}