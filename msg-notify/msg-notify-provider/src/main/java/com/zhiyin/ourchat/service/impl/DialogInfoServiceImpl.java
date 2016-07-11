package com.zhiyin.ourchat.service.impl;

import com.zhiyin.ad.config.AdTimerConfig;
import com.zhiyin.ad.entity.AudioBoard;
import com.zhiyin.ad.mapper.AudioBoardMapper;
import com.zhiyin.frame.idgen.IdGenFactory;
import com.zhiyin.frame.idgen.TableIdGen;
import com.zhiyin.ourchat.entity.DialogInfo;
import com.zhiyin.ourchat.entity.DialogLatest;
import com.zhiyin.ourchat.entity.DialogRecord;
import com.zhiyin.ourchat.mapper.DialogInfoMapper;
import com.zhiyin.ourchat.service.IDialogInfoService;
import com.zhiyin.ourchat.service.IDialogLatestService;
import com.zhiyin.ourchat.service.IDialogListService;
import com.zhiyin.ourchat.service.IDialogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@Service
public class DialogInfoServiceImpl implements IDialogInfoService {

    static Mapper mapper = new DozerBeanMapper();

    @Autowired
    DialogInfoMapper dialogInfoMapper;

    @Resource
    IDialogLatestService dialogLatestService;

    @Resource
    IDialogRecordService dialogRecordService;

    @Override
    public List<AudioBoard> insertDialog(DialogInfo dialogInfo){

        // 设置最新信息
        DialogLatest latest = mapper.map(dialogInfo, DialogLatest.class);
        latest.setUserId( dialogInfo.getSender() );
        latest.setPartnerId(dialogInfo.getReceiver() );
        dialogLatestService.insertSelective(latest);

        latest.setUserId( dialogInfo.getReceiver() );
        latest.setPartnerId(dialogInfo.getSender() );
        dialogLatestService.insertSelective( latest );


        // 消息列表
        DialogRecord record = mapper.map(dialogInfo, DialogRecord.class);

        record.setUserId(dialogInfo.getReceiver());
        record.setPartnerId(dialogInfo.getSender());
        dialogRecordService.insertSelective(record);

        record.setUserId(dialogInfo.getSender());
        record.setPartnerId(dialogInfo.getReceiver() );
        dialogRecordService.insertSelective(record);

        dialogInfo.setId( IdGenFactory.genTableId() );
        dialogInfoMapper.insertSelective( dialogInfo );

        return null;
    }


}
