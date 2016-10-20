package com.zhiyin.dbs.module.community.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.zhiyin.dbs.module.common.config.TableOrderBy;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.common.util.PageInfoUtil;
import com.zhiyin.dbs.module.community.config.CommunityCacheKey;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import com.zhiyin.dbs.module.community.entity.TopicInfo;
import com.zhiyin.dbs.module.community.mapper.CommentInfoMapper;
import com.zhiyin.dbs.module.community.service.ICommentInfoService;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.frame.idgen.IdGenFactory;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@Service
//@CacheConfig(cacheNames = {CommunityCacheKey.CommentInfoKey})
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class CommentInfoServiceImpl extends BaseService<CommentInfo> implements ICommentInfoService {

    @Autowired
    CommentInfoMapper commentInfoMapper;

    @Autowired
    private ITopicInfoService topicInfoService;

    @Override
    public BaseMapper<CommentInfo> getBaseMapper() {
        return commentInfoMapper ;
    }

    @Override
    public Integer updateThumb(Long commentId, int num) {
        commentInfoMapper.selectForUpdate(commentId);

        CommentInfo commentInfo = this.selectByPrimaryKey(commentId);
        CommentInfo tmp = new CommentInfo();
        tmp.setId(commentId);
        tmp.setThumbNum( commentInfo.getThumbNum() + num );

        this.updateByPrimaryKeySelective(tmp);
        return 1;
    }

//    @Cacheable
    @Override
    public CommentInfo selectById(Long id){
        return super.selectById(id);
    }

    @Deprecated
    @Override
    public PageInfo<CommentInfo> selectByTopicAndOrder(Long topicId, int pageNum, int pageSize, String orderby) {

        log.info("sssss");

        if(Strings.isNullOrEmpty(orderby)){
            orderby = TableOrderBy.Default;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CommentInfo> list = commentInfoMapper.selectByTopicAndOrder(topicId,orderby);
        PageInfo<CommentInfo> page = new PageInfo(list);

        return page;
    }


//    @Cacheable
    @Override
    public PageInfo<CommentInfo> selectByTopicAndOrder(Long topicId, PageInfo pageInfo) {

        PageInfoUtil.defaultOrderBy(pageInfo);
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getOrderBy());
        List<CommentInfo> list = commentInfoMapper.selectByTopic(topicId);

        log.info("selectByTopicAndOrder, topicId:{}",topicId);
        log.info("selectByTopicAndOrder, pageInfo:{}", JSON.toJSONString(list));

        PageInfo<CommentInfo> page = new PageInfo(list);

        return page;
    }


    @Override
    public int deleteByIdOwner(Long id, Long userId ) {
        int re = commentInfoMapper.deleteByIdOwner(id,userId);
        return re;
    }


    @Override
    public Long insertSelectiveGet(CommentInfo bo) {
        this.getBaseMapper().setCharsetToUtf8mb4();
        bo.setId(Long.valueOf(IdGenFactory.genTableId()));
        bo.setCreateTime(DateTime.now().toDate());
        bo.setUpdateTime(DateTime.now().toDate());
        bo.setDelStatus(Integer.valueOf(0));

        int insert = this.getBaseMapper().insertSelective(bo);

        topicInfoService.updateIncComment(bo.getTopicId(),bo.getUserId());

        if(insert <= 0) {
            throw new RuntimeException();
        } else {
            return bo.getId();
        }
    }

}