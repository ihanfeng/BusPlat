package com.zhiyin.ourchat.mapper;

import com.zhiyin.ourchat.entity.DialogLatest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DialogLatestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DialogLatest record);

    int insertSelective(DialogLatest record);

    DialogLatest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DialogLatest record);

    int updateByPrimaryKey(DialogLatest record);

    List<DialogLatest> selectAlllLatest( @Param("userId") Long userId);

    List<DialogLatest> selectLatest(@Param("userId") Long userId,@Param("partnerId") Long partnerId);


    int deleteOldLatest(@Param("userId") Long userId, @Param("partnerId") Long partnerId);


}