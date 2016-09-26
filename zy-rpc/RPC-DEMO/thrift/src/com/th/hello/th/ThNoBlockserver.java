package com.th.hello.th;

import com.th.hello.MobileService;
import com.th.hello.imp.HelloImp;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by magicdoom on 2015/7/16.
 */
public class ThNoBlockserver
{

        public static void main(String[] args) {
        TNonblockingServerTransport serverTransport;
        try {
            serverTransport = new TNonblockingServerSocket(9966);
            MobileService.Processor processor = new MobileService.Processor(
                    new HelloImp());

            //多线程半同步半异步
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport)
                    .selectorThreads(10).workerThreads(200);
            tArgs.processor(processor);
            tArgs.transportFactory(new TFramedTransport.Factory());
            //二进制协议
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            // 多线程半同步半异步的服务模型
            TServer server = new TThreadedSelectorServer(tArgs);
            System.out.println("HelloTThreadedSelectorServer start....");
            server.serve(); // 启动服务
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

}
