package com.web.bf.systemowner.modules.masters.skupropertymaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.skupropertymaster.SkuPropertyBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.systemowner.SkuPropertyOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SkuPropertyBF extends BusinessFacade {

	public HashMap<String, ArrayList<Object>> getAllOptionsValuesForSkuProperty()
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getAllOptionsValuesForSkuProperty::: ");

		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();
		ParameterOpr parameterOpr = new ParameterOpr();

		parameterOpr.getParameterList().add(new Parameter(CommonConstant.ParameterCode.SKU_PROPERTY_TYPE, null));

		parameterOpr = new OptionsHelperBC().getOptionsOnParameterCode(parameterOpr);

		allOptionsValues.put("skuPropertyList",
				parameterOpr.getParameterOptionsMap().get(CommonConstant.ParameterCode.SKU_PROPERTY_TYPE));

		myLog.debug("allOptionsValues::: " + allOptionsValues);
		return allOptionsValues;
	}

	public SkuPropertyOpr executeSearch(SkuPropertyOpr searchSkuPropertyOpr)
			throws FrameworkException, BusinessException {
		return new SkuPropertyBC().executeSearch(searchSkuPropertyOpr);
	}

	public SkuPropertyOpr executeSave(SkuPropertyOpr addEditSkuPropertyOpr)
			throws FrameworkException, BusinessException {
		return new SkuPropertyBC().executeSave(addEditSkuPropertyOpr);
	}

}
