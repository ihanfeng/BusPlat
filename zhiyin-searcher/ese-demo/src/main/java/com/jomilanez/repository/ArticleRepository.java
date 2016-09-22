package com.jomilanez.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface ArticleRepository extends ElasticsearchRepository<Article, String> {
}
