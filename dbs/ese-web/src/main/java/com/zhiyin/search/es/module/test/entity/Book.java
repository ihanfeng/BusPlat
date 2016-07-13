package com.zhiyin.search.es.module.test.entity;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "book", type = "book", shards = 1, replicas = 0, indexStoreType = "fs", refreshInterval = "-1")
//@Document(indexName="book")
public class Book {

    private Long id;
    private Double price;
    private String name;
    private String message;

    public Book( ) {

    }


    public Book(Long id, String name ) {
        this.id = id;
        this.name = name;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
