package com.hg.ratelimiter.filter;

/**
 * Created by wangqinghui on 2016/9/23.
 */
public interface KeyGen {

    public String gen(WebReq webReq);
}
