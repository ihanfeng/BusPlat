package com.zhiyin.app.dbs.mapper;

import com.zhiyin.app.dbs.entity.SuggestFeedback;
import com.zhiyin.app.dbs.entity.SuggestFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuggestFeedbackMapper {
    int countByExample(SuggestFeedbackExample example);

    int deleteByExample(SuggestFeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SuggestFeedback record);

    int insertSelective(SuggestFeedback record);

    List<SuggestFeedback> selectByExample(SuggestFeedbackExample example);

    SuggestFeedback selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SuggestFeedback record, @Param("example") SuggestFeedbackExample example);

    int updateByExample(@Param("record") SuggestFeedback record, @Param("example") SuggestFeedbackExample example);

    int updateByPrimaryKeySelective(SuggestFeedback record);

    int updateByPrimaryKey(SuggestFeedback record);
}