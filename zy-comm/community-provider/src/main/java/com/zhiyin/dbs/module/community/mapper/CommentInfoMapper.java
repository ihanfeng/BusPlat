package com.zhiyin.dbs.module.community.mapper;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.community.entity.CommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentInfoMapper extends BaseMapper<CommentInfo> {

    List<CommentInfo> selectByTopic(@Param("topicId") Long topicId);

    @Deprecated
    List<CommentInfo> selectByTopicAndOrder(@Param("topicId") Long topicId,@Param("orderby") String orderby);
}