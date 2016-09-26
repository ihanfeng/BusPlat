package com.zhiyin.dbs.module.community.service.impl;

import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.community.entity.TopicThumb;
import com.zhiyin.dbs.module.community.mapper.TopicThumbMapper;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import com.zhiyin.dbs.module.community.service.ITopicThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class TopicThumbServiceImpl extends BaseService<TopicThumb> implements ITopicThumbService {

    @Autowired
    TopicThumbMapper topicThumbMapper ;

    @Resource
    ITopicInfoService topicInfoService;

    @Override
    public BaseMapper<TopicThumb> getBaseMapper() {
        return topicThumbMapper;
    }

    //TODO 并发更新有问题，需要采用redis
    @Override
    public Integer updateThumb(Long topicId, Long userId){

        TopicThumb topicThumb = new TopicThumb();
        topicThumb.setUserId(userId);
        topicThumb.setTopicId(topicId);

        // 用户如果已经点赞，则取消点赞
        TopicThumb tmp = this.selectByTopicUser(topicId, userId);
        if( tmp == null){
            this.insertSelective(topicThumb);
            topicInfoService.updateThumb(topicId,1);
        }else{
            tmp.setDelStatus(1);
            this.updateByPrimaryKeySelective(tmp);
            topicInfoService.updateThumb(topicId,-1);
        }
        return  1;
    }

    @Override
    public TopicThumb selectByTopicUser(Long topicId,Long userId){
        return topicThumbMapper.selectByTopicUser(topicId,userId);
    }

    /**
     * 是否点赞
     * @param topicId
     * @param userId
     * @return
     */
    @Override
    public Boolean selectIsThumb(Long topicId, Long userId){
        TopicThumb sel = topicThumbMapper.selectByTopicUser(topicId, userId);
        if(sel != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Long> selectThumbers(Long topicId) {
        return topicThumbMapper.selectThumbers(topicId);
    }

    @Override
    public List<Long> selectLatestThumbers(Long topicId) {
        return topicThumbMapper.selectLatestThumbers(topicId);
    }

}