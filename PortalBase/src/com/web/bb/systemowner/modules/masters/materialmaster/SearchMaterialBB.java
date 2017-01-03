package com.web.bb.systemowner.modules.masters.materialmaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.materialmaster.MaterialMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.MaterialOpr;
import com.web.common.dvo.systemowner.MaterialDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchMaterialBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;
	private OptionsDVO allOptions;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/materialmaster/materialmaster";
	private MaterialOpr materialOpr;

	public MaterialOpr getMaterialOpr() {
		if (materialOpr == null) {
			materialOpr = new MaterialOpr();
		}
		return materialOpr;
	}

	public void setMaterialOpr(MaterialOpr materialOpr) {
		this.materialOpr = materialOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In SearchMaterialBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				materialOpr.setMaterialList(null);

				MaterialOpr materialOprRet = new MaterialMasterBF().executeSearch(materialOpr);
				materialOpr.setMaterialList(materialOprRet.getMaterialList());
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

		MaterialDVO materialRecord = materialOpr.getMaterialRecord();
		if (!(validator.validateNull(materialRecord.getCode()) || validator.validateNull(materialRecord.getName())
				|| validator.validateNull(materialRecord.getDescription()) || validator.validateNull(materialRecord
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
		myLog.debug("In SearchMaterialBB :: editDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		MaterialOpr materialOprSent = new MaterialOpr();
		materialOprSent.setMaterialRecord(materialOpr.getMaterialAddEditRecord());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, materialOprSent);
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SearchMaterialBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new MaterialMasterBF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		myLog.debug("In SearchMaterialBB :: retrieveData ends ");
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
		ArrayList<Object> yesNoList = new MaterialMasterBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!materialOpr.getMaterialList().isEmpty()) {
			for (MaterialDVO materialRecord : materialOpr.getMaterialList()) {
				String activeDescription = yesNoValuesMap.get(materialRecord.getActive());
				materialRecord.setActiveDescription(activeDescription);
			}
		}
	}

}
