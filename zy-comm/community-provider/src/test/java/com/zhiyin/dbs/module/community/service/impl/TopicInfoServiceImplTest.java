package com.zhiyin.dbs.module.community.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.zhiyin.dbs.module.common.util.PageInfoUtil;
import com.zhiyin.dbs.module.community.CommunityProviderApplication;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import com.zhiyin.dbs.module.community.entity.TopicInfo;
import com.zhiyin.dbs.module.community.service.ICommentInfoService;
import com.zhiyin.dbs.module.community.service.ICommentThumbService;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.dbs.module.community.service.ITopicThumbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by hg on 2016/7/12.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CommunityProviderApplication.class})
@WebAppConfiguration
public class TopicInfoServiceImplTest  {

    @Resource
    ITopicInfoService topicInfoService ;
    @Resource
    ITopicThumbService topicThumbService ;

    @Resource
    ICommentInfoService commentInfoService;
    @Resource
    ICommentThumbService commentThumbService;

    TopicInfo entity = new TopicInfo();

    @Before
    public void setUp() throws Exception {
        entity.setTitle("test topic" + Character.toChars(127467) );
        entity.setUserId(1L);
        entity.setAreaId(11L);
    }

    @Test
    public void em(){
        Emoji emoji = EmojiManager.getForAlias("fr");
        System.out.println("HEY: " + emoji.getUnicode());


    }

    @Test
    public void test3(){
        // 插入
//        Long topicId = topicInfoService.insertSelectiveGet(entity);
//
//        for(int i=0; i<20;i++){
//            commentInfoService.insertSelectiveGet(comment(topicId));
//
//        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(20);

        PageInfo<CommentInfo> comments = commentInfoService.selectByTopicAndOrder(39564258963456L, pageInfo);
        log.info(JSON.toJSONString(comments));
    }
    /**
     * 插入一个话题，模仿用户点赞
     * @throws Exception
     */
    @Test
    public void testInsertSelective() throws Exception {

        // 插入
        Long topicId = topicInfoService.insertSelectiveGet(entity);

        // 浏览
        topicInfoService.updateIncBrowse(topicId,1L);
        topicInfoService.updateIncBrowse(topicId,2L);

        // 点赞
        Long thumbUserId = 22L;
        topicThumbService.updateThumb(topicId, thumbUserId );
        topicThumbService.updateThumb(topicId,thumbUserId-1);

        // 查询
        TopicInfo topicTmp = topicInfoService.selectById(topicId);
        Assert.assertTrue( topicTmp.getThumbNum() == 2);

        // 取消
        topicThumbService.updateThumb(topicId,thumbUserId);
        topicTmp = topicInfoService.selectById(topicId);
        Assert.assertTrue( topicTmp.getThumbNum() == 1);

        Long comId = commentInfoService.insertSelectiveGet(comment(topicId));
        Long comId2 = commentInfoService.insertSelectiveGet(comment(topicId));

        commentThumbService.updateThumb(comId,genUid() );
        commentThumbService.updateThumb(comId,genUid() );

        commentThumbService.updateThumb(comId2,genUid() );
        commentThumbService.updateThumb(comId2,genUid() );


        PageInfo<CommentInfo> comments = commentInfoService.selectByTopicAndOrder(topicId, 1, 20, null);
        Assert.assertTrue(comments.getList().size() == 2 );

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        PageInfo<TopicInfo> userTopic = topicInfoService.selectByUserId(1L,pageInfo);
//        Assert.assertTrue(userTopic.getSize() == 1);

        topicInfoService.deleteByIdOwner(topicId,entity.getUserId());

    }

    @Test
    public void testCache() throws Exception{

        List<Long> ids = Lists.newArrayList();
        List<Long> commentIds = Lists.newArrayList();
        for(long i=0; i < 5 ;i++){
            entity.setUserId( i+1);
            Long topicId = topicInfoService.insertSelectiveGet(entity);
            ids.add(topicId);

            for (int j = 0; j < 2; j++) {
                Long comId = commentInfoService.insertSelectiveGet(comment(topicId));
                commentIds.add(comId);
            }
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(2);

        for(int i=1;i <5;i++){
            pageInfo.setPageNum(i);
            topicInfoService.selectAllAndOrder(pageInfo);
        }

        for (Long id : ids) {
            topicInfoService.selectById(id);
            topicInfoService.deleteRealByPrimaryKey(id);
        }

        for (Long id : commentIds) {
            commentInfoService.selectById(id);
            commentInfoService.deleteRealByPrimaryKey(id);
        }
    }


    @Test
    public void testIn(){
        topicInfoService.selectById(1L);
    }


    @Test
    public void test() throws Exception {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        pageInfo.setOrderBy("create_time desc");

        PageInfo<TopicInfo> res = topicInfoService.selectAllAndOrder(pageInfo);
//        log.info(JSON.toJSONString(res));

        pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        res = topicInfoService.selectByUserId(22L,pageInfo);
        log.info(JSON.toJSONString(res));

    }


    @Test
    public void selectByAddrId() throws Exception {

        TopicInfo tp = getTopic();
        topicInfoService.insertSelectiveGet(tp);

        PageInfo<TopicInfo> res = topicInfoService.selectByAddrId(tp.getAddrId() ,PageInfoUtil.firstPage());
        log.info(JSON.toJSONString(res));
    }

    @Test
    public void selectByAreaId() throws Exception {
        TopicInfo tp = getTopic();
        for(int i=0;i < 15;i++){
            topicInfoService.insertSelectiveGet(tp);
        }
        PageInfo<TopicInfo> res = topicInfoService.selectByAreaId( tp.getAreaId(), PageInfoUtil.firstPage());
        log.info(JSON.toJSONString(res));
        Assert.assertTrue( res.getSize() >= 1);

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


    public static TopicInfo getTopic(){
        TopicInfo ti = new TopicInfo();
        ti.setUserId(RandomUtils.nextLong(1,110000));
        ti.setAddrId(RandomUtils.nextLong(1,110000));
        ti.setAreaId(RandomUtils.nextLong(1,10000));
        ti.setContent(UUID.randomUUID().toString());
        return ti;
    }
}