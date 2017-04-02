package com.web.bb.systemowner.modules.masters.skupropertymaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import com.web.bf.systemowner.modules.masters.skupropertymaster.SkuPropertyBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.opr.systemowner.SkuPropertyOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SkuPropertyBB extends BackingBean {

	private static final long serialVersionUID = 6274039461461189356L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/masters/skupropertymaster/skuproperty";
	private int activeTabIndex;

	public int getActiveTabIndex() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getActiveTabIndex: ");
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get(CommonConstant.ACTIVE_TAB) != null) {
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside retrieveData::: ");
		try {
			HashMap<String, ArrayList<Object>> allOptionsValues = new SkuPropertyBF()
					.getAllOptionsValuesForSkuProperty();

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
	}

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" tabChanged : tab id : " + tabId);

		if (tabId.equals("searchSkuPropertyList")) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.RE_INITIALIZE_OPR,
					new SkuPropertyOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 0);

		} else if (tabId.equals("addEditSkuProperty")) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CommonConstant.ACTIVE_TAB_OPR,
					new SkuPropertyOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		}
	}
}
