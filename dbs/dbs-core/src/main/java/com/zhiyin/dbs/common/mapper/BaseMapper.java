package com.zhiyin.dbs.common.mapper;

/**
 * Created by wangqinghui on 2016/11/11.
 */

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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



    @Update("set names utf8mb4")
    public void setCharsetToUtf8mb4();

    List<T> selectAll();

    int insertBatch(List<T> list);

    int deleteByIdOwner(@Param("id")Long id, @Param("userId") Long userId);

    //    / 查询并排序
    List<T> selectAllAndOrder(String orderby);

    // 需要将del_status设置为0
    int insertOrUpdate(T record);
}