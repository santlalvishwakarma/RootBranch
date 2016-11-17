package com.web.bb.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.web.bf.systemowner.modules.skumanagement.SkuDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.systemowner.ProductOpr;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.ProductSkuImageMappingDVO;
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
import com.web.util.BaseDVOComparator;
import com.web.util.MessageFormatter;
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
	private List<Object> propertyValuesList;

	private boolean validateForApprove;
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

	public List<Object> getPropertyValuesList() {
		if (propertyValuesList == null) {
			propertyValuesList = new ArrayList<Object>();
		}
		return propertyValuesList;
	}

	public void setPropertyValuesList(List<Object> propertyValuesList) {
		this.propertyValuesList = propertyValuesList;
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
		myLog.debug("In Product Definition Add Edit BB :: executeSave starts ");

		validateForApprove = false;
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

		try {
			if (renderForProduct)
				productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "PRODUCT");
			else
				productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SKU");

			productOpr.getProductRecord().setProductPropertiesMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getPropertiesMappingList(productOpr);

			if (!productOprRecd.getProductRecord().getProductPropertiesMappingList().isEmpty()) {
				productOpr.getProductRecord().setProductPropertiesMappingList(
						productOprRecd.getProductRecord().getProductPropertiesMappingList());
				RequestContext.getCurrentInstance().execute("productPropertyMappingDialog.show();");

			} else {
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				if (productOpr.getIconProductSKURecord().getMapHierarchy())
					addToErrorList(propertiesReader.getValueOfKey("property_list_null"));
				else
					addToErrorList(propertiesReader.getValueOfKey("no_hierarchy_mapped"));

			}
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		propertyValuesList = new ArrayList<Object>();

		if (!productOpr.getProductRecord().getProductPropertiesMappingList().isEmpty()) {
			for (ProductPropertiesMappingDVO productPropertiesMappingRecord : productOpr.getProductRecord()
					.getProductPropertiesMappingList()) {
				for (ProductPropertiesMappingDVO productPropertiesMappingDVO : productPropertiesMappingRecord
						.getSuggestedValuesList()) {
					productPropertiesMappingDVO.getProductPropertiesRecord().setId(
							productPropertiesMappingRecord.getProductPropertiesRecord().getId());
					propertyValuesList.add(productPropertiesMappingDVO);
				}
			}
		}
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("productPropertyValueAutoComplete", propertyValuesList);
	}

	public List<Object> getSuggestedPropertyValuesList(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		List<Object> productItemObjectList = new ArrayList<Object>();
		Object propertyValue = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("property");

		myLog.debug(" propertyValue ---> " + propertyValue);

		if (propertyValue != null && query != null) {
			Long propertyId = Long.parseLong(propertyValue.toString());
			myLog.debug(" propertyId ---> " + propertyId);

			if (propertyValuesList != null && !propertyValuesList.isEmpty()) {
				query = query.toUpperCase();
				for (Object object : propertyValuesList) {
					ProductPropertiesMappingDVO productPropertiesMappingDVO = (ProductPropertiesMappingDVO) object;
					String name = productPropertiesMappingDVO.getName();
					Long propertyIdDB = productPropertiesMappingDVO.getProductPropertiesRecord().getId();
					myLog.debug(" propertyIdDB ---> " + propertyIdDB);

					if (propertyIdDB != null && propertyIdDB.equals(propertyId) && name.toUpperCase().startsWith(query)) {
						productItemObjectList.add(productPropertiesMappingDVO);
					}
				}
			}
		}
		return productItemObjectList;
	}

	public void executeSavePropertyMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSavePropertyMapping starts ");

		if (validateSaveProductPropertiesMapping()
				&& !productOpr.getProductRecord().getProductPropertiesMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				if (renderForProduct)
					productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "PRODUCT");
				else
					productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SKU");

				productOpr = new ProductDefinitionBD().savePropertiesMappingList(productOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("property_save_success"));

				if (!productOpr.getProductRecord().getProductPropertiesMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapProperties(true);
				else
					productOpr.getIconProductSKURecord().setMapProperties(false);
				populateEnableDisableButtons();

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

		int size = productOpr.getProductRecord().getProductPropertiesMappingList().size();
		if (size > 0) {

			for (int i = 0; i < size; i++) {
				ProductPropertiesMappingDVO productPropertiesMappingRecord = productOpr.getProductRecord()
						.getProductPropertiesMappingList().get(i);

				if (!productPropertiesMappingRecord.getOperationalAttributes().getRecordDeleted()) {
					String propertyValue = productPropertiesMappingRecord.getPropertyValue();
					String uomName = productPropertiesMappingRecord.getProductPropertiesRecord().getUomRecord()
							.getName();
					Integer valueTypeSequenceNumber = productPropertiesMappingRecord.getProductPropertiesRecord()
							.getValueType().getSequenceNumber();

					if (productPropertiesMappingRecord.getProductPropertiesRecord().getMandatory() != null
							&& productPropertiesMappingRecord.getProductPropertiesRecord().getMandatory()) {

						if (!CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)
								&& !validator.validateNull(propertyValue)) {
							addToErrorList(propertiesReader.getValueOfKey("property_value_null") + (i + 1));

						}
						if (CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)
								&& productPropertiesMappingRecord.getPropertyValuesList().isEmpty()) {
							addToErrorList(propertiesReader.getValueOfKey("property_value_null") + (i + 1));
						}
					}
					if (!CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)) {
						if (uomName != null && !uomName.equalsIgnoreCase(CommonConstant.UOMCodes.NONE)
								&& propertyValue != null) {
							try {
								Float.parseFloat(propertyValue);

							} catch (NumberFormatException e) {
								addToErrorList(propertiesReader.getValueOfKey("property_value_numeric") + (i + 1));
							}

							if (propertyValue != null && !validator.validateForNoOfDecimals(propertyValue, 3)) {
								addToErrorList(propertiesReader.getValueOfKey("property_value_decimal_places")
										+ (i + 1));
							}
						}
					} else {

						HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();
						for (ProductPropertiesMappingDVO productPropertiesMappingDVO : productPropertiesMappingRecord
								.getPropertyValuesList()) {

							String propertyName = productPropertiesMappingDVO.getName();
							if (uniqueValuesMap.containsKey(propertyName)) {
								addToErrorList(propertiesReader.getValueOfKey("property_value_duplicate") + (i + 1));

							} else {
								uniqueValuesMap.put(propertyName, propertyName);
							}
						}
					}

				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void openMapRawMaterialDetailsDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapRawMaterialDetailsDialog starts ");

		try {
			productOpr.getProductRecord().setProductRMDetailsList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getRawMaterialMappingList(productOpr);
			productOpr.getProductRecord().setProductRMDetailsList(
					productOprRecd.getProductRecord().getProductRMDetailsList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		populateRMData();

		try {
			itemList = new ArrayList<Object>();
			itemList = new ProductDefinitionBD().getSuggestedItemsList(new ItemDVO());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		try {

			ArrayList<Object> pvList = new ProductDefinitionBD()
					.getSuggestedProcessVariationList(new ProcessVariationMappingDVO());

			processVariationList = new ArrayList<Object>();
			for (Object object : pvList) {
				ProcessVariationMappingDVO processVariationMappingRecord = (ProcessVariationMappingDVO) object;
				ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO = new ProductRMProcessVariationDetailsDVO();

				productRMProcessVariationDetailsDVO.setId(processVariationMappingRecord.getId());
				productRMProcessVariationDetailsDVO.setCode(processVariationMappingRecord.getCode());
				productRMProcessVariationDetailsDVO.setName(processVariationMappingRecord.getName());
				productRMProcessVariationDetailsDVO.setActive(processVariationMappingRecord.getActive());
				productRMProcessVariationDetailsDVO.getProcessVariationMappingRecord().setProcessRecord(
						processVariationMappingRecord.getProcessRecord());
				processVariationList.add(productRMProcessVariationDetailsDVO);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();
		for (Object object : itemList) {
			ItemDVO itemRecord = (ItemDVO) object;
			String itemCode = itemRecord.getCode();
			if (!uniqueValuesMap.containsKey(itemCode))
				uniqueValuesMap.put(itemCode, itemCode);
		}

		HashMap<Long, Long> uniqueValuesPVMap = new HashMap<Long, Long>();
		for (Object object : processVariationList) {
			ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO = (ProductRMProcessVariationDetailsDVO) object;
			Long id = productRMProcessVariationDetailsDVO.getId();
			if (!uniqueValuesPVMap.containsKey(id))
				uniqueValuesPVMap.put(id, id);
		}

		// done for populating inactive items and while save doing validations
		for (ProductRMDetailsDVO productRMDetailsDVO : productOpr.getProductRecord().getProductRMDetailsList()) {
			String itemCode = productRMDetailsDVO.getItemRecord().getCode();
			if (!uniqueValuesMap.containsKey(itemCode))
				itemList.add(productRMDetailsDVO.getItemRecord());

			for (ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO : productRMDetailsDVO
					.getProductRMProcessVariationDetailsList()) {
				Long rmProcessVariationDetailsId = productRMProcessVariationDetailsDVO.getId();
				if (!uniqueValuesPVMap.containsKey(rmProcessVariationDetailsId))
					processVariationList.add(productRMProcessVariationDetailsDVO);
			}
		}

		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("productRMCodeAutoComplete", itemList);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("productRMProcessVariationDetailsAutoComplete", processVariationList);

		if (productOpr.getProductRecord().getProductRMDetailsList().isEmpty())
			productOpr.getProductRecord().getProductRMDetailsList().add(new ProductRMDetailsDVO());
	}

	private void populateRMData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		copyOfDataMap = new HashMap<Long, BaseDVO>();

		for (ProductRMDetailsDVO productRMDetailsRecord : productOpr.getProductRecord().getProductRMDetailsList()) {
			Collections.sort(productRMDetailsRecord.getProductRMProcessVariationDetailsList(), new BaseDVOComparator());

			String processVariationString = "";
			for (ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO : productRMDetailsRecord
					.getProductRMProcessVariationDetailsList()) {
				Long rmProcessVariationDetailsId = productRMProcessVariationDetailsDVO.getId();
				myLog.debug(" rmProcessVariationDetailsId ---> " + rmProcessVariationDetailsId);

				if (rmProcessVariationDetailsId != null)
					processVariationString += rmProcessVariationDetailsId + "~";
				else
					processVariationString += "~";
			}
			myLog.debug(" processVariationString ---> " + processVariationString);
			productRMDetailsRecord.setProcessVariationString(processVariationString);

			if (!productRMDetailsRecord.getOperationalAttributes().getRecordDeleted()) {
				copyOfDataMap.put(productRMDetailsRecord.getId(),
						(ProductRMDetailsDVO) DeepCopy.copy(productRMDetailsRecord));
			}
		}
	}

	public void executeRawMaterialDetailsAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductRMDetailsList().add(new ProductRMDetailsDVO());
	}

	public List<Object> getSuggestedItemsList(String query) {
		List<Object> productItemObjectList = new ArrayList<Object>();
		if (query != null) {

			if (itemList != null && !itemList.isEmpty()) {
				query = query.toUpperCase();
				for (Object object : itemList) {
					ItemDVO itemRecord = (ItemDVO) object;
					String name = itemRecord.getName();

					if (name.toUpperCase().startsWith(query)) {
						productItemObjectList.add(itemRecord);
					}
				}
			}
		}
		return productItemObjectList;
	}

	public List<Object> getSuggestedProcessVariationList(String query) {
		List<Object> processVariationListForAutoSuggest = new ArrayList<Object>();

		if (query != null) {

			if (processVariationList != null && !processVariationList.isEmpty()) {
				query = query.toUpperCase();
				for (Object object : processVariationList) {
					ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO = (ProductRMProcessVariationDetailsDVO) object;
					String name = productRMProcessVariationDetailsDVO.getName();

					if (name.toUpperCase().startsWith(query)) {
						processVariationListForAutoSuggest.add(productRMProcessVariationDetailsDVO);
					}
				}
			}
		}

		return processVariationListForAutoSuggest;
	}

	public void executeSaveRawMaterialDetails(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveRawMaterialDetails starts ");

		if (validateSaveRawMaterialMappingDetails()
				&& !productOpr.getProductRecord().getProductRMDetailsList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyRawMaterials() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyRawMaterials()) {
					if (filterSaveProductRawMaterialMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyRawMaterials(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveRawMaterialMappingList(productOpr);
				productOpr.getProductRecord().setProductRMDetailsList(
						productOprRecd.getProductRecord().getProductRMDetailsList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("raw_materials_save_success"));

				if (!productOprRecd.getProductRecord().getProductRMDetailsList().isEmpty())
					productOpr.getIconProductSKURecord().setMapRawMaterials(true);
				else
					productOpr.getIconProductSKURecord().setMapRawMaterials(false);
				populateRMData();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	public boolean validateSaveRawMaterialMappingDetails() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		int size = productOpr.getProductRecord().getProductRMDetailsList().size();
		if (size > 0) {
			HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();

			for (int i = 0; i < size; i++) {
				ProductRMDetailsDVO productRMDetailsRecord = productOpr.getProductRecord().getProductRMDetailsList()
						.get(i);

				if (!productRMDetailsRecord.getOperationalAttributes().getRecordDeleted()) {
					Integer allocationBasedOn = productRMDetailsRecord.getItemRecord().getAllocationBasedOn()
							.getSequenceNumber();
					Integer quantity = productRMDetailsRecord.getQuantity();
					Float weight = productRMDetailsRecord.getWeight();
					String itemCode = productRMDetailsRecord.getItemRecord().getCode();
					Boolean active = productRMDetailsRecord.getItemRecord().getActive();

					if (!validator.validateNull(itemCode)) {
						addToErrorList(propertiesReader.getValueOfKey("item_null") + (i + 1));
					}

					// allocation validations
					if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
						// Quantity validations
						if (!validator.validateIntegerObjectNull(quantity)) {
							addToErrorList(propertiesReader.getValueOfKey("quantity_null") + (i + 1));
						}

					} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
						// Weight validations
						if (!validator.validateFloatObjectNull(weight)) {
							addToErrorList(propertiesReader.getValueOfKey("weight_null") + (i + 1));
						}

					} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
						// both validations
						if (!validator.validateIntegerObjectNull(quantity)) {
							addToErrorList(propertiesReader.getValueOfKey("quantity_null") + (i + 1));
						}
						if (!validator.validateFloatObjectNull(weight)) {
							addToErrorList(propertiesReader.getValueOfKey("weight_null") + (i + 1));
						}
					}

					// unique item code validations
					if (uniqueValuesMap.containsKey(itemCode)) {
						addToErrorList(propertiesReader.getValueOfKey("item_code_duplicate") + (i + 1));

					} else {
						uniqueValuesMap.put(itemCode, itemCode);
					}
					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("item_code_invalid") + (i + 1));
					}

					if (!productRMDetailsRecord.getProductRMProcessVariationDetailsList().isEmpty()) {
						HashMap<Long, Long> uniquePVValuesMap = new HashMap<Long, Long>();
						for (ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO : productRMDetailsRecord
								.getProductRMProcessVariationDetailsList()) {
							Long rmProcessVariationDetailsId = productRMProcessVariationDetailsDVO.getId();
							Boolean pvActive = productRMProcessVariationDetailsDVO.getActive();

							// unique pv validations
							if (uniquePVValuesMap.containsKey(rmProcessVariationDetailsId)) {
								addToErrorList(propertiesReader.getValueOfKey("process_variation_duplicate") + (i + 1));

							} else {
								uniquePVValuesMap.put(rmProcessVariationDetailsId, rmProcessVariationDetailsId);
							}
							if (pvActive == null || !pvActive) {
								String errorMsg = propertiesReader.getValueOfKey("process_variation_invalid");
								String[] arrString = { productRMProcessVariationDetailsDVO.getName() };
								errorMsg = MessageFormatter.getFormattedMessage(errorMsg, arrString);
								addToErrorList(errorMsg + (i + 1));
							}
						}
					}
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	private boolean filterSaveProductRawMaterialMapping() {
		boolean validateFlag = false;
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		for (ProductRMDetailsDVO productRMDetailsRecord : productOpr.getProductRecord().getProductRMDetailsList()) {

			if (copyOfDataMap.containsKey(productRMDetailsRecord.getId())) {
				ProductRMDetailsDVO productRMDetailsDVO = (ProductRMDetailsDVO) copyOfDataMap
						.get(productRMDetailsRecord.getId());

				String itemCodeFromScreen = productRMDetailsRecord.getItemRecord().getCode();
				Integer quantityFromScreen = productRMDetailsRecord.getQuantity();
				Float weightFromScreen = productRMDetailsRecord.getWeight();
				Boolean recordDeletedFromScreen = productRMDetailsRecord.getOperationalAttributes().getRecordDeleted();

				String itemCodeFromDB = productRMDetailsDVO.getItemRecord().getCode();
				Integer quantityFromDB = productRMDetailsDVO.getQuantity();
				Float weightFromDB = productRMDetailsDVO.getWeight();
				Boolean recordDeletedFromDB = productRMDetailsDVO.getOperationalAttributes().getRecordDeleted();

				if (validateForValueChange(itemCodeFromScreen, itemCodeFromDB)
						|| validateForValueChange(quantityFromScreen, quantityFromDB)
						|| validateForValueChange(weightFromScreen, weightFromDB)
						|| validateForValueChange(recordDeletedFromScreen, recordDeletedFromDB)) {
					validateFlag = true;
					break;

				} else {
					Collections.sort(productRMDetailsRecord.getProductRMProcessVariationDetailsList(),
							new BaseDVOComparator());

					String processVariationStringFromDB = productRMDetailsDVO.getProcessVariationString();
					String processVariationStringFromScreen = "";

					for (ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO : productRMDetailsRecord
							.getProductRMProcessVariationDetailsList()) {
						Long rmProcessVariationDetailsId = productRMProcessVariationDetailsDVO.getId();

						if (rmProcessVariationDetailsId != null)
							processVariationStringFromScreen += rmProcessVariationDetailsId + "~";
						else
							processVariationStringFromScreen += "~";
					}
					myLog.debug(" processVariationStringFromScreen ---> " + processVariationStringFromScreen);
					myLog.debug(" processVariationStringFromDB ---> " + processVariationStringFromDB);

					if (validateForValueChange(processVariationStringFromScreen, processVariationStringFromDB)) {
						validateFlag = true;
						break;
					}
				}

			} else {
				validateFlag = true;
				break;
			}

		}
		return validateFlag;
	}

	public List<Object> getSuggestedProcessVariationListBasedOnProcess(String query) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		Object processIdStr = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("processId");
		myLog.debug(" processIdStr :: " + processIdStr);
		if (processIdStr != null) {
			Long processId = Long.valueOf(processIdStr.toString());
			String processCode = processActiveMap.get(processId).getCode();

			myLog.debug(" processCode :: " + processCode);
			myLog.debug(" active :: " + processActiveMap.get(processId).getActive());
			List<Object> processVariationListForAutoSuggest = new ArrayList<Object>();

			if (processCode != null) {

				if (processVariationList != null && !processVariationList.isEmpty()) {
					query = query.toUpperCase();
					for (Object object : processVariationList) {
						ProcessVariationMappingDVO processVariationMappingRecord = (ProcessVariationMappingDVO) object;
						String code = null;
						String name = processVariationMappingRecord.getName();

						ArrayList<ProcessDVO> processList = processVariationMappingRecord.getProcessList();
						for (ProcessDVO processRec : processList) {
							code = processRec.getCode();
							if (processCode != null && code != null && code.equals(processCode)
									&& name.toUpperCase().startsWith(query)) {
								processVariationListForAutoSuggest.add(processVariationMappingRecord);
								break;
							}
						}
					}
					return processVariationListForAutoSuggest;
				}
			}
		}
		return null;
	}

	public void openMapComplementarySkuMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapComplementarySkuMappingDialog starts ");

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
			productOpr.getProductRecord().setProductComplementarySkuMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getComplementarySkuMappingList(productOpr);

			productOpr.getProductRecord().setProductComplementarySkuMappingList(
					productOprRecd.getProductRecord().getProductComplementarySkuMappingList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		// get sku list
		try {
			skuList = new ArrayList<Object>();
			skuList = new ProductDefinitionBD().getSuggestedSKUList(new ProductSkuDVO());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();
		for (Object object : skuList) {
			ProductSkuDVO productSkuDVO = (ProductSkuDVO) object;
			Long id = productSkuDVO.getId();
			if (!uniqueValuesMap.containsKey(id))
				uniqueValuesMap.put(id, id);
		}

		// done for populating inactive skus and while save doing validations
		for (ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO : productOpr.getProductRecord()
				.getProductComplementarySkuMappingList()) {
			Long id = productComplementarySkuMappingDVO.getProductSkuRecord().getId();
			if (!uniqueValuesMap.containsKey(id)) {
				skuList.add(productComplementarySkuMappingDVO.getProductSkuRecord());
			}
			copyOfDataMap.put(productComplementarySkuMappingDVO.getId(),
					(ProductComplementarySkuMappingDVO) DeepCopy.copy(productComplementarySkuMappingDVO));
		}
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("skuCodeAutoComplete", skuList);

		if (productOpr.getProductRecord().getProductComplementarySkuMappingList().isEmpty())
			productOpr.getProductRecord().getProductComplementarySkuMappingList()
					.add(new ProductComplementarySkuMappingDVO());
	}

	public void executeComplementarySkuMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductComplementarySkuMappingList()
				.add(new ProductComplementarySkuMappingDVO());
	}

	public List<Object> getSuggestedSKUList(String query) {
		List<Object> skuListForAutoSuggest = new ArrayList<Object>();

		if (query != null) {

			if (skuList != null && !skuList.isEmpty()) {
				query = query.toUpperCase();
				for (Object object : skuList) {
					ProductSkuDVO productSkuDVO = (ProductSkuDVO) object;
					String code = productSkuDVO.getCode();

					if (code != null && code.toUpperCase().startsWith(query)) {
						skuListForAutoSuggest.add(productSkuDVO);
					}
				}
			} else {
				try {
					ProductSkuDVO productSkuDVO = new ProductSkuDVO();
					productSkuDVO.setCode(query);
					ArrayList<Object> skuObjList = new ProductDefinitionBD().getSuggestedSKUList(productSkuDVO);
					FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("skuCodeAutoComplete", skuObjList);
					return skuObjList;

				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);

				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			}
		}

		return skuListForAutoSuggest;
	}

	public void executeSaveComplementarySkuMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveComplementarySkuMapping starts ");

		if (validateSaveComplementarySkuMapping()
				&& !productOpr.getProductRecord().getProductComplementarySkuMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyComplementarySKU() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyComplementarySKU()) {
					if (filterSaveComplementarySkuMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyComplementarySKU(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveComplementarySkuMappingList(productOpr);
				productOpr.getProductRecord().setProductComplementarySkuMappingList(
						productOprRecd.getProductRecord().getProductComplementarySkuMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("complementary_sku_save_success"));

				if (!productOprRecd.getProductRecord().getProductComplementarySkuMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapComplementarySKU(true);
				else
					productOpr.getIconProductSKURecord().setMapComplementarySKU(false);

				copyOfDataMap = new HashMap<Long, BaseDVO>();
				for (ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO : productOpr
						.getProductRecord().getProductComplementarySkuMappingList()) {
					if (!productComplementarySkuMappingDVO.getOperationalAttributes().getRecordDeleted()) {
						copyOfDataMap.put(productComplementarySkuMappingDVO.getId(),
								(ProductComplementarySkuMappingDVO) DeepCopy.copy(productComplementarySkuMappingDVO));
					}
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	public boolean validateSaveComplementarySkuMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());
		Long skuHeaderId = productOpr.getProductRecord().getProductSkuRecord().getId();

		int size = productOpr.getProductRecord().getProductComplementarySkuMappingList().size();
		if (size > 0) {
			HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();

			for (int i = 0; i < size; i++) {
				ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO = productOpr.getProductRecord()
						.getProductComplementarySkuMappingList().get(i);

				if (!productComplementarySkuMappingDVO.getOperationalAttributes().getRecordDeleted()) {
					Long skuId = productComplementarySkuMappingDVO.getProductSkuRecord().getId();
					Boolean active = productComplementarySkuMappingDVO.getProductSkuRecord().getActive();

					if (!validator.validateLongObjectNull(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_null") + (i + 1));
					}

					// unique sku validations
					if (uniqueValuesMap.containsKey(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_duplicate") + (i + 1));

					} else {
						uniqueValuesMap.put(skuId, skuId);
					}

					if (skuHeaderId != null && skuId != null && skuHeaderId.equals(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_cannot_be_mapped_complementary"));
					}

					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("sku_invalid") + (i + 1));
					}
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean filterSaveComplementarySkuMapping() {
		boolean validateFlag = false;

		for (ProductComplementarySkuMappingDVO productComplementarySkuMappingRecord : productOpr.getProductRecord()
				.getProductComplementarySkuMappingList()) {

			if (copyOfDataMap.containsKey(productComplementarySkuMappingRecord.getId())) {
				ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO = (ProductComplementarySkuMappingDVO) copyOfDataMap
						.get(productComplementarySkuMappingRecord.getId());

				Long skuMappingIdFromScreen = productComplementarySkuMappingRecord.getProductSkuRecord().getId();
				Boolean recordDeletedFromScreen = productComplementarySkuMappingRecord.getOperationalAttributes()
						.getRecordDeleted();

				Long skuMappingIdFromDB = productComplementarySkuMappingDVO.getProductSkuRecord().getId();
				Boolean recordDeletedFromDB = productComplementarySkuMappingDVO.getOperationalAttributes()
						.getRecordDeleted();

				if (validateForValueChange(skuMappingIdFromScreen, skuMappingIdFromDB)
						|| validateForValueChange(recordDeletedFromScreen, recordDeletedFromDB)) {
					validateFlag = true;
					break;
				}
			} else {
				validateFlag = true;
				break;
			}
		}
		return validateFlag;
	}

	public void openMapSimilarSkuMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapSimilarSkuMappingDialog starts ");

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
			productOpr.getProductRecord().setProductSimilarSkuMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getSimilarSkuMappingList(productOpr);

			productOpr.getProductRecord().setProductSimilarSkuMappingList(
					productOprRecd.getProductRecord().getProductSimilarSkuMappingList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		// get sku list
		try {

			skuList = new ArrayList<Object>();
			skuList = new ProductDefinitionBD().getSuggestedSKUList(new ProductSkuDVO());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();
		for (Object object : skuList) {
			ProductSkuDVO productSkuDVO = (ProductSkuDVO) object;
			Long id = productSkuDVO.getId();
			if (!uniqueValuesMap.containsKey(id))
				uniqueValuesMap.put(id, id);
		}

		// done for populating inactive skus and while save doing validations
		for (ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO : productOpr.getProductRecord()
				.getProductSimilarSkuMappingList()) {
			Long id = productSimilarSkuMappingDVO.getProductSkuRecord().getId();
			if (!uniqueValuesMap.containsKey(id)) {
				skuList.add(productSimilarSkuMappingDVO.getProductSkuRecord());
			}
			copyOfDataMap.put(productSimilarSkuMappingDVO.getId(),
					(ProductSimilarSkuMappingDVO) DeepCopy.copy(productSimilarSkuMappingDVO));
		}
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("skuCodeAutoComplete", skuList);

		if (productOpr.getProductRecord().getProductSimilarSkuMappingList().isEmpty())
			productOpr.getProductRecord().getProductSimilarSkuMappingList().add(new ProductSimilarSkuMappingDVO());
	}

	public void executeSimilarSkuMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductSimilarSkuMappingList().add(new ProductSimilarSkuMappingDVO());
	}

	public void executeSaveSimilarSkuMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveSimilarSkuMapping starts ");

		if (validateSaveSimilarSkuMapping()
				&& !productOpr.getProductRecord().getProductSimilarSkuMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifySimiliarSKU() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifySimiliarSKU()) {
					if (filterSaveSimilarSkuMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifySimiliarSKU(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveSimilarSkuMappingList(productOpr);
				productOpr.getProductRecord().setProductSimilarSkuMappingList(
						productOprRecd.getProductRecord().getProductSimilarSkuMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("similar_sku_save_success"));

				if (!productOprRecd.getProductRecord().getProductSimilarSkuMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapSimiliarSKU(true);
				else
					productOpr.getIconProductSKURecord().setMapSimiliarSKU(false);

				copyOfDataMap = new HashMap<Long, BaseDVO>();
				for (ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO : productOpr.getProductRecord()
						.getProductSimilarSkuMappingList()) {
					copyOfDataMap.put(productSimilarSkuMappingDVO.getId(),
							(ProductSimilarSkuMappingDVO) DeepCopy.copy(productSimilarSkuMappingDVO));
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	public boolean validateSaveSimilarSkuMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());
		Long skuHeaderId = productOpr.getProductRecord().getProductSkuRecord().getId();

		int size = productOpr.getProductRecord().getProductSimilarSkuMappingList().size();
		if (size > 0) {
			HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();

			for (int i = 0; i < size; i++) {
				ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO = productOpr.getProductRecord()
						.getProductSimilarSkuMappingList().get(i);

				if (!productSimilarSkuMappingDVO.getOperationalAttributes().getRecordDeleted()) {
					Long skuId = productSimilarSkuMappingDVO.getProductSkuRecord().getId();
					Boolean active = productSimilarSkuMappingDVO.getProductSkuRecord().getActive();

					if (!validator.validateLongObjectNull(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_null") + (i + 1));
					}

					// unique sku validations
					if (uniqueValuesMap.containsKey(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_duplicate") + (i + 1));

					} else {
						uniqueValuesMap.put(skuId, skuId);
					}

					if (skuHeaderId != null && skuId != null && skuHeaderId.equals(skuId)) {
						addToErrorList(propertiesReader.getValueOfKey("sku_cannot_be_mapped_similar"));
					}

					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("sku_invalid") + (i + 1));
					}
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean filterSaveSimilarSkuMapping() {
		boolean validateFlag = false;

		for (ProductSimilarSkuMappingDVO productSimilarSkuMappingRecord : productOpr.getProductRecord()
				.getProductSimilarSkuMappingList()) {

			if (copyOfDataMap.containsKey(productSimilarSkuMappingRecord.getId())) {
				ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO = (ProductSimilarSkuMappingDVO) copyOfDataMap
						.get(productSimilarSkuMappingRecord.getId());

				Long skuMappingIdFromScreen = productSimilarSkuMappingRecord.getProductSkuRecord().getId();
				Boolean recordDeletedFromScreen = productSimilarSkuMappingRecord.getOperationalAttributes()
						.getRecordDeleted();

				Long skuMappingIdFromDB = productSimilarSkuMappingDVO.getProductSkuRecord().getId();
				Boolean recordDeletedFromDB = productSimilarSkuMappingDVO.getOperationalAttributes().getRecordDeleted();

				if (validateForValueChange(skuMappingIdFromScreen, skuMappingIdFromDB)
						|| validateForValueChange(recordDeletedFromScreen, recordDeletedFromDB)) {
					validateFlag = true;
					break;
				}
			} else {
				validateFlag = true;
				break;
			}
		}
		return validateFlag;
	}

	public void openMapPropsMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapPropsMappingDialog starts ");

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
			productOpr.getProductRecord().setProductPropsMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getPropsMappingList(productOpr);

			productOpr.getProductRecord().setProductPropsMappingList(
					productOprRecd.getProductRecord().getProductPropsMappingList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		// get parameter
		Long categoryId = null;
		Integer level = null;
		try {

			ParameterOpr parameterOpr = new ParameterOpr();

			Parameter parameter = new Parameter();
			parameter.setParameterCode(CommonConstant.ParameterCode.ITEM_PROPS_CATEGORY);
			parameterOpr.getParameterList().add(parameter);

			Parameter parameter2 = new Parameter();
			parameter2.setParameterCode(CommonConstant.ParameterCode.ITEM_PROPS_CATEGORY_LEVEL);
			parameterOpr.getParameterList().add(parameter2);

			parameterOpr = new ProductDefinitionBD().getOptionsOnParameterCode(parameterOpr);

			ArrayList<Object> list = parameterOpr.getParameterOptionsMap().get(
					CommonConstant.ParameterCode.ITEM_PROPS_CATEGORY);

			if (list != null && list.size() > 0) {
				Parameter param = (Parameter) list.get(0);
				categoryId = param.getParameterNumberValue().longValue();
			}

			list = parameterOpr.getParameterOptionsMap().get(CommonConstant.ParameterCode.ITEM_PROPS_CATEGORY_LEVEL);

			if (list != null && list.size() > 0) {
				Parameter param = (Parameter) list.get(0);
				level = param.getParameterNumberValue().intValue();
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		// get the item list
		itemList = new ArrayList<Object>();
		try {

			ItemDVO itemDVO = new ItemDVO();
			itemDVO.getApplicationFlags().getApplicationFlagMap().put("CATEGORY", categoryId);
			itemDVO.getApplicationFlags().getApplicationFlagMap().put("LEVEL", level);

			itemList = new ProductDefinitionBD().getSuggestedItemsListForProps(itemDVO);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();
		for (Object object : itemList) {
			ItemDVO itemRecord = (ItemDVO) object;
			String itemCode = itemRecord.getCode();
			if (!uniqueValuesMap.containsKey(itemCode))
				uniqueValuesMap.put(itemCode, itemCode);
		}

		// done for populating inactive items and while save doing validations
		for (ProductPropsMappingDVO productPropsMappingRecord : productOpr.getProductRecord()
				.getProductPropsMappingList()) {
			String itemCode = productPropsMappingRecord.getItemRecord().getCode();
			if (!uniqueValuesMap.containsKey(itemCode))
				itemList.add(productPropsMappingRecord.getItemRecord());

			copyOfDataMap.put(productPropsMappingRecord.getId(),
					(ProductPropsMappingDVO) DeepCopy.copy(productPropsMappingRecord));
		}
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("productRMCodeAutoComplete", itemList);

		if (productOpr.getProductRecord().getProductPropsMappingList().isEmpty())
			productOpr.getProductRecord().getProductPropsMappingList().add(new ProductPropsMappingDVO());
	}

	public void executePropsMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductPropsMappingList().add(new ProductPropsMappingDVO());
	}

	public void executeSavePropsMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSavePropsMapping starts ");

		if (validateSavePropsMapping() && !productOpr.getProductRecord().getProductPropsMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyProps() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyProps()) {
					if (filterSavePropsMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyProps(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().executeSavePropsMapping(productOpr);
				productOpr.getProductRecord().setProductPropsMappingList(
						productOprRecd.getProductRecord().getProductPropsMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("props_save_success"));

				if (!productOprRecd.getProductRecord().getProductPropsMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapProps(true);
				else
					productOpr.getIconProductSKURecord().setMapProps(false);

				for (ProductPropsMappingDVO productPropsMappingRecord : productOpr.getProductRecord()
						.getProductPropsMappingList()) {
					copyOfDataMap.put(productPropsMappingRecord.getId(),
							(ProductPropsMappingDVO) DeepCopy.copy(productPropsMappingRecord));
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSavePropsMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		int size = productOpr.getProductRecord().getProductPropsMappingList().size();
		if (size > 0) {
			HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();

			for (int i = 0; i < size; i++) {
				ProductPropsMappingDVO productPropsMappingRecord = productOpr.getProductRecord()
						.getProductPropsMappingList().get(i);

				if (!productPropsMappingRecord.getOperationalAttributes().getRecordDeleted()) {

					Integer allocationBasedOn = productPropsMappingRecord.getItemRecord().getAllocationBasedOn()
							.getSequenceNumber();
					Integer quantity = productPropsMappingRecord.getQuantity();
					Float weight = productPropsMappingRecord.getWeight();
					String itemCode = productPropsMappingRecord.getItemRecord().getCode();
					Boolean active = productPropsMappingRecord.getItemRecord().getActive();

					if (!validator.validateNull(itemCode)) {
						addToErrorList(propertiesReader.getValueOfKey("item_null") + (i + 1));
					}

					// allocation validations
					if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
						// Quantity validations
						if (!validator.validateIntegerObjectNull(quantity)) {
							addToErrorList(propertiesReader.getValueOfKey("quantity_null") + (i + 1));
						}

					} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
						// Weight validations
						if (!validator.validateFloatObjectNull(weight)) {
							addToErrorList(propertiesReader.getValueOfKey("weight_null") + (i + 1));
						}

					} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
						// both validations
						if (!validator.validateIntegerObjectNull(quantity)) {
							addToErrorList(propertiesReader.getValueOfKey("quantity_null") + (i + 1));
						}
						if (!validator.validateFloatObjectNull(weight)) {
							addToErrorList(propertiesReader.getValueOfKey("weight_null") + (i + 1));
						}
					}

					// unique item code validations
					if (uniqueValuesMap.containsKey(itemCode)) {
						addToErrorList(propertiesReader.getValueOfKey("item_code_duplicate") + (i + 1));

					} else {
						uniqueValuesMap.put(itemCode, itemCode);
					}

					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("item_code_invalid") + (i + 1));
					}

				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean filterSavePropsMapping() {
		boolean validateFlag = false;

		for (ProductPropsMappingDVO productPropsMappingRecord : productOpr.getProductRecord()
				.getProductPropsMappingList()) {
			if (!productPropsMappingRecord.getOperationalAttributes().getRecordDeleted()) {
				if (copyOfDataMap.containsKey(productPropsMappingRecord.getId())) {
					ProductPropsMappingDVO productPropsMappingDVO = (ProductPropsMappingDVO) copyOfDataMap
							.get(productPropsMappingRecord.getId());

					String itemCodeFromScreen = productPropsMappingRecord.getItemRecord().getCode();
					Integer quantityFromScreen = productPropsMappingRecord.getQuantity();
					Float weightFromScreen = productPropsMappingRecord.getWeight();
					Boolean recordDeletedFromScreen = productPropsMappingRecord.getOperationalAttributes()
							.getRecordDeleted();

					String itemCodeFromDB = productPropsMappingDVO.getItemRecord().getCode();
					Integer quantityFromDB = productPropsMappingDVO.getQuantity();
					Float weightFromDB = productPropsMappingDVO.getWeight();
					Boolean recordDeletedFromDB = productPropsMappingDVO.getOperationalAttributes().getRecordDeleted();

					if (validateForValueChange(itemCodeFromScreen, itemCodeFromDB)
							|| validateForValueChange(quantityFromScreen, quantityFromDB)
							|| validateForValueChange(weightFromScreen, weightFromDB)
							|| validateForValueChange(recordDeletedFromScreen, recordDeletedFromDB)) {
						validateFlag = true;
						break;
					}
				} else {
					validateFlag = true;
					break;
				}
			}
		}
		return validateFlag;
	}

	public void openCatalogMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openCatalogMappingDialog starts ");

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
			productOpr.getProductRecord().setProductCatalogMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getCatalogMappingList(productOpr);

			productOpr.getProductRecord().setProductCatalogMappingList(
					productOprRecd.getProductRecord().getProductCatalogMappingList());

			for (ProductCatalogMappingDVO productCatalogMappingRecord : productOpr.getProductRecord()
					.getProductCatalogMappingList()) {
				if (!productCatalogMappingRecord.getOperationalAttributes().getRecordDeleted()) {
					copyOfDataMap.put(productCatalogMappingRecord.getId(),
							(ProductCatalogMappingDVO) DeepCopy.copy(productCatalogMappingRecord));
				}
			}
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeSaveCatalogMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveCatalogMapping starts ");

		if (validateSaveCatalogMapping()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyCatalogs() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyCatalogs()) {
					if (filterSaveCatalogMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyCatalogs(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().executeSaveCatalogMapping(productOpr);
				productOpr.getProductRecord().setProductCatalogMappingList(
						productOprRecd.getProductRecord().getProductCatalogMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("catalog_save_success"));

				copyOfDataMap = new HashMap<Long, BaseDVO>();
				int catalogSelectedCounter = 0;
				int size = productOpr.getProductRecord().getProductCatalogMappingList().size();
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						ProductCatalogMappingDVO productCatalogMappingRecord = productOpr.getProductRecord()
								.getProductCatalogMappingList().get(i);
						Boolean recordSelected = productCatalogMappingRecord.getOperationalAttributes()
								.getRecordSelected();
						if (recordSelected != null && recordSelected)
							catalogSelectedCounter++;
						copyOfDataMap.put(productCatalogMappingRecord.getId(),
								(ProductCatalogMappingDVO) DeepCopy.copy(productCatalogMappingRecord));
					}
				}

				if (catalogSelectedCounter == 0)
					productOpr.getIconProductSKURecord().setMapCatalogs(false);
				else
					productOpr.getIconProductSKURecord().setMapCatalogs(true);

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSaveCatalogMapping() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		int size = productOpr.getProductRecord().getProductCatalogMappingList().size();
		if (size > 0) {

			for (int i = 0; i < size; i++) {
				ProductCatalogMappingDVO productCatalogMappingRecord = productOpr.getProductRecord()
						.getProductCatalogMappingList().get(i);
				Boolean recordSelected = productCatalogMappingRecord.getOperationalAttributes().getRecordSelected();
				if (recordSelected != null && recordSelected) {
					Boolean active = productCatalogMappingRecord.getActive();

					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("catalog_invalid") + (i + 1));
					}
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean filterSaveCatalogMapping() {
		boolean validateFlag = false;

		for (ProductCatalogMappingDVO productCatalogMappingRecord : productOpr.getProductRecord()
				.getProductCatalogMappingList()) {
			if (!productCatalogMappingRecord.getOperationalAttributes().getRecordDeleted()) {
				if (copyOfDataMap.containsKey(productCatalogMappingRecord.getId())) {
					ProductCatalogMappingDVO productCatalogMappingDVO = (ProductCatalogMappingDVO) copyOfDataMap
							.get(productCatalogMappingRecord.getId());

					Boolean recordSelectedFromScreen = productCatalogMappingRecord.getOperationalAttributes()
							.getRecordSelected();
					Boolean recordSelectedFromDB = productCatalogMappingDVO.getOperationalAttributes()
							.getRecordSelected();

					if (validateForValueChange(recordSelectedFromScreen, recordSelectedFromDB)) {
						validateFlag = true;
						break;
					}
				} else {
					validateFlag = true;
					break;
				}
			}
		}
		return validateFlag;
	}

	public void executeCopyProductSKU(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeCopyProductSKU starts ");

		if (validateCopyProductSKU()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				productOpr = new ProductDefinitionBD().executeCopyProductSKU(productOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("product_sku_copy_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateCopyProductSKU() {

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		ProductDVO productRecord = productOpr.getProductRecord();
		CopyProductSKUDVO copyProductSKURecord = productOpr.getCopyProductSKURecord();

		Long productIdSource = copyProductSKURecord.getSourceProductRecord().getId();
		Long productIdDestination = productRecord.getId();
		Long skuIdSource = copyProductSKURecord.getSourceProductRecord().getProductSkuRecord().getId();
		Long skuIdDestination = productRecord.getProductSkuRecord().getId();

		Boolean mapCatalogs = copyProductSKURecord.getMapCatalogs();
		Boolean mapComplementarySKU = copyProductSKURecord.getMapComplementarySKU();
		Boolean mapHierarchy = copyProductSKURecord.getMapHierarchy();
		Boolean mapProcessVariation = copyProductSKURecord.getMapProcessVariation();
		Boolean mapProperties = copyProductSKURecord.getMapProperties();
		Boolean mapProps = copyProductSKURecord.getMapProps();
		Boolean mapRawMaterials = copyProductSKURecord.getMapRawMaterials();
		Boolean mapSimiliarSKU = copyProductSKURecord.getMapSimiliarSKU();

		if (productIdSource == null && productIdDestination != null && skuIdDestination == null) {
			addToErrorList(propertiesReader.getValueOfKey("select_product"));
		}

		if (skuIdSource == null && skuIdDestination != null) {
			addToErrorList(propertiesReader.getValueOfKey("select_sku"));
		}

		if (productIdSource != null && productIdDestination != null && productIdSource.equals(productIdDestination)) {
			addToErrorList(propertiesReader.getValueOfKey("product_source_destination_same"));
		}

		if (skuIdSource != null && skuIdDestination != null && skuIdSource.equals(skuIdDestination)) {
			addToErrorList(propertiesReader.getValueOfKey("sku_source_destination_same"));
		}

		if (skuIdDestination == null && !mapHierarchy && mapProperties) {
			addToErrorList(propertiesReader.getValueOfKey("copy_hierarchy_mand"));
		}

		if ((mapCatalogs != null && mapCatalogs) || (mapComplementarySKU != null && mapComplementarySKU)
				|| (mapHierarchy != null && mapHierarchy) || (mapProcessVariation != null && mapProcessVariation)
				|| (mapProperties != null && mapProperties) || (mapProps != null && mapProps)
				|| (mapRawMaterials != null && mapRawMaterials) || (mapSimiliarSKU != null && mapSimiliarSKU)) {

		} else
			addToErrorList(propertiesReader.getValueOfKey("select_one_for_copy"));

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeApproveProductSku(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeApproveProductSku starts ");

		validateForApprove = true;
		boolean validationFlag = true;
		if (renderForProduct)
			validationFlag = validateSave();

		if (validationFlag) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				if (renderForProduct)
					productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SAVE_PRODUCT");
				else if (renderForSKU)
					productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SAVE_SKU");

				productOpr = new ProductDefinitionBD().executeApproveProductSku(productOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				if (renderForProduct)
					setSuccessMsg(propertiesReader.getValueOfKey("product_approve_success"));
				else if (renderForSKU)
					setSuccessMsg(propertiesReader.getValueOfKey("sku_approve_success"));
				populateEnableDisableButtons();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void executeCancelDeactivateProduct(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeCancelDeactivateProduct starts ");
		productOpr.getProductRecord().setActive(!productOpr.getProductRecord().getActive());
	}

	public void executeDeactivateProduct(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeDeactivateProduct starts ");

		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			productOpr.getProductRecord().setUserLogin(userLogin);
			productOpr.getApplicationFlags().getApplicationFlagMap().put("DEACTIVATE_FLAG", "PRODUCT");

			ProductOpr productOprRecd = new ProductDefinitionBD().executeDeactivateProductSku(productOpr);
			productOpr.getProductRecord().setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			if (productOpr.getProductRecord().getActive())
				setSuccessMsg(propertiesReader.getValueOfKey("product_deactivate_success"));
			else
				setSuccessMsg(propertiesReader.getValueOfKey("product_activate_success"));

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeDeactivateProductSku(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeDeactivateProductSku starts ");

		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			productOpr.getProductRecord().setUserLogin(userLogin);
			productOpr.getApplicationFlags().getApplicationFlagMap().put("DEACTIVATE_FLAG", "SKU");

			ProductOpr productOprRecd = new ProductDefinitionBD().executeDeactivateProductSku(productOpr);
			productOpr.getProductRecord().setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			if (productOpr.getProductRecord().getProductSkuRecord().getActive())
				setSuccessMsg(propertiesReader.getValueOfKey("sku_deactivate_success"));
			else
				setSuccessMsg(propertiesReader.getValueOfKey("sku_activate_success"));

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
			productOpr.getProductRecord().getProductSkuRecord()
					.setActive(!productOpr.getProductRecord().getProductSkuRecord().getActive());
		}
	}

	public void openModifyProductSkuDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openModifyProductSkuDialog starts ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());

		if (renderForProduct) {

			if (productOpr.getProductRecord().getActive()) {
				addToErrorList(propertiesReader.getValueOfKey("product_should_be_active"));
			}
		} else {
			if (productOpr.getProductRecord().getProductSkuRecord().getActive()) {
				addToErrorList(propertiesReader.getValueOfKey("sku_should_be_active"));
			}
		}

		if (getErrorList().size() == 0) {
			try {

				String transactionCode = CommonConstant.TransactionCodes.MODIFY_PRODUCT_SKU;
				allOptions.getAllOptionsValues().put("reasonList",
						new ProductDefinitionBD().getReasonCodeList(transactionCode));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
			RequestContext.getCurrentInstance().addCallbackParam("displayConfirmation", true);
		} else {
			RequestContext.getCurrentInstance().addCallbackParam("displayConfirmation", false);
		}
	}

	public void executeModifyProductSku(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeModifyProductSku starts ");

		if (validateModifyProductSKU()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				productOpr = new ProductDefinitionBD().modifyProductSKU(productOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				if (productOpr.getProductRecord().getProductSkuRecord().getId() != null)
					setSuccessMsg(propertiesReader.getValueOfKey("sku_modify_success"));
				else
					setSuccessMsg(propertiesReader.getValueOfKey("product_modify_success"));
				populateEnableDisableButtons();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateModifyProductSKU() {

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		String reasonCode = productOpr.getProductRecord().getModifyReasonRecord().getCode();

		if (reasonCode == null) {
			addToErrorList(propertiesReader.getValueOfKey("reason_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void openReport(ActionEvent event) {
		try {
			openPopup("/report/productdefinition/productskudetailsreport/");
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
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
		myLog.debug("In Product Definition Add Edit BB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		SkuOpr skuOprSent = new SkuOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, skuOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");

	}

	private void getProductSkuHeaderDetails() {

		// get product or sku details
		if (productOpr.getProductRecord().getId() != null
				|| productOpr.getProductRecord().getProductSkuRecord().getId() != null) {
			try {
				productOpr = new ProductDefinitionBD().getProductDetails(productOpr);

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void executeCreateNewSKU(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeCreateNewSKU starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ProductOpr productOprSent = new ProductOpr();
		productOprSent.getProductRecord().setId(productOpr.getProductRecord().getId());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");

	}

	public void openOtherSkuMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openOtherSkuMappingDialog starts "
				+ productOpr.getProductRecord().getProductSkuRecord().getId());

		try {
			// ArrayList<ProductSkuDVO> skuDVOList = new
			// ArrayList<ProductSkuDVO>();
			List<Object> skuListForAutoSuggest = new ArrayList<Object>();
			productOpr.getProductRecord().setProductOtherSkuMappingList(null);
			productOpr.getProductRecord().setProductOtherSkuMappingDVO(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getOtherSkuMappingList(productOpr);
			productOpr.getProductRecord().setProductOtherSkuMappingDVO(
					productOprRecd.getProductRecord().getProductOtherSkuMappingDVO());
			productOpr.getProductRecord().setProductOtherSkuMappingList(
					productOprRecd.getProductRecord().getProductOtherSkuMappingList());

			if (productOpr.getProductRecord().getProductOtherSkuMappingList().size() > 0) {
				productOpr.getIconProductSKURecord().setMapOtherSKU(true);
				for (ProductOtherSKUMappingDVO listDVO : productOpr.getProductRecord().getProductOtherSkuMappingList()) {
					skuListForAutoSuggest.add(listDVO.getSubProductSkuRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productOtherSkuCodeAutoComplete", skuListForAutoSuggest);

			}
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeOtherSkuMappingAddRow(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		productOpr.getProductRecord().getProductOtherSkuMappingList().add(new ProductOtherSKUMappingDVO());
		if (productOpr.getProductRecord().getProductOtherSkuMappingList() != null
				&& productOpr.getProductRecord().getProductOtherSkuMappingList().size() > 0) {
			myLog.debug("SIZE ADD :: " + productOpr.getProductRecord().getProductOtherSkuMappingList().size());
			ArrayList<Object> skuCodeListObj = new ArrayList<Object>();
			for (ProductOtherSKUMappingDVO listDVO : productOpr.getProductRecord().getProductOtherSkuMappingList()) {
				myLog.debug("CODE :: " + listDVO.getSubProductSkuRecord().getId());
				myLog.debug("CODE :: " + listDVO.getSubProductSkuRecord().getCode());
				skuCodeListObj.add(listDVO.getSubProductSkuRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getAttributes()
					.put("productOtherSkuCodeAutoComplete", skuCodeListObj);
		}
	}

	public List<Object> getSuggestedSkuDetailsForCode(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In getSuggestedSkuDetailsForCode starts ");
		List<Object> skuListForAutoSuggest = (List<Object>) FacesContext.getCurrentInstance().getViewRoot()
				.getViewMap().get("productOtherSkuCodeAutoComplete");
		if (query != null && query.trim().length() > 0) {
			try {
				ProductSkuDVO productSkuDVO = new ProductSkuDVO();
				productSkuDVO.setCode(query);
				productSkuDVO.getApplicationFlags().getApplicationFlagMap()
						.put("MAIN_PRODUCT_SKU_ID", productOpr.getProductRecord().getProductSkuRecord().getId());
				ArrayList<Object> skuObjList = new ProductDefinitionBD().getSuggestedOtherSKUList(productSkuDVO);
				// ArrayList<Object> skuObjListAutoSuggest = new
				// ArrayList<Object>();

				HashMap<String, Object> listMap = new HashMap<String, Object>();
				if (skuListForAutoSuggest != null && skuListForAutoSuggest.size() > 0) {
					for (Object listObj : skuListForAutoSuggest) {
						ProductSkuDVO listDVO = (ProductSkuDVO) listObj;
						String skuCode = listDVO.getCode();
						myLog.debug("MAP::::" + skuCode);
						if (skuCode != null) {
							listMap.put(skuCode, listDVO);
						}
					}
				} else {
					skuListForAutoSuggest = new ArrayList<Object>();
				}

				if (skuObjList != null && skuObjList.size() > 0) {
					for (Object listObj : skuObjList) {
						ProductSkuDVO listDVO = (ProductSkuDVO) listObj;
						String skuCode = listDVO.getCode();

						if (listMap != null && skuCode != null && !listMap.containsKey(skuCode)) {
							skuListForAutoSuggest.add(listObj);
						}
					}
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productOtherSkuCodeAutoComplete", skuListForAutoSuggest);
				return skuObjList;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return skuListForAutoSuggest;
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
}
