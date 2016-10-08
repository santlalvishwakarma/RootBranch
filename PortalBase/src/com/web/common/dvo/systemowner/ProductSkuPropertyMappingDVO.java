package com.web.common.dvo.systemowner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.web.common.dvo.common.BaseDVO;

/**
 * @author NIRAJ
 * 
 */
public class ProductSkuPropertyMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -3480793044311366324L;

	private PropertyDVO productPropertiesRecord;
	private String propertyValueText;
	private Float propertyValueFloat;
	private Date propertyValueDate;
	private UomDVO uomRecord;
	private ArrayList<ProductPropertyValueDVO> productPropertyValueList;
	private ProductPropertyValueDVO productPropertyValueDVO;
	private boolean editable;

	public static final Comparator<ProductSkuPropertyMappingDVO> SEQUENCE_ASCENDING_ORDER = new Comparator<ProductSkuPropertyMappingDVO>() {
		public int compare(ProductSkuPropertyMappingDVO p1, ProductSkuPropertyMappingDVO p2) {
			return -1
					* (p2.getProductPropertiesRecord().getPropertySequence().compareTo(p1.getProductPropertiesRecord()
							.getPropertySequence()));
		}
	};

	public PropertyDVO getProductPropertiesRecord() {
		if (productPropertiesRecord == null) {
			productPropertiesRecord = new PropertyDVO();
		}
		return productPropertiesRecord;
	}

	public void setProductPropertiesRecord(PropertyDVO productPropertiesRecord) {
		this.productPropertiesRecord = productPropertiesRecord;
	}

	/**
	 * @return the propertyValueText
	 */
	public String getPropertyValueText() {
		return propertyValueText;
	}

	/**
	 * @param propertyValueText
	 *            the propertyValueText to set
	 */
	public void setPropertyValueText(String propertyValueText) {
		this.propertyValueText = propertyValueText;
	}

	/**
	 * @return the propertyValueFloat
	 */
	public Float getPropertyValueFloat() {
		return propertyValueFloat;
	}

	/**
	 * @param propertyValueFloat
	 *            the propertyValueFloat to set
	 */
	public void setPropertyValueFloat(Float propertyValueFloat) {
		this.propertyValueFloat = propertyValueFloat;
	}

	/**
	 * @return the propertyValueDate
	 */
	public Date getPropertyValueDate() {
		return propertyValueDate;
	}

	/**
	 * @param propertyValueDate
	 *            the propertyValueDate to set
	 */
	public void setPropertyValueDate(Date propertyValueDate) {
		this.propertyValueDate = propertyValueDate;
	}

	/**
	 * @return the uomRecord
	 */
	public UomDVO getUomRecord() {
		if (uomRecord == null) {
			uomRecord = new UomDVO();
		}
		return uomRecord;
	}

	/**
	 * @param uomRecord
	 *            the uomRecord to set
	 */
	public void setUomRecord(UomDVO uomRecord) {
		this.uomRecord = uomRecord;
	}

	public ArrayList<ProductPropertyValueDVO> getProductPropertyValueList() {
		if (productPropertyValueList == null) {
			productPropertyValueList = new ArrayList<ProductPropertyValueDVO>();
		}
		return productPropertyValueList;
	}

	public void setProductPropertyValueList(ArrayList<ProductPropertyValueDVO> productPropertyValueList) {
		this.productPropertyValueList = productPropertyValueList;
	}

	public ProductPropertyValueDVO getProductPropertyValueDVO() {
		if (productPropertyValueDVO == null) {
			productPropertyValueDVO = new ProductPropertyValueDVO();
		}
		return productPropertyValueDVO;
	}

	public void setProductPropertyValueDVO(ProductPropertyValueDVO productPropertyValueDVO) {
		this.productPropertyValueDVO = productPropertyValueDVO;
	}

	public ArrayList<SelectItem> getProductPropertyValueAsSelect() {
		ArrayList<SelectItem> productPropertyValueAsSelect = new ArrayList<SelectItem>();
		if (productPropertyValueList != null) {
			for (int i = 0; i < productPropertyValueList.size(); i++) {
				productPropertyValueAsSelect.add(new SelectItem(productPropertyValueList.get(i).getPropertyValue(),
						productPropertyValueList.get(i).getPropertyValue()));
			}
		}
		return productPropertyValueAsSelect;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
