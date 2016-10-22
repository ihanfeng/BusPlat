package com.zhiyin.dbs.module.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.exception.SelNotFoundException;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.common.util.PageInfoUtil;
import com.zhiyin.dbs.module.community.config.CommunityCacheKey;
import com.zhiyin.dbs.module.community.entity.TopicInfo;
import com.zhiyin.dbs.module.community.mapper.TopicInfoMapper;
import com.zhiyin.dbs.module.community.service.ITopicInfoService;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Service
//@CacheConfig(cacheNames = {CommunityCacheKey.TopicInfoKey}) // 默认cache名称
@com.alibaba.dubbo.config.annotation.Service(protocol = { "dubbo" })
public class TopicInfoServiceImpl extends BaseService<TopicInfo> implements ITopicInfoService {

    @Autowired
    TopicInfoMapper topicInfoMapper;

    @Override
    public BaseMapper<TopicInfo> getBaseMapper() {
        return topicInfoMapper;
    }

    @Override
    public int deleteByIdOwner(Long id, Long userId) {
        int re = topicInfoMapper.deleteByIdOwner(id,userId);

        return re;
    }

//    @Cacheable
    @Override
    public TopicInfo selectById(Long id){
        return super.selectById(id);
    }

    @Deprecated
    @Override
    public PageInfo<TopicInfo> selectAllAndOrder(int pageNum, int pageSize,String sortby) {
        PageHelper.startPage(pageNum,pageSize);
        List<TopicInfo> info = getBaseMapper().selectAllAndOrder(sortby);

        PageInfo<TopicInfo> page = new PageInfo(info);

        return page;
    }

//    @Cacheable
    @Override
    public PageInfo<TopicInfo> selectAllAndOrder(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize(), PageInfoUtil.defaultOrderBy(pageInfo)  );
        List<TopicInfo> info = getBaseMapper().selectAll();
        PageInfo<TopicInfo> page = new PageInfo(info);
        return page;
    }

//    @Cacheable
    @Override
    public PageInfo<TopicInfo> selectByUserId(Long userId, PageInfo pageInfo) {

        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize(), PageInfoUtil.defaultOrderBy(pageInfo) );
        List<TopicInfo> info = topicInfoMapper.selectByUserId( userId );
        PageInfo<TopicInfo> page = new PageInfo(info);

        return page;
    }

//    @Cacheable
    @Override
    public PageInfo<TopicInfo> selectByAddrId(Long addrId, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize(), PageInfoUtil.defaultOrderBy(pageInfo) );
        List<TopicInfo> info = topicInfoMapper.selectByAddrId(addrId );
        PageInfo<TopicInfo> page = new PageInfo(info);
        return page;
    }

    @Override
    public PageInfo<TopicInfo> selectByAreaId(Long areaId, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize(), PageInfoUtil.defaultOrderBy(pageInfo) );
        List<TopicInfo> info = topicInfoMapper.selectByAreaId(areaId );
        PageInfo<TopicInfo> page = new PageInfo(info);
        return page;
    }

    /**
     * 更新点赞数目时有并发问题
     */
    @Override
    public Integer updateThumb(Long topicId,Integer num){

        // 加锁
        topicInfoMapper.selectForUpdate(topicId);

        TopicInfo topic = this.selectByPrimaryKey(topicId);
        if(topic == null){
            throw new SelNotFoundException("","更新话题不存在");
        }
        TopicInfo tmp = new TopicInfo();
        tmp.setId(topicId);
        tmp.setThumbNum( topic.getThumbNum() + num );

        this.updateByPrimaryKeySelective(tmp);
        return 1;
    }

    /**
     * 增加阅读数量
     * @param topicId
     * @return
     */
    @Override
    public Integer updateIncBrowse(Long topicId, Long userId){
        Integer ret = topicInfoMapper.updateIncBrowse(topicId);
        return ret;
    }

    /**
     * 增加评论数量
     * @param topicId
     * @param userId
     * @return
     */
    @Override
    public Integer updateIncComment(Long topicId, Long userId) {
        Integer ret = topicInfoMapper.updateIncComment(topicId);
        return ret;
    }


}