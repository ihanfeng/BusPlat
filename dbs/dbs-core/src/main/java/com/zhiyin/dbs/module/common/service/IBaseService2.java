package com.zhiyin.dbs.module.common.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.entity.BaseEntity;

import java.util.List;

public interface IBaseService2<KEY,V extends BaseEntity> {

    public V selectById(KEY id);

    public V selectByPrimaryKey(KEY id);

	public int deleteByPrimaryKey(KEY id);

    // 真删除
    public int deleteRealByPrimaryKey(KEY id);


    public int insertSelective(V tmp);

    public Long insertSelectiveGet(V tmp);

    public Integer insertSelective(List<V> tmp);


    public int updateByPrimaryKeySelective(V tmp);

    public PageInfo selectByPage(int pageNum, int pageSize) ;

    public List<V> selectAll();

    }
