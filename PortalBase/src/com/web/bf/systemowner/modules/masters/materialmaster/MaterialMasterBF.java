package com.web.bf.systemowner.modules.masters.materialmaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.materialmaster.MaterialMasterBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.MaterialOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class MaterialMasterBF extends BusinessFacade {

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

	public MaterialOpr executeSearch(MaterialOpr materialOpr) throws FrameworkException, BusinessException {
		return new MaterialMasterBC().executeSearch(materialOpr);
	}

	public MaterialOpr executeSave(MaterialOpr addEditmaterialOpr) throws FrameworkException, BusinessException {
		return new MaterialMasterBC().executeSave(addEditmaterialOpr);
	}

}
