package com.th.hello.th;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by magicdoom on 2015/7/12.
 */
public abstract class TestBase
{
    public static final    CountDownLatch startLatch= new CountDownLatch(1);;
    public static   CountDownLatch endLatch;
     int perCount;
     int clientNum;
    private String rpcName;
    public TestBase(String rpc, String[] args)
    {
        if(args==null||args.length<2)
        {
            throw new RuntimeException("must input 2 paramter:clientNum,perCount");
        }
        rpcName=rpc;
        clientNum=Integer.parseInt(args[0]);
        perCount=Integer.parseInt(args[1]);
        endLatch = new CountDownLatch(clientNum);
    }

    public abstract  void exec(CountDownLatch startLatch,CountDownLatch endLatch)  ;

    public int getPerCount()
    {
        return perCount;
    }

    public int getClientNum()
    {
        return clientNum;
    }

    public void start()
    {
        ExecutorService executor = Executors.newFixedThreadPool(clientNum);
        for (int j = 0; j < clientNum; j++)
        {
            executor.submit(new Runnable()
            {
                @Override
                public void run()
                {
                  exec(TestBase.startLatch,TestBase.endLatch);
                }
            });
        }


        try
        {


            startLatch.countDown();
            long start=System.currentTimeMillis();

            endLatch.await();

            long used=System.currentTimeMillis()-start;
            System.out.println(rpcName+":client num:"+clientNum+",execute per client:"+perCount+",total execute:"+perCount*clientNum+",total time:"+used/1000+"s, tps:"+perCount*clientNum*1000.0/used);

            executor.shutdown();

        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

    }







}
