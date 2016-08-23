package com.zhiyin.dbs.module.community.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.community.entity.TopicThumb;
import org.apache.ibatis.annotations.Param;

public interface TopicThumbMapper  extends BaseMapper<TopicThumb> {
    TopicThumb selectByTopicUser(@Param("topicId") Long topicId,@Param("userId") Long userId);
}