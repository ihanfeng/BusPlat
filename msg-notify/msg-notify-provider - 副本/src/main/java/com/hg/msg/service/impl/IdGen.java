package com.hg.msg.service.impl;

import org.joda.time.DateTime;

/**
 * Created by hg on 2016/3/28.
 */
public class IdGen {

    public static Long gen() {
        return DateTime.now().getMillis();
    }
}
