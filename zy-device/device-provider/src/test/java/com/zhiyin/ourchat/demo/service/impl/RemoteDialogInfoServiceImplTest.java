package com.zhiyin.ourchat.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhiyin.ourchat.service.IDialogLatestService;
import com.zhiyin.ourchat.entity.DialogLatest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-client.xml")
@WebAppConfiguration
public class RemoteDialogInfoServiceImplTest {

    @Autowired
    IDialogLatestService dialogLatestService;

    @Test
    public void selectByUid() throws Exception {

//        PageInfo<DialogLatest> list = dialogLatestService.selectByUid(1L, 1, 2);
//        for(DialogLatest tmp : list.getList()){
//            log.info(JSON.toJSONString(tmp));
//        }

    }


}