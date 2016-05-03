package com.web.foundation.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private Object errorData;

	public BusinessException() {
		super();
	}

	public BusinessException(String strErr, Exception ex) {
		super(strErr, ex);
	}

	public BusinessException(String strErr) {
		super(strErr);
	}

	public Object getErrorData() {
		return errorData;
	}

	public void setErrorData(Object errorData) {
		this.errorData = errorData;
	}

}
