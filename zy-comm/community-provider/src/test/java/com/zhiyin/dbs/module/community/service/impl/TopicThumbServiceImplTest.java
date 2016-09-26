package com.zhiyin.dbs.module.community.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhiyin.dbs.module.community.CommunityApplication;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.dbs.module.community.service.ITopicThumbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2016/9/26.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CommunityApplication.class})
@WebAppConfiguration
public class TopicThumbServiceImplTest  {

    @Resource
    ITopicInfoService topicInfoService ;
    @Resource
    ITopicThumbService topicThumbService ;

    @Test
    public void testSelectThumbers() throws Exception {

        List<Long> users = topicThumbService.selectLatestThumbers(52042922905600L);

        log.info(JSON.toJSONString(users));
    }

    @Test
    public void testSelectLatestThumbers() throws Exception {

    }
}