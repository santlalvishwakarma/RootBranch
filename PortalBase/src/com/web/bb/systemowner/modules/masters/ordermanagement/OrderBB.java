package com.web.bb.systemowner.modules.masters.ordermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.ordermanagement.OrderMasterBF;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class OrderBB extends BackingBean {

	private static final long serialVersionUID = 9049755834003859419L;
	private String propertiesLocation = "/com/web/bb/systemowner/modules/ordermanagement/ordermanagement";

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
	}

	@Override
	public boolean validateSave() {
		return false;
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside retrieveData::: ");
		try {
			HashMap<String, ArrayList<Object>> allOptionsValues = new OrderMasterBF().getAllOptionsValuesForOrder();
			myLog.debug(" 1:::" + allOptionsValues);
			getAllOptions().setAllOptionsValues(allOptionsValues);
			myLog.debug("ends retrieveData: ");
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	@Override
	public OptionsDVO getAllOptions() {
		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}
		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
		this.allOptions = allOptions;
	}

}
