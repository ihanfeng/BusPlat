package com.zhiyin.dbs.module.common.mapper;

import com.zhiyin.dbs.module.common.entity.BaseEntity;

import java.util.List;

public interface BaseMapper2<KEY , E extends BaseEntity> {
    int deleteByPrimaryKey(KEY id);

    int insert(E record);

    int insertSelective(E record);

    E selectByPrimaryKey(KEY id);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);

    List<E> selectAll();

    int insertBatch(List<E> list);


}
