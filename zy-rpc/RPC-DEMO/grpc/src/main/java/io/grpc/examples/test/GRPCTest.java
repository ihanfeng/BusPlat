package io.grpc.examples.test;



import io.grpc.examples.HelloClient;

import java.util.concurrent.CountDownLatch;

/**
 * Created by nange on 2015/7/16.
 */
public class GRPCTest extends TestBase
{
    public GRPCTest(String rpc, String[] args)
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
       new HelloClient("127.0.0.1",50051).start(getPerCount());
        endLatch.countDown();
    }


    public static void main(String[] args)
    {
        new GRPCTest("grpc",args).start();
    }
}
