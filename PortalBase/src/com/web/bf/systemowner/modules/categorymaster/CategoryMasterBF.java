package com.web.bf.systemowner.modules.categorymaster;

import com.web.bc.systemowner.modules.categorymaster.CategoryMasterBC;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class CategoryMasterBF extends BusinessFacade {

	public CategoryOpr executeSearch(CategoryOpr searchCategoryOpr) throws FrameworkException, BusinessException {
		return new CategoryMasterBC().executeSearch(searchCategoryOpr);
	}

	public CategoryOpr executeSave(CategoryOpr addEditCategoryOpr) throws FrameworkException, BusinessException {
		return new CategoryMasterBC().executeSave(addEditCategoryOpr);
	}

	public CategoryOpr getMappedCategoryLevel(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		return new CategoryMasterBC().getMappedCategoryLevel(addEditCategoryOpr);
	}

}
