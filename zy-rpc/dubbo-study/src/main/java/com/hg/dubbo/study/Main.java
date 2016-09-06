package com.hg.dubbo.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangqinghui on 2016/8/29.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("people-application.xml");
        People p = (People)ctx.getBean("cutesource");
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }
}
