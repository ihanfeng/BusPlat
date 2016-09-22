package com.jomilanez.repository;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "#{indexNameProvider.indexName}", type = "article")
public class Article {

    @Id
    private String id;

    @Field(type = FieldType.String)
    private String title;

    @Field(type = FieldType.String)
    private String description;

    public Article(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
