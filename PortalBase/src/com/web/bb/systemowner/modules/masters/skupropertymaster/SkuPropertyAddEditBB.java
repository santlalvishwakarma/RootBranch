package com.web.bb.systemowner.modules.masters.skupropertymaster;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.skupropertymaster.SkuPropertyBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SkuPropertyOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SkuPropertyAddEditBB extends BackingBean {

	private static final long serialVersionUID = 1019425507741943563L;
	private String propertiesLocation = "/com/web/bb/systemowner/modules/masters/skupropertymaster/skuproperty";

	private SkuPropertyOpr addEditSkuPropertyOpr;

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
		myLog.debug("Inside executeSave: ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		boolean isValidateSave = validateSave();

		if (isValidateSave) {
			try {
				addEditSkuPropertyOpr.getSkuPropertyList().clear();
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditSkuPropertyOpr.getSkuPropertyRecord().setUserLogin(userLogin);
				addEditSkuPropertyOpr = new SkuPropertyBF().executeSave(addEditSkuPropertyOpr);
				setSuccessMsg(propertiesReader.getValueOfKey("sku_property_save"));
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
		myLog.debug("Inside validateSave: ");

		boolean validate = true;

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		getErrorList().clear();
		setSuccessMsg("");

		boolean isPropertyName = validator.validateNull(addEditSkuPropertyOpr.getSkuPropertyRecord().getName());
		boolean isPropertyCode = validator.validateNull(addEditSkuPropertyOpr.getSkuPropertyRecord().getCode());
		Integer parameterID = addEditSkuPropertyOpr.getSkuPropertyRecord().getSkuPropertyType().getParameterID();
		boolean isSkuPropertyType = validator.validateIntegerObjectNull(parameterID);

		if (!isPropertyCode) {
			addToErrorList(propertiesReader.getValueOfKey("property_code_null"));
			validate = false;
		}

		if (!isPropertyName) {
			addToErrorList(propertiesReader.getValueOfKey("property_name_null"));
			validate = false;
		}

		if (!isSkuPropertyType || parameterID.equals(0)) {
			addToErrorList(propertiesReader.getValueOfKey("property_type_null"));
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

	public SkuPropertyOpr getAddEditSkuPropertyOpr() {
		if (addEditSkuPropertyOpr == null) {
			addEditSkuPropertyOpr = new SkuPropertyOpr();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			SkuPropertyOpr selectedAddEditSkuPropertyOpr = (SkuPropertyOpr) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);
			addEditSkuPropertyOpr.setSkuPropertyRecord(selectedAddEditSkuPropertyOpr.getSelectedSkuPropertyRecord());
		}
		return addEditSkuPropertyOpr;
	}

	public void setAddEditSkuPropertyOpr(SkuPropertyOpr addEditSkuPropertyOpr) {
		this.addEditSkuPropertyOpr = addEditSkuPropertyOpr;
	}

}
