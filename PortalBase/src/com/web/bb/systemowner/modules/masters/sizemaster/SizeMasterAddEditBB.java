package com.web.bb.systemowner.modules.masters.sizemaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SizeMasterAddEditBB extends BackingBean {

	private static final long serialVersionUID = 1365027609281638236L;
	private OptionsDVO allOptions;
	private SizeOpr addEditSizeOpr;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/sizemaster/sizemaster";

	public SizeOpr getAddEditSizeOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			addEditSizeOpr = (SizeOpr) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			if (addEditSizeOpr == null) {
				addEditSizeOpr = (SizeOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.ACTIVE_TAB_OPR);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.remove(CommonConstant.ACTIVE_TAB_OPR);
			}
		}

		if (addEditSizeOpr == null) {
			addEditSizeOpr = new SizeOpr();
		}
		return addEditSizeOpr;
	}

	public void setAddEditSizeOpr(SizeOpr addEditSizeOpr) {
		this.addEditSizeOpr = addEditSizeOpr;
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
		myLog.debug("In SizeMasterAddEditBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditSizeOpr.getSizeRecord().setUserLogin(userLogin);

				addEditSizeOpr = new SizeMasterBF().executeSave(addEditSizeOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("size_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		SizeDVO sizeRecord = addEditSizeOpr.getSizeRecord();

		String sizeCode = sizeRecord.getCode();
		String sizeName = sizeRecord.getName();

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (!validator.validateNull(sizeCode)) {
			addToErrorList(propertiesReader.getValueOfKey("size_code_null"));
		}

		if (!validator.validateNull(sizeName)) {
			addToErrorList(propertiesReader.getValueOfKey("size_name_null"));
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
		myLog.debug("In SizeMasterAddEditBB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		SizeOpr sizeOprSent = new SizeOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, sizeOprSent);

	}

}
