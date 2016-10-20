package com.zhiyin.community.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import com.zhiyin.dbs.module.community.service.ICommentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/10/16.
 */

@Slf4j
@RestController
public class DubboServiceController {

    @com.alibaba.dubbo.config.annotation.Reference
    private ICommentInfoService commentInfoService;

    @RequestMapping(method = RequestMethod.GET, path = "/ttdubbo" )
    public String greeting() {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(20);

        PageInfo<CommentInfo> comments = commentInfoService.selectByTopicAndOrder(70829125292032L, pageInfo);
        log.info(JSON.toJSONString(comments));
        return "Ok";

    }
    @RequestMapping(method = RequestMethod.GET, path = "/ttdubbo2" )
    public String greeting2() {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(20);

        PageInfo<CommentInfo> comments = commentInfoService.selectByTopicAndOrder(70829125292032L, 1,20,null);
        log.info(JSON.toJSONString(comments));
        return "Ok";

    }

}
