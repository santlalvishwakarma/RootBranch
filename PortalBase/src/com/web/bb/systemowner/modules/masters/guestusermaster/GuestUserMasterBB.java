package com.web.bb.systemowner.modules.masters.guestusermaster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.web.bf.systemowner.modules.masters.guestusermaster.GuestUserMasterBF;
import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.GuestUserOpr;
import com.web.common.dvo.retail.modules.GuestUserDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.sf.modules.core.CoreSF;
import com.web.util.PropertiesReader;

public class GuestUserMasterBB extends BackingBean {

	private static final long serialVersionUID = 4390729765398510000L;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/guestusermaster/guestusermaster";
	private GuestUserOpr guestUserOpr;

	public GuestUserOpr getGuestUserOpr() {
		if (guestUserOpr == null) {
			guestUserOpr = new GuestUserOpr();
		}
		return guestUserOpr;
	}

	public void setGuestUserOpr(GuestUserOpr guestUserOpr) {
		this.guestUserOpr = guestUserOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In GuestUserMasterBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				guestUserOpr.setGuestUserList(null);

				GuestUserOpr guestUserOprRet = new GuestUserMasterBF().executeSearch(guestUserOpr);
				guestUserOpr.setGuestUserList(guestUserOprRet.getGuestUserList());
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

		GuestUserDVO unitRecord = guestUserOpr.getGuestUserRecord();
		if (!(validator.validateNull(unitRecord.getName()) || validator.validateNull(unitRecord.getEmailId()) || validator
				.validateNull(unitRecord.getPhoneNumber()))) {
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In GuestUserMasterBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				guestUserOpr.getSelectedUserRecord().setUserLogin(userLogin);

				guestUserOpr = new GuestUserMasterBF().executeSave(guestUserOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				executeSearch(null);
				RequestContext.getCurrentInstance().execute("updateSearchList();");
				setSuccessMsg(propertiesReader.getValueOfKey("guest_user_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		GuestUserDVO guestUserRecord = guestUserOpr.getSelectedUserRecord();

		String name = guestUserRecord.getName();
		String emailId = guestUserRecord.getEmailId();
		String phone = guestUserRecord.getPhoneNumber();

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (!validator.validateNull(name)) {
			addToErrorList(propertiesReader.getValueOfKey("name_null"));
		}

		if (!validator.validateNull(emailId)) {
			addToErrorList(propertiesReader.getValueOfKey("email_null"));
		}

		if (!validator.validateNull(phone)) {
			addToErrorList(propertiesReader.getValueOfKey("phone_null"));
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In GuestUserMasterBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new CoreSF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		myLog.debug("In GuestUserMasterBB :: retrieveData ends ");
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
		// TODO Auto-generated method stub

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
		if (!guestUserOpr.getGuestUserList().isEmpty()) {
			for (GuestUserDVO guestUserRecord : guestUserOpr.getGuestUserList()) {
				String activeDescription = yesNoValuesMap.get(guestUserRecord.getActive());
				guestUserRecord.setActiveDescription(activeDescription);
			}
		}
	}

}
