package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.util.deepcopy.DeepCopy;

public class ProductOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -3311426889260891951L;
	private ProductDVO productRecord;
	private ProductDVO quickEditProductRecord;
	private ArrayList<ProductDVO> searchResultList;
	private ArrayList<ProductDVO> productDVOList;

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

	public ArrayList<ProductDVO> getSearchResultList() {
		if (searchResultList == null) {
			searchResultList = new ArrayList<ProductDVO>();
		}
		return searchResultList;
	}

	public void setSearchResultList(ArrayList<ProductDVO> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public ArrayList<ProductDVO> getProductDVOList() {
		if (productDVOList == null) {
			productDVOList = new ArrayList<ProductDVO>();
		}
		return productDVOList;
	}

	public void setProductDVOList(ArrayList<ProductDVO> productDVOList) {
		this.productDVOList = productDVOList;
	}

}
