package com.web.bf.systemowner.modules.masters.colormaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.colormaster.ColorMasterBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.ColorOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class ColorMasterBF extends BusinessFacade {

	public HashMap<String, ArrayList<Object>> getAllOptionsValuesForSearch() throws FrameworkException,
			BusinessException {
		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();

		allOptionsValues.put("yesNoList", new OptionsHelperBC().getYesNoList());

		return allOptionsValues;
	}

	public ArrayList<Object> getStatusCodeList() throws FrameworkException, BusinessException {
		return new OptionsHelperBC().getStatusCodeListBasedOnParameter(new Parameter(
				CommonConstant.ParameterCode.ACTIVE_INACTIVE_STATUS, null));
	}

	public ColorOpr executeSearch(ColorOpr colorOpr) throws FrameworkException, BusinessException {
		return new ColorMasterBC().executeSearch(colorOpr);
	}

	public ColorOpr executeSave(ColorOpr addEditColorOpr) throws FrameworkException, BusinessException {
		return new ColorMasterBC().executeSave(addEditColorOpr);
	}

}
