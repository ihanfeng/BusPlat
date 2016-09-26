package com.hg.ratelimiter.filter;

/**
 * Created by wangqinghui on 2016/9/23.
 */
public class DefaultKeyGen implements KeyGen {


    @Override
    public String gen(WebReq webReq) {
        return webReq.toString();
    }
}
