package com.web.bb.systemowner.modules.ordermanagement;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.ordermanagement.OrderMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.RetailOrderOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class EditOrdersBB extends BackingBean {

	private static final long serialVersionUID = 1598546047857812304L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/ordermanagement/ordermanagement";
	private int activeTabIndex;

	private RetailOrderOpr editRetailOrderOpr;

	public int getActiveTabIndex() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getActiveTabIndex: ");
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB) != null) {
			activeTabIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(CommonConstant.ACTIVE_TAB);
		}
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

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

	public String setOrderForEdit() {
		return null;
	}

	public RetailOrderOpr getEditRetailOrderOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			RetailOrderOpr selectedRetailOrderOpr = (RetailOrderOpr) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);
			editRetailOrderOpr.setRetailOrderRecord(selectedRetailOrderOpr.getRetailOrderRecord());
			populateData();
		}
		if (editRetailOrderOpr == null) {
			editRetailOrderOpr = new RetailOrderOpr();
		}
		return editRetailOrderOpr;
	}

	public void setEditRetailOrderOpr(RetailOrderOpr editRetailOrderOpr) {
		this.editRetailOrderOpr = editRetailOrderOpr;
	}

	private void populateData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside populateData: ");

		try {
			editRetailOrderOpr = new OrderMasterBF().getOrderHeaderRecord(editRetailOrderOpr);
			editRetailOrderOpr = new OrderMasterBF().getOrderDetailRecord(editRetailOrderOpr);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

}
