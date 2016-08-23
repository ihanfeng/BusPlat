import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.zhiyin.dbs.module.community.CommunityApplication;
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

/**
 * Created by hg on 2016/7/12.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CommunityApplication.class})
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
    }

    @Test
    public void em(){
        Emoji emoji = EmojiManager.getForAlias("fr");
        System.out.println("HEY: " + emoji.getUnicode());
    }

    /**
     * 插入一个话题，模仿用户点赞
     * @throws Exception
     */
    @Test
    public void testInsertSelective() throws Exception {

        Long topicId = topicInfoService.insertSelectiveGet(entity);

        topicInfoService.updateIncBrowse(topicId,1L);
        topicInfoService.updateIncBrowse(topicId,2L);

        Long thumbUserId = 22L;
        topicThumbService.updateThumb(topicId, thumbUserId );
        topicThumbService.updateThumb(topicId,thumbUserId-1);
        TopicInfo topicTmp = topicInfoService.selectByPrimaryKey(topicId);
        Assert.assertTrue( topicTmp.getThumbNum() == 2);

        topicThumbService.updateThumb(topicId,thumbUserId);
        topicTmp = topicInfoService.selectByPrimaryKey(topicId);
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
        Assert.assertTrue(userTopic.getSize() == 1);

        topicInfoService.deleteByIdOwner(topicId,entity.getUserId());

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
        PageInfo p = new PageInfo();
        p.setSize(10);
        p.setPageNum(1);
        PageInfo<TopicInfo> res = topicInfoService.selectByAddrId(1L,p);
        log.info(JSON.toJSONString(res));
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
}