package com.hg.dubbo.study.javassist;

import com.alibaba.dubbo.common.bytecode.Proxy;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.support.FailbackClusterInvoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import com.hg.dubbo.study.spi.Spi;
import com.hg.dubbo.study.spi.SpiAImpl;

/**
 * Created by wangqinghui on 2016/8/30.
 */
public class JavassistProxyTest {

    public static void main(String[] args) {

        Proxy spi = Proxy.getProxy(DemoService.class);

        Invoker<DemoService> invoker = new DubboInvoker<DemoService>(null,null,null);
        DemoService demoService = (DemoService) spi.newInstance(new InvokerInvocationHandler(invoker));


        demoService.sayHello("ss");

        System.out.println();
    }
}
