package com.zhiyin.ourchat.service.impl;

import com.zhiyin.frame.idgen.IdGenFactory;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.mapper.DialogLatestMapper;
import com.zhiyin.ourchat.service.IDialogLatestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@Service
public class DialogLatestServiceImpl implements IDialogLatestService {

    @Autowired
    DialogLatestMapper dialogLatestMapper;

    @Override
    public List<DialogLatest> selectAlllLatest(Long userId ) {
        List<DialogLatest> list = dialogLatestMapper.selectAlllLatest(userId);
        return list;
    }

    @Override
    public List<DialogLatest> selectLatest(Long userId, Long partnerId ) {
        List<DialogLatest> list = dialogLatestMapper.selectLatest(userId , partnerId );
        return list;
    }



    @Override
    public Long insertSelective(DialogLatest record) {

        // 先把旧的信息删除
        dialogLatestMapper.deleteOldLatest(record.getUserId(),record.getPartnerId());


        record.setId( IdGenFactory.genTableId() );
        dialogLatestMapper.insertSelective(record);
        return 1L;
    }


}
