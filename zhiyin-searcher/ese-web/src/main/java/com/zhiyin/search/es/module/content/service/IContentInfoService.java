package com.zhiyin.search.es.module.content.service;

import com.zhiyin.search.es.module.content.entity.ContentInfoMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by wangqinghui on 2016/4/11.
 */
public interface IContentInfoService {
    ContentInfoMapping save(ContentInfoMapping post);

    ContentInfoMapping findOne(Long id);

    Iterable<ContentInfoMapping> findAll();


    Page<ContentInfoMapping> findAll(Pageable pageRequest);


//    Page<ContentInfoMapping> findByName(String tagName, Pageable pageRequest);

    public Page<ContentInfoMapping> findByTitleOrDescription(String title, String description, Pageable pageable);
    Page<ContentInfoMapping> findByTitle(String title, Pageable pageable);


    Page<ContentInfoMapping> search(Long roleId, String query, Pageable pageable);

    Page<ContentInfoMapping> findByDescription(String document, Pageable pageable);


    void delete( Long id);

    void deleteAll( );
}