package com.hg.ratelimiter.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangqinghui on 2016/9/23.
 */
@Getter
@Setter
public class WebReq {

    private String ip;
    private String method;
    private String path;

    private String token;


    public WebReq(HttpServletRequest request){

        this.ip = request.getRequestURI();

        this.method = request.getMethod();
//        this.path = request.getURI().getPath();
    }

}
