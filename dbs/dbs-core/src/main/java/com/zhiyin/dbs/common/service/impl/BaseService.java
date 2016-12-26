package com.zhiyin.dbs.common.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.zhiyin.dbs.common.base.BaseEntity;
import com.zhiyin.dbs.common.mapper.BaseMapper;
import com.zhiyin.frame.idgen.IdGenFactory;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

public abstract class BaseService<V extends BaseEntity,E,PK extends Serializable> {

//	public abstract IBaseService<V> getManage();

    public abstract BaseMapper<V,E,PK> getBaseMapper();


    public int deleteByPrimaryKey(PK id){
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    public int deleteRealByPrimaryKey(PK id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    public int insertSelective(V bo) {
        this.getBaseMapper().setCharsetToUtf8mb4();

        bo.setId( IdGenFactory.genTableId() );
		bo.setCreateTime(DateTime.now().toDate());
		bo.setUpdateTime(DateTime.now().toDate());
		bo.setDelStatus(0);

        postInsertBefore(bo);
		return getBaseMapper().insertSelective(bo);
	}

    public Long insertSelectiveGet(V bo) {
        this.getBaseMapper().setCharsetToUtf8mb4();
        bo.setId( IdGenFactory.genTableId() );
        bo.setCreateTime(DateTime.now().toDate());
        bo.setUpdateTime(DateTime.now().toDate());
        bo.setDelStatus(0);

        postInsertBefore(bo);

        int insert = getBaseMapper().insertSelective(bo);
        if( insert <= 0){
            throw new RuntimeException();
        }

        return bo.getId();
    }


    public Integer insertSelective(List<V> list) {

        for(int i=0; i<list.size();i++ ){
            insertSelective(list.get(i));
        }

        return 1;
    }

    public void postInsertBefore(V bo){

    }

	public int updateByPrimaryKeySelective(V bo) {
        this.getBaseMapper().setCharsetToUtf8mb4();
        return getBaseMapper().updateByPrimaryKeySelective(bo);
	}


//    @Cacheable
    public V selectByPrimaryKey(PK id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

//    @Cacheable
    public V selectById(PK id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

//    @Cacheable(value = "commonCache")
    public PageInfo selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<V> list = getBaseMapper().selectAll();
        PageInfo<V> page = new PageInfo(list);

        return page;
    }



    public List<V> selectAll(){
        return getBaseMapper().selectAll();
    }


    public int deleteByIdOwner(Long id, Long userId ) {

        int re = getBaseMapper().deleteByIdOwner(id,userId);

        return re;
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
