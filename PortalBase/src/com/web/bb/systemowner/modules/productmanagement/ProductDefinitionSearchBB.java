package com.web.bb.systemowner.modules.productmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.web.bf.systemowner.modules.productmanagement.ProductDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.systemowner.ProductOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.PropertyDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.jsf.converters.ProductCategoryConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

/**
 * @author Santlal
 * 
 */
public class ProductDefinitionSearchBB extends BackingBean {

	private static final long serialVersionUID = 3115234382157049259L;

	private String propertiesLocation = "com/web/bb/systemowner/modules/productmanagement/productdefinition";
	private ProductOpr productOpr;
	private OptionsDVO allOptions;
	private ProductDVO selectedProductRecord;
	private transient BaseDVOConverter baseDVOConverter;
	private ArrayList<Object> productHierarchyList;
	private transient ProductCategoryConverter productCategoryConverter;

	public ProductOpr getProductOpr() {

		if (productOpr == null) {
			productOpr = new ProductOpr();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			productOpr.setProductDVOList(new ArrayList<ProductDVO>());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.RE_INITIALIZE_OPR);
		}
		return productOpr;
	}

	public void setProductOpr(ProductOpr productOpr) {
		this.productOpr = productOpr;
	}

	public ProductDVO getSelectedProductRecord() {
		if (selectedProductRecord == null) {
			selectedProductRecord = new ProductDVO();
		}
		return selectedProductRecord;
	}

	public void setSelectedProductRecord(ProductDVO selectedProductRecord) {
		this.selectedProductRecord = selectedProductRecord;
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

	public ArrayList<Object> getProductHierarchyList() {
		if (productHierarchyList == null) {
			productHierarchyList = new ArrayList<Object>();
		}
		return productHierarchyList;
	}

	public void setProductHierarchyList(ArrayList<Object> productHierarchyList) {
		this.productHierarchyList = productHierarchyList;
	}

	public ProductCategoryConverter getProductCategoryConverter() {
		if (productCategoryConverter == null) {
			productCategoryConverter = new ProductCategoryConverter();
		}
		return productCategoryConverter;
	}

	public void setProductCategoryConverter(ProductCategoryConverter productCategoryConverter) {
		this.productCategoryConverter = productCategoryConverter;
	}

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		myLog.debug("In Product Definition Search BB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				productOpr.setProductDVOList(null);
				ProductOpr productOprRet = (new ProductDefinitionBF()).executeSearch(productOpr);
				productOpr.setProductDVOList(productOprRet.getProductDVOList());
				populateData();

			} catch (FrameworkException e) {

				handleException(e, propertiesLocation);
			} catch (BusinessException e) {

				handleException(e, propertiesLocation);
			}

		}
	}

	@Override
	public boolean validateSearch() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		PropertiesReader propertiesReader2 = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		ProductDVO productRecord = productOpr.getProductRecord();
		if (!(validator.validateNull(productRecord.getCode()) || validator.validateNull(productRecord.getName())
				|| validator.validateNull(productRecord.getDescription())
				|| validator.validateNull(productRecord.getProductSkuRecord().getCode())
				|| validator.validateNull(productRecord.getProductSkuRecord().getDescription()) || validator
					.validateNull(productRecord.getStatusRecord().getCode()))) {

			if (productOpr.getProductCategoryList().isEmpty()) {
				validateFlag = false;
			}
		}

		int propertySelected = 0;
		// if (!productRecord.getProductPropertiesMappingList().isEmpty()) {
		// for (ProductPropertiesMappingDVO productPropertiesMappingDVO :
		// productRecord
		// .getProductPropertiesMappingList()) {
		// if
		// (!productPropertiesMappingDVO.getOperationalAttributes().getRecordDeleted())
		// {
		// Long propertyId =
		// productPropertiesMappingDVO.getProductPropertiesRecord().getId();
		// if (propertyId == null)
		// productPropertiesMappingDVO.getOperationalAttributes().setRecordDeleted(true);
		// }
		// if
		// (!productPropertiesMappingDVO.getOperationalAttributes().getRecordDeleted())
		// propertySelected++;
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// myLog.debug(" condition " +
		// productPropertiesMappingDVO.getPropertyCondition());
		// myLog.debug(" condition " +
		// allOptions.getAllOptionsValues().get("conditionList"));
		// }
		// }

		if (!validateFlag && propertySelected == 0) {
			addToErrorList(propertiesReader.getValueOfKey("all_fields_null"));
			validateFlag = false;
		}

		if (!productOpr.getProductCategoryList().isEmpty()) {
			addToErrorList(propertiesReader2.getValueOfKey("enter_either_hierarchy_or_category"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		return validateFlag;
	}

	@Override
	public void clearPage(ActionEvent event) {
	}

	@Override
	public void resetPage(ActionEvent event) {
	}

	@Override
	public void executeSave(ActionEvent event) {
	}

	@Override
	public boolean validateSave() {
		return false;
	}

	@Override
	public void addEditTabClicked(ActionEvent event) {
	}

	@Override
	public void searchTabClicked(ActionEvent event) {
	}

	@Override
	public void editDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: editDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ProductOpr productOprSent = new ProductOpr();
		productOprSent.getProductRecord().setId(selectedProductRecord.getId());
		productOprSent.getProductRecord().getProductSkuRecord().getImageRecord()
				.setThumbnailImageURL(selectedProductRecord.getProductSkuRecord().getImageRecord().getImageURL());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "PRODUCT");

	}

	public void editSKUDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: editSKUDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ProductOpr productOprSent = new ProductOpr();
		productOprSent.getProductRecord().setId(selectedProductRecord.getId());
		productOprSent.getProductRecord().getProductSkuRecord()
				.setId(selectedProductRecord.getProductSkuRecord().getId());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");
	}

	public void createNewProduct() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: createNewProduct starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		ProductOpr productOprSent = new ProductOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "PRODUCT");

	}

	public void createNewSKU() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: createNewSKU starts ");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		String productStatusCode = selectedProductRecord.getStatusRecord().getCode();
		Boolean active = selectedProductRecord.getActive();
		if (!CommonConstant.StatusCodes.APPROVED.equals(productStatusCode)) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_not_approved"));
		}
		if (active != null && !active) {
			addToErrorList(propertiesReader.getValueOfKey("product_code_not_active"));
		}

		if (getErrorList().size() == 0) {
			ProductOpr productOprSent = new ProductOpr();
			productOprSent.getProductRecord().setId(selectedProductRecord.getId());

			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(CommonConstant.ACTIVE_TAB_OPR, productOprSent);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");
		}
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: retrieveData starts ");
		beanName = "productDefinitionSearchListBB";
		try {

			propertiesForAutoSuggest = new ArrayList<PropertyDVO>();
			propertiesForAutoSuggest = new ProductDefinitionBF().getSuggestedPropertiesBasedOnName("%");
			myLog.debug(" propertiesForAutoSuggest size ---> " + propertiesForAutoSuggest.size());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		try {
			productHierarchyList = new ProductDefinitionBF().getSuggestedHierarchies(new HierarchyDVO());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productHierarchyAutoComplete", productHierarchyList);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {

				String parameterCode = CommonConstant.ParameterCode.PRODUCT_PROPERTIES_CONDITIONS;
				Parameter parameter = new Parameter();
				parameter.setParameterCode(parameterCode);

				ParameterOpr parameterOpr = new ParameterOpr();
				parameterOpr.getParameterList().add(parameter);

				parameterOpr = new ProductDefinitionBF().getOptionsOnParameterCode(parameterOpr);

				allOptions.getAllOptionsValues().put("conditionList",
						parameterOpr.getParameterOptionsMap().get(parameterCode));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
			try {
				allOptions.getAllOptionsValues().put("statusList", new ProductDefinitionBF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		try {
			productCategoriesForAutoSuggest = new ProductDefinitionBF().getAllCategories();

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		myLog.debug("In Product Category BB :: retrieveData ends ");
	}

	@Override
	public void executeAddRow(ActionEvent event) {
		productOpr.getProductRecord().getProductPropertiesMappingList().add(new ProductPropertiesMappingDVO());
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

	public List<Object> getSuggestedHierarchies(String query) {
		List<Object> productHierarchyObjectList = new ArrayList<Object>();
		if (query != null) {

			if (productHierarchyList != null && !productHierarchyList.isEmpty()) {
				query = query.toUpperCase();
				for (Object object : productHierarchyList) {
					HierarchyDVO productHierarchyRecord = (HierarchyDVO) object;
					String name = productHierarchyRecord.getName();

					if (name != null && name.toUpperCase().startsWith(query)) {
						productHierarchyObjectList.add(productHierarchyRecord);
					}
				}
			}
		}
		return productHierarchyObjectList;
	}

	public List<ProductPropertiesDVO> getSuggestedPropertiesBasedOnName(String query) {
		List<ProductPropertiesDVO> productPropertiesList = new ArrayList<ProductPropertiesDVO>();
		if (query != null) {
			query = query.toUpperCase();
			for (ProductPropertiesDVO productPropertiesRecord : propertiesForAutoSuggest) {
				String name = productPropertiesRecord.getName();

				if (name.toUpperCase().startsWith(query)) {
					productPropertiesList.add(productPropertiesRecord);
				}
			}
		}
		return productPropertiesList;
	}

	public void executeClearProperties(ActionEvent event) {
		productOpr.getProductRecord().setProductPropertiesMappingList(new ArrayList<ProductPropertiesMappingDVO>());
	}

	public List<CategoryDVO> getSuggestedCategories(String query) {

		List<CategoryDVO> productCategoryList = new ArrayList<CategoryDVO>();
		if (query != null) {
			query = query.toUpperCase();

			for (CategoryDVO productCategoryRecord : productCategoriesForAutoSuggest) {

				String name = productCategoryRecord.getName();

				if (name.toUpperCase().startsWith(query)) {
					productCategoryList.add(productCategoryRecord);
				}
			}
		}
		return productCategoryList;
	}

	public void executeEnlargeImage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Search BB :: executeEnlargeImage starts ");

		ArrayList<ImageDVO> imageList = new ArrayList<ImageDVO>();

		if (!selectedProductRecord.getProductImageMappingList().isEmpty()) {
			for (ProductImageMappingDVO productImageMappingDVO : selectedProductRecord.getProductImageMappingList()) {

				ImageDVO imageRecord = new ImageDVO(productImageMappingDVO.getImageURL());
				imageList.add(imageRecord);

			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.IMAGE_DVO_LIST, imageList);
	}

	public String productMappingForSingleProduct() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside productMappingForSingleProduct():::");

		productOpr.getSelectedProductRecord().getOperationalAttributes().setRecordSelected(true);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ParameterCode.SELECTED_PRODUCT_RECORD, productOpr.getSelectedProductRecord());

		RequestContext.getCurrentInstance().execute("productMappingToAddEditList();");
		return null;
	}

	public void productMappingForMultipleProduct(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside productMappingForMultipleProduct():::");

		for (int i = 0; i < productOpr.getProductList().size(); i++) {
			productOpr.getProductList().get(i).getOperationalAttributes().setRecordSelected(true);
		}

		myLog.debug("before productOpr.getProductList().size()" + productOpr.getProductList().size());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.ParameterCode.ALL_SEARCHED_PRODUCT_RECORDS, productOpr.getProductList());
		// productOpr.getProductList().removeAll(productOpr.getProductList());
		myLog.debug("after productOpr.getProductList().size()" + productOpr.getProductList().size());

		RequestContext.getCurrentInstance().execute("productMappingToAddEditList();");
	}

	private void populateData() throws FrameworkException, BusinessException {
		HashMap<Boolean, String> yesNoValuesMap = new HashMap<Boolean, String>();
		ArrayList<Object> yesNoList = new ProductDefinitionBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!productOpr.getProductDVOList().isEmpty()) {
			for (ProductDVO productRecord : productOpr.getProductDVOList()) {
				String activeDescription = yesNoValuesMap.get(productRecord.getActive());
				productRecord.setActiveDescription(activeDescription);
			}
		}
	}

	public void clearPopUpPage(ActionEvent event) {
		productOpr = new ProductOpr();
	}

}
