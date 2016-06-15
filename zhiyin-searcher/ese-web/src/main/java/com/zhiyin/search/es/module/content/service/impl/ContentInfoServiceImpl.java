package com.zhiyin.search.es.module.content.service.impl;



import com.zhiyin.search.es.module.content.entity.ContentInfoMapping;
import com.zhiyin.search.es.module.content.repository.ContentInfoRepository;


import com.zhiyin.search.es.module.content.service.IContentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by wangqinghui on 2016/4/11.
 */
@Slf4j
@Service
public class ContentInfoServiceImpl implements IContentInfoService {

    @Autowired
    private ContentInfoRepository contentInfoRepository;

    @Override
    public ContentInfoMapping save(ContentInfoMapping post) {
        return contentInfoRepository.save(post);
    }

    @Override
    public ContentInfoMapping findOne(Long id) {
        return contentInfoRepository.findOne(id);
    }

    @Override
    public Iterable<ContentInfoMapping> findAll() {
        return contentInfoRepository.findAll();
    }

    @Override
    public Page<ContentInfoMapping> findAll(Pageable pageRequest) {
        return contentInfoRepository.findAll(pageRequest);
    }

    @Override
    public Page<ContentInfoMapping> findByTitleOrDescription(String title, String description, Pageable pageable) {
        return contentInfoRepository.findByTitleOrDescription(title,description,pageable);
    }

    @Override
    public Page<ContentInfoMapping> findByTitle(String title, Pageable pageable) {
        return contentInfoRepository.findByTitle(title,pageable);
    }

    @Override
    public Page<ContentInfoMapping> search(Long roleId , String query, Pageable pageable) {
        return contentInfoRepository.findByRoleIdAndTitle(roleId,query,pageable);
    }

    @Override
    public Page<ContentInfoMapping> findByDescription(String description, Pageable pageable) {
        return contentInfoRepository.findByDescription(description,pageable);
    }

    @Override
    public void delete(Long id) {
        contentInfoRepository.delete(id);
    }

    /**
     * 慎用
     */
    @Override
    public void deleteAll( ) {
        log.error("delete all content index, very dangerous.");
        contentInfoRepository.deleteAll();
    }

}