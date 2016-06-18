package com.zhiyin.sms.sender;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 2016/6/18.
 */
public class SmsSenderTest {

    @Test
    public void testSend() throws Exception {
        String content = "您的验证码是：sss【知音】";

        SmsSender.send( "18811591895",content );
    }
}