package com.web.foundation.exception;

public class DAOException extends FrameworkException {
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String strErr, Exception ex) {
		super(strErr, ex);
	}

	public DAOException(String strErr) {
		super(strErr);
	}
}
