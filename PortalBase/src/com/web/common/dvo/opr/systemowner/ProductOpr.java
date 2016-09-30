package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
<<<<<<< HEAD
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
=======
import com.web.common.dvo.systemowner.ProductCategoryDVO;
>>>>>>> branch 'master' of https://github.com/santlalvishwakarma/RootBranch.git
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.util.deepcopy.DeepCopy;

public class ProductOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -3311426889260891951L;
	private ProductDVO productRecord;
	private ProductDVO quickEditProductRecord;
	private List<ProductDVO> searchResultList;
	private List<ProductDVO> productDVOList;
<<<<<<< HEAD
	private List<CategoryDVO> productCategoryList;
	private List<HierarchyDVO> productHierarchyList;
	private HierarchyDVO hierarchyRecord;
=======
	private List<ProductCategoryDVO> productCategoryList;
>>>>>>> branch 'master' of https://github.com/santlalvishwakarma/RootBranch.git

	public ProductDVO getQuickEditProductRecord() {
		if (quickEditProductRecord == null) {
			quickEditProductRecord = new ProductDVO();
		}
		return quickEditProductRecord;
	}

	public void setQuickEditProductRecord(ProductDVO quickEditProductRecord) {
		this.quickEditProductRecord = (ProductDVO) DeepCopy.copy(quickEditProductRecord);
	}

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
	}

	public List<ProductDVO> getSearchResultList() {
		if (searchResultList == null) {
			searchResultList = new ArrayList<ProductDVO>();
		}
		return searchResultList;
	}

	public void setSearchResultList(List<ProductDVO> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public List<ProductDVO> getProductDVOList() {
		if (productDVOList == null) {
			productDVOList = new ArrayList<ProductDVO>();
		}
		return productDVOList;
	}

	public void setProductDVOList(List<ProductDVO> productDVOList) {
		this.productDVOList = productDVOList;
	}

	public List<CategoryDVO> getProductCategoryList() {
		if (productCategoryList == null) {
			productCategoryList = new ArrayList<CategoryDVO>();
		}
		return productCategoryList;
	}

	public void setProductCategoryList(List<CategoryDVO> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public List<HierarchyDVO> getProductHierarchyList() {
		if (productHierarchyList == null) {
			productHierarchyList = new ArrayList<HierarchyDVO>();
		}
		return productHierarchyList;
	}

	public void setProductHierarchyList(List<HierarchyDVO> productHierarchyList) {
		this.productHierarchyList = productHierarchyList;
	}

	public HierarchyDVO getHierarchyRecord() {
		if (hierarchyRecord == null) {
			hierarchyRecord = new HierarchyDVO();
		}
		return hierarchyRecord;
	}

	public void setHierarchyRecord(HierarchyDVO hierarchyRecord) {
		this.hierarchyRecord = hierarchyRecord;
	}

}
