package com.zhiyin.ourchat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyin.dbs.module.common.mapper.BaseMapper;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.frame.idgen.IdGenFactory;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.entity.DialogStatus;
import com.zhiyin.ourchat.mapper.DialogRecordMapper;
import com.zhiyin.ourchat.mapper.DialogStatusMapper;
import com.zhiyin.ourchat.service.IDialogRecordService;
import com.zhiyin.ourchat.service.IDialogStatusService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangqinghui on 2016/8/22.
 */

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class DialogStatusServiceImpl extends BaseService<DialogStatus> implements IDialogStatusService {

    @Autowired
    DialogStatusMapper dialogStatusMapper;


    /**
     * 如果记录不存在，则插入
     * 如果记录存在，则更新
     * @param userId
     * @param partnerId
     * @return
     */
    @Override
    public Boolean updateReadNum(Long userId, Long partnerId) {

        DialogStatus dialogStatus = new DialogStatus();
        dialogStatus.setId(IdGenFactory.genTableId());
        dialogStatus.setUserId(userId);
        dialogStatus.setPartnerId(partnerId);
        dialogStatus.setUnreadNum(1);
        dialogStatus.setUpdateTime(DateTime.now().toDate());
        dialogStatus.setReadTime(DateTime.now().toDate());
        dialogStatus.setCreateTime(DateTime.now().toDate());

        dialogStatusMapper.updateReadNum(dialogStatus);

        return true;
    }

    /**
     * 设置对话读取状态
     * @param userId
     * @param partnerId
     * @return
     */
    @Override
    public Boolean updateReaded(Long userId, Long partnerId) {
        DialogStatus dialogStatus = new DialogStatus();
        dialogStatus.setUserId(userId);
        dialogStatus.setPartnerId(partnerId);
        dialogStatus.setUpdateTime(DateTime.now().toDate());
        dialogStatus.setReadTime(DateTime.now().toDate());
        dialogStatus.setUnreadNum(0);
        dialogStatus.setDelStatus(0);

        dialogStatusMapper.updateReaded(dialogStatus);
        return true;
    }

    @Override
    public DialogStatus selectByPartner(Long userId, Long partnerId) {
        DialogStatus sel = dialogStatusMapper.selectByPartner(userId, partnerId);

        if(sel == null){
            sel = new DialogStatus();
            sel.setUnreadNum(0);
            sel.setUserId(userId);
            sel.setPartnerId(partnerId);
            sel.setUpdateTime(DateTime.now().toDate());
            sel.setReadTime(DateTime.now().toDate());
            sel.setCreateTime(DateTime.now().toDate());
        }
        return sel;
    }


    @Override
    public Integer deleteByUid(Long userId) {
        return dialogStatusMapper.deleteByUid(userId);
    }

    @Override
    public BaseMapper<DialogStatus> getBaseMapper() {
        return dialogStatusMapper;
    }

}
