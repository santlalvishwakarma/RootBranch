package com.web.bf.systemowner.modules.masters.guestusermaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.guestusermaster.GuestUserMasterBC;
import com.web.common.dvo.opr.systemowner.GuestUserOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class GuestUserMasterBF extends BusinessFacade {

	public HashMap<String, ArrayList<Object>> getAllOptionsValuesForSearch() throws FrameworkException,
			BusinessException {
		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();

		allOptionsValues.put("yesNoList", new OptionsHelperBC().getYesNoList());

		return allOptionsValues;
	}

	public GuestUserOpr executeSearch(GuestUserOpr guestUserOpr) throws FrameworkException, BusinessException {
		return new GuestUserMasterBC().executeSearch(guestUserOpr);
	}

	public GuestUserOpr executeSave(GuestUserOpr guestUserOpr) throws FrameworkException, BusinessException {
		return new GuestUserMasterBC().executeSave(guestUserOpr);
	}
}
