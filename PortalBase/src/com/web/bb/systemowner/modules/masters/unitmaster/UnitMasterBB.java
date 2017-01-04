package com.web.bb.systemowner.modules.masters.unitmaster;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.UnitOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class UnitMasterBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;

	private int activeTabIndex;

	public int getActiveTabIndex() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB) != null) {
			activeTabIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB);
		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.ACTIVE_TAB) != null) {
			activeTabIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.ACTIVE_TAB);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(CommonConstant.ACTIVE_TAB);
		}
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" In UnitMasterBB : tabChanged : tab id : " + tabId);

		if (tabId.equals("searchListUnit")) {
			if (!FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.put(CommonConstant.RE_INITIALIZE_OPR, new UnitOpr());
			}
		} else if (tabId.equals("addeditUnit")) {
			if (!FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.put(CommonConstant.ACTIVE_TAB_OPR, new UnitOpr());
			}
		}
	}

	@Override
	public void executeSearch(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSearch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAddRow(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public OptionsDVO getAllOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
		// TODO Auto-generated method stub

	}

}
