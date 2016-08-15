package com.hg.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hg.msg.ProviderApplication;
import com.hg.msg.entity.MsgUserNotify;
import com.hg.msg.service.IMsgNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by hg on 2016/3/29.
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ProviderApplication.class})
@WebAppConfiguration
@EnableAuthorizationServer
@EnableResourceServer
public class MsgNotifyServiceTest {


    Long testUserId = 11L;

    Long UserAId = 111L;
    Long UserBId = 112L;
    Long adminId = 1L;

    Long ProductAId = 435L;

    @Autowired
    private IMsgNotifyService msgNotifyService;

    List<MsgUserNotify> userNotifyList = Lists.newArrayList();


    /**
     * 测试spring事物
     */
    @Test
    public void testTrans() {
        msgNotifyService.testTrans();
    }

    @Test
    public void testAnnounce() throws Exception {

        userNotifyList = msgNotifyService.getUserNotify(testUserId);
        Assert.assertTrue(userNotifyList.size() == 0);


        // 创建公告
        msgNotifyService.createAnnounce("anno1", adminId);
        msgNotifyService.createAnnounce("anno2", adminId);

        // 拉取公告
        msgNotifyService.pullAnnounce(testUserId);

        userNotifyList = msgNotifyService.getUserNotify(testUserId);
        Assert.assertTrue(userNotifyList.size() == 2);


        msgNotifyService.createAnnounce("anno3", adminId);
        msgNotifyService.pullAnnounce(testUserId);
        userNotifyList = msgNotifyService.getUserNotify(testUserId);
        Assert.assertTrue(userNotifyList.size() == 3);


        log.info(JSON.toJSONString(userNotifyList));

    }

    @Test
    public void testCreateMessage() throws Exception {

        Long id = msgNotifyService.createMessage("test message", UserAId, UserBId);

        log.info("message id:{}", id);

        userNotifyList = msgNotifyService.getUserNotify(UserBId);
        log.info(JSON.toJSONString(userNotifyList));
        Assert.assertTrue(userNotifyList.size() == 1);

    }


    @Test
    public void testCreateRemind() throws Exception {

        // 用户B评论Product A
        msgNotifyService.createRemind(ProductAId, "product", "comment", UserBId, "用户B评论Product A");
    }


    @Test
    public void testSubscribe() {

        msgNotifyService.subscribe(1L, 22L, "product", "create_product");
    }

    @Test
    public void testPullRemind() {

        msgNotifyService.pullRemind(UserAId);
    }


    @Test
    public void testRemindSuit() throws InterruptedException {

        // 用户A订阅ProductA的事件
        msgNotifyService.subscribe(UserAId, ProductAId, "product", "like_product");

        Thread.sleep(1000);

        // 用户B评论ProductA
        msgNotifyService.createRemind(ProductAId, "product", "comment", UserBId, "用户B评论Product A");

        msgNotifyService.pullRemind(UserAId);


        userNotifyList = msgNotifyService.getUserNotify(UserAId);
        Assert.assertTrue(userNotifyList.size() == 1);
        log.info(JSON.toJSONString(userNotifyList));

    }
}