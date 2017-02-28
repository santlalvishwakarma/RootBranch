package com.web.bf.systemowner.modules.masters.unitmaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.unitmaster.UnitMasterBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.UnitOpr;
import com.web.common.dvo.systemowner.UnitDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class UnitMasterBF extends BusinessFacade {

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

	public UnitOpr executeSearch(UnitOpr unitOpr) throws FrameworkException, BusinessException {
		return new UnitMasterBC().executeSearch(unitOpr);
	}

	public UnitOpr executeSave(UnitOpr addEditUnitOpr) throws FrameworkException, BusinessException {
		return new UnitMasterBC().executeSave(addEditUnitOpr);
	}

	public List<Object> getSuggestedUnitRecord(UnitDVO unitDVO) throws FrameworkException, BusinessException {
		return new UnitMasterBC().getSuggestedUnitRecord(unitDVO);
	}

}
