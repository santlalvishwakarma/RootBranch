package com.web.common.dvo.common;

public abstract class ImplDataValueObject extends DataValueObject {

	private static final long serialVersionUID = 4744044246126968457L;
	private AuditAttributes auditAttributes;// = new AuditAttributes();
	private OperationalAttributes operationalAttributes;// = new
	private ApplicationFlags applicationFlags;

	public OperationalAttributes getOperationalAttributes() {
		if (operationalAttributes == null) {
			operationalAttributes = new OperationalAttributes();
		}
		return operationalAttributes;
	}

	public void setOperationalAttributes(OperationalAttributes operationalAttributes) {
		this.operationalAttributes = operationalAttributes;
	}

	public AuditAttributes getAuditAttributes() {
		if (auditAttributes == null) {
			auditAttributes = new AuditAttributes();
		}
		return auditAttributes;
	}

	public void setAuditAttributes(AuditAttributes auditAttributes) {
		this.auditAttributes = auditAttributes;
	}

	public void setApplicationFlags(ApplicationFlags applicationFlags) {
		this.applicationFlags = applicationFlags;
	}

	public ApplicationFlags getApplicationFlags() {
		if (applicationFlags == null) {
			applicationFlags = new ApplicationFlags();
		}
		return applicationFlags;
	}
}
