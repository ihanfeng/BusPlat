package com.zhiyin.sms.sender.kalos;

public class SmsException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 6417641452178955756L;

	public SmsException() {
		super();
	}

	public SmsException(String message) {
		super(message);
	}

	public SmsException(Throwable cause) {
		super(cause);
	}

	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}
}