package com.hg.msg.service.impl;

import org.joda.time.DateTime;

/**
 * Created by wangqinghui on 2016/3/28.
 */
public class IdGen {

    public static Long gen(){
        return DateTime.now().getMillis();
    }
}
