package com.zhiyin.dbs.module.community.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.community.entity.TopicInfo;
import com.zhiyin.dbs.module.community.entity.TopicMedia;
import com.zhiyin.dbs.module.community.mapper.TopicInfoMapper;
import com.zhiyin.dbs.module.community.mapper.TopicMediaMapper;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.dbs.module.community.service.ITopicMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/7/11.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class TopicMediaServiceImpl extends BaseService<TopicMedia> implements ITopicMediaService {

    @Autowired
    TopicMediaMapper topicMediaMapper ;

    @Override
    public BaseMapper<TopicMedia> getBaseMapper() {
        return topicMediaMapper;
    }


}