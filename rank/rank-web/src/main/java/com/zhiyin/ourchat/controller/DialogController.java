package com.zhiyin.ourchat.controller;

import com.zhiyin.ourchat.vo.ReloadListenDataC2s;
import com.zhiyin.ourchat.web.S2cObj;
import com.zhiyin.ourchat.web.WebResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by hg on 2016/7/15.
 */
@RestController
@RequestMapping("/contents/listen/rank")
public class ContentController {


    @ApiOperation(value = "重新加载数据", nickname = "ReloadData", response = S2cObj.class)
    @RequestMapping(value = "/reload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResp<S2cObj> reload(
            @Valid @RequestBody ReloadListenDataC2s c2s,
            BindingResult bindingResult) {

        return null;
    }

}
