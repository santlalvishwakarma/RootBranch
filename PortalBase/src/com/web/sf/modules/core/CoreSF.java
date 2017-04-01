package com.web.sf.modules.core;

import java.util.ArrayList;
import java.util.List;

import com.web.bc.systemowner.modules.categorymaster.CategoryMasterBC;
import com.web.bc.systemowner.modules.hierarchymaster.HierarchyMasterBC;
import com.web.bc.systemowner.modules.productskuproperty.ProductSkuPropertyBC;
import com.web.bf.systemowner.modules.masters.unitmaster.UnitMasterBF;
import com.web.bf.systemowner.modules.productmanagement.ProductDefinitionBF;
import com.web.bf.systemowner.modules.websitemaster.WebsiteMasterBF;
import com.web.bf.systemowner.rolemanagement.RoleManagementBF;
import com.web.common.dvo.opr.systemowner.RoleManagementOpr;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.PropertyDVO;
import com.web.common.dvo.systemowner.UnitDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class CoreSF extends BusinessFacade {

	public RoleManagementOpr getAllRoles() throws FrameworkException, BusinessException {
		RoleManagementOpr resultRoleManagementOpr = new RoleManagementBF().getAllRoles(new RoleManagementOpr());
		return resultRoleManagementOpr;
	}

	public WebsiteOpr getAllWebsites(WebsiteOpr websiteOpr) throws FrameworkException, BusinessException {
		WebsiteOpr returnWebsiteOpr = new WebsiteMasterBF().getAllWebsites(websiteOpr);
		return returnWebsiteOpr;
	}

	public List<PropertyDVO> getSuggestedPropertiesBasedOnName(String propertyName) throws FrameworkException,
			BusinessException {
		return new ProductSkuPropertyBC().getSuggestedPropertiesBasedOnName(propertyName);
	}

	public List<CategoryDVO> getAllCategories() throws FrameworkException, BusinessException {
		return new CategoryMasterBC().getAllCategories();
	}

	public ArrayList<Object> getSuggestedHierarchies(HierarchyDVO productHierarchyDVO) throws FrameworkException,
			BusinessException {
		return new HierarchyMasterBC().getSuggestedHierarchies(productHierarchyDVO);
	}

	public ArrayList<Object> getSuggestedProductsList(ProductDVO productDVO) throws FrameworkException,
			BusinessException {
		return new ProductDefinitionBF().getSuggestedProductsList(productDVO);
	}

	public List<Object> getSuggestedUnitRecord(UnitDVO unitDVO) throws FrameworkException, BusinessException {
		return new UnitMasterBF().getSuggestedUnitRecord(unitDVO);
	}
}
