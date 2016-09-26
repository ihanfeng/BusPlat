package com.xxx.demo.server;

import com.xxx.demo.imp.HelloImp;

/**
 * Created by magicdoom on 2015/7/11.
 */
public class Myserver
{
    public static void main(String[] args) {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            // 初始化Communicator对象，args可以传一些初始化参数，如连接超时，初始化客户端连接池的数量等
            String[] initParams = new String[] { "--Ice.Config=server.properties"
            };
            ic = Ice.Util.initialize(initParams);
            // 创建名为OnlineBookAdapter的适配器，并要求适配器使用缺省的协议（TCP/IP 端口为10000的请求）
            Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
                    "IceHelloAdapter", "default -p 10000");
            // 实例化一个OnlineBook服务对象
            HelloImp object = new HelloImp();
            // 将服务单元增加到适配器中，并给服务对象指定ID为OnlineBook，该名称用于唯一确定一个服务单元
            adapter.add(object, Ice.Util.stringToIdentity("IcehelloBook"));
            // 激活适配器
            adapter.activate();
            // 让服务在退出之前，一直持续对请求的监听
            System.out.print("server started ");
            ic.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
        System.exit(status);
    }
}
