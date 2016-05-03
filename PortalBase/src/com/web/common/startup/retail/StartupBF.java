package com.web.common.startup.retail;

import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class StartupBF extends BusinessFacade {
	public StartupOpr getMenuMapping() throws FrameworkException, BusinessException {
		return new StartupBC().getMenuMapping();
	}

	public OptionsDVO getAllOptions(OptionsDVO options) {
		// OptionsHelperBC optionsHelperBC = new OptionsHelperBC();
		// OptionsDVO optionsDVO = new OptionsDVO();
		// HashMap<String, ArrayList<SelectItem>> allOptionsMap = new
		// HashMap<String, ArrayList<SelectItem>>();
		// allOptionsMap = optionsHelperBC.getAllHierarchies(new HashMap());
		// optionsDVO.getAllOptionsValues().put(CommonConstant.HIERARCHY_LIST,
		// allOptionsMap.get(CommonConstant.HIERARCHY_LIST));
		//
		// HashMap<String, HashMap<Long, HierarchyDVO>> allHierarchies =
		// optionsHelperBC
		// .getAllHierarchiesList(new HashMap());
		// optionsDVO.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.ALL_HIERARCHIES_LIST,
		// allHierarchies.get(CommonConstant.ALL_HIERARCHIES_LIST));

		return null;
	}

}
