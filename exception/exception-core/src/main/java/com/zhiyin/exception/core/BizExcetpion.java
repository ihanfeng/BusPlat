package com.zhiyin.exception.core;


public class BizExcetpion extends RuntimeException {
	private static final long serialVersionUID = 1L;		
	public BizExcetpion() {
		this(null, null);
	}
	public BizExcetpion(String message, Throwable cause) {
		super(message, cause, true, false);
	}
	
	public BizExcetpion(String message) {
		this(message, null);
	}
	
	public BizExcetpion(Throwable cause) {
		this(null, cause);
	}

}