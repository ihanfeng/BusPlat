package com.zhiyin.fd.factory;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

/**
 * Created by hg on 2016/7/23.
 */
public class FakeVal {

    public static Integer nextInt(){
        return RandomUtils.nextInt();
    }

    public static Long nextLong(){
        return RandomUtils.nextLong();
    }

}
