package com.zhiyin.search.es.module;

import org.elasticsearch.common.collect.Lists;

import java.util.List;

public class S2cContentSearchResult {

    private Integer totalPage;
    private Integer currentPage;
    private Integer pageSize;
    private List<S2cContentInfo> contents = Lists.newArrayList();


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<S2cContentInfo> getContents() {
        return contents;
    }

    public void setContents(List<S2cContentInfo> contents) {
        this.contents = contents;
    }


}
