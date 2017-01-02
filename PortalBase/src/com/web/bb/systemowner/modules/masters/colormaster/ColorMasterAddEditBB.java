package com.web.bb.systemowner.modules.masters.colormaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.colormaster.ColorMasterBF;
import com.web.common.constants.CommonConstant;
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

public class ColorMasterAddEditBB extends BackingBean {

	private static final long serialVersionUID = 1365027609281638236L;
	private OptionsDVO allOptions;
	private ColorOpr addEditColorOpr;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/colormaster/colormaster";

	public ColorOpr getAddEditColorOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			addEditColorOpr = (ColorOpr) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			if (addEditColorOpr == null) {
				addEditColorOpr = (ColorOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.ACTIVE_TAB_OPR);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.remove(CommonConstant.ACTIVE_TAB_OPR);
			}
		}

		if (addEditColorOpr == null) {
			addEditColorOpr = new ColorOpr();
		}
		return addEditColorOpr;
	}

	public void setAddEditColorOpr(ColorOpr addEditColorOpr) {
		this.addEditColorOpr = addEditColorOpr;
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterAddEditBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditColorOpr.getColorRecord().setUserLogin(userLogin);

				addEditColorOpr = new ColorMasterBF().executeSave(addEditColorOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("color_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		ColorDVO colorRecord = addEditColorOpr.getColorRecord();

		String colorCode = colorRecord.getCode();
		String colorName = colorRecord.getName();

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (!validator.validateNull(colorCode)) {
			addToErrorList(propertiesReader.getValueOfKey("color_code_null"));
		}

		if (!validator.validateNull(colorName)) {
			addToErrorList(propertiesReader.getValueOfKey("color_name_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
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
		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}
		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	public void executeAddMore(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterAddEditBB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ColorOpr colorOprSent = new ColorOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, colorOprSent);

	}

}
