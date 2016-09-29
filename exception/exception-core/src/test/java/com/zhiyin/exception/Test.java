package com.zhiyin.exception;

import com.zhiyin.exception.core.BizExcetpion;
import com.zhiyin.exception.core.BizExcetpion2;

/**
 * Created by wangqinghui on 2016/9/28.
 */
public class Test {
    public static void main(String[] args) {

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        for(int i=0; i<1000000;i++){
            BizExcetpion excetpion = new BizExcetpion("ss");
        }
        endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);

        startTime = System.currentTimeMillis();
        for(int i=0; i<1000000;i++){
            BizExcetpion2 excetpion = new BizExcetpion2("ss");
        }
        endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);


    }
}
