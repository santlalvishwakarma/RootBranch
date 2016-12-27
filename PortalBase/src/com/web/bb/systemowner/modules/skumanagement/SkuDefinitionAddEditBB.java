package com.web.bb.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.web.bf.systemowner.modules.skumanagement.SkuDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
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
}
