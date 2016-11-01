package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.ProductSkuDVO;

public class SkuOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 4151783329047026630L;
	private ProductSkuDVO productSkuRecord;
	private List<ProductSkuDVO> productSkuList;

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
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
