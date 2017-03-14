package com.web.bb.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.web.bf.systemowner.modules.skumanagement.SkuDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.ColorDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.MaterialDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuColorMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.ProductSkuImageMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuMaterialMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuSizeMappingDVO;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.dvo.util.File;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.sf.modules.core.CoreSF;
import com.web.util.PropertiesReader;
import com.web.util.deepcopy.DeepCopy;

/**
 * @author NIRAJ
 * 
 */
public class SkuDefinitionAddEditBB extends BackingBean {

	private static final long serialVersionUID = 2279688254567743485L;
	private String propertiesLocation = "com/web/bb/systemowner/modules/skumanagement/skudefinition";
	private SkuOpr skuOpr;

	private transient BaseDVOConverter baseDVOConverter;
	private OptionsDVO allOptions;
	private boolean renderForProduct;
	private boolean renderForSKU;
	private boolean renderPopupLinks;
	private Boolean disableApproveButton;
	private Boolean disableActivateButton;
	private Boolean disableModifyButton;
	private Boolean disableFields;

	// private List<Object> uomListForAutoSuggest;
	private List<Object> hierarchyListForAutoSuggest;
	private List<Object> itemList;
	private List<Object> processVariationList;
	private List<Object> skuList;
	private Map<Long, BaseDVO> copyOfDataMap;

	private boolean renderGetBackToAutoSku = false;
	private ProductSkuImageMappingDVO productSkuImageMappingRecord;

	public SkuOpr getSkuOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			skuOpr = (SkuOpr) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

