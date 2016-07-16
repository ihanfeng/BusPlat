package com.zhiyin.dbs.module.common.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.entity.BaseEntity;

import java.util.List;

public interface IBaseService<V extends BaseEntity> {

    public V selectById(Long id);

    public V selectByPrimaryKey(Long id);

    public int deleteByPrimaryKey(Long id);

    // 真删除
    public int deleteRealByPrimaryKey(Long id);


    public int insertSelective(V tmp);

    public Long insertSelectiveGet(V tmp);

    public Integer insertSelective(List<V> tmp);


    public int updateByPrimaryKeySelective(V tmp);

    public PageInfo selectByPage(int pageNum, int pageSize);

    public List<V> selectAll();

}
