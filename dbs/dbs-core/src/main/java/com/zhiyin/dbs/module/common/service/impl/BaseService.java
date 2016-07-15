package com.zhiyin.dbs.module.common.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.entity.BaseEntity;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.frame.idgen.IdGenFactory;
import org.joda.time.DateTime;

import java.util.List;

public abstract class BaseService<V extends BaseEntity> {

//	public abstract IBaseService<V> getManage();

    public abstract BaseMapper<V> getBaseMapper();


    public int deleteByPrimaryKey(Long id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    public int deleteRealByPrimaryKey(Long id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    public int insertSelective(V bo) {
        bo.setId(IdGenFactory.genTableId());
        bo.setCreateTime(DateTime.now().toDate());
        bo.setUpdateTime(DateTime.now().toDate());
        bo.setDelStatus(0);

        postInsertBefore(bo);
        return getBaseMapper().insertSelective(bo);
    }

    public Long insertSelectiveGet(V bo) {
        bo.setId(IdGenFactory.genTableId());
        bo.setCreateTime(DateTime.now().toDate());
        bo.setUpdateTime(DateTime.now().toDate());
        bo.setDelStatus(0);

        postInsertBefore(bo);

        int insert = getBaseMapper().insertSelective(bo);
        if (insert <= 0) {
            throw new RuntimeException();
        }

        return bo.getId();
    }


    public Integer insertSelective(List<V> list) {

        for (int i = 0; i < list.size(); i++) {
            insertSelective(list.get(i));
        }

        return 1;
    }

    public void postInsertBefore(V bo) {

    }

    public int updateByPrimaryKeySelective(V bo) {
        return getBaseMapper().updateByPrimaryKeySelective(bo);
    }


    public V selectByPrimaryKey(Long id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    public V selectById(Long id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    //    @Cacheable(value = "commonCache")
    public PageInfo selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<V> list = getBaseMapper().selectAll();
        PageInfo<V> page = new PageInfo(list);

        return page;
    }


    public List<V> selectAll() {
        return getBaseMapper().selectAll();
    }


//    @Override
//    public int deleteByPrimaryKey(Long id) {
//        BaseEntity tmp = new BaseEntity();
//        tmp.setId(id);
//        tmp.setUpdateTime(DateTime.now().toDate());
//        tmp.setDelStatus(1);
//        return getBaseMapper().updateByPrimaryKeySelective(tmp);
//    }


//	public int updateByPrimaryKey(V bo) {
//		return getManage().updateByPrimaryKey(bo);
//	}

//	public int fakeDeleteByPrimaryKey(Long id) {
//		return getBaseMapper().fakeDeleteByPrimaryKey(id);
//	}
}
