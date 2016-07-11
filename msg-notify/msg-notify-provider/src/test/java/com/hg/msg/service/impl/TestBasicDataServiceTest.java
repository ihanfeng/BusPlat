package com.hg.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hg.msg.ProviderApplication;
import com.hg.msg.entity.MsgUserNotify;
import com.hg.msg.service.IMsgNotifyService;
import com.hg.msg.service.ITestBasicDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wangqinghui on 2016/3/29.
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ProviderApplication.class})
@WebAppConfiguration
@WebIntegrationTest("server.port:0")
public class TestBasicDataServiceTest {


    @Autowired
    private WebApplicationContext context;

    @Value("${local.server.port}")
    private int port;

    private MockMvc mockMvc;
    private RestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Autowired
    private ITestBasicDataService testBasicDataService;

    List<MsgUserNotify> userNotifyList = Lists.newArrayList();



    @Test
    public void webappBookIsbnApi() {
//        Book book = restTemplate.getForObject("http://localhost:" + port +"/books/9876-5432-1111", Book.class);
//        assertNotNull(book);
//        assertEquals("中文测试", book.getPublisher().getName());
    }


    @Test
    public void webappPublisherApi() throws Exception {
        //MockHttpServletRequestBuilder.accept方法是设置客户端可识别的内容类型
        //MockHttpServletRequestBuilder.contentType,设置请求头中的Content-Type字段,表示请求体的内容类型
        mockMvc.perform(get("/publishers/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(status().isOk())
                .andExpect(content().string(containsString("中文测试")))
                .andExpect(jsonPath("$.name").value("中文测试"));
    }


    @Test
    public void testRemindSuit() throws InterruptedException {

        PageInfo result = testBasicDataService.selectIncByTest(0L, 1105L);

        // 返回数据

//        Assert.assertTrue( result.getPageSize() == result.getSize());

        result = testBasicDataService.selectIncByTest(1L, 1120L);

        log.info(JSON.toJSONString(result.getList()));

    }
}