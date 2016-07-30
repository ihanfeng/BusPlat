package com.zhiyin.ranker.api.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhiyin.dbs.module.user.entity.UserInfo;
import com.zhiyin.dbs.module.user.service.IUserInfoService;
import com.zhiyin.ranker.api.common.RankServerFactory;
import com.zhiyin.ranker.api.vo.ContentListenNumRankC2s;
import com.zhiyin.ranker.api.vo.ContentListenNumRankS2c;
import com.zhiyin.ranker.api.vo.RankDataS2c;
import com.zhiyin.ranker.api.web.S2cObj;
import com.zhiyin.ranker.api.web.WebResp;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hq.rank.core.RankData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hg on 2016/7/15.
 */
@Slf4j
@RestController
@RequestMapping("/contents/rank")
public class DialogController {

    @com.alibaba.dubbo.config.annotation.Reference
    IUserInfoService userInfoService;

//    @Autowired
//    private ILoadRankDataService loadRankDataService;

    @ApiOperation(value = " ", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/cnum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(
            @Valid @RequestBody ContentListenNumRankC2s c2s,
            BindingResult bindingResult) {
        UserInfo user = userInfoService.selectById( c2s.getUserId() );
        if(user== null){
            return null;
        }
        List<RankData> res = RankServerFactory.rankService.getRankDatasAroundId(RankServerFactory.ContentListenNumRank, user.getGid(), 2, 2);
        log.info(JSON.toJSONString(res));

        List<RankDataS2c> rankListS2c = Lists.newArrayList();
        for(RankData tmp : res){
            RankDataS2c rd = new RankDataS2c();
            UserInfo u = userInfoService.selectByGid( tmp.getId() );
            if(u== null){
                continue;
            }
            rd.setUserId( u.getId() );
            rankListS2c.add(rd);
        }

        ContentListenNumRankS2c s2c = new ContentListenNumRankS2c();
        s2c.setList(rankListS2c);
        return succRet(s2c);
    }

    public static WebResp<S2cObj> succRet(S2cObj succInfo){
        return new WebResp<>(
                HttpStatus.OK.value(),"" , succInfo);
    }

}