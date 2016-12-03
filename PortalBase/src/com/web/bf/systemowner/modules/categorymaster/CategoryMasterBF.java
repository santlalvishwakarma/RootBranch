package com.web.bf.systemowner.modules.categorymaster;

import java.util.ArrayList;
import java.util.List;

import com.web.bc.systemowner.modules.categorymaster.CategoryMasterBC;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
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

	public CategoryOpr getPublishToHomeCategoryList() throws BusinessException, FrameworkException {
		return new CategoryMasterBC().getPublishToHomeCategoryList();
	}

	public List<Object> getSuggestedCategories(CategoryDVO categoryDVO) throws BusinessException, FrameworkException {
		return new CategoryMasterBC().getSuggestedCategories(categoryDVO);
	}

	public CategoryOpr executeSavePublishCategory(CategoryOpr addEditCategoryOpr) throws BusinessException,
			FrameworkException {
		return new CategoryMasterBC().executeSavePublishCategory(addEditCategoryOpr);
	}

	public CategoryOpr mapCategoryToLevels(CategoryOpr addEditCategoryOpr) throws FrameworkException, BusinessException {
		return new CategoryMasterBC().mapCategoryToLevels(addEditCategoryOpr);
	}

	public ArrayList<Object> getMappedCategoryLevels(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		return new CategoryMasterBC().getMappedCategoryLevels(addEditCategoryOpr);
	}

	public ArrayList<Object> getSuggestedHierarchyMappingForCode(CategoryOpr addEditCategoryOpr, String query)
			throws FrameworkException, BusinessException {
		return new CategoryMasterBC().getSuggestedHierarchyMappingForCode(addEditCategoryOpr, query);
	}

	public CategoryOpr mapHierarchyToCategory(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		return new CategoryMasterBC().mapHierarchyToCategory(addEditCategoryOpr);
	}

	public CategoryOpr getMappedHierarchyCategory(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		return new CategoryMasterBC().getMappedHierarchyCategory(addEditCategoryOpr);
	}

}
