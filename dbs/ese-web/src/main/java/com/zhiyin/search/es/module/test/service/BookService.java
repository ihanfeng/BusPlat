package com.zhiyin.search.es.module.test.service;

import com.zhiyin.search.es.module.test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by wangqinghui on 2016/4/11.
 */
public interface BookService {


    Book save(Book post);

    Book findOne(Long id);

    Iterable<Book> findAll();


    Page<Book> findAll(Pageable pageRequest);


    Page<Book> findByName(String tagName, Pageable pageRequest);

}