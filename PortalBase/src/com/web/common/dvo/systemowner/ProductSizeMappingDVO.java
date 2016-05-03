package com.web.common.dvo.systemowner;

import java.util.Comparator;

import com.web.common.dvo.common.BaseDVO;

public class ProductSizeMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 1426391250186903766L;

	private ProductSkuDVO productSkuRecord;
	private SizeDVO sizeRecord;

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
	}

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
	}

	public static final Comparator<ProductSizeMappingDVO> SIZE_ASCENDING_ORDER = new Comparator<ProductSizeMappingDVO>() {
		public int compare(ProductSizeMappingDVO p1, ProductSizeMappingDVO p2) {
			// ITSDLogger myLog =
			// TSDLogger.getLogger(this.getClass().getName());
			// myLog.debug("p2 :: " + p2.getPriceForComparison() + " :: p1 ::" +
			// p1.getPriceForComparison());
			return -1
					* (p2.getSizeRecord().getCode().compareTo(p1
							.getSizeRecord().getCode()));
		}
	};

	// USED TO SORT BY LATEST ADDED PRODUCTS, HENCE ON MODIFIED DATE
	public static final Comparator<ProductSizeMappingDVO> SIZE_DESCENDING_ORDER = new Comparator<ProductSizeMappingDVO>() {
		public int compare(ProductSizeMappingDVO p1, ProductSizeMappingDVO p2) {
			// ITSDLogger myLog =
			// TSDLogger.getLogger(this.getClass().getName());
			// myLog.debug("p2 :: " +
			// p2.getAuditAttributes().getLastModifiedDate() + " :: p1 ::"
			// + p1.getAuditAttributes().getLastModifiedDate());
			return p2.getSizeRecord().getCode()
					.compareTo(p1.getSizeRecord().getCode());
		}
	};

}
