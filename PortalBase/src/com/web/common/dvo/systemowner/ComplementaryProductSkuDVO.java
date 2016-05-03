package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class ComplementaryProductSkuDVO extends BaseDVO {

	private static final long serialVersionUID = -278644192893523713L;
	private ProductDVO productRecord;

	/**
	 * @return the productRecord
	 */
	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	/**
	 * @param productRecord
	 *            the productRecord to set
	 */
	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
	}

}
