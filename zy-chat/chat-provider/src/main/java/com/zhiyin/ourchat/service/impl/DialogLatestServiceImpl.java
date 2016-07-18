package com.zhiyin.ourchat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.frame.idgen.IdGenFactory;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.mapper.DialogLatestMapper;
import com.zhiyin.ourchat.service.IDialogLatestService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DialogLatestServiceImpl extends BaseService<DialogLatest> implements IDialogLatestService {

    @Autowired
    DialogLatestMapper dialogLatestMapper;

    @Override
    public List<DialogLatest> selectByUid(Long userId) {
        List<DialogLatest> list = dialogLatestMapper.selectByUid(userId);
        return list;
    }

    /**
     * 按照时间降序排列
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<DialogLatest> selectByUid(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DialogLatest> list = dialogLatestMapper.selectByUid(userId);
        PageInfo<DialogLatest> page = new PageInfo(list);
        return page;
    }

    @Override
    public List<DialogLatest> selectLatest(Long userId, Long partnerId) {
        List<DialogLatest> list = dialogLatestMapper.selectLatest(userId, partnerId);
        return list;
    }

    @Override
    public BaseMapper<DialogLatest> getBaseMapper() {
        return dialogLatestMapper;
    }

    @Override
    public int insertSelective(DialogLatest record) {

        record.setCreateTime(DateTime.now().toDate());
        record.setUpdateTime(DateTime.now().toDate());

        // 先把旧的信息删除
        dialogLatestMapper.deleteByPartner(record.getUserId(), record.getPartnerId());

        record.setId(IdGenFactory.genTableId());
        record.setIsRead(0);
        dialogLatestMapper.insertSelective(record);
        return 1;
    }

    @Override
    public Integer deleteByUid(Long userId) {
        return dialogLatestMapper.deleteByUid(userId);
    }

    @Override
    public Integer deleteByPartner(Long userId, Long partnerId) {
        return dialogLatestMapper.deleteByPartner(userId, partnerId);
    }


}
