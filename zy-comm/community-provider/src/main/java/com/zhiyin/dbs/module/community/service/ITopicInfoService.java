package com.zhiyin.dbs.module.community.service;

import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.dbs.module.community.entity.TopicInfo;

/**
 * Created by hg on 2016/7/11.
 */
public interface ITopicInfoService  extends IBaseService<TopicInfo> {

    @Deprecated
    PageInfo<TopicInfo> selectAllAndOrder(int pageNum, int pageSize, String sortby);

    PageInfo<TopicInfo> selectAllAndOrder(PageInfo pageInfo);

    int deleteByIdOwner(Long id, Long userId);

    PageInfo<TopicInfo> selectByUserId(Long userId, PageInfo pageInfo);

    PageInfo<TopicInfo> selectByAddrId(Long addrId, PageInfo pageInfo);

    PageInfo<TopicInfo> selectByAreaId(Long areaId, PageInfo pageInfo);

    // TODO 并发处理
    Integer updateThumb(Long topicId, Integer num);


    Integer updateIncBrowse(Long topicId, Long userId);

    Integer updateIncComment(Long topicId, Long userId);

}
