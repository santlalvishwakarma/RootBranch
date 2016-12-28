package com.web.bb.systemowner.modules.skumanagement;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

/**
 * @author NIRAJ
 * 
 */
public class SkuDefinitionBB extends BackingBean {

	private static final long serialVersionUID = -4713529382010106398L;

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
			// RequestContext context = RequestContext.getCurrentInstance();
			// context.execute("addEditProductDefinition();");
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

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" In Product BB : tabChanged : tab id : " + tabId);

		if (tabId.equals("searchListSku")) {
			if (!FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.put(CommonConstant.RE_INITIALIZE_OPR, new SkuOpr());
			}
		} else if (tabId.equals("addEditSku")) {
			if (!FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.put(CommonConstant.ACTIVE_TAB_OPR, new SkuOpr());
			}
		}
	}
}
