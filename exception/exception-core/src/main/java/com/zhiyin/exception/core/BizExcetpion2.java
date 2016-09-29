package com.zhiyin.exception.core;


public class BizExcetpion2 extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BizExcetpion2() {
		this(null);
	}

	public BizExcetpion2(String message) {
		super(message);
	}



}