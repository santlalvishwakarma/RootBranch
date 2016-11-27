package com.web.bb.systemowner.modules.productmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.productmanagement.ProductDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.ProductOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductHierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

/**
 * @author NIRAJ
 * 
 */
public class ProductDefinitionAddEditBB extends BackingBean {

	private static final long serialVersionUID = 2279688254567743485L;
	private String propertiesLocation = "com/web/bb/systemowner/modules/productmanagement/productdefinition";
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

	private boolean renderGetBackToAutoSku = false;
	private ProductSkuDVO productSkuRecord;
	private List<CategoryDVO> categoryListForAutoSuggest;

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

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
	}

	public List<CategoryDVO> getCategoryListForAutoSuggest() {
		if (categoryListForAutoSuggest == null) {
			categoryListForAutoSuggest = new ArrayList<CategoryDVO>();
		}
		return categoryListForAutoSuggest;
	}

	public void setCategoryListForAutoSuggest(List<CategoryDVO> categoryListForAutoSuggest) {
		this.categoryListForAutoSuggest = categoryListForAutoSuggest;
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

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				productOpr = new ProductDefinitionBF().executeSaveProductSKUDetails(productOpr);

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

		if (!validator.validateNull(productCode)) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_null"));
		}
		if (productCode != null && !validator.validateCharsWithoutSpecialChars(productCode)) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_invalid"));
		}

		if (!validator.validateNull(productName)) {
			addToErrorList(propertiesReader.getValueOfKey("product_name_null"));
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

			allOptions.setAllOptionsValues(new ProductDefinitionBF().getAllOptionsValuesForProduct());
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
				productOpr = new ProductDefinitionBF().getProductDetails(productOpr);
				populateData();

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

	private void populateData() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: populateData starts ");
		HashMap<Boolean, String> yesNoValuesMap = new HashMap<Boolean, String>();
		ArrayList<Object> yesNoList = new ProductDefinitionBF().getAllOptionsValuesForSearch().get("yesNoList");

		myLog.debug("yes no list::" + yesNoList.size());

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!productOpr.getProductRecord().getProductSkuList().isEmpty()) {
			for (ProductSkuDVO productSkuRecord : productOpr.getProductRecord().getProductSkuList()) {
				String activeDescription = yesNoValuesMap.get(productSkuRecord.getActive());
				productSkuRecord.setActiveDescription(activeDescription);
			}
		}
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

			hierarchyListForAutoSuggest = new ProductDefinitionBF().getSuggestedHierarchies(new HierarchyDVO());

			myLog.debug("In Product Definition Add Edit BB :: hierarchy list size::"
					+ hierarchyListForAutoSuggest.size());

			categoryListForAutoSuggest = new ProductDefinitionBF().getAllCategories();

			productOpr.getProductRecord().setProductHierarchyCategoryMappingList(null);

			ProductOpr productOprSent = new ProductDefinitionBF().getProductHierarchyCategoryMappingList(productOpr);

			productOpr.getProductRecord().setProductHierarchyCategoryMappingList(
					productOprSent.getProductRecord().getProductHierarchyCategoryMappingList());

			if (!productOpr.getProductRecord().getProductHierarchyCategoryMappingList().isEmpty()) {
				List<Object> hierarchyList = new ArrayList<Object>();

				for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : productOpr
						.getProductRecord().getProductHierarchyCategoryMappingList()) {
					hierarchyList.add(productHierarchyCategoryMappingDVO.getHierarchyRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productHierarchyCodeAutoComplete", hierarchyList);

				List<Object> categoryLevel1List = new ArrayList<Object>();
				for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : productOpr
						.getProductRecord().getProductHierarchyCategoryMappingList()) {
					categoryLevel1List.add(productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord()
							.getCategoryLevelOneRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel1AutoComplete", categoryLevel1List);

				List<Object> categoryLevel2List = new ArrayList<Object>();
				for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : productOpr
						.getProductRecord().getProductHierarchyCategoryMappingList()) {
					categoryLevel2List.add(productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord()
							.getCategoryLevelTwoRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel2AutoComplete", categoryLevel2List);

				List<Object> categoryLevel3List = new ArrayList<Object>();
				for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : productOpr
						.getProductRecord().getProductHierarchyCategoryMappingList()) {
					categoryLevel3List.add(productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord()
							.getCategoryLevelThreeRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel3AutoComplete", categoryLevel3List);

				List<Object> categoryLevel4List = new ArrayList<Object>();
				for (ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO : productOpr
						.getProductRecord().getProductHierarchyCategoryMappingList()) {
					categoryLevel4List.add(productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord()
							.getCategoryLevelFourRecord());
				}
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel4AutoComplete", categoryLevel4List);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public List<Object> getSuggestedHierarchiesForCode(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: getSuggestedHierarchiesForCode starts ");
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				HierarchyDVO productHierarchyRecord = (HierarchyDVO) object;
				String code = productHierarchyRecord.getCode();
				myLog.debug("hierarchy id:: " + productHierarchyRecord.getId());

				if (code.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}

		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("productHierarchyCodeAutoComplete", hierarchyListForAutoSuggest);

		myLog.debug("filter size::" + productHierarchyList.size());
		return productHierarchyList;
	}

	public List<Object> getSuggestedHierarchiesForName(String query) {
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				HierarchyDVO productHierarchyRecord = (HierarchyDVO) object;
				String name = productHierarchyRecord.getName();

				if (name.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}
		return productHierarchyList;
	}

	public List<Object> getSuggestedCategoryForCode(String query) {
		ArrayList<Object> productCategoryList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (CategoryDVO categoryDVO : categoryListForAutoSuggest) {
				String code = categoryDVO.getCode();

				if (code.toUpperCase().startsWith(query)) {
					productCategoryList.add(categoryDVO);
				}
			}
		}
		return productCategoryList;
	}

	public List<Object> getSuggestedCategoryForName(String query) {
		ArrayList<Object> productCategoryList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (CategoryDVO categoryDVO : categoryListForAutoSuggest) {
				String code = categoryDVO.getName();

				if (code.toUpperCase().startsWith(query)) {
					productCategoryList.add(categoryDVO);
				}
			}
		}
		return productCategoryList;
	}

	public List<Object> getSuggestedCategoryLevel1BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: getSuggestedCategoryLevel1BasedOnHierarchy starts ");
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		myLog.debug("hierarchy id::" + hierarchyId);
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (hierarchyId == null) {
			addToErrorList(propertiesReader.getValueOfKey("hierarchy_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(1);

				List<Object> categoryLevel1List = new ProductDefinitionBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel1AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel1List == null || categoryLevel1List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_1_no_records"));
				}
				return categoryLevel1List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		return null;
	}

	public List<Object> getSuggestedCategoryLevel2BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category1Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_1_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(2);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);

				List<Object> categoryLevel2List = new ProductDefinitionBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel2AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel2List == null || categoryLevel2List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_2_no_records"));
				}

				return categoryLevel2List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedCategoryLevel3BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");
		Object category2Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category2Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category2Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_2_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(3);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);
				if (category2Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelTwoRecord().setId((Long) category2Id);

				List<Object> categoryLevel3List = new ProductDefinitionBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel3AutoComplete", categoryListForAutoSuggest);

				if (categoryLevel3List == null || categoryLevel3List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_3_no_records"));
				}
				return categoryLevel3List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		addToErrorList(propertiesReader.getValueOfKey("category_level_3_no_records"));
		return null;
	}

	public List<Object> getSuggestedCategoryLevel4BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");
		Object category2Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category2Id");
		Object category3Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category3Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category3Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_3_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(4);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);
				if (category2Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelTwoRecord().setId((Long) category2Id);
				if (category3Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelThreeRecord().setId((Long) category3Id);

				List<Object> categoryLevel4List = new ProductDefinitionBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel4AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel4List == null || categoryLevel4List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_4_no_records"));
				}
				return categoryLevel4List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}

		}
		addToErrorList(propertiesReader.getValueOfKey("category_level_4_no_records"));
		return null;
	}

	public void executeHierarchyMappingAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductHierarchyCategoryMappingList()
				.add(new ProductHierarchyCategoryMappingDVO());
	}

	public void hierarchyChanged(ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO) {
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelOneRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelTwoRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelThreeRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelFourRecord(null);
	}

	public void categoryLevel1Changed(ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO) {
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelTwoRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelThreeRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelFourRecord(null);
	}

	public void categoryLevel2Changed(ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO) {
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelThreeRecord(null);
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelFourRecord(null);
	}

	public void categoryLevel3Changed(ProductHierarchyCategoryMappingDVO productHierarchyCategoryMappingDVO) {
		productHierarchyCategoryMappingDVO.getHierarchyCategoryMappingRecord().setCategoryLevelFourRecord(null);
	}

	public void executeSaveHierarchyMapping(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveHierarchyMapping starts ");

		if (validateSaveProductHierarchyMapping()
				&& !productOpr.getProductRecord().getProductHierarchyCategoryMappingList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				productOpr.getProductRecord().setUserLogin(userLogin);

				ProductOpr productOprRecd = new ProductDefinitionBF().saveHierarchiesMappingList(productOpr);

				productOpr.getProductRecord().setProductHierarchyCategoryMappingList(
						productOprRecd.getProductRecord().getProductHierarchyCategoryMappingList());
				productOpr.getProductRecord()
						.setAuditAttributes(productOprRecd.getProductRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("hierarchy_save_success"));

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

		int size = productOpr.getProductRecord().getProductHierarchyCategoryMappingList().size();

		if (size > 0) {
			for (int i = 0; i < size; i++) {
				ProductHierarchyCategoryMappingDVO productHierarchyCategoryRecord = productOpr.getProductRecord()
						.getProductHierarchyCategoryMappingList().get(i);

				if (!productHierarchyCategoryRecord.getOperationalAttributes().getRecordDeleted()) {

					Long hierarchyId = productHierarchyCategoryRecord.getHierarchyRecord().getId();

					if (!validator.validateLongObjectNull(hierarchyId)) {
						addToErrorList(propertiesReader.getValueOfKey("hierarchy_null") + (i + 1));
					}
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("hierarchy_list_null"));
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

		ImageDVO imageRecord = new ImageDVO(getProductSkuRecord().getDefaultImageRecord().getZoomImageURL());
		myLog.debug("In Sku Definition Search BB :: zoom image url::"
				+ getProductSkuRecord().getDefaultImageRecord().getZoomImageURL());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.IMAGE_DVO, imageRecord);
	}
}
