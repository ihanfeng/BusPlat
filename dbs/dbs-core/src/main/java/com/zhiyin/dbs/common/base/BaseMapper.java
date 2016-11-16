package com.zhiyin.dbs.common.base;

/**
 * Created by wangqinghui on 2016/11/11.
 */

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author wangmengjun
 *
 */
public interface BaseMapper<T, E, PK extends Serializable> {

    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(PK pk);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(PK pk);

    int updateByExampleSelective(@Param("record") T record,
                                 @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}