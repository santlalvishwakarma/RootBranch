package com.web.bb.systemowner.modules.hierarchymaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import com.web.bf.systemowner.modules.hierarchymaster.HierarchyMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.HierarchyOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class AddEditHierarchyMasterBB extends BackingBean {

	private static final long serialVersionUID = -207056909000545895L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/hierarchymaster/hierarchy";
	private int activeTabIndex;

	private HierarchyOpr addEditHierarchyOpr;

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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" executeSave starts: ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		boolean validate = validateSave();

		if (validate) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditHierarchyOpr.getHierarchyRecord().setUserLogin(userLogin);
				addEditHierarchyOpr = new HierarchyMasterBF().executeSave(addEditHierarchyOpr);
				setSuccessMsg(propertiesReader.getValueOfKey("hierarchy_save"));
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	@Override
	public boolean validateSave() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside validateSearch: ");

		boolean validate = true;

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		boolean isHierarchyName = validator.validateNull(addEditHierarchyOpr.getHierarchyRecord().getName());
		boolean isHierarchyCode = validator.validateNull(addEditHierarchyOpr.getHierarchyRecord().getCode());

		if (!isHierarchyCode) {
			addToErrorList(propertiesReader.getValueOfKey("hierarchy_code_null"));
			validate = false;
		}

		if (!isHierarchyName) {
			addToErrorList(propertiesReader.getValueOfKey("hierarchy_name_null"));
			validate = false;
		}

		return validate;
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

	public String setHierarchyForEdit() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" setHierarchyForEdit starts: ");

		myLog.debug("My name is "
				+ FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("MY_NAME"));

		return null;
	}

	public void resetPage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" resetPage starts: ");

	}

	public HierarchyOpr getAddEditHierarchyOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getAddEditHierarchyOpr: ");
		if (addEditHierarchyOpr == null) {
			addEditHierarchyOpr = new HierarchyOpr();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			HierarchyOpr selectedAddEditHierarchyOpr = (HierarchyOpr) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);
			addEditHierarchyOpr.setHierarchyRecord(selectedAddEditHierarchyOpr.getSelectedhierarchyRecord());
		}
		return addEditHierarchyOpr;
	}

	public void setAddEditHierarchyOpr(HierarchyOpr addEditHierarchyOpr) {
		this.addEditHierarchyOpr = addEditHierarchyOpr;
	}

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" tabChanged : tab id : " + tabId);

		if (tabId.equals("searchListHierarchy")) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(CommonConstant.RE_INITIALIZE_OPR, new HierarchyOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 0);
			// RequestContext.getCurrentInstance().execute("searchCustomerPurchaseBill();");

		} else if (tabId.equals("addeditHierarchy")) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(CommonConstant.ACTIVE_TAB_OPR, new HierarchyOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
			// RequestContext.getCurrentInstance().execute("addEditCustomerPurchaseBill();");
		}
	}

}
