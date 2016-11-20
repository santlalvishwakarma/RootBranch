package com.web.bb.systemowner.modules.hierarchymaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

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

/**
 * @author deepaknaithani
 * 
 */
public class SearchHierarchyMasterBB extends BackingBean {

	private static final long serialVersionUID = 774238267360492006L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/hierarchymaster/hierarchy";

	private HierarchyOpr searchHierarchyOpr;

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		boolean validate = validateSearch();

		if (validate) {
			try {
				searchHierarchyOpr.getHierarchyList().clear();
				new HierarchyMasterBF().executeSearch(searchHierarchyOpr);
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

		boolean isHierarchyName = validator.validateNull(searchHierarchyOpr.getHierarchyRecord().getName());
		boolean isHierarchyCode = validator.validateNull(searchHierarchyOpr.getHierarchyRecord().getCode());

		if (!isHierarchyName && !isHierarchyCode) {
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
				.put(CommonConstant.ACTIVE_TAB_OPR, searchHierarchyOpr);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MY_NAME", "Deepak Naithani");

		myLog.debug(" Hierarchy Id: " + searchHierarchyOpr.getHierarchyRecord().getId());
		myLog.debug(" Hierarchy Code: " + searchHierarchyOpr.getHierarchyRecord().getCode());
		myLog.debug(" Hierarchy Name: " + searchHierarchyOpr.getHierarchyRecord().getName());
		RequestContext.getCurrentInstance().execute("editSelectedHierarchyRecord();");

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

	public HierarchyOpr getSearchHierarchyOpr() {
		if (searchHierarchyOpr == null) {
			searchHierarchyOpr = new HierarchyOpr();
		}
		return searchHierarchyOpr;
	}

	public void setSearchHierarchyOpr(HierarchyOpr searchHierarchyOpr) {
		this.searchHierarchyOpr = searchHierarchyOpr;
	}

}
