package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.common.StatusDVO;

public class MaterialDVO extends BaseDVO {

	private static final long serialVersionUID = 5547801079087933293L;
	private StatusDVO statusRecord;

	public StatusDVO getStatusRecord() {
		if (statusRecord == null) {
			statusRecord = new StatusDVO();
		}
		return statusRecord;
	}

	public void setStatusRecord(StatusDVO statusRecord) {
		this.statusRecord = statusRecord;
	}

}
