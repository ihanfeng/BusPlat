package com.zhiyin.dbs.module.community.mapper;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.community.entity.CommentThumb;
import com.zhiyin.dbs.module.community.entity.TopicThumb;
import com.zhiyin.dbs.module.msg.entity.SystemNotify;
import org.apache.ibatis.annotations.Param;

public interface CommentThumbMapper  extends BaseMapper<CommentThumb> {
    CommentThumb selectByCommentUser(@Param("commentId") Long commentId,@Param("userId") Long userId);
}