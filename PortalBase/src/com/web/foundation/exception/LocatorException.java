package com.web.foundation.exception;

public class LocatorException extends FrameworkException {
	private static final long serialVersionUID = 1L;

	public LocatorException() {
		super();
	}

	public LocatorException(String strErr, Exception ex) {
		super(strErr, ex);
	}

	public LocatorException(String strErr) {
		super(strErr);
	}

}
