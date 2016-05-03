package com.web.util;

import java.util.Comparator;

import com.web.common.dvo.common.BaseDVO;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class BaseDVOComparator implements Comparator<BaseDVO> {

	private ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

	public int compare(BaseDVO object1, BaseDVO object2) {

		Long id1 = object1.getId();
		Long id2 = object2.getId();

		myLog.debug("id1:" + id1);
		myLog.debug("id2:" + id2);

		return id1.compareTo(id2);
	}
}
