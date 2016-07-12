package com.zhiyin.dbs.module.common.exception;

public class UpdateFailException extends RuntimeException {
	String errorcode;
	String errorInfo;

	public UpdateFailException(String errorcode, String errorInfo) {
		this.errorcode = errorcode;
		this.errorInfo = errorInfo;
	}

	public String toString() {
		return errorcode;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
