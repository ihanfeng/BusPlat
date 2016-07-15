package com.zhiyin.ourchat.service.impl;

import com.zhiyin.frame.idgen.IdGenFactory;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.mapper.DialogRecordMapper;
import com.zhiyin.ourchat.service.IDialogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */

@Service
public class DialogRecordServiceImpl implements IDialogRecordService {

    @Autowired
    DialogRecordMapper dialogRecordMapper;


    @Override
    public Integer insertSelective(DialogRecord record){
        record.setId(IdGenFactory.genTableId());
        return dialogRecordMapper.insertSelective(record);
    }

    @Override
    public List<DialogRecord> selectByPartner(Long userId, Long partnerId){
        return dialogRecordMapper.selectByPartner(userId,partnerId);
    }



    @Override
    public Integer deleteByPrimaryKey(Long id){
        return dialogRecordMapper.deleteByPrimaryKey( id );
    }

    @Override
    public Integer deleteByUid(Long userId){
        return dialogRecordMapper.deleteByUid( userId );
    }

}
