package com.th.hello.imp;

import com.th.hello.MobileService;
import com.th.hello.Order;
import org.apache.thrift.TException;

/**
 * Created by magicdoom on 2015/7/16.
 */
public class HelloImp implements MobileService.Iface
{
    @Override
    public Order hello(Order order) throws TException
    {
        return order;
    }
}
