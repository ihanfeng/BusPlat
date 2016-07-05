package com.zhiyin.search.es.module.test.controller;

import com.alibaba.fastjson.JSON;
import com.zhiyin.search.es.module.test.entity.Book;
import com.zhiyin.search.es.module.test.service.BookService;
import com.zhiyin.search.es.util.EsUtil;
import com.zhiyin.search.es.web.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Resource
    private BookService bookService;

    @Autowired
    private Client client;


    @RequestMapping(value = "/testok", method = RequestMethod.GET)
    public String test() {
        String hello = "hello you!";
        log.info( hello );
        return hello;
    }

    @RequestMapping(value = "/iniIndex", method = RequestMethod.GET)
    public String iniIndex() {
        log.info( "ini all index." );

        EsUtil.importData(client, AccountRestController.class.getResource("/").getPath()+"data/accounts.json","test","account");

        return "ini succ!";
    }

}
