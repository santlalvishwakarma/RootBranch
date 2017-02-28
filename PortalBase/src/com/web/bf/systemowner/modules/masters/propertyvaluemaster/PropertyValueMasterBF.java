package com.web.bf.systemowner.modules.masters.propertyvaluemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.systemowner.modules.master.propertyvaluemaster.PropertyValueMasterBC;
import com.web.bc.systemowner.modules.master.sizemaster.SizeMasterBC;
import com.web.bf.systemowner.modules.masters.unitmaster.UnitMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.PropertyValueMappingOpr;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.dvo.systemowner.UnitDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class PropertyValueMasterBF extends BusinessFacade {

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

	public PropertyValueMappingOpr executeSearch(PropertyValueMappingOpr propertyValueMappingOpr)
			throws FrameworkException, BusinessException {
		return new PropertyValueMasterBC().executeSearch(propertyValueMappingOpr);
	}

	public List<Object> getSuggestedSizeRecord(SizeDVO sizeDVO) throws FrameworkException, BusinessException {
		return new SizeMasterBC().getSuggestedSizeRecord(sizeDVO);
	}

	public List<Object> getSuggestedUnitRecord(UnitDVO unitDVO) throws FrameworkException, BusinessException {
		return new UnitMasterBF().getSuggestedUnitRecord(unitDVO);
	}

	public PropertyValueMappingOpr executeSave(PropertyValueMappingOpr propertyValueMappingOpr)
			throws FrameworkException, BusinessException {
		return new PropertyValueMasterBC().executeSave(propertyValueMappingOpr);
	}

}
