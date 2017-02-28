package com.web.bb.systemowner.modules.masters.sizemaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchSizeBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;
	private OptionsDVO allOptions;
	private SizeOpr sizeOpr;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/sizemaster/sizemaster";

	public SizeOpr getSizeOpr() {
		if (sizeOpr == null) {
			sizeOpr = new SizeOpr();
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			sizeOpr.setSizeList(new ArrayList<SizeDVO>());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.RE_INITIALIZE_OPR);
		}
		return sizeOpr;
	}

	public void setSizeOpr(SizeOpr sizeOpr) {
		this.sizeOpr = sizeOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In SearchSizeBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				sizeOpr.setSizeList(null);

				SizeOpr sizeOprRet = new SizeMasterBF().executeSearch(sizeOpr);
				sizeOpr.setSizeList(sizeOprRet.getSizeList());
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

		SizeDVO sizeRecord = sizeOpr.getSizeRecord();
		if (!(validator.validateNull(sizeRecord.getCode()) || validator.validateNull(sizeRecord.getName())
				|| validator.validateNull(sizeRecord.getDescription()) || validator.validateNull(sizeRecord
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

		SizeOpr sizeOprSent = new SizeOpr();
		sizeOprSent.setSizeRecord(sizeOpr.getSelectedSizeRecord());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, sizeOprSent);
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SearchSizeBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new SizeMasterBF().getStatusCodeList());
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
		ArrayList<Object> yesNoList = new SizeMasterBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!getSizeOpr().getSizeList().isEmpty()) {
			for (SizeDVO sizeRecord : sizeOpr.getSizeList()) {
				String activeDescription = yesNoValuesMap.get(sizeRecord.getActive());
				sizeRecord.setActiveDescription(activeDescription);
			}
		}
	}

}
