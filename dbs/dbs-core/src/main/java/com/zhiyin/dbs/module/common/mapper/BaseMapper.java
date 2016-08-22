package com.zhiyin.dbs.module.common.mapper;

import com.zhiyin.dbs.module.common.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BaseMapper<E extends BaseEntity> {

    @Update("set names utf8mb4")
    public void setCharsetToUtf8mb4();

    int deleteByPrimaryKey(Long id);

    int insert(E record);

    int insertSelective(E record);

    E selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);

    List<E> selectAll();

    int insertBatch(List<E> list);

    int deleteByIdOwner(@Param("id")Long id, @Param("userId") Long userId);

}
