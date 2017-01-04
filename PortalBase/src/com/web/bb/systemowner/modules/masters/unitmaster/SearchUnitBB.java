package com.web.bb.systemowner.modules.masters.unitmaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.unitmaster.UnitMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.UnitOpr;
import com.web.common.dvo.systemowner.UnitDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchUnitBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;
	private OptionsDVO allOptions;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/unitmaster/unitmaster";
	private UnitOpr unitOpr;

	public UnitOpr getUnitOpr() {
		if (unitOpr == null) {
			unitOpr = new UnitOpr();
		}
		return unitOpr;
	}

	public void setUnitOpr(UnitOpr unitOpr) {
		this.unitOpr = unitOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In SearchUnitBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				unitOpr.setUnitList(null);

				UnitOpr unitOprRet = new UnitMasterBF().executeSearch(unitOpr);
				unitOpr.setUnitList(unitOprRet.getUnitList());
				populateData();

			} catch (FrameworkException e) {

				handleException(e, propertiesLocation);
			} catch (BusinessException e) {

				handleException(e, propertiesLocation);
			}

		}
	}

	@Override
	public boolean validateSearch() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		UnitDVO unitRecord = unitOpr.getUnitRecord();
		if (!(validator.validateNull(unitRecord.getCode()) || validator.validateNull(unitRecord.getName())
				|| validator.validateNull(unitRecord.getDescription()) || validator.validateNull(unitRecord
				.getStatusRecord().getCode()))) {
			addToErrorList(propertiesReader.getValueOfKey("all_fields_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		return validateFlag;
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SearchUnitBB :: editDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		UnitOpr unitOprSent = new UnitOpr();
		unitOprSent.setUnitRecord(unitOpr.getUnitAddEditRecord());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, unitOprSent);
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SearchUnitBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new UnitMasterBF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		myLog.debug("In SearchUnitBB :: retrieveData ends ");
	}

	@Override
	public void executeAddRow(ActionEvent event) {
		// TODO Auto-generated method stub

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

	private void populateData() throws FrameworkException, BusinessException {
		HashMap<Boolean, String> yesNoValuesMap = new HashMap<Boolean, String>();
		ArrayList<Object> yesNoList = new UnitMasterBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!unitOpr.getUnitList().isEmpty()) {
			for (UnitDVO unitRecord : unitOpr.getUnitList()) {
				String activeDescription = yesNoValuesMap.get(unitRecord.getActive());
				unitRecord.setActiveDescription(activeDescription);
			}
		}
	}

}
