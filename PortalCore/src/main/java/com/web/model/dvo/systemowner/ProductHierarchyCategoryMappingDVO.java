package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.HierarchyCategoryMappingDVO;
import com.web.model.dvo.HierarchyDVO;

/**
 * @author NIRAJ
 * 
 */
public class ProductHierarchyCategoryMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 7090855107139766800L;

	private ProductDVO productRecord;
	private HierarchyDVO hierarchyRecord;
	private HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord;

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
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

	public HierarchyCategoryMappingDVO getHierarchyCategoryMappingRecord() {
		if (hierarchyCategoryMappingRecord == null) {
			hierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();
		}
		return hierarchyCategoryMappingRecord;
	}

	public void setHierarchyCategoryMappingRecord(HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord) {
		this.hierarchyCategoryMappingRecord = hierarchyCategoryMappingRecord;
	}

}
