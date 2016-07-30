package com.zhiyin.ranker.api.common.controller;

import com.zhiyin.ranker.api.vo.ReloadListenDataC2s;
import com.zhiyin.ranker.api.web.S2cObj;
import com.zhiyin.ranker.api.web.WebResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "contents/rank")
public class HttpErrorController   {


//   @RequestMapping(value = "/lisnum", method = {RequestMethod.GET, RequestMethod.POST})
//   public WebResp<S2cObj> listnum() {
//      return new WebResp<>(
//              HttpStatus.NOT_FOUND.value(), "访问接口不存在", null);
//   }
   @ApiOperation(value = "重新加载数据", nickname = "ReloadData", response = S2cObj.class)
   @RequestMapping(value = "/reload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public WebResp<S2cObj> reload(
           @Valid @RequestBody ReloadListenDataC2s c2s,
           BindingResult bindingResult) {

      return null;
   }

}