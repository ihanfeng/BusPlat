package com.alibaba.dubbo.demo.consumer;



import com.alibaba.dubbo.container.Main;

import java.util.concurrent.CountDownLatch;

/**
 * Created by nange on 2015/7/16.
 */
public class DubboTest extends TestBase
{
    public static    int perExeCount;
    public DubboTest(String rpc, String[] args)
    {
        super(rpc,args);
        perExeCount=getPerCount();
        this.args=args;
    }
    String[] args;

    @Override
    public void exec(CountDownLatch startLatch, CountDownLatch endLatch)
    {
        try
        {
            startLatch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
//        new Gridclient().start(getPerCount());
       Main.main(null);
        //endLatch.countDown();
    }




    public static void main(String[] args)
    {
        new DubboTest("dubbo",args).start();
    }
}
