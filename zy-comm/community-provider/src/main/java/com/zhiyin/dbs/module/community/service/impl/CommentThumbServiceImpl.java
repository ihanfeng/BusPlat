package com.zhiyin.dbs.module.community.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.community.entity.CommentThumb;
import com.zhiyin.dbs.module.community.mapper.CommentThumbMapper;
import com.zhiyin.dbs.module.community.service.ICommentInfoService;
import com.zhiyin.dbs.module.community.service.ICommentThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/7/11.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class CommentThumbServiceImpl extends BaseService<CommentThumb> implements ICommentThumbService {

    @Autowired
    CommentThumbMapper commentThumbMapper ;

    @Resource
    ICommentInfoService commentInfoService;

    @Override
    public BaseMapper<CommentThumb> getBaseMapper() {
        return commentThumbMapper;
    }

    //TODO 并发更新有问题，需要采用redis
    @Override
    public Integer updateThumb(Long commentId, Long userId){

        CommentThumb thumb = new CommentThumb();
        thumb.setUserId(userId);
        thumb.setCommentId(commentId);

        // 用户如果已经点赞，则取消点赞
        CommentThumb tmp = this.selectByCommentUser(commentId, userId);
        if( tmp == null){
            this.insertSelective(thumb);
            Integer res = commentInfoService.updateThumb(commentId, 1);
        }else{
            tmp.setDelStatus(1);
            this.updateByPrimaryKeySelective(tmp);
            commentInfoService.updateThumb(commentId,-1);
        }
        return  1;
    }

    @Override
    public CommentThumb selectByCommentUser(Long commentId,Long userId){
        return commentThumbMapper.selectByCommentUser(commentId,userId);
    }


}