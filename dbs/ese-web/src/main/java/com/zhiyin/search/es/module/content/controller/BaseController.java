package com.zhiyin.search.es.module.content.controller;

import com.zhiyin.search.es.web.S2cObj;
import com.zhiyin.search.es.web.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    /**
     * 失败返回值
     * @param failInfo
     * @return
     */
    public static WebResponse<S2cObj> failRet(String failInfo){
        return new WebResponse<>(
                HttpStatus.BAD_REQUEST.value(), failInfo , null);
    }

//	public static ApiResp failRet(String failInfo){
//		return null;
//	}

	public static WebResponse<S2cObj> failReqRet( ){
		return new WebResponse<>(
				HttpStatus.BAD_REQUEST.value(), "请求参数错误" , null);
	}

	public static WebResponse<S2cObj> failRet(int code,String failInfo){
		return new WebResponse<>(
				code, failInfo , null);
	}

    /**
     * 验证失败
     *
     * @return
     */
	public static WebResponse<S2cObj> failAuthRet(){
		return new WebResponse<>(
                HttpStatus.UNAUTHORIZED.value(), "用户信息无效" , null);
	}

    /**
     * 成功返回值
     * @param succInfo
     * @return
     */
    public static WebResponse<S2cObj> succRet(S2cObj succInfo){
        return new WebResponse<>(
                HttpStatus.OK.value(),"" , succInfo);
    }

    public static WebResponse<S2cObj> succRet( ){
        return new WebResponse<>(
                HttpStatus.OK.value(),"" , null);
    }
//	public String processSubjectId(){
//
//	}

}
