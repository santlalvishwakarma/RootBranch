package com.web.bb.systemowner.modules.categorymaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import com.web.bf.systemowner.modules.categorymaster.CategoryMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchCategoryBB extends BackingBean {

	private static final long serialVersionUID = -4709634562840005770L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/categorymaster/category";
	private int activeTabIndex;

	private CategoryOpr searchCategoryOpr;

	public int getActiveTabIndex() {
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

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" tabChanged : tab id : " + tabId);

		if (tabId.equals("searchListCategory")) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.RE_INITIALIZE_OPR, new CategoryOpr());
			// RequestContext.getCurrentInstance().execute("searchCustomerPurchaseBill();");

		} else if (tabId.equals("addeditCategory")) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.ACTIVE_TAB_OPR, new CategoryOpr());
			// RequestContext.getCurrentInstance().execute("addEditCustomerPurchaseBill();");
		}
	}

	public CategoryOpr getSearchCategoryOpr() {
		if (searchCategoryOpr == null) {
			searchCategoryOpr = new CategoryOpr();
		}
		return searchCategoryOpr;
	}

	public void setSearchCategoryOpr(CategoryOpr searchCategoryOpr) {
		this.searchCategoryOpr = searchCategoryOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		boolean validate = validateSearch();

		if (validate) {
			try {
				searchCategoryOpr.setCategoryList(null);
				new CategoryMasterBF().executeSearch(searchCategoryOpr);
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

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		boolean isCategoryName = validator.validateNull(searchCategoryOpr.getCategoryRecord().getName());
		boolean isCategoryCode = validator.validateNull(searchCategoryOpr.getCategoryRecord().getCode());

		if (!isCategoryName && !isCategoryCode) {
			addToErrorList(propertiesReader.getValueOfKey("all_fields_null"));
			validate = false;
		}

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
		myLog.debug(" editDetails starts: ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, searchCategoryOpr);

		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CommonConstant.ACTIVE_TAB,
		// 1);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, searchCategoryOpr);
		myLog.debug(" Category Id: " + searchCategoryOpr.getCategoryRecord().getId());
		myLog.debug(" Category Code: " + searchCategoryOpr.getCategoryRecord().getCode());
		myLog.debug(" Category Name: " + searchCategoryOpr.getCategoryRecord().getName());
		RequestContext.getCurrentInstance().execute("editSelectedCategoryRecord();");
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

	public void clearFields() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" clearFields starts: ");

		searchCategoryOpr.getCategoryRecord().setName("");
		searchCategoryOpr.getCategoryRecord().setCode("");
	}
}
