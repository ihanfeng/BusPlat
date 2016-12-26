package com.zhiyin.dbs.common.service;

import com.zhiyin.dbs.common.base.BaseEntity;
import com.zhiyin.dbs.module.common.entity.PageInfo;

import java.util.List;

public interface IBaseService<KEY,V extends BaseEntity> {

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
    public int deleteByIdOwner(Long id, Long userId) ;
    }
