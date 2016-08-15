package com.zhiyin.ranker.api.controller;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.zhiyin.ranker.api.vo.*;
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
import java.util.List;

/**
 * Created by hg on 2016/7/15.
 */
@Slf4j
@RestController
@RequestMapping("/rank")
public class DemoLbController {

    @Autowired
    private LeaderboardTemplate demoLb;

    @ApiOperation(value = "init", nickname = "初始化排名数据", response = S2cObj.class)
    @RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String iniData(
            @Valid @RequestBody C2sObj c2s,
            BindingResult bindingResult) {

        for(int i = 1; i< 10;i++){
            demoLb.rankMember(i+"",i);
        }

        return "succ";
    }

    public WebResp<S2cObj> top(Integer top){

        top = Optional.fromNullable(top).or(20);

        List<RankDataS2c> list = Lists.newArrayList();

//        ArrayList<String> tmp = demoLb.top(top);
//        for(String name : tmp){
//            RankDataS2c rd  = new RankDataS2c();
//            rd.setUserId(Long.valueOf(name));
//            list.add(rd);
//        }
        List<LeaderData> tmp = demoLb.topData(top);


        BaseTopS2c s2c = new BaseTopS2c();
        s2c.setList(conv(tmp));

        return succRet(s2c);
    }

    @ApiOperation(value = " ", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/top/{top}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(@PathVariable("top") Integer top) {
        return top(top);
    }

    @ApiOperation(value = " ", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/top", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(
            @Valid @RequestBody BaseTopC2s c2s,
            BindingResult bindingResult) {
        Integer top = c2s.getTop();
        return top(top);
    }

    @ApiOperation(value = "", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/around/{uid}/{around}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> aroundG(@PathVariable("uid") Long uid,@PathVariable("around") Integer around) {

        return around( uid,around );
    }

    @ApiOperation(value = "", nickname = "", response = S2cObj.class)
    @RequestMapping(value = "/around", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> list(
            @Valid @RequestBody BaseAroundC2s c2s,
            BindingResult bindingResult) {

        return around(c2s.getUserId(),c2s.getAround());
    }

    public WebResp<S2cObj> around(Long uid, Integer around){

        BaseAroundS2c s2c = new BaseAroundS2c();

        Integer aroundV = Optional.fromNullable(around).or(5);

        List<LeaderData> res = demoLb.aroundMe(uid + "",aroundV);

        s2c.setList(conv(res));
        return succRet(s2c);
    }



    public static List<RankDataS2c> conv(List<LeaderData> list){


        List<RankDataS2c> rankListS2c = Lists.newArrayList();
        for(LeaderData tmp : list){
            RankDataS2c rd = new RankDataS2c();
            rd.setUserId(Long.valueOf(tmp.getMember()));
            rd.setRankNum((int) tmp.getRank());
            rankListS2c.add(rd);
        }

        return rankListS2c;
    }

    public static WebResp<S2cObj> succRet(S2cObj succInfo){
        return new WebResp<>(
                HttpStatus.OK.value(),"" , succInfo);
    }

}
