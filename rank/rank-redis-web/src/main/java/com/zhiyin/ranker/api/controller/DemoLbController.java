package com.zhiyin.ranker.api.controller;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.zhiyin.ranker.api.vo.ContentListenNumRankC2s;
import com.zhiyin.ranker.api.vo.ContentListenNumRankS2c;
import com.zhiyin.ranker.api.vo.ContentListenNumTopC2s;
import com.zhiyin.ranker.api.vo.RankDataS2c;
import com.zhiyin.ranker.api.web.C2sObj;
import com.zhiyin.ranker.api.web.S2cObj;
import com.zhiyin.ranker.api.web.WebResp;
import com.zhiyin.ranker.lb.LeaderData;
import com.zhiyin.ranker.lb.LeaderboardTemplate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hg on 2016/7/15.
 */
@Slf4j
@RestController
//@RequestMapping("/contents/rank")
@RequestMapping("/conts/lisnum/rank")

public class DemoLbController {

    @Autowired
    private LeaderboardTemplate demoLb;

    @ApiOperation(value = "init", nickname = "初始化排名数据", response = S2cObj.class)
    @RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String iniData(
            @Valid @RequestBody C2sObj c2s,
            BindingResult bindingResult) {
//        loadRankDataService.loadContentStat();

        for(int i = 1; i< 10;i++){
            demoLb.rankMember(i+"",i);
        }

        return "succ";
    }

    @ApiOperation(value = " ", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/top/{top}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(@PathVariable("top") Integer top) {

        top = Optional.fromNullable(top).or(20);

        List<RankDataS2c> list = Lists.newArrayList();

        ArrayList<String> tmp = demoLb.top(top);


        for(String name : tmp){
            RankDataS2c rd  = new RankDataS2c();
            rd.setUserId(Long.valueOf(name));
        }

        ContentListenNumRankS2c s2c = new ContentListenNumRankS2c();
        s2c.setList(list);

        return succRet(s2c);
    }

    @ApiOperation(value = " ", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/top", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(
            @Valid @RequestBody ContentListenNumTopC2s c2s,
            BindingResult bindingResult) {

        Integer top = c2s.getTop();
        top = Optional.fromNullable(top).or(20);

        List<RankDataS2c> list = Lists.newArrayList();

        ArrayList<String> tmp = demoLb.top(top);


        for(String name : tmp){
            RankDataS2c rd  = new RankDataS2c();
            rd.setUserId(Long.valueOf(name));
        }

        ContentListenNumRankS2c s2c = new ContentListenNumRankS2c();
        s2c.setList(list);

        return succRet(s2c);
    }

    @ApiOperation(value = "", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/around", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(
            @Valid @RequestBody ContentListenNumRankC2s c2s,
            BindingResult bindingResult) {

        ContentListenNumRankS2c s2c = new ContentListenNumRankS2c();


        Integer around = Optional.fromNullable(c2s.getAround()).or(5);

        List<LeaderData> res = demoLb.aroundMe(c2s.getUserId() + "");

        List<RankDataS2c> rankListS2c = Lists.newArrayList();
        for(LeaderData tmp : res){
            RankDataS2c rd = new RankDataS2c();
            rd.setUserId(Long.valueOf(tmp.getMember()));
            rd.setRankNum((int) tmp.getRank());
            rankListS2c.add(rd);
        }

        s2c.setList(rankListS2c);
        return succRet(s2c);
    }

    public static WebResp<S2cObj> succRet(S2cObj succInfo){
        return new WebResp<>(
                HttpStatus.OK.value(),"" , succInfo);
    }

}
