package com.th.hello.test;



import com.th.hello.th.Gridclient;
import com.th.hello.th.TestBase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by nange on 2015/7/16.
 */
public class THTest extends TestBase
{
    public THTest(String rpc, String[] args)
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
        new THTest("thrift",args).start();
    }
}
