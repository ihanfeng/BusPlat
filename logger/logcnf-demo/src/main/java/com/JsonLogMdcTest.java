package com;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangqinghui on 2016/11/16.
 */
//@Slf4j
public class JsonLogMdcTest {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(JsonLogMdcTest.class);

        MDC.put("userId", "u1000");
        log.info("mdc test.");

    }
}
