package com.web.common.dvo.common;

public abstract class OperationalDataValueObject extends DataValueObject {

	private static final long serialVersionUID = -7476461505802502192L;
	private ApplicationFlags applicationFlags;

	public ApplicationFlags getApplicationFlags() {
		if (applicationFlags == null) {
			applicationFlags = new ApplicationFlags();
		}
		return applicationFlags;
	}

	public void setApplicationFlags(ApplicationFlags applicationFlags) {
		this.applicationFlags = applicationFlags;
	}
}
