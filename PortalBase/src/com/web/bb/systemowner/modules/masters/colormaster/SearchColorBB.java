package com.web.bb.systemowner.modules.masters.colormaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.colormaster.ColorMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.ColorOpr;
import com.web.common.dvo.systemowner.ColorDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchColorBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;
	private OptionsDVO allOptions;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/colormaster/colormaster";
	private ColorOpr colorOpr;

	public ColorOpr getColorOpr() {
		if (colorOpr == null) {
			colorOpr = new ColorOpr();
		}
		return colorOpr;
	}

	public void setColorOpr(ColorOpr colorOpr) {
		this.colorOpr = colorOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In SearchSizeBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				colorOpr.setColorList(null);

				ColorOpr colorOprRet = new ColorMasterBF().executeSearch(colorOpr);
				colorOpr.setColorList(colorOprRet.getColorList());
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

		ColorDVO colorRecord = colorOpr.getColorRecord();
		if (!(validator.validateNull(colorRecord.getCode()) || validator.validateNull(colorRecord.getName())
				|| validator.validateNull(colorRecord.getDescription()) || validator.validateNull(colorRecord
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
		myLog.debug("In SearchSizeBB :: editDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ColorOpr colorOprSent = new ColorOpr();
		colorOprSent.setColorRecord(colorOpr.getColorAddEditRecord());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, colorOprSent);
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SearchSizeBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new ColorMasterBF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		myLog.debug("In SearchSizeBB :: retrieveData ends ");
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
		ArrayList<Object> yesNoList = new ColorMasterBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!colorOpr.getColorList().isEmpty()) {
			for (ColorDVO colorRecord : colorOpr.getColorList()) {
				String activeDescription = yesNoValuesMap.get(colorRecord.getActive());
				colorRecord.setActiveDescription(activeDescription);
			}
		}
	}

}
