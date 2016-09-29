package com.web.common.dvo.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

/**
 * @author NIRAJ
 * 
 */

public class CategoryLevelDVO extends BaseDVO {

	private static final long serialVersionUID = 4900768993826543652L;

	private String comments;

	private ArrayList<ProductCategoryPropertiesMappingDVO> productCategoryPropertiesMappingList;

	private String categoryLevels;

	private Parameter allocationBasedOn;

	private String billComments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ArrayList<ProductCategoryPropertiesMappingDVO> getProductCategoryPropertiesMappingList() {
		if (productCategoryPropertiesMappingList == null) {
			productCategoryPropertiesMappingList = new ArrayList<ProductCategoryPropertiesMappingDVO>();
		}
		return productCategoryPropertiesMappingList;
	}

	public void setProductCategoryPropertiesMappingList(
			ArrayList<ProductCategoryPropertiesMappingDVO> productCategoryPropertiesMappingList) {
		this.productCategoryPropertiesMappingList = productCategoryPropertiesMappingList;
	}

	public String getCategoryLevels() {
		return categoryLevels;
	}

	public void setCategoryLevels(String categoryLevels) {
		this.categoryLevels = categoryLevels;
	}

	public Parameter getAllocationBasedOn() {
		if (allocationBasedOn == null) {
			allocationBasedOn = new Parameter();
		}
		return allocationBasedOn;
	}

	public void setAllocationBasedOn(Parameter allocationBasedOn) {
		this.allocationBasedOn = allocationBasedOn;
	}

	public String getBillComments() {
		return billComments;
	}

	public void setBillComments(String billComments) {
		this.billComments = billComments;
	}

}
