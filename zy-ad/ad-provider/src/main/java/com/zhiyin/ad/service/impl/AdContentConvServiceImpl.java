package com.zhiyin.ad.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhiyin.ad.entity.AdAudioDetail;
import com.zhiyin.ad.entity.AdBasicInfo;
import com.zhiyin.ad.entity.AdCgMap;
import com.zhiyin.ad.service.IAdAudioDetailService;
import com.zhiyin.ad.service.IAdBasicInfoService;
import com.zhiyin.ad.service.IAdCgMapService;
import com.zhiyin.ad.service.IAdContentConvService;
import com.zhiyin.dbs.module.common.service.impl.BaseService;
import com.zhiyin.dbs.module.content.entity.BasicContent;
import com.zhiyin.dbs.module.content.entity.ContentGroup;
import com.zhiyin.dbs.module.content.entity.ContentInfo;
import com.zhiyin.dbs.module.content.service.IBasicContentService;
import com.zhiyin.dbs.module.content.service.IContentGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 将广告添加到内容
 * Created by hg on 2016/7/19.
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AdContentConvServiceImpl implements IAdContentConvService {

    IAdAudioDetailService adAudioDetailService;
    IAdBasicInfoService adBasicInfoService;
    IContentGroupService contentGroupService;
    IBasicContentService basicContentService;

    IAdCgMapService adCgMapService;

    public void shelfOnAddContent(Long adId){

        AdBasicInfo ad = adBasicInfoService.selectById(adId);
        if(ad == null){
            log.error("ad is null,id:{}",adId);
            return;
        }

        List<AdAudioDetail> audios = adAudioDetailService.selectByAd(adId);
        for(AdAudioDetail tmp : audios){
            ContentGroup cg = new ContentGroup();
            cg.setTitle( tmp.getTitle() );
            cg.getType(  );
            cg.setRoleId( tmp.getRoleId() );

            Long cgId = contentGroupService.insertSelectiveGet(cg);
            if(cgId <= 0){
                log.error("add cg error. {}", JSON.toJSONString(cg));
            }

            BasicContent bc = new BasicContent();
            bc.setTitle( tmp.getTitle() );
            bc.setArticleId(cgId);
            bc.setSavePath(tmp.getSavePath() );
            basicContentService.insertSelectiveGet(bc);

            // 添加广告音频和内容组映射关系
            AdCgMap adCgMap = new AdCgMap();
            adCgMap.setAdAudioId( tmp.getId() );
            adCgMap.setCgId( cg.getId() );
            adCgMapService.insertSelectiveGet(adCgMap);


        }


    }

}
