package com.zhiyin.dbs.module.common.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    String errorCode;
    String errorInfo;
}


