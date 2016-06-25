package com.zhiyin.search.es.module.content.repository;

import com.zhiyin.search.es.module.content.entity.ContentInfoMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentInfoRepository extends ElasticsearchRepository<ContentInfoMapping, Long> {
    Page<ContentInfoMapping> findByTitle(String title, Pageable pageable);

    Page<ContentInfoMapping> findByRoleIdAndTitle(Long roleId, String title,Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?1\"}} , {\"match\": {\"roleId\": \"?0\"}} ]}}")
    Page<ContentInfoMapping> findByRoleIdAndTitleUsingCustomQuery(Long roleId, String title,Pageable pageable);


    Page<ContentInfoMapping> findByDescription(String description, Pageable pageable);

    //	 List<ContentInfoMapping> findByName(String name);
    public Page<ContentInfoMapping> findByTitleOrDescription(String title, String description, Pageable pageable);

}
