package com.web.bf.systemowner.modules.masters.ordermanagement;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.ordermanagement.OrderMasterBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.systemowner.RetailOrderOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class OrderMasterBF extends BusinessFacade {

	public RetailOrderOpr executeSearch(RetailOrderOpr searchRetailOrderOpr) throws FrameworkException,
			BusinessException {
		return new OrderMasterBC().executeSearch(searchRetailOrderOpr);
	}

	public HashMap<String, ArrayList<Object>> getAllOptionsValuesForOrder() throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getAllOptionsValuesForOrder::: ");

		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();
		ParameterOpr parameterOpr = new ParameterOpr();

		parameterOpr.getParameterList().add(new Parameter(CommonConstant.ParameterCode.PAYMENT_STATUS, null));
		parameterOpr.getParameterList().add(new Parameter(CommonConstant.ParameterCode.ORDER_STATUS, null));

		parameterOpr = new OptionsHelperBC().getOptionsOnParameterCode(parameterOpr);

		allOptionsValues.put("paymentStatusList",
				parameterOpr.getParameterOptionsMap().get(CommonConstant.ParameterCode.PAYMENT_STATUS));
		allOptionsValues.put("orderStatusList",
				parameterOpr.getParameterOptionsMap().get(CommonConstant.ParameterCode.ORDER_STATUS));
		myLog.debug("allOptionsValues::: " + allOptionsValues);
		return allOptionsValues;
	}

	public RetailOrderOpr getOrderHeaderRecord(RetailOrderOpr editRetailOrderOpr) throws FrameworkException,
			BusinessException {
		return new OrderMasterBC().getOrderHeaderRecord(editRetailOrderOpr);
	}

	public RetailOrderOpr getOrderDetailRecord(RetailOrderOpr editRetailOrderOpr) throws FrameworkException,
			BusinessException {
		return new OrderMasterBC().getOrderDetailRecord(editRetailOrderOpr);
	}

}
