package com.zhiyin.dbs.module.community.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import com.zhiyin.dbs.module.community.entity.TopicInfo;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
public interface ICommentInfoService extends IBaseService<CommentInfo> {

    Integer updateThumb(Long commentId, int i);

    PageInfo<CommentInfo> selectByTopicAndOrder(Long topicId, int pageNum, int pageSize, String sortby);

    int deleteByIdOwner(Long id, Long userId);
}
