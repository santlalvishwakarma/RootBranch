package com.web.bf.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.skumanagement.SkuDefinitionBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class SkuDefinitionBF extends BusinessFacade {

	public SkuOpr executeSearch(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		return new SkuDefinitionBC().executeSearch(skuOpr);
	}

	public ArrayList<Object> getStatusCodeList() throws FrameworkException, BusinessException {
		return new OptionsHelperBC().getStatusCodeListBasedOnParameter(new Parameter(
				CommonConstant.ParameterCode.ACTIVE_INACTIVE_STATUS, null));
	}

	public HashMap<String, ArrayList<Object>> getAllOptionsValuesForSearch() throws FrameworkException,
			BusinessException {
		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();

		allOptionsValues.put("yesNoList", new OptionsHelperBC().getYesNoList());

		return allOptionsValues;
	}

	public SkuOpr getSkuDetails(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		return new SkuDefinitionBC().getSkuDetails(skuOpr);
	}

	public SkuOpr getImageMappingList(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		return new SkuDefinitionBC().getImageMappingList(skuOpr);
	}

	public SkuOpr saveImageMappingList(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		return new SkuDefinitionBC().saveImageMappingList(skuOpr);
	}

	public SkuOpr executeSaveSkuDetails(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		return new SkuDefinitionBC().executeSaveSkuDetails(skuOpr);
	}

}
