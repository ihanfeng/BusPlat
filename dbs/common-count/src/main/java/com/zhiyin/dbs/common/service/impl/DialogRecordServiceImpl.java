package com.zhiyin.dbs.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.common.entity.DialogRecord;
import com.zhiyin.dbs.common.mapper.DialogRecordMapper;
import com.zhiyin.dbs.common.service.IDialogRecordService;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DialogRecordServiceImpl extends BaseService<DialogRecord> implements IDialogRecordService {

    @Autowired
    DialogRecordMapper dialogRecordMapper;

    @Override
    public List<DialogRecord> selectByPartner(Long userId, Long partnerId) {
        return dialogRecordMapper.selectByPartner(userId, partnerId);
    }

    @Override
    public PageInfo<DialogRecord> selectByPartner(Long userId, Long partnerId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DialogRecord> list = dialogRecordMapper.selectByPartner(userId, partnerId);
        PageInfo<DialogRecord> page = new PageInfo(list);
        return page;
    }

    @Override
    public Integer deleteByUid(Long userId) {
        return dialogRecordMapper.deleteByUid(userId);
    }

    @Override
    public BaseMapper<DialogRecord> getBaseMapper() {
        return dialogRecordMapper;
    }

}
