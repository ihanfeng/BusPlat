package com.zhiyin.dbs.module.common.exception;

/**
 * Created by hg on 2016/7/28.
 */
public class SelNotFoundException extends BizBaseException {
    public SelNotFoundException(String errorCode, String errorInfo) {
        super(errorCode, errorInfo);
    }
}