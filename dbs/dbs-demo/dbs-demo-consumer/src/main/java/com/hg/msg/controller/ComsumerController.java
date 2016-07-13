//package com.hg.msg.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.hg.msg.config.serveice.MsgNotifyRestService;
//import com.hg.msg.entity.MsgUserNotify;
//import com.hg.msg.service.IMsgNotifyInfoService;
//import com.hg.msg.service.IMsgNotifyService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created by wangqinghui on 2016/3/29.
// */
//@Slf4j
//@RestController
//public class ComsumerController {
//
//    // rest服务调用
//    @Autowired
//    protected MsgNotifyRestService msgNotifyRestService;
//
//    // 使用dubbo的服务
//    @com.alibaba.dubbo.config.annotation.Reference
//    private IMsgNotifyService msgNotifyService;
//
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping("/discovery")
//    public String doDiscoveryService(){
//        StringBuilder buf = new StringBuilder();
//        List<String> serviceIds = discoveryClient.getServices();
//        if(!CollectionUtils.isEmpty(serviceIds)){
//            for(String s : serviceIds){
//                System.out.println("serviceId:" + s);
//                List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
//                if(!CollectionUtils.isEmpty(serviceInstances)){
//                    for(ServiceInstance si:serviceInstances){
//                        buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
//                    }
//                }else{
//                    buf.append("no service.");
//                }
//            }
//        }
//
//
//        return buf.toString();
//    }
//
//
//
//    @RequestMapping("/testrest")
//    public String greeting() {
//        log.info("test ok.");
//        List<MsgUserNotify> tmp = msgNotifyRestService.getUserNotify(112L);
//        log.info(JSON.toJSONString(tmp));
//
//        return JSON.toJSONString(tmp);
//    }
//
//
//
//    @RequestMapping("/testdubbo")
//    public String testdubbo() {
//
//        List<MsgUserNotify> tmp = msgNotifyService.getUserNotify(112L);
//        log.info(JSON.toJSONString(tmp));
//
//        tmp = msgNotifyService.getUserNotify(2L);
//
//        return JSON.toJSONString(tmp);
//    }
//
//}
