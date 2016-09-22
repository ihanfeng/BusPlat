package com.zhiyin.search.es.module.content.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "contents", type = "content", shards = 1, replicas = 0, indexStoreType = "fs", refreshInterval = "-1")
public class ContentInfoMapping {

    @Id
    private Long id;

//    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String title;
    private Float duration;//时长
    private String document;
    private String description;//描述
    private String tag;//内容标签
    private String savePath;//保存路径

//    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long roleId;


}
