package com.zhiyin.dbs.module.common.exception;

/**
 * Created by hg on 2016/7/28.
 */
public class BizBaseException extends BaseException {
    public BizBaseException(String errorCode, String errorInfo) {
        super(errorCode, errorInfo);
    }
}