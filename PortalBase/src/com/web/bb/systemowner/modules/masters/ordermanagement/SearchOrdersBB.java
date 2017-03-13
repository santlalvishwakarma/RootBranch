package com.web.bb.systemowner.modules.masters.ordermanagement;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.web.bf.systemowner.modules.masters.ordermanagement.OrderMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.RetailOrderOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SearchOrdersBB extends BackingBean {

	private static final long serialVersionUID = -6444146167898579704L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/masters/ordermanagement/ordermanagement";

	private RetailOrderOpr searchRetailOrderOpr;

	private transient BaseDVOConverter baseDVOConverter;

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		boolean validate = validateSearch();

		if (validate) {
			try {
				searchRetailOrderOpr.getRetailOrderList().clear();
				searchRetailOrderOpr = new OrderMasterBF().executeSearch(searchRetailOrderOpr);
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
				searchRetailOrderOpr);
		myLog.debug(searchRetailOrderOpr.getSelectedRetailOrderRecord().getId());

		RequestContext.getCurrentInstance().execute("editSelectedOrderRecord();");
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

	public RetailOrderOpr getSearchRetailOrderOpr() {
		if (searchRetailOrderOpr == null) {
			searchRetailOrderOpr = new RetailOrderOpr();
		}
		return searchRetailOrderOpr;
	}

	public void setSearchRetailOrderOpr(RetailOrderOpr searchRetailOrderOpr) {
		this.searchRetailOrderOpr = searchRetailOrderOpr;
	}

	public BaseDVOConverter getBaseDVOConverter() {
		if (baseDVOConverter == null) {
			baseDVOConverter = new BaseDVOConverter();
		}
		return baseDVOConverter;
	}

	public void setBaseDVOConverter(BaseDVOConverter baseDVOConverter) {
		this.baseDVOConverter = baseDVOConverter;
	}

}
