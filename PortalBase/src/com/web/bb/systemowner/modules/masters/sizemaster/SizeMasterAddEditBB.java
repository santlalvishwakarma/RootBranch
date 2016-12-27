package com.web.bb.systemowner.modules.masters.sizemaster;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.PropertiesReader;

public class SizeMasterAddEditBB extends BackingBean {

	private static final long serialVersionUID = 1365027609281638236L;
	private OptionsDVO allOptions;
	private SizeOpr addEditSizeOpr;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/sizemaster/sizemaster";

	public SizeOpr getAddEditSizeOpr() {
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
				setSuccessMsg(propertiesReader.getValueOfKey("product_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		// TODO Auto-generated method stub
		return false;
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

}
