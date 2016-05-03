package com.web.foundation.exception;

public class FrameworkException extends Exception {
	private static final long serialVersionUID = 1L;

	public FrameworkException() {
		super();
	}

	public FrameworkException(String strErr, Throwable throwable) {
		super(strErr, throwable);
	}

	public FrameworkException(String strErr) {
		super(strErr);
	}

}
