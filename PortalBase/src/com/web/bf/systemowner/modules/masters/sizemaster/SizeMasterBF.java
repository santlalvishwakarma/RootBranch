package com.web.bf.systemowner.modules.masters.sizemaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.sizemaster.SizeMasterBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class SizeMasterBF extends BusinessFacade {

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

	public SizeOpr executeSearch(SizeOpr sizeOpr) throws FrameworkException, BusinessException {
		return new SizeMasterBC().executeSearch(sizeOpr);
	}

	public SizeOpr executeSave(SizeOpr addEditSizeOpr) throws FrameworkException, BusinessException {
		return new SizeMasterBC().executeSave(addEditSizeOpr);
	}

}
