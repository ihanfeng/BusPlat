package com.zhiyin.search.es.module.test.repository;

import com.zhiyin.search.es.module.test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends ElasticsearchCrudRepository<Book, Long> {

    List<Book> findByNameAndPrice(String name, Integer price);

    List<Book> findByNameOrPrice(String name, Integer price);

    Page<Book> findByName(String name, Pageable page);

    Page<Book> findByNameNot(String name, Pageable page);

    Page<Book> findByPriceBetween(int price, Pageable page);

    Page<Book> findByNameLike(String name, Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<Book> findByMessage(String message, Pageable pageable);
}