			// retrive initial data
			retrieveData();
		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			if (skuOpr == null) {
				skuOpr = (SkuOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.ACTIVE_TAB_OPR);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.remove(CommonConstant.ACTIVE_TAB_OPR);
			}
			// retrive initial data
			retrieveData();
		}

		if (skuOpr == null) {
			skuOpr = new SkuOpr();
		}
		return skuOpr;
	}

	public void setSkuOpr(SkuOpr skuOpr) {
		this.skuOpr = skuOpr;
	}

	public BaseDVOConverter getBaseDVOConverter() {
		if (baseDVOConverter == null) {
			baseDVOConverter = new BaseDVOConverter();
		}
		return baseDVOConverter;
	}

	public void setBaseDVOConverter(BaseDVOConverter baseDVOConverter) {
		this.baseDVOConverter = baseDVOConverter;
	}

	public boolean isRenderForProduct() {
		return renderForProduct;
	}

	public void setRenderForProduct(boolean renderForProduct) {
		this.renderForProduct = renderForProduct;
	}

	public boolean isRenderForSKU() {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("EDIT_DETAILS") != null) {
			String editDetails = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("EDIT_DETAILS");
			if (editDetails != null && editDetails.equals("SKU")) {
				renderForSKU = true;
			}
		}
		return renderForSKU;
	}

	public void setRenderForSKU(boolean renderForSKU) {
		this.renderForSKU = renderForSKU;
	}

	public boolean isRenderPopupLinks() {
		return renderPopupLinks;
	}

	public void setRenderPopupLinks(boolean renderPopupLinks) {
		this.renderPopupLinks = renderPopupLinks;
	}

	public Boolean getDisableApproveButton() {
		return disableApproveButton;
	}

	public void setDisableApproveButton(Boolean disableApproveButton) {
		this.disableApproveButton = disableApproveButton;
	}

	public Boolean getDisableActivateButton() {
		return disableActivateButton;
	}

	public void setDisableActivateButton(Boolean disableActivateButton) {
		this.disableActivateButton = disableActivateButton;
	}

	public Boolean getDisableModifyButton() {
		return disableModifyButton;
	}

	public void setDisableModifyButton(Boolean disableModifyButton) {
		this.disableModifyButton = disableModifyButton;
	}

	public Boolean getDisableFields() {
		return disableFields;
	}

	public void setDisableFields(Boolean disableFields) {
		this.disableFields = disableFields;
	}

	// public List<Object> getUomListForAutoSuggest() {
	// if (uomListForAutoSuggest == null) {
	// uomListForAutoSuggest = new ArrayList<Object>();
	// }
	// return uomListForAutoSuggest;
	// }
	//
	// public void setUomListForAutoSuggest(List<Object> uomListForAutoSuggest)
	// {
	// this.uomListForAutoSuggest = uomListForAutoSuggest;
	// }

	public List<Object> getHierarchyListForAutoSuggest() {
		if (hierarchyListForAutoSuggest == null) {
			hierarchyListForAutoSuggest = new ArrayList<Object>();
		}
		return hierarchyListForAutoSuggest;
	}

	public void setHierarchyListForAutoSuggest(List<Object> hierarchyListForAutoSuggest) {
		this.hierarchyListForAutoSuggest = hierarchyListForAutoSuggest;
	}

	public List<Object> getItemList() {
		if (itemList == null) {
			itemList = new ArrayList<Object>();
		}
		return itemList;
	}

	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}

	public List<Object> getProcessVariationList() {
		if (processVariationList == null) {
			processVariationList = new ArrayList<Object>();
		}
		return processVariationList;
	}

	public void setProcessVariationList(List<Object> processVariationList) {
		this.processVariationList = processVariationList;
	}

	public List<Object> getSkuList() {
		if (skuList == null) {
			skuList = new ArrayList<Object>();
		}
		return skuList;
	}

	public void setSkuList(List<Object> skuList) {
		this.skuList = skuList;
	}

	public Map<Long, BaseDVO> getCopyOfDataMap() {
		if (copyOfDataMap == null) {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
		}
		return copyOfDataMap;
	}

	public void setCopyOfDataMap(Map<Long, BaseDVO> copyOfDataMap) {
		this.copyOfDataMap = copyOfDataMap;
	}

	public boolean isRenderGetBackToAutoSku() {
		return renderGetBackToAutoSku;
	}

	public void setRenderGetBackToAutoSku(boolean renderGetBackToAutoSku) {
		this.renderGetBackToAutoSku = renderGetBackToAutoSku;
	}

	public ProductSkuImageMappingDVO getProductSkuImageMappingRecord() {
		if (productSkuImageMappingRecord == null) {
			productSkuImageMappingRecord = new ProductSkuImageMappingDVO();
		}
		return productSkuImageMappingRecord;
	}

	public void setProductSkuImageMappingRecord(ProductSkuImageMappingDVO productSkuImageMappingRecord) {
		this.productSkuImageMappingRecord = productSkuImageMappingRecord;
	}

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				skuOpr.getProductSkuRecord().setUserLogin(userLogin);

				populatePriceForSku(skuOpr);

				skuOpr = new SkuDefinitionBF().executeSaveSkuDetails(skuOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("product_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	private void populatePriceForSku(SkuOpr skuOpr) {
		Float discountAmount = skuOpr.getProductSkuRecord().getDiscountAmount();
		Float discountPercent = skuOpr.getProductSkuRecord().getDiscountPercent();
		Float basePrice = skuOpr.getProductSkuRecord().getBasePrice();

		if (discountAmount != null && discountAmount > 0) {
			skuOpr.getProductSkuRecord().setFinalBasePrice(basePrice - discountAmount);
		} else if (discountPercent != null && discountPercent > 0) {
			Float percentAmount = (basePrice * discountPercent) / 100;
			skuOpr.getProductSkuRecord().setFinalBasePrice(basePrice - percentAmount);
		} else {
			skuOpr.getProductSkuRecord().setFinalBasePrice(basePrice);
		}
	}

	@Override
	public boolean validateSave() {

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		// product validations
		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();
		String skuCode = productSkuRecord.getCode();
		String skuName = productSkuRecord.getName();
		Float basePrice = productSkuRecord.getBasePrice();
		Float discountAmount = productSkuRecord.getDiscountAmount();
		Float discountPercent = productSkuRecord.getDiscountPercent();
		Long productId = productSkuRecord.getProductRecord().getId();

		if (!validator.validateNull(skuCode)) {
			addToErrorList(propertiesReader.getValueOfKey("sku_code_null"));
		}
		if (skuCode != null && !validator.validateCharsWithoutSpecialChars(skuCode)) {
			addToErrorList(propertiesReader.getValueOfKey("sku_code_invalid"));
		}

		if (!validator.validateNull(skuName)) {
			addToErrorList(propertiesReader.getValueOfKey("sku_name_null"));
		}

		if (basePrice == null || basePrice <= 0) {
			addToErrorList(propertiesReader.getValueOfKey("base_price_null"));
		}

		if ((discountAmount != null && discountAmount > 0) || (discountPercent != null && discountPercent > 0)) {

			if ((discountAmount != null && discountPercent != null) && (discountAmount > 0 && discountPercent > 0)) {
				addToErrorList(propertiesReader.getValueOfKey("discount_amount_percent_both_entered"));
			}
		}

		if (productId == null) {
			addToErrorList(propertiesReader.getValueOfKey("product_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: retrieveData starts ");

		// get all options values
		allOptions = new OptionsDVO();

		// get product or sku details
		if (skuOpr.getProductSkuRecord().getId() != null) {
			try {
				skuOpr = new SkuDefinitionBF().getSkuDetails(skuOpr);

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		List<Object> productList = new ArrayList<Object>();
		productList.add(skuOpr.getProductSkuRecord().getProductRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("productCodeAutoComplete", productList);

		myLog.debug("In Product Definition Add Edit BB :: retrieveData ends ");
	}

	public List<Object> getSuggestedProductsList(String query) {
		try {
			ProductDVO productDVO = new ProductDVO();
			productDVO.setName(query);
			productDVO.setActive(true);
			ArrayList<Object> productList = new CoreSF().getSuggestedProductsList(productDVO);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("productCodeAutoComplete", productList);
			return productList;

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		return null;
	}

	public void openImageMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openImageMappingDialog starts ");

		try {
			skuOpr.getProductSkuRecord().setProductSkuImageMappingList(null);
			SkuOpr skuOprRecd = new SkuDefinitionBF().getImageMappingList(skuOpr);

			skuOpr.getProductSkuRecord().setProductSkuImageMappingList(
					skuOprRecd.getProductSkuRecord().getProductSkuImageMappingList());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	@Override
	public OptionsDVO getAllOptions() {
		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}
		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	public void openMapPropertyDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapPropertyDialog starts ");

		skuOpr.getProductSkuRecord().setProductSkuColorMappingList(null);

		skuOpr.getProductSkuRecord().setProductSkuSizeMappingList(null);

		skuOpr.getProductSkuRecord().setProductSkuMaterialMappingList(null);

		try {

			SkuOpr skuOprRet = new SkuDefinitionBF().getSkuPropertyMappingList(skuOpr);

			skuOpr.getProductSkuRecord().setProductSkuColorMappingList(
					skuOprRet.getProductSkuRecord().getProductSkuColorMappingList());

			skuOpr.getProductSkuRecord().setProductSkuSizeMappingList(
					skuOprRet.getProductSkuRecord().getProductSkuSizeMappingList());

			for (ProductSkuSizeMappingDVO productSkuSizeMappingDVO : skuOpr.getProductSkuRecord()
					.getProductSkuSizeMappingList()) {
				productSkuSizeMappingDVO = new SkuDefinitionBF().getSizeMappedValueList(productSkuSizeMappingDVO);
			}

			skuOpr.getProductSkuRecord().setProductSkuMaterialMappingList(
					skuOprRet.getProductSkuRecord().getProductSkuMaterialMappingList());

			populateSuggestionBox();

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

	public void executeSavePropertyMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: executeSavePropertyMapping starts ");

		if (validateSaveProductPropertiesMapping()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				skuOpr.getProductSkuRecord().setUserLogin(userLogin);

				SkuOpr skuOprRet = new SkuDefinitionBF().executeSavePropertyMapping(skuOpr);

				skuOpr.getProductSkuRecord()
						.getAuditAttributes()
						.setLastModifiedDate(skuOprRet.getProductSkuRecord().getAuditAttributes().getLastModifiedDate());

				openMapPropertyDialog(null);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("product_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSaveProductPropertiesMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		List<ProductSkuSizeMappingDVO> productSkuSizeMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuSizeMappingList();

		if (productSkuSizeMappingList != null && !productSkuSizeMappingList.isEmpty()) {

			Map<Long, Long> uniqueSizeMap = new HashMap<Long, Long>();

			for (int i = 0; i < productSkuSizeMappingList.size(); i++) {
				ProductSkuSizeMappingDVO productSkuSizeMappingRecord = new ProductSkuSizeMappingDVO();
				productSkuSizeMappingRecord = productSkuSizeMappingList.get(i);
				if (!validator.validateLongObjectNull(productSkuSizeMappingRecord.getPropertyValueMappingRecord()
						.getSizeRecord().getId())) {
					addToErrorList(propertiesReader.getValueOfKey("size_name_null") + (i + 1));
				} else if (!validator.validateLongObjectNull(productSkuSizeMappingRecord
						.getPropertyValueMappingRecord().getUnitRecord().getId())) {
					addToErrorList(propertiesReader.getValueOfKey("unit_null") + (i + 1));
				} else if (!validator.validateNull(productSkuSizeMappingRecord.getPropertyValueMappingRecord()
						.getPropertyValue())) {
					addToErrorList(propertiesReader.getValueOfKey("property_value_null") + (i + 1));
				} else {
					if (uniqueSizeMap.isEmpty()) {
						uniqueSizeMap.put(productSkuSizeMappingRecord.getPropertyValueMappingRecord().getId(),
								productSkuSizeMappingRecord.getPropertyValueMappingRecord().getId());
					} else {
						if (uniqueSizeMap.containsKey(productSkuSizeMappingRecord.getPropertyValueMappingRecord()
								.getId())) {
							addToErrorList(propertiesReader.getValueOfKey("duplicate_size_record") + (i + 1));

						} else {
							uniqueSizeMap.put(productSkuSizeMappingRecord.getPropertyValueMappingRecord().getId(),
									productSkuSizeMappingRecord.getPropertyValueMappingRecord().getId());
						}
					}
				}

			}
		}

		List<ProductSkuColorMappingDVO> productSkuColorMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuColorMappingList();

		if (productSkuColorMappingList != null && !productSkuColorMappingList.isEmpty()) {
			Map<Long, Long> uniqueColorMap = new HashMap<Long, Long>();
			for (int i = 0; i < productSkuColorMappingList.size(); i++) {
				ProductSkuColorMappingDVO productSkuColorMappingRecord = productSkuColorMappingList.get(i);
				if (!validator.validateLongObjectNull(productSkuColorMappingRecord.getColorRecord().getId())) {
					addToErrorList(propertiesReader.getValueOfKey("color_null") + (i + 1));
				} else {
					if (uniqueColorMap.isEmpty()) {
						uniqueColorMap.put(productSkuColorMappingRecord.getColorRecord().getId(),
								productSkuColorMappingRecord.getColorRecord().getId());
					} else {
						if (uniqueColorMap.containsKey(productSkuColorMappingRecord.getColorRecord().getId())) {
							addToErrorList(propertiesReader.getValueOfKey("duplicate_color_record") + (i + 1));
						} else {
							uniqueColorMap.put(productSkuColorMappingRecord.getColorRecord().getId(),
									productSkuColorMappingRecord.getColorRecord().getId());
						}
					}
				}
			}
		}

		List<ProductSkuMaterialMappingDVO> productSkuMaterialMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuMaterialMappingList();

		if (productSkuMaterialMappingList != null && !productSkuMaterialMappingList.isEmpty()) {
			Map<Long, Long> uniqueMaterialMap = new HashMap<Long, Long>();
			for (int i = 0; i < productSkuMaterialMappingList.size(); i++) {
				ProductSkuMaterialMappingDVO productSkuMaterialMappingRecord = productSkuMaterialMappingList.get(i);
				if (!validator.validateLongObjectNull(productSkuMaterialMappingRecord.getMaterialRecord().getId())) {
					addToErrorList(propertiesReader.getValueOfKey("material_null") + (i + 1));
				} else {
					if (uniqueMaterialMap.isEmpty()) {
						uniqueMaterialMap.put(productSkuMaterialMappingRecord.getMaterialRecord().getId(),
								productSkuMaterialMappingRecord.getMaterialRecord().getId());
					} else {
						if (uniqueMaterialMap.containsKey(productSkuMaterialMappingRecord.getMaterialRecord().getId())) {
							addToErrorList(propertiesReader.getValueOfKey("duplicate_material_record") + (i + 1));
						} else {
							uniqueMaterialMap.put(productSkuMaterialMappingRecord.getMaterialRecord().getId(),
									productSkuMaterialMappingRecord.getMaterialRecord().getId());
						}
					}
				}
			}
		}

		if (productSkuSizeMappingList.isEmpty() && productSkuColorMappingList.isEmpty()
				&& productSkuMaterialMappingList.isEmpty()) {
			addToErrorList(propertiesReader.getValueOfKey("all_property_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public List<Object> getSuggestedSizeRecordForName(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {

				PropertyValueMappingDVO propertyValueMappingRecord = new PropertyValueMappingDVO();

				propertyValueMappingRecord.getSizeRecord().setName(query);
				propertyValueMappingRecord.setActive(true);

				List<Object> list = new SkuDefinitionBF().getSuggestedSizeMappingRecord(propertyValueMappingRecord);
				myLog.debug(" getSuggestedSizeRecord :: list size" + list.size());

				@SuppressWarnings("unchecked")
				List<Object> allSizeList = (ArrayList<Object>) FacesContext.getCurrentInstance().getViewRoot()
						.getViewMap().get("sizeAutoComplete");

				if (allSizeList == null) {
					allSizeList = new ArrayList<Object>();
				}

				allSizeList.addAll(list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("sizeAutoComplete", allSizeList);

				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public void sizeChanged(ProductSkuSizeMappingDVO productSkuSizeMappingDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		productSkuSizeMappingDVO.getPropertyValueMappingRecord().setPropertyValue(null);
		productSkuSizeMappingDVO.getPropertyValueMappingRecord().setUnitRecord(null);

		if (productSkuSizeMappingDVO.getPropertyValueMappingRecord().getSizeRecord().getId() != null) {
			try {
				productSkuSizeMappingDVO.setPropertyValueList(new ArrayList<String>());
				PropertyValueMappingDVO propertyValueMappingRecord = new PropertyValueMappingDVO();
				propertyValueMappingRecord.getSizeRecord().setId(
						productSkuSizeMappingDVO.getPropertyValueMappingRecord().getSizeRecord().getId());

				List<Object> list = new SkuDefinitionBF().getSuggestedSizeMappingRecord(propertyValueMappingRecord);
				myLog.debug(" sizeChanged :: list size" + list.size());
				if (list != null && list.size() > 0) {
					for (Object object : list) {
						PropertyValueMappingDVO propertyValueMappingDVO = (PropertyValueMappingDVO) object;
						productSkuSizeMappingDVO.getPropertyValueList().add(propertyValueMappingDVO.getPropertyValue());
					}
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void propertyValueChanged(ProductSkuSizeMappingDVO productSkuSizeMappingDVO) {
		productSkuSizeMappingDVO.getPropertyValueMappingRecord().setUnitRecord(null);
	}

	public List<Object> getSuggestedUnitRecordForName(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		Object sizeId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("sizeId");
		Object propertyValue = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("propertyValue");

		myLog.debug(" sizeId::" + sizeId);
		if (query != null) {
			try {

				PropertyValueMappingDVO propertyValueMappingRecord = new PropertyValueMappingDVO();

				propertyValueMappingRecord.getUnitRecord().setName(query);
				propertyValueMappingRecord.getSizeRecord().setId((Long) sizeId);
				propertyValueMappingRecord.setPropertyValue((String) propertyValue);
				propertyValueMappingRecord.setActive(true);

				List<Object> list = new SkuDefinitionBF().getSuggestedSizeMappingRecord(propertyValueMappingRecord);
				myLog.debug(" getSuggestedSizeRecord :: list size" + list.size());

				@SuppressWarnings("unchecked")
				List<Object> allSizeList = (ArrayList<Object>) FacesContext.getCurrentInstance().getViewRoot()
						.getViewMap().get("unitAutoComplete");

				if (allSizeList == null) {
					allSizeList = new ArrayList<Object>();
				}

				allSizeList.addAll(list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("unitAutoComplete", allSizeList);

				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public void handleFileUploadForDefaultImage(FileUploadEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: handleFileUploadForDefaultImage starts ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean uploadFlag = false;
		String zoomFileName, regularFileName, thumbFileName, reportFileName, code;
		Integer version;

		String fileName = event.getFile().getFileName();
		myLog.debug("fileName " + fileName);
		String contextPath = "sku/";

		ProductSkuImageMappingDVO imageMappingRecord = new ProductSkuImageMappingDVO();

		File uploadFile = extractUploadedFileData(event);

		code = skuOpr.getProductSkuRecord().getCode();
		code = code.replace(" ", "_");

		// call upload for zoom image
		zoomFileName = code + "_0_z." + uploadFile.getExtension();
		String zoomFileUrl = contextPath + zoomFileName;
		myLog.debug("zoomFileUrl " + zoomFileUrl);
		imageMappingRecord.getImageRecord().setZoomImageURL(zoomFileUrl);
		uploadFile.setName(zoomFileName);
		myLog.debug("zoom fileName " + uploadFile.getName());
		uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.ZOOM_IMAGE_WIDTH,
				CommonConstant.ImageAttributes.ZOOM_IMAGE_HEIGHT, contextPath);
		myLog.debug("uploadFlag zoom :: " + uploadFlag);
		// end call upload for zoom image

		if (uploadFlag) {
			// call upload for regular image
			regularFileName = code + "_0_r." + uploadFile.getExtension();
			String regularFileUrl = contextPath + regularFileName;
			myLog.debug("regularFileUrl " + regularFileUrl);
			imageMappingRecord.getImageRecord().setImageURL(regularFileUrl);
			uploadFile.setName(regularFileName);
			uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.IMAGE_WIDTH,
					CommonConstant.ImageAttributes.IMAGE_HEIGHT, contextPath);
			myLog.debug("uploadFlag regular :: " + uploadFlag);
			// end call upload for regular image

			if (uploadFlag) {
				// call upload for thumbnail image
				thumbFileName = code + "_0_t." + uploadFile.getExtension();
				String thumbFileUrl = contextPath + thumbFileName;
				myLog.debug("thumbFileUrl " + thumbFileUrl);
				imageMappingRecord.getImageRecord().setThumbnailImageURL(thumbFileUrl);
				uploadFile.setName(thumbFileName);
				uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.THUMB_IMAGE_WIDTH,
						CommonConstant.ImageAttributes.THUMB_IMAGE_HEIGHT, contextPath);
				myLog.debug("uploadFlag thumbnail :: " + uploadFlag);
				// end call upload for thumbnail image

				if (uploadFlag) {
					myLog.debug("All files uploaded successfully :::::::::: ");
					setSuccessMsg(propertiesReader.getValueOfKey("upload_successful"));

					imageMappingRecord.setSequenceNumber(0L);
					imageMappingRecord.getOperationalAttributes().setRecordDeleted(false);
					boolean recordAdded = false;
					if (!skuOpr.getProductSkuRecord().getProductSkuImageMappingList().isEmpty()) {
						int size = skuOpr.getProductSkuRecord().getProductSkuImageMappingList().size();
						myLog.debug(" size :: " + size);
						for (int i = 0; i < size; i++) {
							ProductSkuImageMappingDVO productImageMappingDVO = skuOpr.getProductSkuRecord()
									.getProductSkuImageMappingList().get(i);
							myLog.debug(" seq no :: " + productImageMappingDVO.getSequenceNumber());

							if (productImageMappingDVO.getSequenceNumber() != null
									&& productImageMappingDVO.getSequenceNumber().equals(0L)) {
								skuOpr.getProductSkuRecord().getProductSkuImageMappingList().set(i, imageMappingRecord);
								recordAdded = true;
								myLog.debug(" size 22 :: "
										+ skuOpr.getProductSkuRecord().getProductSkuImageMappingList().size());
								break;
							}
						}
					}

					if (!recordAdded) {
						skuOpr.getProductSkuRecord().getProductSkuImageMappingList().add(0, imageMappingRecord);
					}
					skuOpr.getProductSkuRecord().setDefaultImageRecord(
							(ImageDVO) DeepCopy.copy(imageMappingRecord.getImageRecord()));
					myLog.debug("All files uploaded successfully :::::::::: size :: "
							+ skuOpr.getProductSkuRecord().getProductSkuImageMappingList().size());

				} else {
					myLog.debug("ERROR uploading thumbnail :::::::::: ");
					addToErrorList(propertiesReader.getValueOfKey("upload_error"));
				}
			} else {
				myLog.debug("ERROR uploading regular image :::::::::: ");
				addToErrorList(propertiesReader.getValueOfKey("upload_error"));
			}
		}

	}

	public void handleFileUploadForAlternateImage(FileUploadEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: handleFileUploadForAlternateImage starts ");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean uploadFlag = false;
		String zoomFileName, regularFileName, thumbFileName, reportFileName, code;

		String fileName = event.getFile().getFileName();
		myLog.debug("fileName " + fileName);
		String contextPath = "sku/";
		ProductSkuImageMappingDVO imageRecord = new ProductSkuImageMappingDVO();

		File uploadFile = extractUploadedFileData(event);

		code = skuOpr.getProductSkuRecord().getCode();
		code = code.replace(" ", "_");

		Long seqNo = 1L;
		if (!skuOpr.getProductSkuRecord().getProductSkuImageMappingList().isEmpty()) {
			int size = skuOpr.getProductSkuRecord().getProductSkuImageMappingList().size();
			myLog.debug("size " + size);

			ProductSkuImageMappingDVO productSkuImageMappingDVO = skuOpr.getProductSkuRecord()
					.getProductSkuImageMappingList().get(size - 1);
			Long sequenceNumber = productSkuImageMappingDVO.getSequenceNumber();
			myLog.debug("sequenceNumber " + sequenceNumber);
			if (sequenceNumber != null)
				seqNo = sequenceNumber + 1;
		}
		myLog.debug("seqNo " + seqNo);

		// call upload for zoom image
		zoomFileName = code + "_" + seqNo + "_z." + uploadFile.getExtension();
		String zoomFileUrl = contextPath + zoomFileName;
		myLog.debug("zoomFileUrl " + zoomFileUrl);
		imageRecord.getImageRecord().setZoomImageURL(zoomFileUrl);
		uploadFile.setName(zoomFileName);
		myLog.debug("zoom fileName " + uploadFile.getName());
		uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.ZOOM_IMAGE_WIDTH,
				CommonConstant.ImageAttributes.ZOOM_IMAGE_HEIGHT, contextPath);
		myLog.debug("uploadFlag zoom :: " + uploadFlag);
		// end call upload for zoom image

		if (uploadFlag) {
			// call upload for regular image
			regularFileName = code + "_" + seqNo + "_r." + uploadFile.getExtension();
			String regularFileUrl = contextPath + regularFileName;
			myLog.debug("regularFileUrl " + regularFileUrl);
			imageRecord.getImageRecord().setImageURL(regularFileUrl);
			uploadFile.setName(regularFileName);
			uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.IMAGE_WIDTH,
					CommonConstant.ImageAttributes.IMAGE_HEIGHT, contextPath);
			myLog.debug("uploadFlag regular :: " + uploadFlag);
			// end call upload for regular image

			if (uploadFlag) {
				// call upload for thumbnail image
				thumbFileName = code + "_" + seqNo + "_t." + uploadFile.getExtension();
				String thumbFileUrl = contextPath + thumbFileName;
				myLog.debug("thumbFileUrl " + thumbFileUrl);
				imageRecord.getImageRecord().setThumbnailImageURL(thumbFileUrl);
				uploadFile.setName(thumbFileName);
				uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.THUMB_IMAGE_WIDTH,
						CommonConstant.ImageAttributes.THUMB_IMAGE_HEIGHT, contextPath);
				myLog.debug("uploadFlag thumbnail :: " + uploadFlag);
				// end call upload for thumbnail image

				if (uploadFlag) {
					myLog.debug("All files uploaded successfully :::::::::: ");
					setSuccessMsg(propertiesReader.getValueOfKey("upload_successful"));

					// defaultImageRecord.setSequenceNumber(seqNo);
					skuOpr.getProductSkuRecord().getProductSkuImageMappingList()
							.add((ProductSkuImageMappingDVO) DeepCopy.copy(imageRecord));

					myLog.debug("All files uploaded successfully :::::::::: size :: "
							+ skuOpr.getProductSkuRecord().getProductSkuImageMappingList().size());
				}
			} else {
				myLog.debug("ERROR uploading regular image :::::::::: ");
				addToErrorList(propertiesReader.getValueOfKey("upload_error"));
			}
		}

	}

	public void executeSaveImageMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: executeSaveImageMapping starts ");

		if (!skuOpr.getProductSkuRecord().getProductSkuImageMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				skuOpr.getProductSkuRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				// if
				// (productOpr.getProductRecord().getModifyProductSKURecord().getModifyImages()
				// == null
				// ||
				// !productOpr.getProductRecord().getModifyProductSKURecord().getModifyImages())
				// {
				// if (filterSaveImageMapping())
				// productOpr.getProductRecord().getModifyProductSKURecord().setModifyImages(true);
				// }

				SkuOpr skuOprRecd = new SkuDefinitionBF().saveImageMappingList(skuOpr);
				skuOpr.getProductSkuRecord().setProductSkuImageMappingList(
						skuOprRecd.getProductSkuRecord().getProductSkuImageMappingList());
				skuOpr.getProductSkuRecord().setAuditAttributes(skuOprRecd.getProductSkuRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("image_save_success"));

				if (!skuOprRecd.getProductSkuRecord().getProductSkuImageMappingList().isEmpty()) {
					for (ProductSkuImageMappingDVO imageMappingDVO : skuOprRecd.getProductSkuRecord()
							.getProductSkuImageMappingList()) {
						if (imageMappingDVO.getSequenceNumber() != null
								&& imageMappingDVO.getSequenceNumber().equals(0L)) {
							skuOpr.getProductSkuRecord().setDefaultImageRecord(imageMappingDVO.getImageRecord());
						}
					}
				} else {
					skuOpr.getProductSkuRecord().setDefaultImageRecord(null);
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void executeAddMore(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionAddEditBB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		SkuOpr skuOprSent = new SkuOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, skuOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");

	}

	public void executeEnlargeImage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Sku Definition add edit BB :: executeEnlargeImage starts ");

		ImageDVO imageRecord = new ImageDVO(getProductSkuImageMappingRecord().getImageRecord().getZoomImageURL());
		myLog.debug("In Sku Definition add edit BB :: zoom image url::"
				+ getProductSkuImageMappingRecord().getImageRecord().getZoomImageURL());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.IMAGE_DVO, imageRecord);
	}

	public void executeSizeAddRow(ActionEvent event) {
		skuOpr.getProductSkuRecord().getProductSkuSizeMappingList().add(new ProductSkuSizeMappingDVO());
	}

	public void executeColorAddRow(ActionEvent event) {
		skuOpr.getProductSkuRecord().getProductSkuColorMappingList().add(new ProductSkuColorMappingDVO());
	}

	public void executeMaterialAddRow(ActionEvent event) {
		skuOpr.getProductSkuRecord().getProductSkuMaterialMappingList().add(new ProductSkuMaterialMappingDVO());
	}

	private void populateSuggestionBox() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Sku Definition add edit BB :: populateSuggestionBox starts ");

		List<ProductSkuSizeMappingDVO> productSkuSizeMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuSizeMappingList();

		List<ProductSkuColorMappingDVO> productSkuColorMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuColorMappingList();

		List<ProductSkuMaterialMappingDVO> productSkuMaterialMappingList = skuOpr.getProductSkuRecord()
				.getProductSkuMaterialMappingList();

		if (!productSkuSizeMappingList.isEmpty()) {
			List<Object> sizeList = new ArrayList<Object>();

			myLog.debug("productSkuSizeMappingList :: not empty ");

			for (ProductSkuSizeMappingDVO productSkuSizeMappingDVO : productSkuSizeMappingList) {
				sizeList.add(productSkuSizeMappingDVO.getPropertyValueMappingRecord());

				myLog.debug("value::"
						+ productSkuSizeMappingDVO.getPropertyValueMappingRecord().getSizeRecord().getCode());
			}

			myLog.debug("size list :: " + sizeList.size());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("sizeAutoComplete", sizeList);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("unitAutoComplete", sizeList);

		}

		if (!productSkuColorMappingList.isEmpty()) {
			List<Object> colorList = new ArrayList<Object>();

			for (ProductSkuColorMappingDVO productSkuColorMappingDVO : productSkuColorMappingList) {
				colorList.add(productSkuColorMappingDVO.getColorRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("colorAutoComplete", colorList);
		}

		if (!productSkuMaterialMappingList.isEmpty()) {
			List<Object> materialList = new ArrayList<Object>();

			for (ProductSkuMaterialMappingDVO oroductSkuMaterialMappingDVO : productSkuMaterialMappingList) {
				materialList.add(oroductSkuMaterialMappingDVO.getMaterialRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("materialAutoComplete", materialList);
		}
	}

	public List<Object> getSuggestedColorRecord(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {

				ColorDVO colorRecord = new ColorDVO();

				colorRecord.setName(query);
				colorRecord.setActive(true);

				List<Object> list = new SkuDefinitionBF().getSuggestedColorRecord(colorRecord);
				myLog.debug(" getSuggestedColorRecord :: list size" + list.size());

				@SuppressWarnings("unchecked")
				List<Object> allSizeList = (ArrayList<Object>) FacesContext.getCurrentInstance().getViewRoot()
						.getViewMap().get("colorAutoComplete");

				if (allSizeList == null) {
					allSizeList = new ArrayList<Object>();
				}

				allSizeList.addAll(list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("colorAutoComplete", allSizeList);

				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedMaterialRecord(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {

				MaterialDVO materialRecord = new MaterialDVO();

				materialRecord.setName(query);
				materialRecord.setActive(true);

				List<Object> list = new SkuDefinitionBF().getSuggestedMaterialRecord(materialRecord);
				myLog.debug(" getSuggestedMaterialRecord :: list size" + list.size());

				@SuppressWarnings("unchecked")
				List<Object> allSizeList = (ArrayList<Object>) FacesContext.getCurrentInstance().getViewRoot()
						.getViewMap().get("materialAutoComplete");

				if (allSizeList == null) {
					allSizeList = new ArrayList<Object>();
				}

				allSizeList.addAll(list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("materialAutoComplete", allSizeList);

				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}
}
