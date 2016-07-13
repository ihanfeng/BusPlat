package com.zhiyin.dbs.module.common.mapper;

import com.zhiyin.dbs.module.common.entity.BaseEntity;

import java.util.List;

public interface BaseMapper<E extends BaseEntity> {
    int deleteByPrimaryKey(Long id);

    int insert(E record);

    int insertSelective(E record);

    E selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);

    List<E> selectAll();

    int insertBatch(List<E> list);


}
