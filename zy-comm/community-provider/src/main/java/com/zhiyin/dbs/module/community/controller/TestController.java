package com.zhiyin.dbs.module.community.controller;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import com.zhiyin.dbs.module.community.entity.TopicInfo;
import com.zhiyin.dbs.module.community.service.ICommentInfoService;
import com.zhiyin.dbs.module.community.service.ICommentThumbService;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.dbs.module.community.service.ITopicThumbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    ITopicInfoService topicInfoService ;
    @Resource
    ITopicThumbService topicThumbService ;
    @Resource
    ICommentInfoService commentInfoService;
    @Resource
    ICommentThumbService commentThumbService;

    TopicInfo entity = new TopicInfo();


    @RequestMapping(value = "/test")
    public String ok() {
        try {
            for(int i=0;i<1000;i++){
                testInsertSelective();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public static Long genUid(){
        return RandomUtils.nextLong(1,10000);
    }

    public static CommentInfo comment(Long topicId){
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setTopicId(topicId);
        commentInfo.setUserId(RandomUtils.nextLong(1,10000));

        commentInfo.setComment("tttt");
        return commentInfo;
    }

    public void testInsertSelective() throws Exception {

        entity.setTitle( "test topic" );
        entity.setUserId(RandomUtils.nextLong(1,10000));
        entity.setAreaId(RandomUtils.nextLong(1,10000));

        // 插入
        Long topicId = topicInfoService.insertSelectiveGet(entity);

        // 浏览
        topicInfoService.updateIncBrowse(topicId, getId() );
        topicInfoService.updateIncBrowse(topicId, getId() );

        // 点赞
        Long thumbUserId = getId() ;
        topicThumbService.updateThumb(topicId, thumbUserId );
        topicThumbService.updateThumb(topicId,thumbUserId-1);

        // 查询
        TopicInfo topicTmp = topicInfoService.selectById(topicId);
//        Assert.assertTrue( topicTmp.getThumbNum() == 2);

        // 取消
        topicThumbService.updateThumb(topicId,thumbUserId);
        topicTmp = topicInfoService.selectById(topicId);
//        Assert.assertTrue( topicTmp.getThumbNum() == 1);

        Long comId = commentInfoService.insertSelectiveGet(comment(topicId));
        Long comId2 = commentInfoService.insertSelectiveGet(comment(topicId));

        commentThumbService.updateThumb(comId,genUid() );
        commentThumbService.updateThumb(comId,genUid() );

        commentThumbService.updateThumb(comId2,genUid() );
        commentThumbService.updateThumb(comId2,genUid() );


        PageInfo<CommentInfo> comments = commentInfoService.selectByTopicAndOrder(topicId, 1, 20, null);
//        Assert.assertTrue(comments.getList().size() == 2 );

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        PageInfo<TopicInfo> userTopic = topicInfoService.selectByUserId(1L,pageInfo);
//        Assert.assertTrue(userTopic.getSize() == 1);

        topicInfoService.deleteByIdOwner(topicId,entity.getUserId());

    }

    public Long getId(){
        return RandomUtils.nextLong(1,10000);
    }
}
