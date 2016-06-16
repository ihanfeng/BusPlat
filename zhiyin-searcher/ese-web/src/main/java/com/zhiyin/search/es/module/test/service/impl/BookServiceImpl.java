package com.zhiyin.search.es.module.test.service.impl;

import com.zhiyin.search.es.module.test.entity.Book;
import com.zhiyin.search.es.module.test.repository.BookRepository;
import com.zhiyin.search.es.module.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/4/11.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book Book) {
        bookRepository.save(Book);
        return Book;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageRequest) {
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Page<Book> findByName(String tagName, Pageable pageRequest) {
        return bookRepository.findByName(tagName, pageRequest);
    }

}