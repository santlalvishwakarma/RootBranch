package com.web.bb.systemowner.modules.productmanagement;

import java.io.File;
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

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.systemowner.ProductOpr;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.BaseDVOComparator;
import com.web.util.CommonUtil;
import com.web.util.MessageFormatter;
import com.web.util.PropertiesReader;
import com.web.util.deepcopy.DeepCopy;

/**
 * @author NIRAJ
 * 
 */
public class ProductDefinitionAddEditBB extends BackingBean {

	private static final long serialVersionUID = 2279688254567743485L;
	private String propertiesLocation = "com/tsd/retail/backingbeans/modules/psd/productdefinition/productdefinition";
	private ProductOpr productOpr;
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

	public ProductOpr getProductOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			productOpr = (ProductOpr) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

			// retrive initial data
			retrieveData();
		} else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			if (productOpr == null) {
				productOpr = (ProductOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.ACTIVE_TAB_OPR);
				renderGetBackToAutoSku = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.remove(CommonConstant.ACTIVE_TAB_OPR);
			}
			// retrive initial data
			retrieveData();
		}
		if (productOpr == null) {
			productOpr = new ProductOpr();
		}
		return productOpr;
	}

	public void setProductOpr(ProductOpr productOpr) {
		this.productOpr = productOpr;
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

	public ProductHierarchyDVO getSelectedProductHierarchyRecord() {
		if (selectedProductHierarchyRecord == null) {
			selectedProductHierarchyRecord = new ProductHierarchyDVO();
		}
		return selectedProductHierarchyRecord;
	}

	public void setSelectedProductHierarchyRecord(ProductHierarchyDVO selectedProductHierarchyRecord) {
		this.selectedProductHierarchyRecord = selectedProductHierarchyRecord;
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

	public Map<Long, ProcessDVO> getProcessActiveMap() {
		if (processActiveMap == null) {
			processActiveMap = new HashMap<Long, ProcessDVO>();
		}
		return processActiveMap;
	}

	public void setProcessActiveMap(HashMap<Long, ProcessDVO> processActiveMap) {
		this.processActiveMap = processActiveMap;
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

	public Map<Integer, ProductCategoryLevelDVO> getLevelNameMap() {
		if (levelNameMap == null) {
			levelNameMap = new HashMap<Integer, ProductCategoryLevelDVO>();
		}
		return levelNameMap;
	}

	public void setLevelNameMap(HashMap<Integer, ProductCategoryLevelDVO> levelNameMap) {
		this.levelNameMap = levelNameMap;
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

	public ProductItemPropertyMappingDVO getSelectedItemCategory() {
		if (selectedItemCategory == null) {
			selectedItemCategory = new ProductItemPropertyMappingDVO();
		}
		return selectedItemCategory;
	}

	public void setSelectedItemCategory(ProductItemPropertyMappingDVO selectedItemCategory) {
		this.selectedItemCategory = selectedItemCategory;
	}

	public boolean isRenderGetBackToAutoSku() {
		return renderGetBackToAutoSku;
	}

	public void setRenderGetBackToAutoSku(boolean renderGetBackToAutoSku) {
		this.renderGetBackToAutoSku = renderGetBackToAutoSku;
	}

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	@Override
	public void clearPage(ActionEvent event) {
	}

	@Override
	public void resetPage(ActionEvent event) {
	}

	@Override
	public void executeSave(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSave starts ");

		validateForApprove = false;
		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);
				productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SAVE_PRODUCT");

				productOpr = new ProductDefinitionBD().executeSaveProductSKUDetails(productOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("product_save_success"));
				populateEnableDisableButtons();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		// product validations
		ProductDVO productRecord = productOpr.getProductRecord();
		String productCode = productRecord.getCode();
		String productName = productRecord.getName();
		// String defaultUomCode = productRecord.getUomRecord().getCode();
		Integer allocationBasedOnId = productRecord.getAllocationBasedOn().getParameterID();
		Integer allocationBasedOn = null;
		Integer defaultPricingModel = productRecord.getDefaultPricingModel().getParameterID();
		String quantityUomCode = productRecord.getUomRecord().getCode();
		String weightUomCode = productRecord.getWeightUomRecord().getCode();

		if (!validator.validateNull(productCode)) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_null"));
		}
		if (productCode != null && !validator.validateCharsWithoutSpecialChars(productCode)) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_invalid"));
		}

		if (!validator.validateNull(productName)) {
			addToErrorList(propertiesReader.getValueOfKey("product_name_null"));
		}
		// if (!validator.validateNull(defaultUomCode)) {
		// addToErrorList(propertiesReader.getValueOfKey("uom_code_null"));
		// }
		// if (!validator.validateIntegerObjectNull(allocationBasedOnId)) {
		// addToErrorList(propertiesReader.getValueOfKey("allocation_basis_null"));
		// }
		if (!validator.validateIntegerObjectNull(defaultPricingModel)) {
			addToErrorList(propertiesReader.getValueOfKey("pricing_model_null"));
		}

		// HashMap<String, String> uniqueValuesMap = new HashMap<String,
		// String>();
		// for (UomDVO uomDVO : productRecord.getUomList()) {
		// String uomCode = uomDVO.getCode();
		// // unique uom validations
		// if (uniqueValuesMap.containsKey(uomCode)) {
		//
		// String errorMsg = propertiesReader.getValueOfKey("uom_duplicate");
		// String[] arrString = { uomCode };
		// errorMsg = MessageFormatter.getFormattedMessage(errorMsg, arrString);
		// addToErrorList(errorMsg);
		//
		// } else {
		// uniqueValuesMap.put(uomCode, uomCode);
		// }
		// }

		if (validateForApprove) {

			if (!validator.validateIntegerObjectNull(allocationBasedOnId)) {
				addToErrorList(propertiesReader.getValueOfKey("allocation_basis_null"));
			} else {

				ArrayList<Object> allocationBasisList = getAllOptions().getAllOptionsValues()
						.get("allocationBasisList");
				if (allocationBasisList != null && allocationBasedOnId != null) {
					for (Object object : allocationBasisList) {
						Parameter parameter = (Parameter) object;

						if (allocationBasedOnId.equals(parameter.getParameterID())) {
							allocationBasedOn = parameter.getSequenceNumber();
							productRecord.getAllocationBasedOn().setSequenceNumber(allocationBasedOn);
							break;
						}
					}
				}

				// allocation validations
				if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
					// Quantity validations
					if (!validator.validateNull(quantityUomCode)) {
						addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null"));
					}

				} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
					// Weight validations
					if (!validator.validateNull(weightUomCode)) {
						addToErrorList(propertiesReader.getValueOfKey("weight_uom_null"));
					}

				} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
					// both validations
					if (!validator.validateNull(quantityUomCode)) {
						addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null"));
					}
					if (!validator.validateNull(weightUomCode)) {
						addToErrorList(propertiesReader.getValueOfKey("weight_uom_null"));
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

	public void executeSaveSKU(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveSKU starts ");

		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			productOpr.getProductRecord().setUserLogin(userLogin);

			productOpr.getApplicationFlags().getApplicationFlagMap().put("SAVE_FLAG", "SAVE_SKU");
			productOpr = new ProductDefinitionBD().executeSaveProductSKUDetails(productOpr);

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("sku_save_success"));
			populateEnableDisableButtons();

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	@Override
	public void addEditTabClicked(ActionEvent event) {
	}

	@Override
	public void searchTabClicked(ActionEvent event) {
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: retrieveData starts ");
		// try {
		// UomDVO uomDVO = new UomDVO();
		// uomListForAutoSuggest = new
		// ProductDefinitionBD().getSuggestedUOMList(uomDVO);
		// FacesContext.getCurrentInstance().getViewRoot().getViewMap()
		// .put("productUOMAutoComplete", uomListForAutoSuggest);
		//
		// } catch (FrameworkException e) {
		// handleException(e, propertiesLocation);
		//
		// } catch (BusinessException e) {
		// handleException(e, propertiesLocation);
		// }

		// get all options values
		allOptions = new OptionsDVO();

		// if (allOptions.getAllOptionsValues().isEmpty()) {
		try {

			allOptions.setAllOptionsValues(new ProductDefinitionBD().getAllOptionsValuesForProduct());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("itemCategoryDropDown", allOptions.getAllOptionsValues().get("itemCategoryList"));

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		// }

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

		String editDetails = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("EDIT_DETAILS");
		if (editDetails == null) {
			editDetails = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("EDIT_DETAILS");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("EDIT_DETAILS");
		}
		renderForSKU = false;
		renderForProduct = false;

		if (editDetails != null && editDetails.trim().equals("SKU")) {
			renderForSKU = true;
		} else {
			renderForProduct = true;
		}
		populateEnableDisableButtons();

		myLog.debug("In Product Definition Add Edit BB :: retrieveData ends ");
	}

	private void populateEnableDisableButtons() {

		disableSaveButton = true;
		disableApproveButton = false;
		renderPopupLinks = false;
		disableModifyButton = false;
		disableFields = false;
		disableActivateButton = false;

		String productStatusCode = productOpr.getProductRecord().getStatusRecord().getCode();
		String skuStatusCode = productOpr.getProductRecord().getProductSkuRecord().getStatusRecord().getCode();

		if (renderForProduct) {

			if (productStatusCode == null || CommonConstant.StatusCodes.NEW.equals(productStatusCode)) {
				disableSaveButton = false;
				disableModifyButton = true;

			} else if (CommonConstant.StatusCodes.APPROVED.equals(productStatusCode)) {
				disableApproveButton = true;

			} else if (CommonConstant.StatusCodes.INACTIVE.equals(productStatusCode)) {
				disableModifyButton = true;
				disableApproveButton = true;
				disableActivateButton = true;
			}

			if (productOpr.getProductRecord().getId() != null) {
				renderPopupLinks = true;
			}

		} else if (renderForSKU) {

			if (skuStatusCode == null || CommonConstant.StatusCodes.NEW.equals(skuStatusCode)) {
				disableSaveButton = false;
				disableModifyButton = true;

			} else if (CommonConstant.StatusCodes.APPROVED.equals(skuStatusCode)) {
				disableApproveButton = true;

			} else if (CommonConstant.StatusCodes.INACTIVE.equals(skuStatusCode)) {
				disableModifyButton = true;
				disableApproveButton = true;
				disableActivateButton = true;
			}

			if (productOpr.getProductRecord().getProductSkuRecord().getId() != null) {
				renderPopupLinks = true;
			}
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

	public List<Object> getSuggestedUOMList(String query) {
		List<Object> uomList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			// for (Object object : uomListForAutoSuggest) {
			// UomDVO uomDVO = (UomDVO) object;
			// String name = uomDVO.getName();
			//
			// if (name.toUpperCase().startsWith(query)) {
			// uomList.add(uomDVO);
			// }
			// }
		}
		return uomList;
	}

	public void openMapHierarchyDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapHierarchyLink starts ");

		try {

			hierarchyListForAutoSuggest = new ProductDefinitionBD().getSuggestedHierarchies(new HierarchyDVO());

			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productHierarchyCodeAutoComplete", hierarchyListForAutoSuggest);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productHierarchyNameAutoComplete", hierarchyListForAutoSuggest);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productHierarchyDescriptionAutoComplete", hierarchyListForAutoSuggest);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();
			productOpr.getProductRecord().setProductHierarchyMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getHierarchiesMappingList(productOpr);
			productOpr.getProductRecord().setProductHierarchyMappingList(
					productOprRecd.getProductRecord().getProductHierarchyMappingList());

			for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
					.getProductHierarchyMappingList()) {
				if (!productHierarchyRecord.getOperationalAttributes().getRecordDeleted()) {
					copyOfDataMap.put(productHierarchyRecord.getId(),
							(ProductHierarchyMappingDVO) DeepCopy.copy(productHierarchyRecord));
				}
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		populateDefaultHierarchy();

		if (productOpr.getProductRecord().getProductHierarchyMappingList().isEmpty())
			productOpr.getProductRecord().getProductHierarchyMappingList().add(new ProductHierarchyMappingDVO());
	}

	private void populateDefaultHierarchy() {
		for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
				.getProductHierarchyMappingList()) {

			if (!productHierarchyRecord.getOperationalAttributes().getRecordDeleted()) {
				Boolean fetchProperties = productHierarchyRecord.getFetchProperties();
				if (fetchProperties) {
					this.productHierarchyRecord = (ProductHierarchyMappingDVO) DeepCopy.copy(productHierarchyRecord);
					break;
				}
			}
		}
	}

	public void executeHierarchyMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductHierarchyMappingList().add(new ProductHierarchyMappingDVO());
	}

	public List<Object> getSuggestedHierarchiesForCode(String query) {
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				ProductHierarchyDVO productHierarchyRecord = (ProductHierarchyDVO) object;
				String code = productHierarchyRecord.getCode();

				if (code.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}
		return productHierarchyList;
	}

	public List<Object> getSuggestedHierarchiesForName(String query) {
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				ProductHierarchyDVO productHierarchyRecord = (ProductHierarchyDVO) object;
				String name = productHierarchyRecord.getName();

				if (name.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}
		return productHierarchyList;
	}

	public List<Object> getSuggestedHierarchiesForDescription(String query) {
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				ProductHierarchyDVO productHierarchyRecord = (ProductHierarchyDVO) object;
				String description = productHierarchyRecord.getDescription();

				if (description != null && description.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}
		return productHierarchyList;
	}

	public void validateSaveProductHierarchyMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: validateSaveProductHierarchyMapping starts ");
		if (this.productHierarchyRecord == null)
			this.productHierarchyRecord = new ProductHierarchyMappingDVO();
		Long hierarchyMappingIdFromDB = this.productHierarchyRecord.getId();
		Long hierarchyIdFromDB = this.productHierarchyRecord.getProductHierarchyRecord().getId();
		myLog.debug(" hierarchy id db ---> " + hierarchyIdFromDB);
		setErrorList(new ArrayList<String>());

		Boolean displayConfirmation = false;
		if (hierarchyMappingIdFromDB != null && hierarchyIdFromDB != null) {
			for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
					.getProductHierarchyMappingList()) {

				if (productHierarchyRecord.getId() != null
						&& productHierarchyRecord.getId().equals(hierarchyMappingIdFromDB)) {

					Boolean fetchProperties = productHierarchyRecord.getFetchProperties();
					Long hierarchyId = productHierarchyRecord.getProductHierarchyRecord().getId();

					if (!fetchProperties || productHierarchyRecord.getOperationalAttributes().getRecordDeleted())
						displayConfirmation = true;
					else if (hierarchyId != null && !hierarchyId.equals(hierarchyIdFromDB))
						displayConfirmation = true;

					if (disableFields && displayConfirmation) {
						PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
						addToErrorList(propertiesReader.getValueOfKey("hierarchy_cannot_be_modified"));
						displayConfirmation = false;
					}
					break;
				}
			}
		}
		RequestContext.getCurrentInstance().addCallbackParam("displayConfirmation", displayConfirmation);
	}

	public void executeSaveHierarchyMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveHierarchyMapping starts ");

		if (getErrorList().size() > 0) {
			String errorMsg = getErrorList().get(0);
			setErrorList(new ArrayList<String>());
			addToErrorList(errorMsg);

		} else if (validateSaveProductHierarchyMapping()
				&& !productOpr.getProductRecord().getProductHierarchyMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyHierarchy() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyHierarchy()) {
					if (filterSaveProductHierarchyMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyHierarchy(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveHierarchiesMappingList(productOpr);
				// get product or sku details
				getProductSkuHeaderDetails();

				productOpr.getProductRecord().setProductHierarchyMappingList(
						productOprRecd.getProductRecord().getProductHierarchyMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());
				populateDefaultHierarchy();

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("hierarchy_save_success"));

				if (!productOprRecd.getProductRecord().getProductHierarchyMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapHierarchy(true);
				else
					productOpr.getIconProductSKURecord().setMapHierarchy(false);

				productOpr.getIconProductSKURecord().setMapProperties(
						productOprRecd.getIconProductSKURecord().getMapProperties());

				copyOfDataMap = new HashMap<Long, BaseDVO>();
				for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
						.getProductHierarchyMappingList()) {
					if (!productHierarchyRecord.getOperationalAttributes().getRecordDeleted()) {
						copyOfDataMap.put(productHierarchyRecord.getId(),
								(ProductHierarchyMappingDVO) DeepCopy.copy(productHierarchyRecord));
					}
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSaveProductHierarchyMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		int size = productOpr.getProductRecord().getProductHierarchyMappingList().size();
		int defaultSelectedCount = 0;

		if (size > 0) {
			HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();
			for (int i = 0; i < size; i++) {
				ProductHierarchyMappingDVO productHierarchyRecord = productOpr.getProductRecord()
						.getProductHierarchyMappingList().get(i);

				if (!productHierarchyRecord.getOperationalAttributes().getRecordDeleted()) {

					Long hierarchyId = productHierarchyRecord.getProductHierarchyRecord().getId();
					Boolean fetchProperties = productHierarchyRecord.getFetchProperties();

					if (!validator.validateLongObjectNull(hierarchyId)) {
						addToErrorList(propertiesReader.getValueOfKey("hierarchy_null") + (i + 1));
					}
					if (fetchProperties != null && fetchProperties)
						defaultSelectedCount++;

					// unique property value validations
					if (hierarchyId != null) {
						if (uniqueValuesMap.containsKey(hierarchyId)) {
							addToErrorList(propertiesReader.getValueOfKey("hierarchy_duplicate") + (i + 1));

						} else {
							uniqueValuesMap.put(hierarchyId, hierarchyId);
						}
					}
				}
			}
		}
		if (defaultSelectedCount != 1) {
			addToErrorList(propertiesReader.getValueOfKey("select_one_for_properties_mapping"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public boolean filterSaveProductHierarchyMapping() {
		boolean validateFlag = false;
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
				.getProductHierarchyMappingList()) {
			if (!productHierarchyRecord.getOperationalAttributes().getRecordDeleted()) {
				if (copyOfDataMap.containsKey(productHierarchyRecord.getId())) {
					ProductHierarchyMappingDVO productHierarchyMappingDVO = (ProductHierarchyMappingDVO) copyOfDataMap
							.get(productHierarchyRecord.getId());

					Long hierarchyIdFromScreen = productHierarchyRecord.getProductHierarchyRecord().getId();
					Long hierarchyIdFromDB = productHierarchyMappingDVO.getProductHierarchyRecord().getId();
					Boolean recordDeletedFromScreen = productHierarchyRecord.getOperationalAttributes()
							.getRecordDeleted();
					Boolean recordDeletedFromDB = productHierarchyRecord.getOperationalAttributes().getRecordDeleted();

					myLog.debug(" hierarchy id ---> " + hierarchyIdFromScreen);
					myLog.debug(" hierarchy id from db ---> " + hierarchyIdFromDB);
					if (validateForValueChange(hierarchyIdFromScreen, hierarchyIdFromDB)
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

	public String openEditHierarchyDialog() {

		if (levelNameMap == null || levelNameMap.isEmpty()) {
			try {

				List<Object> levelList = new ProductDefinitionBD().getAllLevelsNames();
				levelNameMap = new HashMap<Integer, ProductCategoryLevelDVO>();

				for (Object object : levelList) {
					ProductCategoryLevelDVO productCategoryLevelRecord = (ProductCategoryLevelDVO) object;
					levelNameMap.put(productCategoryLevelRecord.getLevel(), productCategoryLevelRecord);
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		Long productHierarchyId = selectedProductHierarchyRecord.getId();
		try {

			selectedProductHierarchyRecord.setProductHierarchyCategoryMappingList(null);
			ProductHierarchyOpr productHierarchyOprRet = new ProductDefinitionBD()
					.getProductHierarchyLevelList(productHierarchyId);

			selectedProductHierarchyRecord.setProductHierarchyCategoryMappingList(productHierarchyOprRet
					.getProductHierarchyRecord().getProductHierarchyCategoryMappingList());

			for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : selectedProductHierarchyRecord
					.getProductHierarchyCategoryMappingList()) {
				Integer level = productHierarchyCategoryMappingDVO.getProductCategoryLevelRecord().getLevel();
				productHierarchyCategoryMappingDVO.setProductCategoryLevelRecord(levelNameMap.get(level));
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		return null;
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

	public void openMapProcessVariationMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapProcessVariationMappingDialog starts ");

		try {
			productOpr.getProductRecord().setProductRMDetailsList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getProcessVariationMappingList(productOpr);
			productOpr.getProductRecord().setProductProcessVariationMappingList(
					productOprRecd.getProductRecord().getProductProcessVariationMappingList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		populateProcessVariationData();

		// get process list
		ArrayList<Object> processList = new ArrayList<Object>();
		try {

			processList = new ProductDefinitionBD().getSuggestedProcessList(new ProcessDVO());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		try {
			processVariationList = new ArrayList<Object>();
			processVariationList = new ProductDefinitionBD()
					.getSuggestedProcessVariationList(new ProcessVariationMappingDVO());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		// unique values
		processActiveMap = new HashMap<Long, ProcessDVO>();
		HashMap<String, String> uniqueValuesMap = new HashMap<String, String>();
		for (Object object : processList) {
			ProcessDVO processRecord = (ProcessDVO) object;
			String code = processRecord.getCode();

			if (!uniqueValuesMap.containsKey(code))
				uniqueValuesMap.put(code, code);
			processActiveMap.put(processRecord.getId(), processRecord);
		}

		HashMap<Long, Long> uniqueValuesPVMap = new HashMap<Long, Long>();
		for (Object object : processVariationList) {
			ProcessVariationMappingDVO processVariationMappingRecord = (ProcessVariationMappingDVO) object;
			Long id = processVariationMappingRecord.getId();
			if (!uniqueValuesPVMap.containsKey(id))
				uniqueValuesPVMap.put(id, id);
		}

		// done for populating inactive items and while save doing validations
		for (ProductProcessVariationMappingDVO productProcessVariationMappingDVO : productOpr.getProductRecord()
				.getProductProcessVariationMappingList()) {

			String processCode = productProcessVariationMappingDVO.getProcessRecord().getCode();
			if (processCode != null && !uniqueValuesMap.containsKey(processCode)) {
				processList.add(productProcessVariationMappingDVO.getProcessRecord());
				processActiveMap.put(productProcessVariationMappingDVO.getProcessRecord().getId(),
						productProcessVariationMappingDVO.getProcessRecord());
			}

			if (!productProcessVariationMappingDVO.getProcessRecord().getProcessVariationMappingList().isEmpty()) {

				for (ProcessVariationMappingDVO processVariationMappingDVO : productProcessVariationMappingDVO
						.getProcessRecord().getProcessVariationMappingList()) {
					Long processVariationDetailsId = processVariationMappingDVO.getId();
					if (!uniqueValuesPVMap.containsKey(processVariationDetailsId))
						processVariationList.add(processVariationMappingDVO);
				}
			}
		}

		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("processVariationAutoComplete", processVariationList);
		allOptions.getAllOptionsValues().put("processList", processList);

		if (productOpr.getProductRecord().getProductProcessVariationMappingList().isEmpty())
			productOpr.getProductRecord().getProductProcessVariationMappingList()
					.add(new ProductProcessVariationMappingDVO());
	}

	private void populateProcessVariationData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		copyOfDataMap = new HashMap<Long, BaseDVO>();

		for (ProductProcessVariationMappingDVO productProcessVariationMappingRecord : productOpr.getProductRecord()
				.getProductProcessVariationMappingList()) {
			Collections.sort(productProcessVariationMappingRecord.getProcessRecord().getProcessVariationMappingList(),
					new BaseDVOComparator());

			String processVariationString = "";
			for (ProcessVariationMappingDVO processVariationMappingDVO : productProcessVariationMappingRecord
					.getProcessRecord().getProcessVariationMappingList()) {
				Long processVariationDetailsId = processVariationMappingDVO.getId();

				if (processVariationDetailsId != null)
					processVariationString += processVariationDetailsId + "~";
				else
					processVariationString += "~";
			}
			myLog.debug(" processVariationString ---> " + processVariationString);
			productProcessVariationMappingRecord.setProcessVariationString(processVariationString);

			if (!productProcessVariationMappingRecord.getOperationalAttributes().getRecordDeleted()) {
				copyOfDataMap.put(productProcessVariationMappingRecord.getId(),
						(ProductProcessVariationMappingDVO) DeepCopy.copy(productProcessVariationMappingRecord));
			}
		}
	}

	public void executeProcessVariationMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductProcessVariationMappingList()
				.add(new ProductProcessVariationMappingDVO());
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

	public void processCodeChanged(ProductProcessVariationMappingDVO productProcessVariationMappingDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: processCodeChanged starts ");

		Object processIdStr = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("processId");
		myLog.debug(" processIdStr :: " + processIdStr);

		productProcessVariationMappingDVO.getProcessRecord().setProcessVariationMappingList(
				new ArrayList<ProcessVariationMappingDVO>());
	}

	public void executeSaveProcessVariationMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveProcessVariationMapping starts ");

		if (validateSaveProcessVariationMapping()
				&& !productOpr.getProductRecord().getProductProcessVariationMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyProcessVariation() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyProcessVariation()) {
					if (filterSaveProductProcessVariationMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyProcessVariation(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveProcessVariationMappingList(productOpr);
				productOpr.getProductRecord().setProductProcessVariationMappingList(
						productOprRecd.getProductRecord().getProductProcessVariationMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("process_variation_save_success"));

				if (!productOprRecd.getProductRecord().getProductProcessVariationMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapProcessVariation(true);
				else
					productOpr.getIconProductSKURecord().setMapProcessVariation(false);
				populateProcessVariationData();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	public boolean validateSaveProcessVariationMapping() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		int size = productOpr.getProductRecord().getProductProcessVariationMappingList().size();
		if (size > 0) {
			HashMap<Long, Long> uniqueValuesMap = new HashMap<Long, Long>();

			for (int i = 0; i < size; i++) {
				ProductProcessVariationMappingDVO productProcessVariationMappingDVO = productOpr.getProductRecord()
						.getProductProcessVariationMappingList().get(i);

				if (!productProcessVariationMappingDVO.getOperationalAttributes().getRecordDeleted()) {
					Long processId = productProcessVariationMappingDVO.getProcessRecord().getId();
					Boolean active = processActiveMap.get(processId).getActive();

					if (!validator.validateLongObjectNull(processId)) {
						addToErrorList(propertiesReader.getValueOfKey("process_null") + (i + 1));
					}

					// unique process validations
					if (uniqueValuesMap.containsKey(processId)) {
						addToErrorList(propertiesReader.getValueOfKey("process_duplicate") + (i + 1));

					} else {
						uniqueValuesMap.put(processId, processId);
					}
					if (active == null || !active) {
						addToErrorList(propertiesReader.getValueOfKey("process_code_invalid") + (i + 1));
					}

					if (!productProcessVariationMappingDVO.getProcessRecord().getProcessVariationMappingList()
							.isEmpty()) {
						HashMap<Long, Long> uniquePVValuesMap = new HashMap<Long, Long>();
						for (ProcessVariationMappingDVO processVariationMappingDVO : productProcessVariationMappingDVO
								.getProcessRecord().getProcessVariationMappingList()) {
							Long processVariationDetailsId = processVariationMappingDVO.getId();
							Boolean pvActive = processVariationMappingDVO.getActive();

							// unique pv validations
							if (uniquePVValuesMap.containsKey(processVariationDetailsId)) {
								addToErrorList(propertiesReader.getValueOfKey("process_variation_duplicate") + (i + 1));

							} else {
								uniquePVValuesMap.put(processVariationDetailsId, processVariationDetailsId);
							}

							if (pvActive == null || !pvActive) {
								String errorMsg = propertiesReader.getValueOfKey("process_variation_invalid");
								String[] arrString = { processVariationMappingDVO.getName() };
								errorMsg = MessageFormatter.getFormattedMessage(errorMsg, arrString);
								addToErrorList(errorMsg + (i + 1));
							}
						}
					} else {
						addToErrorList(propertiesReader.getValueOfKey("process_variation_null") + (i + 1));
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

	private boolean filterSaveProductProcessVariationMapping() {
		boolean validateFlag = false;
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		for (ProductProcessVariationMappingDVO productProcessVariationMappingRecord : productOpr.getProductRecord()
				.getProductProcessVariationMappingList()) {

			if (copyOfDataMap.containsKey(productProcessVariationMappingRecord.getId())) {
				ProductProcessVariationMappingDVO productProcessVariationMappingDVO = (ProductProcessVariationMappingDVO) copyOfDataMap
						.get(productProcessVariationMappingRecord.getId());

				Long processIdFromScreen = productProcessVariationMappingRecord.getProcessRecord().getId();
				Boolean recordDeletedFromScreen = productProcessVariationMappingRecord.getOperationalAttributes()
						.getRecordDeleted();

				Long processIdFromDB = productProcessVariationMappingDVO.getProcessRecord().getId();
				Boolean recordDeletedFromDB = productProcessVariationMappingDVO.getOperationalAttributes()
						.getRecordDeleted();

				if (validateForValueChange(processIdFromScreen, processIdFromDB)
						|| validateForValueChange(recordDeletedFromScreen, recordDeletedFromDB)) {
					validateFlag = true;
					break;

				} else {
					Collections.sort(productProcessVariationMappingRecord.getProcessRecord()
							.getProcessVariationMappingList(), new BaseDVOComparator());

					String processVariationStringFromDB = productProcessVariationMappingDVO.getProcessVariationString();
					String processVariationStringFromScreen = "";

					for (ProcessVariationMappingDVO processVariationMappingDVO : productProcessVariationMappingRecord
							.getProcessRecord().getProcessVariationMappingList()) {
						Long processVariationDetailsId = processVariationMappingDVO.getId();

						if (processVariationDetailsId != null)
							processVariationStringFromScreen += processVariationDetailsId + "~";
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

	public List<Object> getSuggestedProductsList(String query) {
		try {
			ProductDVO productDVO = new ProductDVO();
			productDVO.setName(query);
			ArrayList<Object> productList = new ProductDefinitionBD().getSuggestedProductsList(productDVO);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("productCodeAutoComplete", productList);
			return productList;

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		return null;
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

	public void openImageMappingDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openImageMappingDialog starts ");

		try {
			copyOfDataMap = new HashMap<Long, BaseDVO>();

			productOpr.getProductRecord().setProductImageMappingList(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getImageMappingList(productOpr);

			productOpr.getProductRecord().setProductImageMappingList(
					productOprRecd.getProductRecord().getProductImageMappingList());
			populateDefaultImage();

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

	private void populateDefaultImage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// load the default image
		if (!productOpr.getProductRecord().getProductImageMappingList().isEmpty()) {
			for (ProductImageMappingDVO productImageMappingDVO : productOpr.getProductRecord()
					.getProductImageMappingList()) {
				myLog.debug("In method :: seq no ---> " + productImageMappingDVO.getSequenceNumber());
				if (productImageMappingDVO.getSequenceNumber() != null
						&& productImageMappingDVO.getSequenceNumber().equals(0L)) {
					myLog.debug(" default selected " + productImageMappingDVO.getThumbnailImageURL());
					productOpr.getProductRecord().setDefaultImageRecord(productImageMappingDVO);
				}
				copyOfDataMap.put(productImageMappingDVO.getId(),
						(ProductImageMappingDVO) DeepCopy.copy(productImageMappingDVO));
			}
		}
	}

	public void handleFileUploadForDefaultImage(FileUploadEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean uploadFlag = false;
		String zoomFileName, regularFileName, thumbFileName, reportFileName, code;
		Integer version;

		String fileName = event.getFile().getFileName();
		myLog.debug("fileName " + fileName);
		String contextPath = "product/";

		ProductImageMappingDVO defaultImageRecord = productOpr.getProductRecord().getDefaultImageRecord();

		File uploadFile = extractUploadedFileData(event);

		if (renderForProduct) {
			code = productOpr.getProductRecord().getCode();
			version = productOpr.getProductRecord().getProductVersion();
		} else {
			code = productOpr.getProductRecord().getProductSkuRecord().getCode();
			version = productOpr.getProductRecord().getProductSkuRecord().getSkuVersion();
		}
		code = code + "_" + version;
		code = code.replace(" ", "_");

		// call upload for zoom image
		zoomFileName = code + "_0_z." + uploadFile.getExtension();
		String zoomFileUrl = contextPath + zoomFileName;
		myLog.debug("zoomFileUrl " + zoomFileUrl);
		defaultImageRecord.setZoomImageURL(zoomFileUrl);
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
			defaultImageRecord.setImageURL(regularFileUrl);
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
				defaultImageRecord.setThumbnailImageURL(thumbFileUrl);
				uploadFile.setName(thumbFileName);
				uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.THUMB_IMAGE_WIDTH,
						CommonConstant.ImageAttributes.THUMB_IMAGE_HEIGHT, contextPath);
				myLog.debug("uploadFlag thumbnail :: " + uploadFlag);
				// end call upload for thumbnail image

				if (uploadFlag) {
					// call upload for report image
					reportFileName = code + "_0_rpt." + uploadFile.getExtension();
					String reportFileUrl = contextPath + reportFileName;
					myLog.debug("reportFileUrl " + reportFileUrl);
					defaultImageRecord.setReportImageURL(reportFileUrl);
					uploadFile.setName(reportFileName);
					uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.REPORT_IMAGE_WIDTH,
							CommonConstant.ImageAttributes.REPORT_IMAGE_HEIGHT, contextPath);
					myLog.debug("uploadFlag report :: " + uploadFlag);
					// end call upload for report image

					if (uploadFlag) {
						myLog.debug("All files uploaded successfully :::::::::: ");
						setSuccessMsg(propertiesReader.getValueOfKey("upload_successful"));

						defaultImageRecord.setSequenceNumber(0L);
						defaultImageRecord.getOperationalAttributes().setRecordDeleted(false);
						boolean recordAdded = false;
						if (!productOpr.getProductRecord().getProductImageMappingList().isEmpty()) {
							int size = productOpr.getProductRecord().getProductImageMappingList().size();
							myLog.debug(" size 11 :: " + size);
							for (int i = 0; i < size; i++) {
								ProductImageMappingDVO productImageMappingDVO = productOpr.getProductRecord()
										.getProductImageMappingList().get(i);
								myLog.debug(" seq no :: " + productImageMappingDVO.getSequenceNumber());

								if (productImageMappingDVO.getSequenceNumber() != null
										&& productImageMappingDVO.getSequenceNumber().equals(0L)) {
									productOpr.getProductRecord().getProductImageMappingList()
											.set(i, defaultImageRecord);
									recordAdded = true;
									myLog.debug(" size 22 :: "
											+ productOpr.getProductRecord().getProductImageMappingList().size());
									break;
								}
							}
						}

						if (!recordAdded) {
							productOpr.getProductRecord().getProductImageMappingList().add(0, defaultImageRecord);
						}
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyImages(true);

						productOpr.getProductRecord().setDefaultImageRecord(
								(ProductImageMappingDVO) DeepCopy.copy(defaultImageRecord));
						myLog.debug("All files uploaded successfully :::::::::: size :: "
								+ productOpr.getProductRecord().getProductImageMappingList().size());

					} else {
						myLog.debug("ERROR uploading thumbnail :::::::::: ");
						addToErrorList(propertiesReader.getValueOfKey("upload_error"));
					}
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
		myLog.debug("In Product Definition Add Edit BB :: executeSaveImageMapping starts ");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean uploadFlag = false;
		String zoomFileName, regularFileName, thumbFileName, reportFileName, code;
		Integer version;

		String fileName = event.getFile().getFileName();
		myLog.debug("fileName " + fileName);
		String contextPath = "product/";
		ProductImageMappingDVO defaultImageRecord = new ProductImageMappingDVO();

		File uploadFile = extractUploadedFileData(event);

		if (renderForProduct) {
			code = productOpr.getProductRecord().getCode();
			version = productOpr.getProductRecord().getProductVersion();
		} else {
			code = productOpr.getProductRecord().getProductSkuRecord().getCode();
			version = productOpr.getProductRecord().getProductSkuRecord().getSkuVersion();
		}
		code = code + "_" + version;
		code = code.replace(" ", "_");

		Long seqNo = 1L;
		if (!productOpr.getProductRecord().getProductImageMappingList().isEmpty()) {
			int size = productOpr.getProductRecord().getProductImageMappingList().size();
			myLog.debug("size " + size);

			ProductImageMappingDVO productImageMappingDVO = productOpr.getProductRecord().getProductImageMappingList()
					.get(size - 1);
			Long sequenceNumber = productImageMappingDVO.getSequenceNumber();
			myLog.debug("sequenceNumber " + sequenceNumber);
			if (sequenceNumber != null)
				seqNo = sequenceNumber + 1;
		}
		myLog.debug("seqNo " + seqNo);

		// call upload for zoom image
		zoomFileName = code + "_" + seqNo + "_z." + uploadFile.getExtension();
		String zoomFileUrl = contextPath + zoomFileName;
		myLog.debug("zoomFileUrl " + zoomFileUrl);
		defaultImageRecord.setZoomImageURL(zoomFileUrl);
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
			defaultImageRecord.setImageURL(regularFileUrl);
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
				defaultImageRecord.setThumbnailImageURL(thumbFileUrl);
				uploadFile.setName(thumbFileName);
				uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.THUMB_IMAGE_WIDTH,
						CommonConstant.ImageAttributes.THUMB_IMAGE_HEIGHT, contextPath);
				myLog.debug("uploadFlag thumbnail :: " + uploadFlag);
				// end call upload for thumbnail image
				if (uploadFlag) {
					// call upload for report image
					reportFileName = code + "_0_rpt." + uploadFile.getExtension();
					String reportFileUrl = contextPath + reportFileName;
					myLog.debug("reportFileUrl " + reportFileUrl);
					defaultImageRecord.setReportImageURL(reportFileUrl);
					uploadFile.setName(reportFileName);
					uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.REPORT_IMAGE_WIDTH,
							CommonConstant.ImageAttributes.REPORT_IMAGE_HEIGHT, contextPath);
					myLog.debug("uploadFlag report :: " + uploadFlag);
					// end call upload for report image

					if (uploadFlag) {
						myLog.debug("All files uploaded successfully :::::::::: ");
						setSuccessMsg(propertiesReader.getValueOfKey("upload_successful"));

						defaultImageRecord.setSequenceNumber(seqNo);
						productOpr.getProductRecord().getProductImageMappingList()
								.add((ProductImageMappingDVO) DeepCopy.copy(defaultImageRecord));
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyImages(true);

						myLog.debug("All files uploaded successfully :::::::::: size :: "
								+ productOpr.getProductRecord().getProductImageMappingList().size());
					} else {
						myLog.debug("ERROR uploading report :::::::::: ");
						addToErrorList(propertiesReader.getValueOfKey("upload_error"));
					}
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

	public void executeSaveImageMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveImageMapping starts ");

		if (!productOpr.getProductRecord().getProductImageMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				// if the flag is false, then check if data is changed on screen
				// or no
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyImages() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyImages()) {
					if (filterSaveImageMapping())
						productOpr.getProductRecord().getModifyProductSKURecord().setModifyImages(true);
				}

				ProductOpr productOprRecd = new ProductDefinitionBD().saveImageMappingList(productOpr);
				productOpr.getProductRecord().setProductImageMappingList(
						productOprRecd.getProductRecord().getProductImageMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("image_save_success"));

				if (!productOprRecd.getProductRecord().getProductImageMappingList().isEmpty())
					productOpr.getIconProductSKURecord().setMapImages(true);
				else
					productOpr.getIconProductSKURecord().setMapImages(false);

				copyOfDataMap = new HashMap<Long, BaseDVO>();
				populateDefaultImage();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean filterSaveImageMapping() {
		boolean validateFlag = false;

		if (!productOpr.getProductRecord().getProductImageMappingList().isEmpty()) {
			for (ProductImageMappingDVO productImageMappingRecord : productOpr.getProductRecord()
					.getProductImageMappingList()) {

				if (copyOfDataMap.containsKey(productImageMappingRecord.getId())) {
					ProductImageMappingDVO productImageMappingDVO = (ProductImageMappingDVO) copyOfDataMap
							.get(productImageMappingRecord.getId());

					Long imageMappingIdFromScreen = productImageMappingRecord.getId();
					Boolean recordDeletedFromScreen = productImageMappingRecord.getOperationalAttributes()
							.getRecordDeleted();

					Long imageMappingIdFromDB = productImageMappingDVO.getId();
					Boolean recordDeletedFromDB = productImageMappingDVO.getOperationalAttributes().getRecordDeleted();

					if (validateForValueChange(imageMappingIdFromScreen, imageMappingIdFromDB)
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

	public void executeAddMore(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: add more starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ProductOpr productOprSent = new ProductOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "PRODUCT");

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

	public void executeSaveOtherSkuMapping(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In getSuggestedSkuDetailsForCode starts ");
		if (validateExecuteSaveOtherSkuMapping()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().getProductOtherSkuMappingDVO().setUserLogin(userLogin);
				productOpr = new ProductDefinitionBD().executeSaveOtherSkuMapping(productOpr);
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				openOtherSkuMappingDialog(ae);
				setSuccessMsg(propertiesReader.getValueOfKey("sku_save_success"));
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateExecuteSaveOtherSkuMapping() {
		boolean valSaveFlag = false;
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		if (productOpr.getProductRecord().getProductOtherSkuMappingList() != null
				&& productOpr.getProductRecord().getProductOtherSkuMappingList().size() > 0) {
			HashMap<Object, String> mappingMap = new HashMap<Object, String>();
			for (ProductOtherSKUMappingDVO listDVO : productOpr.getProductRecord().getProductOtherSkuMappingList()) {

				if (listDVO.getSubProductSkuRecord().getId() != null) {
					if (mappingMap != null && mappingMap.containsKey(listDVO.getSubProductSkuRecord().getId())) {
						mappingMap.put(listDVO.getSubProductSkuRecord().getId(), "DOUBLE");
					} else {
						mappingMap.put(listDVO.getSubProductSkuRecord().getId(), "SINGLE");
					}
				}
			}

			for (int i = 0; i < productOpr.getProductRecord().getProductOtherSkuMappingList().size(); i++) {

				ProductOtherSKUMappingDVO listDVO = productOpr.getProductRecord().getProductOtherSkuMappingList()
						.get(i);
				if (listDVO.getSubProductSkuRecord().getId() == null) {
					String errorMsg = propertiesReader.getValueOfKey("product_sku_null");
					addToErrorList(errorMsg + (i + 1));

					if (listDVO.getSkuQuantity() == null) {
						String errorMsg1 = propertiesReader.getValueOfKey("product_sku_qty_null");
						addToErrorList(errorMsg1 + (i + 1));
					} else if (listDVO.getSkuQuantity() != null && listDVO.getSkuQuantity() == 0) {
						String errorMsg2 = propertiesReader.getValueOfKey("product_sku_qty_more_than_zero");
						addToErrorList(errorMsg2 + (i + 1));
					}
					if (listDVO.getSkuQuantityUOM().getUomCode().getCode() == null) {
						String errorMsg3 = propertiesReader.getValueOfKey("product_sku_qty_uom_null");
						addToErrorList(errorMsg3 + (i + 1));
					}
				} else if (mappingMap != null && mappingMap.containsKey(listDVO.getSubProductSkuRecord().getId())) {
					String skuStatus = mappingMap.get(listDVO.getSubProductSkuRecord().getId());
					if (skuStatus != null && skuStatus.equalsIgnoreCase("DOUBLE")) {
						String errorMsg = propertiesReader.getValueOfKey("product_sku_cannot_be_double");
						addToErrorList(errorMsg + (i + 1));
					} else {
						if (listDVO.getSkuQuantity() == null) {
							String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_null");
							addToErrorList(errorMsg + (i + 1));
						} else if (listDVO.getSkuQuantity() != null && listDVO.getSkuQuantity() == 0) {
							String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_more_than_zero");
							addToErrorList(errorMsg + (i + 1));
						}
						if (listDVO.getSkuQuantityUOM().getUomCode().getCode() == null) {
							String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_uom_null");
							addToErrorList(errorMsg + (i + 1));
						}
					}
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("add_sku_to_map"));
		}

		if (getErrorList().size() > 0) {
			valSaveFlag = false;
		} else {
			valSaveFlag = true;
		}

		return valSaveFlag;
	}

	public void openMapItemPropertyDialog(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: openMapItemPropertyDialog starts "
				+ productOpr.getProductRecord().getProductSkuRecord().getId());

		try {
			List<Object> itemCodeListForAutoSuggest = new ArrayList<Object>();
			List<Object> itemCategoryListForAutoSuggest = new ArrayList<Object>();
			productOpr.getProductRecord().setProductItemPropertyMappingList(null);
			productOpr.getProductRecord().setProductItemPropertyMappingRecord(null);
			ProductOpr productOprRecd = new ProductDefinitionBD().getProductSkuBomDetailsList(productOpr);
			productOpr.getProductRecord().setProductItemPropertyMappingList(
					productOprRecd.getProductRecord().getProductItemPropertyMappingList());
			productOpr.getProductRecord().setProductItemPropertyMappingRecord(
					productOprRecd.getProductRecord().getProductItemPropertyMappingRecord());

			if (productOpr.getProductRecord().getProductItemPropertyMappingList().size() > 0) {
				for (ProductItemPropertyMappingDVO listDVO : productOpr.getProductRecord()
						.getProductItemPropertyMappingList()) {
					itemCodeListForAutoSuggest.add(listDVO.getItemRecord());
					itemCategoryListForAutoSuggest.add(listDVO.getItemCategoryRecord());
					// if (listDVO.getPerUnitWeight() == null) {
					// listDVO.setPerUnitWeight((float) 1);
					// }
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("itemCodeAutoCompleteForCategory", itemCodeListForAutoSuggest);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("itemCategoryAutoCompleteForBom", itemCategoryListForAutoSuggest);
			} else {
				executeItemPropertyMappingAddRow(event);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeItemPropertyMappingAddRow(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeItemPropertyMappingAddRow starts ");

		if (productOpr.getProductRecord().getProductItemPropertyMappingList() != null
				&& productOpr.getProductRecord().getProductItemPropertyMappingList().size() > 0) {
			ProductItemPropertyMappingDVO propertyMappingDVO = new ProductItemPropertyMappingDVO();
			propertyMappingDVO
					.setLineNumber(productOpr.getProductRecord().getProductItemPropertyMappingList().size() + 1);
			productOpr.getProductRecord().getProductItemPropertyMappingList().add(propertyMappingDVO);

			ArrayList<Object> itemCodeListForAutoSuggest = new ArrayList<Object>();
			ArrayList<Object> itemCategoryListForAutoSuggest = new ArrayList<Object>();
			for (ProductItemPropertyMappingDVO listDVO : productOpr.getProductRecord()
					.getProductItemPropertyMappingList()) {
				itemCodeListForAutoSuggest.add(listDVO.getItemRecord());
				itemCategoryListForAutoSuggest.add(listDVO.getItemCategoryRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("itemCodeAutoCompleteForCategory", itemCodeListForAutoSuggest);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("itemCategoryAutoCompleteForBom", itemCategoryListForAutoSuggest);
		} else {
			ProductItemPropertyMappingDVO propertyMappingDVO = new ProductItemPropertyMappingDVO();
			propertyMappingDVO.setLineNumber(1);
			productOpr.getProductRecord().getProductItemPropertyMappingList().add(propertyMappingDVO);
		}

	}

	public void itemCategorySelected(ProductItemPropertyMappingDVO itemPropertyMappingDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		getSelectedItemCategory();
		selectedItemCategory = itemPropertyMappingDVO;

		if (renderForSKU) {
			selectedItemCategory.getItemCategoryRecord().setItemCategoryPropertiesMappingList(null);
			if (selectedItemCategory.getItemCategoryRecord().getId() != null
					&& productOpr.getProductRecord().getProductItemPropertyMappingRecord().getMappedPropertiesList()
							.size() > 0) {
				try {
					ItemCategoryDVO itemCategoryDVOSend = new ItemCategoryDVO();
					itemCategoryDVOSend.setId(selectedItemCategory.getItemCategoryRecord().getId());
					ItemCategoryDVO itemCategoryDVORecv = new ProductDefinitionBD()
							.getMappedItemPropertiesBasedOnCategory(itemCategoryDVOSend);
					if (itemCategoryDVORecv.getItemCategoryPropertiesMappingList().size() > 0) {
						for (ItemCategoryPropertiesMappingDVO listRecord : itemCategoryDVORecv
								.getItemCategoryPropertiesMappingList()) {
							String itemPropertyCode = listRecord.getItemPropertiesRecord().getCode();

							for (ItemPropertiesMappingDVO innerListRecord : productOpr.getProductRecord()
									.getProductItemPropertyMappingRecord().getMappedPropertiesList()) {
								String productPropertyCode = innerListRecord.getCode();
								if (itemPropertyCode != null && productPropertyCode != null
										&& itemPropertyCode.equals(productPropertyCode)) {
									selectedItemCategory.getItemCategoryRecord().getItemCategoryPropertiesMappingList()
											.add(listRecord);
								}
							}
						}
					}

					if (selectedItemCategory.getItemCategoryRecord().getItemCategoryPropertiesMappingList().size() > 0) {
						Integer lineNumber = selectedItemCategory.getLineNumber();
						if (lineNumber > 0) {
							productOpr
									.getProductRecord()
									.getProductItemPropertyMappingList()
									.get(lineNumber.intValue() - 1)
									.getItemCategoryRecord()
									.setItemCategoryPropertiesMappingList(
											selectedItemCategory.getItemCategoryRecord()
													.getItemCategoryPropertiesMappingList());
						}

					}
				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);
				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			}
		}
	}

	public List<Object> getSuggestedItemCategoryListForName(String query) {
		if (query != null) {
			try {
				List<Object> itemCategoryListForAutoSuggest = (List<Object>) FacesContext.getCurrentInstance()
						.getViewRoot().getViewMap().get("itemCategoryAutoCompleteForBom");
				ItemCategoryDVO itemCategoryDVO = new ItemCategoryDVO();
				itemCategoryDVO.setName(query);
				ArrayList<Object> itemCategoryList = new ProductDefinitionBD()
						.getSuggestedItemCategoryListBasedOnLevel(itemCategoryDVO);

				HashMap<Long, Object> listMap = new HashMap<Long, Object>();
				if (itemCategoryListForAutoSuggest != null && itemCategoryListForAutoSuggest.size() > 0) {
					for (Object listObj : itemCategoryListForAutoSuggest) {
						ItemCategoryDVO listDVO = (ItemCategoryDVO) listObj;
						Long itemCategoryId = listDVO.getId();
						if (itemCategoryId != null) {
							listMap.put(itemCategoryId, listDVO);
						}
					}
				} else {
					itemCategoryListForAutoSuggest = new ArrayList<Object>();
				}
				if (itemCategoryList != null && itemCategoryList.size() > 0) {
					for (Object listObj : itemCategoryList) {
						ItemCategoryDVO listDVO = (ItemCategoryDVO) listObj;
						Long itemCategoryId = listDVO.getId();

						if (itemCategoryId != null && !listMap.containsKey(itemCategoryId)) {
							itemCategoryListForAutoSuggest.add(listObj);
						}
					}
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("itemCategoryAutoCompleteForBom", itemCategoryListForAutoSuggest);
				return itemCategoryListForAutoSuggest;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedItemDetailsBasedOnItemCategory(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In getSuggestedItemDetailsBasedOnItemCategory starts ::::");
		List<Object> itemCodeListForAutoSuggest = (List<Object>) FacesContext.getCurrentInstance().getViewRoot()
				.getViewMap().get("itemCodeAutoCompleteForCategory");
		Object itemPropertiesToGenerate = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("itemPropertiesMapped");
		ProductItemPropertyMappingDVO propertyMappingDVO = (ProductItemPropertyMappingDVO) itemPropertiesToGenerate;
		if (query != null && query.trim().length() > 0) {
			try {
				ItemDVO itemDVO = new ItemDVO();
				itemDVO.setCode(query);
				itemDVO.getItemHierarchyRecord().getItemCategoryRecord()
						.setId(propertyMappingDVO.getItemCategoryRecord().getId());
				ArrayList<Object> itemCodeList = new ProductDefinitionBD().getSuggestedItemListBasedOnCategory(itemDVO);

				HashMap<String, Object> listMap = new HashMap<String, Object>();
				if (itemCodeListForAutoSuggest != null && itemCodeListForAutoSuggest.size() > 0) {
					for (Object listObj : itemCodeListForAutoSuggest) {
						ItemDVO listDVO = (ItemDVO) listObj;
						String itemCode = listDVO.getCode();
						if (itemCode != null) {
							listMap.put(itemCode, listDVO);
						}
					}
				} else {
					itemCodeListForAutoSuggest = new ArrayList<Object>();
				}
				if (itemCodeList != null && itemCodeList.size() > 0) {
					for (Object listObj : itemCodeList) {
						ItemDVO listDVO = (ItemDVO) listObj;
						String itemCode = listDVO.getCode();

						if (itemCode != null && !listMap.containsKey(itemCode)) {
							itemCodeListForAutoSuggest.add(listObj);
						}
					}
				}
				myLog.debug("SIZE 3 ::::" + itemCodeListForAutoSuggest.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("itemCodeAutoCompleteForCategory", itemCodeListForAutoSuggest);
				return itemCodeList;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return itemCodeListForAutoSuggest;
	}

	public void itemCodeSelected(ProductItemPropertyMappingDVO itemPropertyMappingDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String selectedItemCode = itemPropertyMappingDVO.getItemRecord().getCode();
		Integer selectedLineNumber = itemPropertyMappingDVO.getLineNumber();
		myLog.debug("In itemCodeSelected starts ::::" + selectedItemCode + "::Line number::" + selectedLineNumber
				+ "::wt UOM::" + itemPropertyMappingDVO.getItemRecord().getWeightUomRecord().getCode());
		if (selectedItemCode != null && selectedItemCode.trim().length() > 0) {
			try {
				ItemDVO itemDVO = new ProductDefinitionBD().getMappedMandatoryProperties(itemPropertyMappingDVO
						.getItemRecord());
				if (selectedLineNumber != null) {
					productOpr.getProductRecord().getProductItemPropertyMappingList().get(selectedLineNumber - 1)
							.getItemRecord().setIsMandatoryProperty(itemDVO.getIsMandatoryProperty());
					productOpr.getProductRecord().getProductItemPropertyMappingList().get(selectedLineNumber - 1)
							.getQuantityUOM()
							.setCode(itemPropertyMappingDVO.getItemRecord().getQuantityUomRecord().getCode());
					productOpr.getProductRecord().getProductItemPropertyMappingList().get(selectedLineNumber - 1)
							.getWeightUOM()
							.setCode(itemPropertyMappingDVO.getItemRecord().getWeightUomRecord().getCode());
					openAvailableItemPropertyDialog();
				}
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void openAvailableItemPropertyDialog() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		RequestContext context = RequestContext.getCurrentInstance();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());

		Object itemPropertiesToGenerate = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("itemPropertiesMapped");
		ProductItemPropertyMappingDVO propertyMappingDVO = (ProductItemPropertyMappingDVO) itemPropertiesToGenerate;

		String itemCodeSelected = propertyMappingDVO.getItemRecord().getCode();
		myLog.debug("ITEM CODE::::" + itemCodeSelected);
		try {
			if (itemCodeSelected != null && itemCodeSelected.toString().trim().length() > 0) {
				if (selectedItemCategory == null) {
					selectedItemCategory = new ProductItemPropertyMappingDVO();
				}
				selectedItemCategory = (ProductItemPropertyMappingDVO) DeepCopy.copy(propertyMappingDVO);
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().setItemPropertiesRecord(null);
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().setItemPropertiesMappingList(null);
				ProductItemPropertyMappingDVO productItemPropertyMappingDVORec = new ProductDefinitionBD()
						.getItemPropertiesMappedToItem(selectedItemCategory);
				productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.setItemPropertiesRecord(productItemPropertyMappingDVORec.getItemPropertiesRecord());
				productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.setItemPropertiesMappingList(productItemPropertyMappingDVORec.getItemPropertiesMappingList());
				context.execute("openAvailablePropertyDialog();");
			} else {
				addToErrorList(propertiesReader.getValueOfKey("item_code_null"));
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeSaveItemPropertiesToGenerateCombination(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveItemPropertiesToGenerateCombination starts ");
		setErrorList(new ArrayList<String>());
		RequestContext context = RequestContext.getCurrentInstance();
		String warningMessage = null;

		if (validateSavePropertiesGenerationCombination()) {
			try {
				// productOpr.productRecord.productItemPropertyMappingRecord.itemPropertiesMappingList}">
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().setUserLogin(userLogin);
				ProductItemPropertyMappingDVO productItemPropertyMappingDVORec = new ProductDefinitionBD()
						.saveItemPropertiesToGenerateCombination(productOpr.getProductRecord()
								.getProductItemPropertyMappingRecord());
				if (productItemPropertyMappingDVORec.getItemPropertiesRecord().getItemPropertyRecord().getId() != null) {
					productOpr
							.getProductRecord()
							.getProductItemPropertyMappingRecord()
							.getItemPropertiesRecord()
							.setItemPropertyRecord(
									productItemPropertyMappingDVORec.getItemPropertiesRecord().getItemPropertyRecord());

					boolean sizeAttribute = false;
					ItemPropertiesMappingDVO itemPropertiesMappingSendDVO = new ItemPropertiesMappingDVO();
					for (ItemPropertiesMappingDVO listDVO : productOpr.getProductRecord()
							.getProductItemPropertyMappingRecord().getItemPropertiesMappingList()) {
						String propertyName = listDVO.getItemPropertyRecord().getName();
						String propertyValue = listDVO.getDefaultPropertyValue();
						if (propertyName != null) {
							if (propertyName.equalsIgnoreCase(CommonConstant.ItemProperty.ITEM_PROPERTY_LENGTH)) {
								FoundationValidator validator = new FoundationValidator();
								if (propertyValue != null && validator.validateDecimalNumber(propertyValue)) {
									sizeAttribute = true;
									itemPropertiesMappingSendDVO.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.setLength(Float.valueOf(propertyValue));
									itemPropertiesMappingSendDVO
											.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.getApplicationFlags()
											.getApplicationFlagMap()
											.put("LENGTH_UOM", listDVO.getItemPropertyRecord().getUomRecord().getCode());

								}
							}

							if (propertyName.equalsIgnoreCase(CommonConstant.ItemProperty.ITEM_PROPERTY_BREADTH)) {
								FoundationValidator validator = new FoundationValidator();
								if (propertyValue != null && validator.validateDecimalNumber(propertyValue)) {
									sizeAttribute = true;
									itemPropertiesMappingSendDVO.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.setBreadth(Float.valueOf(propertyValue));
									itemPropertiesMappingSendDVO
											.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.getApplicationFlags()
											.getApplicationFlagMap()
											.put("BREADTH_UOM",
													listDVO.getItemPropertyRecord().getUomRecord().getCode());

								}
							}
							if (propertyName.equalsIgnoreCase(CommonConstant.ItemProperty.ITEM_PROPERTY_HEIGHT)) {
								FoundationValidator validator = new FoundationValidator();
								if (propertyValue != null && validator.validateDecimalNumber(propertyValue)) {
									sizeAttribute = true;
									itemPropertiesMappingSendDVO.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.setHeight(Float.valueOf(propertyValue));
									itemPropertiesMappingSendDVO
											.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.getApplicationFlags()
											.getApplicationFlagMap()
											.put("HEIGHTH_UOM",
													listDVO.getItemPropertyRecord().getUomRecord().getCode());

								}
							}
							if (propertyName.equalsIgnoreCase(CommonConstant.ItemProperty.ITEM_PROPERTY_DIAMETER)) {
								FoundationValidator validator = new FoundationValidator();
								if (propertyValue != null && validator.validateDecimalNumber(propertyValue)) {
									sizeAttribute = true;
									itemPropertiesMappingSendDVO.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.setDiameter(Float.valueOf(propertyValue));
									itemPropertiesMappingSendDVO
											.getInvDiamondStoneSizeWeightChartDetailsRecord()
											.getApplicationFlags()
											.getApplicationFlagMap()
											.put("DIAMETER_UOM",
													listDVO.getItemPropertyRecord().getUomRecord().getCode());

								}
							}
						}
					}
					Float sizeChartPerUnitWt = null;
					UomDVO sizeChartPerUnitWtUOM = null;
					if (sizeAttribute) {
						itemPropertiesMappingSendDVO.getInvDiamondStoneSizeWeightChartDetailsRecord()
								.getApplicationFlags().getApplicationFlagMap()
								.put("ITEM_CODE", selectedItemCategory.getItemRecord().getCode());
						ItemPropertiesMappingDVO itemPropertiesMappingRecvDVO = new ProductDefinitionBD()
								.getItemSizeChartDetails(itemPropertiesMappingSendDVO);
						sizeChartPerUnitWt = itemPropertiesMappingRecvDVO
								.getInvDiamondStoneSizeWeightChartDetailsRecord().getWeight();
						sizeChartPerUnitWtUOM = itemPropertiesMappingRecvDVO
								.getInvDiamondStoneSizeWeightChartDetailsRecord().getWeightUOMRecord();
						if (itemPropertiesMappingRecvDVO.getApplicationFlags().getApplicationFlagMap()
								.containsKey("WARNING_MSG")) {
							warningMessage = (String) itemPropertiesMappingRecvDVO.getApplicationFlags()
									.getApplicationFlagMap().get("WARNING_MSG");
							itemPropertiesMappingRecvDVO.getApplicationFlags().getApplicationFlagMap()
									.remove("WARNING_MSG");
						}
					}

					// Long selectedItemCategoryId =
					// selectedItemCategory.getItemCategoryRecord().getId();
					// String selectedItemCode =
					// selectedItemCategory.getItemRecord().getCode();
					Integer lineNumber = selectedItemCategory.getLineNumber();
					myLog.debug(" executeSaveItemPropertiesToGenerateCombination lineNumber ::" + lineNumber + ":SIZE:"
							+ productOpr.getProductRecord().getProductItemPropertyMappingList().size());

					// for Updating combination ID on item properties dialog
					for (ProductItemPropertyMappingDVO productItemPropertyMappingDVO : productOpr.getProductRecord()
							.getProductItemPropertyMappingList()) {
						// Long mainItemCategory =
						// productItemPropertyMappingDVO.getItemCategoryRecord().getId();
						// String mainItemCode =
						// productItemPropertyMappingDVO.getItemRecord().getCode();
						Integer listLineNumer = productItemPropertyMappingDVO.getLineNumber();
						myLog.debug(" executeSaveItemPropertiesToGenerateCombination listLineNumer ::" + listLineNumer);
						if (listLineNumer != null && lineNumber != null
								&& listLineNumer.intValue() == lineNumber.intValue()) {

							String itemPerUnitWtPropertyValue = (String) productOpr.getProductRecord()
									.getProductItemPropertyMappingRecord().getApplicationFlags()
									.getApplicationFlagMap().get("PER_UNIT_WT");
							String perUnitWtUOM = (String) productOpr.getProductRecord()
									.getProductItemPropertyMappingRecord().getApplicationFlags()
									.getApplicationFlagMap().get("PER_UNIT_WT_UOM");

							myLog.debug("itemPerUnitWtPropertyValue::: " + itemPerUnitWtPropertyValue
									+ "::sizeChartPerUnitWt:" + sizeChartPerUnitWt);
							if (sizeChartPerUnitWt != null) {
								productItemPropertyMappingDVO.setPerUnitWeight(sizeChartPerUnitWt);
								productItemPropertyMappingDVO.getWeightUOM().setCode(sizeChartPerUnitWtUOM.getCode());
							} else if (itemPerUnitWtPropertyValue != null
									&& itemPerUnitWtPropertyValue.trim().length() > 0) {
								productItemPropertyMappingDVO.setPerUnitWeight(Float
										.valueOf(itemPerUnitWtPropertyValue));
								productItemPropertyMappingDVO.getWeightUOM().setCode(perUnitWtUOM);
							}

							if (sizeChartPerUnitWt == null) {
								productItemPropertyMappingDVO.getOperationalAttributes().setRecordUpdated(true);
							}

							productItemPropertyMappingDVO.getItemPropertiesRecordEdited().setValueTypeDescription(
									productItemPropertyMappingDVORec.getItemPropertiesRecord().getItemPropertyRecord()
											.getDescription());
							productItemPropertyMappingDVO.getItemPropertiesRecordEdited().setId(
									productItemPropertyMappingDVORec.getItemPropertiesRecord().getItemPropertyRecord()
											.getId());
							break;
						}
					}

					context.execute("closePropertyMappingDialog();");
					setSuccessMsg("Properties Combination created successfully");
				} else {
					addToErrorList("Properties combination failed to create");
				}
				if (warningMessage != null) {
					addToErrorList(warningMessage);
				}
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSavePropertiesGenerationCombination() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		boolean valSaveFlag = false;
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator validator = new FoundationValidator();

		if (productOpr.getProductRecord().getProductItemPropertyMappingRecord().getItemPropertiesMappingList().size() > 0) {
			for (int i = 0; i < productOpr.getProductRecord().getProductItemPropertyMappingRecord()
					.getItemPropertiesMappingList().size(); i++) {
				String propertyName = productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.getItemPropertiesMappingList().get(i).getItemPropertyRecord().getName();
				String defaultValue = productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.getItemPropertiesMappingList().get(i).getDefaultPropertyValue();
				Boolean mandatory = productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.getItemPropertiesMappingList().get(i).getItemPropertyRecord().getMandatory();
				String uomCode = productOpr.getProductRecord().getProductItemPropertyMappingRecord()
						.getItemPropertiesMappingList().get(i).getItemPropertyRecord().getUomRecord().getCode();
				if (mandatory && (defaultValue == null || defaultValue.equalsIgnoreCase(""))) {
					String errorMsg = propertiesReader.getValueOfKey("add_properties_value");
					addToErrorList(errorMsg + (i + 1));
				}

				myLog.debug("propertyName::: " + propertyName);
				if (propertyName != null
						&& propertyName.equals(CommonConstant.ItemProperty.ITEM_PROPERTY_PERUNITWEIGHT)
						&& defaultValue != null && defaultValue.trim().length() > 0) {
					boolean isFloatValue = validator.validateDecimalNumber(defaultValue);
					myLog.debug("defaultValue::: " + defaultValue + "::isFloatValue ::" + isFloatValue);
					if (isFloatValue) {
						productOpr.getProductRecord().getProductItemPropertyMappingRecord().getApplicationFlags()
								.getApplicationFlagMap().put("PER_UNIT_WT", defaultValue);
						productOpr.getProductRecord().getProductItemPropertyMappingRecord().getApplicationFlags()
								.getApplicationFlagMap().put("PER_UNIT_WT_UOM", uomCode);
					}
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("add_properties_for_bom"));
		}

		if (getErrorList().size() > 0) {
			valSaveFlag = false;
		} else {
			valSaveFlag = true;
		}
		return valSaveFlag;
	}

	public void executeSaveBOMCaptureDetailsForProduct(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In getSuggestedSkuDetailsForCode starts ");
		if (validateSaveProductSkuBomDetails()) {
			try {
				if (productOpr.getProductRecord().getModifyProductSKURecord().getModifyItem() == null
						|| !productOpr.getProductRecord().getModifyProductSKURecord().getModifyItem()) {
					if (productOpr.getProductRecord().getProductItemPropertyMappingList().size() > 0) {
						int rowExists = 0;
						for (ProductItemPropertyMappingDVO listDVO : productOpr.getProductRecord()
								.getProductItemPropertyMappingList()) {
							if (!listDVO.getOperationalAttributes().getRecordDeleted() && listDVO.getId() == null) {
								rowExists++;
							}
						}
						if (rowExists > 0) {
							productOpr.getProductRecord().getModifyProductSKURecord().setModifyItem(true);
						}
					}
				}
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().setUserLogin(userLogin);
				productOpr = new ProductDefinitionBD().saveBOMCaptureDetailsForProduct(productOpr);
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				openMapItemPropertyDialog(ae);
				setSuccessMsg(propertiesReader.getValueOfKey("sku_save_success"));
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSaveProductSkuBomDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		boolean valSaveFlag = false;
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator validator = new FoundationValidator();

		if (productOpr.getProductRecord().getProductItemPropertyMappingList().size() > 0) {

			ArrayList<ItemPropertiesMappingDVO> productPropertyList = productOpr.getProductRecord()
					.getProductItemPropertyMappingRecord().getMappedPropertiesList();
			boolean isProductPropertyMapped = false;
			if (productPropertyList != null && productPropertyList.size() > 0) {
				isProductPropertyMapped = true;
			}
			HashMap<Object, String> mappingMap = new HashMap<Object, String>();
			for (ProductItemPropertyMappingDVO listDVO : productOpr.getProductRecord()
					.getProductItemPropertyMappingList()) {
				if (!listDVO.getOperationalAttributes().getRecordDeleted()) {
					Long propertiesCombinationId = listDVO.getItemPropertiesRecordEdited().getId();
					String listItemCode = listDVO.getItemRecord().getCode();

					if (listItemCode != null && listItemCode.trim().length() > 0) {
						String propertiesComIdCode = null;
						if (propertiesCombinationId != null) {
							propertiesComIdCode = propertiesCombinationId.toString() + listItemCode;
						} else {
							propertiesComIdCode = listItemCode;
						}

						if (mappingMap != null && mappingMap.containsKey(propertiesComIdCode)) {
							mappingMap.put(propertiesComIdCode, "DOUBLE");
						} else {
							mappingMap.put(propertiesComIdCode, "SINGLE");
						}
					}
				}
			}

			for (int i = 0; i < productOpr.getProductRecord().getProductItemPropertyMappingList().size(); i++) {

				if (!productOpr.getProductRecord().getProductItemPropertyMappingList().get(i)
						.getOperationalAttributes().getRecordDeleted()) {
					ProductItemPropertyMappingDVO listDVO = productOpr.getProductRecord()
							.getProductItemPropertyMappingList().get(i);
					Long listItemCategoryId = listDVO.getItemCategoryRecord().getId();
					String itemCategoryCode = listDVO.getItemCategoryRecord().getCode();
					String listItemCode = listDVO.getItemRecord().getCode();
					Boolean isPropertyMandatory = listDVO.getItemRecord().getIsMandatoryProperty();
					Long propertiesCombinationId = listDVO.getItemPropertiesRecordEdited().getId();
					Integer allocationBasedOn = listDVO.getItemRecord().getAllocationBasedOn().getSequenceNumber();
					Float quantity = listDVO.getQuantity();
					String quantityUom = listDVO.getQuantityUOM().getCode();
					Float editedWeight = listDVO.getEditedWeight();
					String weightUom = listDVO.getWeightUOM().getCode();
					ArrayList<ProductSkuBomProcessVariationDetailsDVO> pvList = listDVO.getSkuBomProcessVariationList();
					ArrayList<ItemCategoryPropertiesMappingDVO> productCategoryPropertiesList = listDVO
							.getItemCategoryRecord().getItemCategoryPropertiesMappingList();

					if (propertiesCombinationId != null && listItemCode != null && listItemCode.trim().length() > 0
							&& mappingMap.containsKey(propertiesCombinationId.toString() + listItemCode)
							&& mappingMap.get(propertiesCombinationId.toString() + listItemCode).equals("DOUBLE")) {
						String errorMsg = propertiesReader.getValueOfKey("duplicate_item_code_combination");
						addToErrorList(errorMsg + (i + 1));
					} else if (listItemCode != null && listItemCode.trim().length() > 0
							&& mappingMap.containsKey(listItemCode) && mappingMap.get(listItemCode).equals("DOUBLE")) {
						String errorMsg = propertiesReader.getValueOfKey("duplicate_item_code_combination");
						addToErrorList(errorMsg + (i + 1));
					} else {
						if (listItemCategoryId == null) {
							String errorMsg = propertiesReader.getValueOfKey("item_category_null");
							addToErrorList(errorMsg + (i + 1));
						}

						if (listItemCode == null || listItemCode.trim().length() == 0) {
							String errorMsg = propertiesReader.getValueOfKey("item_code_null");
							addToErrorList(errorMsg + (i + 1));
						}

						if (isPropertyMandatory != null && isPropertyMandatory && propertiesCombinationId == null) {
							String errorMsg = propertiesReader.getValueOfKey("product_sku_combination_null");
							addToErrorList(errorMsg + (i + 1));
						}

						if (renderForSKU && listItemCategoryId != null && listItemCode != null
								&& productCategoryPropertiesList.size() > 0 && isProductPropertyMapped) {
							boolean isProductPropertyMappedToItem = false;
							boolean isProductPropertyValueSameForItem = false;
							outerLoop: for (ItemCategoryPropertiesMappingDVO itemCategoryPropertyRecord : productCategoryPropertiesList) {
								String itemPropertyCode = itemCategoryPropertyRecord.getItemPropertiesRecord()
										.getCode();
								// scanning all properties and their value
								// mapped to product
								for (ItemPropertiesMappingDVO productPropertyRecord : productPropertyList) {
									String productPropertyCode = productPropertyRecord.getCode();
									if (itemPropertyCode.equals(productPropertyCode)) {
										isProductPropertyMappedToItem = true;
										String productPropertyValue = productPropertyRecord.getDefaultPropertyValue();
										if (listItemCode.equalsIgnoreCase(productPropertyValue)) {
											isProductPropertyValueSameForItem = true;
											break outerLoop;
										}
									}
								}
							}

							if (isProductPropertyMappedToItem && !isProductPropertyValueSameForItem) {
								String errMsg = propertiesReader.getValueOfKey("enter_mapped_property_value");
								addToErrorList(errMsg + " " + itemCategoryCode + " at row " + (i + 1));
							}

						}
						// if (propertiesCombinationId == null) {
						// String errorMsg =
						// propertiesReader.getValueOfKey("product_sku_combination_null");
						// addToErrorList(errorMsg + (i + 1));
						// }

						if (pvList != null && pvList.size() > 0 && quantity != null && quantity > 0) {
							for (int j = 0; j < pvList.size(); j++) {
								if (!pvList.get(j).getOperationalAttributes().getRecordDeleted()) {
									Float dtlQuantity = pvList.get(j).getQuantity();
									Long processId = pvList.get(j).getProcessRecord().getId();
									Long processVariationId = pvList.get(j).getProcessVariationMappingRecord().getId();

									if (!validator.validateFloatObjectNull(dtlQuantity)
											|| dtlQuantity.floatValue() <= 0) {
										String errMsg = propertiesReader.getValueOfKey("quantity_null");
										addToErrorList(errMsg + (i + 1));
									} else if (quantity != null && quantity.floatValue() < dtlQuantity.floatValue()) {
										String errMsg = propertiesReader.getValueOfKey("pv_quantity_greater");
										addToErrorList(errMsg + (i + 1));
									}

									if (!validator.validateLongObjectNull(processId)) {
										String errMsg = propertiesReader.getValueOfKey("process_null");
										addToErrorList(errMsg + (i + 1));
									}

									if (!validator.validateLongObjectNull(processVariationId)) {
										String errMsg = propertiesReader.getValueOfKey("process_variation_null");
										addToErrorList(errMsg + (i + 1));
									}
								}
							}
						} else {
							listDVO.setSkuBomProcessVariationList(null);
						}

						if (allocationBasedOn != null) {
							if (allocationBasedOn.equals(CommonConstant.ParameterSequenceNumber.ONE)) {
								if (quantity == null || quantity <= 0) {
									String errorMsg = propertiesReader.getValueOfKey("quantity_null");
									addToErrorList(errorMsg + (i + 1));
								} else if (quantityUom == null) {
									String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_uom_null");
									addToErrorList(errorMsg + (i + 1));
								}

								if (editedWeight != null) {
									if (editedWeight <= 0) {
										String errorMsg = propertiesReader.getValueOfKey("weight_null");
										addToErrorList(errorMsg + (i + 1));
									}

									if (weightUom == null) {
										String errorMsg = propertiesReader.getValueOfKey("product_sku_weight_uom_null");
										addToErrorList(errorMsg + (i + 1));
									}
								} else if (weightUom != null) {
									if (editedWeight == null || editedWeight <= 0) {
										String errorMsg = propertiesReader.getValueOfKey("weight_null");
										addToErrorList(errorMsg + (i + 1));
									}
								}

							} else if (allocationBasedOn.equals(CommonConstant.ParameterSequenceNumber.TWO)) {
								if (editedWeight == null || editedWeight <= 0) {
									String errorMsg = propertiesReader.getValueOfKey("weight_null");
									addToErrorList(errorMsg + (i + 1));
								} else if (weightUom == null) {
									String errorMsg = propertiesReader.getValueOfKey("product_sku_weight_uom_null");
									addToErrorList(errorMsg + (i + 1));
								}

								if (quantity != null) {
									if (quantity <= 0) {
										String errorMsg = propertiesReader.getValueOfKey("quantity_null");
										addToErrorList(errorMsg + (i + 1));
									}

									if (quantityUom == null) {
										String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_uom_null");
										addToErrorList(errorMsg + (i + 1));
									}
								} else if (quantityUom != null) {
									if (quantity == null || quantity <= 0) {
										String errorMsg = propertiesReader.getValueOfKey("quantity_null");
										addToErrorList(errorMsg + (i + 1));
									}
								}

							} else if (allocationBasedOn.equals(CommonConstant.ParameterSequenceNumber.THREE)) {

								if (quantity == null || quantity <= 0) {
									String errorMsg = propertiesReader.getValueOfKey("quantity_null");
									addToErrorList(errorMsg + (i + 1));
								} else if (quantityUom == null) {
									String errorMsg = propertiesReader.getValueOfKey("product_sku_qty_uom_null");
									addToErrorList(errorMsg + (i + 1));
								}

								if (editedWeight == null || editedWeight <= 0) {
									String errorMsg = propertiesReader.getValueOfKey("weight_null");
									addToErrorList(errorMsg + (i + 1));
								} else if (weightUom == null) {
									String errorMsg = propertiesReader.getValueOfKey("product_sku_weight_uom_null");
									addToErrorList(errorMsg + (i + 1));
								}
							}
						}
					}
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("add_properties_for_bom"));
		}

		if (getErrorList().size() > 0) {
			valSaveFlag = false;
		} else {
			valSaveFlag = true;
		}
		return valSaveFlag;
	}

	public void calculateWeightAsPerQuantity(ProductItemPropertyMappingDVO itemPropertyMappingRecord) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In calculateWeightAsPerQuantity starts ");
		setErrorList(new ArrayList<String>());
		// PropertiesReader propertiesReader = new
		// PropertiesReader(propertiesLocation);
		Integer lineNumber = itemPropertyMappingRecord.getLineNumber();

		// Long productSkuBomId = itemPropertyMappingRecord.getId();
		// Long selectedItemCategoryId =
		// itemPropertyMappingRecord.getItemCategoryRecord().getId();
		// String selectedItemCode =
		// itemPropertyMappingRecord.getItemRecord().getCode();

		if (productOpr.getProductRecord().getProductItemPropertyMappingList().size() > 0) {
			for (int i = 0; i < productOpr.getProductRecord().getProductItemPropertyMappingList().size(); i++) {
				ProductItemPropertyMappingDVO listDVO = productOpr.getProductRecord()
						.getProductItemPropertyMappingList().get(i);
				// Long listProductSkuBomId = listDVO.getId();
				// Long listItemCategoryId =
				// listDVO.getItemCategoryRecord().getId();
				// String listItemCode = listDVO.getItemRecord().getCode();
				Integer listLineNumber = listDVO.getLineNumber();

				if (listLineNumber != null && lineNumber != null && lineNumber.intValue() == listLineNumber.intValue()) {
					Float quantity = listDVO.getQuantity();
					Float perUniWt = (float) 1.0;
					if (listDVO.getPerUnitWeight() != null) {
						perUniWt = listDVO.getPerUnitWeight();
					}
					if (quantity != null && quantity > 0) {
						Float calWeight = quantity * perUniWt;
						calWeight = CommonUtil.roundOffFloat(calWeight);
						myLog.debug("calWeight - " + calWeight + "::: at - " + i);
						listDVO.setCalculatedWeight(calWeight);
						listDVO.setEditedWeight(calWeight);
					}
					break;
				}

				// if (productSkuBomId != null) {
				// if (listProductSkuBomId != null &&
				// productSkuBomId.equals(listProductSkuBomId)) {
				// myLog.debug("HDR ID MATCH - " + listProductSkuBomId);
				// Float quantity = listDVO.getQuantity();
				// Float perUniWt = (float) 1.0;
				// if (listDVO.getPerUnitWeight() != null) {
				// perUniWt = listDVO.getPerUnitWeight();
				// }
				// if (quantity != null) {
				// Float calWeight = quantity * perUniWt;
				// calWeight = CommonUtil.roundOffFloat(calWeight);
				// myLog.debug("calWeight - " + calWeight + "::: at - " + i);
				// listDVO.setCalculatedWeight(calWeight);
				// listDVO.setEditedWeight(calWeight);
				// }
				// break;
				// }
				// } else if (selectedItemCategoryId != null && selectedItemCode
				// != null) {
				// if (listItemCategoryId != null && listItemCode != null
				// && listItemCategoryId.equals(selectedItemCategoryId)
				// && listItemCode.equals(selectedItemCode)) {
				// myLog.debug("ITEM CODE  MATCH - " + listItemCategoryId +
				// " - " + listItemCode);
				// Float quantity = listDVO.getQuantity();
				// Float perUniWt = (float) 1.0;
				// if (listDVO.getPerUnitWeight() != null) {
				// perUniWt = listDVO.getPerUnitWeight();
				// }
				// if (quantity != null) {
				// Float calWeight = quantity * perUniWt;
				// calWeight = CommonUtil.roundOffFloat(calWeight);
				// myLog.debug("calWeight - " + calWeight + "::: at - " + i);
				// listDVO.setCalculatedWeight(calWeight);
				// listDVO.setEditedWeight(calWeight);
				// }
				// break;
				// }
				// } else {
				// String errorMsg =
				// propertiesReader.getValueOfKey("product_sku_item_category_null");
				// addToErrorList(errorMsg + (i + 1));
				// break;
				// }
			}
		}
	}

	public void getSkuBomProcessVariationDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In getSkuBomProcessVariationDetails starts ");
		RequestContext context = RequestContext.getCurrentInstance();

		if (validateOpenSkuBomProcessVariationDetails()) {
			Object selectedBomHeaderRecord = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
					.getAttributes().get("itemPropertyMappingRow");
			ProductItemPropertyMappingDVO productItemPropertyMappingRecord = (ProductItemPropertyMappingDVO) selectedBomHeaderRecord;
			productOpr.getProductRecord().setProductItemPropertyMappingRecord(
					(ProductItemPropertyMappingDVO) DeepCopy.copy(productItemPropertyMappingRecord));
			try {
				ProductOpr productOprRec = new ProductDefinitionBD().getSkuBomProcessVariationDetails(productOpr);

				ArrayList<ProductSkuBomProcessVariationDetailsDVO> skuMappingPVList = productOprRec.getProductRecord()
						.getProductItemPropertyMappingRecord().getSkuBomProcessVariationList();

				productOpr
						.getProductRecord()
						.getProductItemPropertyMappingRecord()
						.setSkuBomProcessVariationList(
								(ArrayList<ProductSkuBomProcessVariationDetailsDVO>) DeepCopy.copy(skuMappingPVList));

				if (skuMappingPVList != null && skuMappingPVList.size() > 0) {
					List<Object> processVariationListObj = new ArrayList<Object>();

					for (ProductSkuBomProcessVariationDetailsDVO listRecord : skuMappingPVList) {
						processVariationListObj.add(listRecord.getProcessVariationMappingRecord());
					}
					FacesContext.getCurrentInstance().getViewRoot().getViewMap()
							.put("processVariationAutoCompleteForPV", processVariationListObj);
				}

				context.execute("openProductSkuBomProcessVariationDialog();");
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateOpenSkuBomProcessVariationDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In executeAddRowForSkuBomPV starts ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = false;
		setErrorList(new ArrayList<String>());
		FoundationValidator validator = new FoundationValidator();

		Object selectedBomHeaderRecord = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("itemPropertyMappingRow");
		ProductItemPropertyMappingDVO productItemPropertyMappingRecord = (ProductItemPropertyMappingDVO) selectedBomHeaderRecord;
		Long skuBomHeaderId = productItemPropertyMappingRecord.getId();

		for (int i = 0; i < productOpr.getProductRecord().getProductItemPropertyMappingList().size(); i++) {
			ProductItemPropertyMappingDVO listRecord = productOpr.getProductRecord()
					.getProductItemPropertyMappingList().get(i);
			Long skuBomListID = listRecord.getId();

			if (skuBomListID != null && skuBomHeaderId != null && skuBomHeaderId.equals(skuBomListID)) {
				if (!validator.validateFloatObjectNull(listRecord.getQuantity()) || listRecord.getQuantity() <= 0) {
					String errorMsg = propertiesReader.getValueOfKey("quantity_null");
					addToErrorList(errorMsg + (i + 1));
				}
				if (!validator.validateNull(listRecord.getQuantityUOM().getCode())) {
					String errorMsg = propertiesReader.getValueOfKey("quantity_null");
					addToErrorList(errorMsg + (i + 1));
				}
				break;
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeAddRowForSkuBomPV(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In executeAddRowForSkuBomPV starts ");

		ProductSkuBomProcessVariationDetailsDVO productSkuBomProcessVariationDetailsDVO = new ProductSkuBomProcessVariationDetailsDVO();
		productSkuBomProcessVariationDetailsDVO.setQuantityUomRecord(productOpr.getProductRecord()
				.getProductItemPropertyMappingRecord().getQuantityUOM());
		productOpr.getProductRecord().getProductItemPropertyMappingRecord().getSkuBomProcessVariationList()
				.add(productSkuBomProcessVariationDetailsDVO);

	}

	public List<Object> getSuggestedProcessVariationListBasedOnProcessForPV(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				Object processIdStr = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
						.getAttributes().get("selectedProcessId");
				myLog.debug(" processIdStr :: " + processIdStr);

				if (processIdStr != null) {
					ProcessDVO processDVOSend = new ProcessDVO();
					processDVOSend.setIdString(processIdStr.toString());
					ArrayList<ProcessVariationMappingDVO> processVariationList = new ProductDefinitionBD()
							.getProcessVariationMappingListOnProcess(processDVOSend);

					List<Object> processVariationListObj = (List<Object>) FacesContext.getCurrentInstance()
							.getViewRoot().getViewMap().get("processVariationAutoCompleteForPV");
					HashMap<Long, Object> listMap = new HashMap<Long, Object>();

					if (processVariationListObj != null && processVariationListObj.size() > 0) {
						for (Object listObj : processVariationListObj) {
							ProcessVariationMappingDVO listDVO = (ProcessVariationMappingDVO) listObj;
							Long processVariationId = listDVO.getId();
							if (processVariationId != null) {
								listMap.put(processVariationId, listDVO);
							}
						}
					} else {
						processVariationListObj = new ArrayList<Object>();
					}

					if (processVariationList != null && processVariationList.size() > 0) {
						for (Object listObj : processVariationList) {
							ProcessVariationMappingDVO listDVO = (ProcessVariationMappingDVO) listObj;
							Long pvId = listDVO.getId();
							if (listMap != null && listMap.size() > 0) {
								if (!listMap.containsKey(pvId)) {
									processVariationListObj.add(listObj);
								}
							} else {
								processVariationListObj.add(listObj);
							}
						}
					}
					// if (processVariationListObj == null ||
					// processVariationListObj.isEmpty()) {
					// processVariationListObj = new ArrayList<Object>();
					// }
					// processVariationListObj.addAll(processVariationList);
					FacesContext.getCurrentInstance().getViewRoot().getViewMap()
							.put("processVariationAutoCompleteForPV", processVariationListObj);
					return processVariationListObj;
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public void executeSubmitProductSkuPvDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In executeSaveProductSkuPvDetails starts ");
		RequestContext context = RequestContext.getCurrentInstance();
		if (validateSaveProductSkuPvDetails()) {

			Long productSkubomHeaderId = productOpr.getProductRecord().getProductItemPropertyMappingRecord().getId();

			// for submitting data on sku bom header screen
			for (int i = 0; i < productOpr.getProductRecord().getProductItemPropertyMappingList().size(); i++) {
				ProductItemPropertyMappingDVO skuBomHeaderListRecord = productOpr.getProductRecord()
						.getProductItemPropertyMappingList().get(i);

				Long productSkubomHeaderListId = skuBomHeaderListRecord.getId();
				if (productSkubomHeaderListId != null && productSkubomHeaderId != null
						&& productSkubomHeaderListId.equals(productSkubomHeaderId)) {
					skuBomHeaderListRecord.setSkuBomProcessVariationList(productOpr.getProductRecord()
							.getProductItemPropertyMappingRecord().getSkuBomProcessVariationList());
					break;
				}
			}

			context.execute("closeProductSkuBomProcessVariationDialog();");
		}
	}

	public boolean validateSaveProductSkuPvDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In validateSaveProductSkuPvDetails starts ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = false;
		setErrorList(new ArrayList<String>());
		FoundationValidator validator = new FoundationValidator();

		ArrayList<ProductSkuBomProcessVariationDetailsDVO> pvMappingList = productOpr.getProductRecord()
				.getProductItemPropertyMappingRecord().getSkuBomProcessVariationList();
		if (pvMappingList != null && pvMappingList.size() > 0) {
			Float hdrQuantity = productOpr.getProductRecord().getProductItemPropertyMappingRecord().getQuantity();

			for (int i = 0; i < pvMappingList.size(); i++) {
				if (!pvMappingList.get(i).getOperationalAttributes().getRecordDeleted()) {
					Float dtlQuantity = pvMappingList.get(i).getQuantity();
					Long processId = pvMappingList.get(i).getProcessRecord().getId();
					Long processVariationId = pvMappingList.get(i).getProcessVariationMappingRecord().getId();

					if (!validator.validateFloatObjectNull(dtlQuantity) || dtlQuantity.floatValue() <= 0) {
						String errMsg = propertiesReader.getValueOfKey("quantity_null");
						addToErrorList(errMsg + (i + 1));
					} else if (hdrQuantity != null && hdrQuantity.floatValue() < dtlQuantity.floatValue()) {
						String errMsg = propertiesReader.getValueOfKey("pv_quantity_greater");
						addToErrorList(errMsg + (i + 1));
					}

					if (!validator.validateLongObjectNull(processId)) {
						String errMsg = propertiesReader.getValueOfKey("process_null");
						addToErrorList(errMsg + (i + 1));
					}

					if (!validator.validateLongObjectNull(processVariationId)) {
						String errMsg = propertiesReader.getValueOfKey("process_variation_null");
						addToErrorList(errMsg + (i + 1));
					}
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("add_pv_bom_details"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeEnlargeImage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: executeEnlargeImage starts ");

		ArrayList<ImageDVO> imageList = new ArrayList<ImageDVO>();

		if (productOpr.getProductRecord().getDefaultImageRecord().getImageURL() != null) {
			ImageDVO imageRecord = new ImageDVO(productOpr.getProductRecord().getDefaultImageRecord().getImageURL());
			imageList.add(imageRecord);

		}
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.IMAGE_DVO_LIST, imageList);
	}

	public String redirectBackToAutoSku() {
		String statusCode = productOpr.getProductRecord().getProductSkuRecord().getStatusRecord().getCode();
		if (statusCode != null
				&& FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.containsKey("AUTO_SKU_GEN_EDIT_RECORD")) {
			AutoSKUGenerationDVO autoSKUGenerationDVO = (AutoSKUGenerationDVO) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("AUTO_SKU_GEN_EDIT_RECORD");
			autoSKUGenerationDVO.getAutoSkuGenerationHeaderRecord().getGeneratedProductSku().getStatusRecord()
					.setCode(statusCode);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put("AUTO_SKU_GEN_EDIT_RECORD", autoSKUGenerationDVO);
		}
		return "pretty:autoSKUGeneration";
	}
}
