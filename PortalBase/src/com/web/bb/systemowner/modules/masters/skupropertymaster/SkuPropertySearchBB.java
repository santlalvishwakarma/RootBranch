package com.web.bb.systemowner.modules.masters.skupropertymaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.ordermanagement.OrderMasterBF;
import com.web.bf.systemowner.modules.masters.skupropertymaster.SkuPropertyBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SkuPropertyOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SkuPropertySearchBB extends BackingBean {

	private static final long serialVersionUID = 3459709729230282558L;

	private SkuPropertyOpr searchSkuPropertyOpr;
	private String propertiesLocation = "/com/web/bb/systemowner/modules/masters/skupropertymaster/skuproperty";

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		boolean isValidateSearch = validateSearch();

		if (isValidateSearch) {
			try {
				searchSkuPropertyOpr.getSkuPropertyList().clear();
				searchSkuPropertyOpr = new SkuPropertyBF().executeSearch(searchSkuPropertyOpr);
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSearch() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside validateSearch: ");

		boolean validate = true;

		return validate;
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside editDetails: ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CommonConstant.ACTIVE_TAB_OPR,
				searchSkuPropertyOpr);
		myLog.debug("Selected sku property record: " + searchSkuPropertyOpr.getSelectedSkuPropertyRecord().getId());

		// RequestContext.getCurrentInstance().execute("");

	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	@Override
	public OptionsDVO getAllOptions() {
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	public SkuPropertyOpr getSearchSkuPropertyOpr() {
		if (searchSkuPropertyOpr == null) {
			searchSkuPropertyOpr = new SkuPropertyOpr();
		}
		return searchSkuPropertyOpr;
	}

	public void setSearchSkuPropertyOpr(SkuPropertyOpr searchSkuPropertyOpr) {
		this.searchSkuPropertyOpr = searchSkuPropertyOpr;
	}

}
