package com.hg.msg.mapper;

import com.hg.msg.entity.MsgNotify;
import com.hg.msg.entity.TestBasicData;
import com.hg.msg.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface TestBasicDataMapper extends MyMapper<TestBasicData> {

    public List<TestBasicData> selectInc(@Param("fromTime") Date fromTime, @Param("toTime") Date toTime);

    public List<TestBasicData> selectIncByTest(@Param("fromTime") Long fromTime, @Param("toTime") Long toTime);

}