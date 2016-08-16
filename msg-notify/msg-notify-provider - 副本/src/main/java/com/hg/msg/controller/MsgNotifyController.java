package com.hg.msg.controller;

import com.alibaba.fastjson.JSON;
import com.hg.msg.entity.MsgUserNotify;
import com.hg.msg.service.IMsgNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * A RESTFul controller for accessing account information.
 *
 * @author Paul Chapman
 */
@Slf4j
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController
//@RequestMapping("/msgnotify/")
public class MsgNotifyController {

    @Autowired
    private IMsgNotifyService msgNotifyService;

    @RequestMapping(method = RequestMethod.GET, path = "/testok")
    public List<MsgUserNotify> greeting() {
        log.info("test ok.");
        List<MsgUserNotify> tmp = msgNotifyService.getUserNotify(112L);
        log.info(JSON.toJSONString(tmp));
        return tmp;
    }

    @RequestMapping("/notify/{userId}")
    public List<MsgUserNotify> getUserNotify(@PathVariable("userId") Long userId) {
        List<MsgUserNotify> tmp = msgNotifyService.getUserNotify(userId);
        log.info(JSON.toJSONString(tmp));
        return tmp;
    }


}
