package com.web.bf.systemowner.modules.publishtohomecategory;

import java.util.ArrayList;
import java.util.List;

import com.web.bc.systemowner.modules.productmanagement.ProductDefinitionBC;
import com.web.bc.systemowner.modules.publishtohomecategory.PublishToHomeCategoryBC;
import com.web.common.dvo.opr.systemowner.PublishToHomeCategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.sf.modules.core.CoreSF;

public class PublishToHomeCategoryBF extends BusinessFacade {

	public PublishToHomeCategoryOpr getPublishToHomeCategories() throws FrameworkException, BusinessException {
		return new PublishToHomeCategoryBC().getPublishToHomeCategories();
	}

	public ArrayList<Object> getSuggestedHierarchies(HierarchyDVO productHierarchyDVO) throws FrameworkException,
			BusinessException {
		return new CoreSF().getSuggestedHierarchies(productHierarchyDVO);
	}

	public List<CategoryDVO> getAllCategories() throws FrameworkException, BusinessException {
		return new CoreSF().getAllCategories();
	}

	public List<Object> getSuggestedCategoriesBasedOnCategoryAndLevel(
			HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO) throws FrameworkException, BusinessException {
		return new ProductDefinitionBC().getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);
	}

	public PublishToHomeCategoryOpr executeSavePublishCategory(PublishToHomeCategoryOpr publishToHomeCategoryOpr)
			throws FrameworkException, BusinessException {
		return new PublishToHomeCategoryBC().executeSavePublishCategory(publishToHomeCategoryOpr);
	}

}
