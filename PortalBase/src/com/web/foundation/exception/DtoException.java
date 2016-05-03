package com.web.foundation.exception;

public class DtoException extends FrameworkException {

	private static final long serialVersionUID = 1L;

	public DtoException() {
		super();
	}

	public DtoException(String strErr, Exception ex) {
		super(strErr, ex);
	}

	public DtoException(String strErr) {
		super(strErr);
	}
}
