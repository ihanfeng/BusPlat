package com.zhiyin.dbs.module.community.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.community.entity.TopicThumb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicThumbMapper  extends BaseMapper<TopicThumb> {
    TopicThumb selectByTopicUser(@Param("topicId") Long topicId,@Param("userId") Long userId);


    public List<TopicThumb> selectThumbers(@Param("topicId") Long topicId);

//    public List<Long> selectLatestThumbers(@Param("topicId") Long topicId);

}