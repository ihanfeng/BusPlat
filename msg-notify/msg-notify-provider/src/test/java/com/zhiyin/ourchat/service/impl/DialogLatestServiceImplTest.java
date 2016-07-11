package com.zhiyin.ourchat.service.impl;

import com.hg.msg.ProviderApplication;
import com.zhiyin.ourchat.OurChatApplication;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.service.IDialogLatestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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
public class DialogLatestServiceImplTest {

    @Resource
    IDialogLatestService dialogLatestService;

    @Test
    public void testSelectAlllLatest() throws Exception {

    }

    @Test
    public void testSelectLatest() throws Exception {

    }

    @Test
    public void testInsertSelective() throws Exception {

    }
}