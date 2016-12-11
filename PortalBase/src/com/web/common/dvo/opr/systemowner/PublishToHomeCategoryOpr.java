package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.PublishToHomeCategoryDVO;

public class PublishToHomeCategoryOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 3218332574357930611L;

	private List<PublishToHomeCategoryDVO> publishToHomeCategoryList;
	private PublishToHomeCategoryDVO publishToHomeCategoryRecord;

	public List<PublishToHomeCategoryDVO> getPublishToHomeCategoryList() {
		if (publishToHomeCategoryList == null) {
			publishToHomeCategoryList = new ArrayList<PublishToHomeCategoryDVO>();
		}
		return publishToHomeCategoryList;
	}

	public void setPublishToHomeCategoryList(List<PublishToHomeCategoryDVO> publishToHomeCategoryList) {
		this.publishToHomeCategoryList = publishToHomeCategoryList;
	}

	public PublishToHomeCategoryDVO getPublishToHomeCategoryRecord() {
		if (publishToHomeCategoryRecord == null) {
			publishToHomeCategoryRecord = new PublishToHomeCategoryDVO();
		}
		return publishToHomeCategoryRecord;
	}

	public void setPublishToHomeCategoryRecord(PublishToHomeCategoryDVO publishToHomeCategoryRecord) {
		this.publishToHomeCategoryRecord = publishToHomeCategoryRecord;
	}

}
