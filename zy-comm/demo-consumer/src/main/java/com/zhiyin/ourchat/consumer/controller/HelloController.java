package com.zhiyin.ourchat.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.service.IDialogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class HelloController {

    @com.alibaba.dubbo.config.annotation.Reference
    private IDialogRecordService dialogRecordService;

    @RequestMapping(method = RequestMethod.GET, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        PageInfo<DialogRecord> tmp = dialogRecordService.selectByPartner(1L, 2L, 1, 20);
        return JSON.toJSONString(tmp);
    }

}
