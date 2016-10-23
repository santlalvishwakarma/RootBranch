package com.web.common.dvo.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.StatusDVO;

public class ProductDVO extends BaseDVO {

	private static final long serialVersionUID = -7161473032765983215L;
	private StatusDVO statusRecord;
	private HierarchyDVO hierarchyRecord;
	private ProductSkuDVO productSkuRecord;
	private List<ProductSkuDVO> productSkuList;

	public StatusDVO getStatusRecord() {
		if (statusRecord == null) {
			statusRecord = new StatusDVO();
		}
		return statusRecord;
	}

	public void setStatusRecord(StatusDVO statusRecord) {
		this.statusRecord = statusRecord;
	}

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
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

	public List<ProductSkuDVO> getProductSkuList() {
		if (productSkuList == null) {
			productSkuList = new ArrayList<ProductSkuDVO>();
		}
		return productSkuList;
	}

	public void setProductSkuList(List<ProductSkuDVO> productSkuList) {
		this.productSkuList = productSkuList;
	}

}
