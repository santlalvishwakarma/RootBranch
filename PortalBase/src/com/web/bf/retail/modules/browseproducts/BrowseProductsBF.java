package com.web.bf.retail.modules.browseproducts;

import com.web.bc.retail.modules.browseproducts.BrowseProductsBC;
import com.web.common.dvo.opr.retail.BrowseProductsOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class BrowseProductsBF extends BusinessFacade {

	// public BrowseProductsOpr searchMappingOnCategoryCodes(BrowseProductsOpr
	// searchParameters)
	// throws FrameworkException, BusinessException {
	// BrowseProductsBC browseProductsBC = new BrowseProductsBC();
	// BrowseProductsOpr browseProductsOpr = new BrowseProductsOpr();
	// browseProductsOpr =
	// browseProductsBC.searchMappingOnCategoryCodes(searchParameters);
	// browseProductsOpr =
	// browseProductsBC.searchUserComments(browseProductsOpr);
	// browseProductsOpr =
	// browseProductsBC.searchCategoryMappingImages(browseProductsOpr);
	// return browseProductsOpr;
	// }
	//
	// public BrowseProductsOpr searchUserComments(BrowseProductsOpr
	// searchParameters) throws FrameworkException,
	// BusinessException {
	// return new BrowseProductsBC().searchUserComments(searchParameters);
	// }
	//
	// public BrowseProductsOpr searchUserCommentsForCatalog(BrowseProductsOpr
	// searchParameters)
	// throws FrameworkException, BusinessException {
	// BrowseProductsBC browseProductsBC = new BrowseProductsBC();
	// BrowseProductsOpr browseProductsOpr = new BrowseProductsOpr();
	// browseProductsOpr =
	// browseProductsBC.searchUserCommentsForCatalog(searchParameters);
	// browseProductsOpr =
	// browseProductsBC.searchCategoryMappingImages(searchParameters);
	// // return new
	// // BrowseProductsBC().searchUserCommentsForCatalog(searchParameters);
	// return browseProductsOpr;
	// }

	public BrowseProductsOpr getCountryDependentData(BrowseProductsOpr browseProductsOpr) throws FrameworkException {
		return new BrowseProductsBC().getCountryDependantData(browseProductsOpr);
	}

	public BrowseProductsOpr searchProductsOnCategoryCodes(BrowseProductsOpr searchBrowseProductsOpr)
			throws FrameworkException, BusinessException {
		return new BrowseProductsBC().searchProductsOnCategoryCodes(searchBrowseProductsOpr);
	}

	public BrowseProductsOpr searchSubCategory(BrowseProductsOpr searchBrowseProductsOpr) throws FrameworkException,
			BusinessException {
		return new BrowseProductsBC().searchSubCategory(searchBrowseProductsOpr);
	}

}
