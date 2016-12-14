package com.zhiyin.app.dbs.mapper;

import com.zhiyin.app.dbs.entity.Feedback;
import com.zhiyin.app.dbs.entity.FeedbackExample;
import com.zhiyin.dbs.common.base.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper extends BaseMapper<Feedback, FeedbackExample, Long> {
}