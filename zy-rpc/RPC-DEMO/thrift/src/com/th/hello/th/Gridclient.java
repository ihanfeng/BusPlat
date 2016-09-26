package com.th.hello.th;

import com.th.hello.MobileService;
import com.th.hello.Order;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by magicdoom on 2015/7/11.
 */
public class Gridclient
{
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 9966;
    public static final int TIMEOUT = 30000;

    public void      start(int perCount)
    {

        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        //HelloTNonblockingServer
        ////使用高密度二进制协议
        // TProtocol protocol = new TCompactProtocol(transport);
        //HelloTHsHaServer
        ////使用二进制协议
        TProtocol protocol = new TBinaryProtocol(transport);
        MobileService.Client client = new MobileService.Client(protocol);
        try
        {
            transport.open();


            long start=System.currentTimeMillis();
            int count=perCount;
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
                x=   client.hello(order);
                //System.out.println(x);
            }
            long used=System.currentTimeMillis()-start;
            System.out.println(x);
            System.out.println("tps "+count*1000.0/used);
        } catch (TTransportException e)
        {
            e.printStackTrace();
        } catch (TException e)
        {
            e.printStackTrace();
        } finally
        {

            //关闭资源
            transport.close();
        }





    }






    public static void main(String[] args) {
        new Gridclient().start(300000);
    }
}
