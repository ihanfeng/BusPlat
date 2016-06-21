package com.zhiyin.frame.idgen.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2016/4/28.
 */
public class IdInfoConvUtilTest {

    @Test
    public void testPrintInfo() throws Exception {
        long l = 725601819622903810L;

        IdInfoConvUtil.printInfo(l);

    }
}