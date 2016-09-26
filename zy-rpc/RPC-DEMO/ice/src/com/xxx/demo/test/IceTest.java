package com.xxx.demo.test;

import com.xxx.demo.client.Gridclient;

import java.util.concurrent.CountDownLatch;

/**
 * Created by nange on 2015/7/16.
 */
public class IceTest extends TestBase
{
    public IceTest(String rpc,String[] args)
    {
        super(rpc,args);
    }

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
        new Gridclient().start(getPerCount());
        endLatch.countDown();
    }


    public static void main(String[] args)
    {
        new IceTest("ice",args).start();
    }
}
