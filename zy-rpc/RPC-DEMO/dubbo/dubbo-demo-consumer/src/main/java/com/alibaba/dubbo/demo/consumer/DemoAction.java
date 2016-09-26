/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.consumer;

import java.util.ArrayList;
import java.util.List;


import com.alibaba.dubbo.demo.hello.HelloService;
import com.alibaba.dubbo.demo.hello.Order;
//import com.alibaba.dubbo.demo.user.User;
//import com.alibaba.dubbo.demo.user.facade.AnotherUserRestService;
//import com.alibaba.dubbo.rpc.RpcContext;

public class DemoAction {



    private HelloService helloService;

    public HelloService getHelloService()
    {
        return helloService;
    }

    public void setHelloService(HelloService helloService)
    {
        this.helloService = helloService;
    }


    public void start() throws Exception {

        long start=System.currentTimeMillis();
        int count=DubboTest.perExeCount;
        Order x=null;
        for(int i=0;i<count;i++)
        {
            Order order=new Order();
            order.orderDate=count;
            order.orderId=start;
            order.orderNum= String.valueOf(i);
            order.phone ="1358"+i ;
            order.ticketType=i ;
            order.orderStatus=i;
            order.amount=i;
            x=   helloService.hello(order);
            //System.out.println(x);
        }
        DubboTest.endLatch.countDown();
        long used=System.currentTimeMillis()-start;
        System.out.println(x);
        System.out.println("tps " + count * 1000.0 / used);

    }

}