package com.web.bf.systemowner.modules.hierarchymaster;

import com.web.bc.systemowner.modules.hierarchymaster.HierarchyMasterBC;
import com.web.common.dvo.opr.systemowner.HierarchyOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class HierarchyMasterBF extends BusinessFacade {

	public HierarchyOpr executeSave(HierarchyOpr addEditHierarchyOpr) throws FrameworkException, BusinessException {
		return new HierarchyMasterBC().executeSave(addEditHierarchyOpr);
	}

	public HierarchyOpr executeSearch(HierarchyOpr searchHierarchyOpr) throws FrameworkException, BusinessException {
		return new HierarchyMasterBC().executeSearch(searchHierarchyOpr);
	}

}
