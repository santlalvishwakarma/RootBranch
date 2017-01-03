package com.web.bb.systemowner.modules.masters.materialmaster;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.materialmaster.MaterialMasterBF;
import com.web.common.constants.CommonConstant;
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

public class MaterialMasterAddEditBB extends BackingBean {

	private static final long serialVersionUID = 1365027609281638236L;
	private OptionsDVO allOptions;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/materialmaster/materialmaster";
	private MaterialOpr addEditMaterialOpr;

	public MaterialOpr getAddEditMaterialOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			addEditMaterialOpr = (MaterialOpr) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			if (addEditMaterialOpr == null) {
				addEditMaterialOpr = (MaterialOpr) FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().get(CommonConstant.ACTIVE_TAB_OPR);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.remove(CommonConstant.ACTIVE_TAB_OPR);
			}
		}

		if (addEditMaterialOpr == null) {
			addEditMaterialOpr = new MaterialOpr();
		}
		return addEditMaterialOpr;
	}

	public void setAddEditMaterialOpr(MaterialOpr addEditMaterialOpr) {
		this.addEditMaterialOpr = addEditMaterialOpr;
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
		myLog.debug("In MaterialMasterAddEditBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditMaterialOpr.getMaterialRecord().setUserLogin(userLogin);

				addEditMaterialOpr = new MaterialMasterBF().executeSave(addEditMaterialOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("material_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		MaterialDVO materialRecord = addEditMaterialOpr.getMaterialRecord();

		String materialCode = materialRecord.getCode();
		String materialName = materialRecord.getName();

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (!validator.validateNull(materialCode)) {
			addToErrorList(propertiesReader.getValueOfKey("material_code_null"));
		}

		if (!validator.validateNull(materialName)) {
			addToErrorList(propertiesReader.getValueOfKey("material_name_null"));
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
		myLog.debug("In MaterialMasterAddEditBB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		MaterialOpr materialOprSent = new MaterialOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, materialOprSent);

	}

}
