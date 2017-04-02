package com.web.bb.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.skumanagement.SkuDefinitionBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
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
public class SkuDefinitionSearchBB extends BackingBean {

	private static final long serialVersionUID = 3115234382157049259L;

	private String propertiesLocation = "com/web/bb/systemowner/modules/skumanagement/skudefinition";
	private SkuOpr skuOpr;
	private OptionsDVO allOptions;
	private ProductSkuDVO selectedSkuRecord;
	private transient BaseDVOConverter baseDVOConverter;
	private transient ProductCategoryConverter productCategoryConverter;

	public SkuOpr getSkuOpr() {
		if (skuOpr == null) {
			skuOpr = new SkuOpr();
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			skuOpr.setProductSkuList(new ArrayList<ProductSkuDVO>());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.RE_INITIALIZE_OPR);
		}

		return skuOpr;
	}

	public void setSkuOpr(SkuOpr skuOpr) {
		this.skuOpr = skuOpr;
	}

	public ProductSkuDVO getSelectedSkuRecord() {
		if (selectedSkuRecord == null) {
			selectedSkuRecord = new ProductSkuDVO();
		}
		return selectedSkuRecord;
	}

	public void setSelectedSkuRecord(ProductSkuDVO selectedSkuRecord) {
		this.selectedSkuRecord = selectedSkuRecord;
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
		myLog.debug("In SkuDefinitionSearchBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				skuOpr.setProductSkuList(null);

				SkuOpr skuOprRet = (new SkuDefinitionBF()).executeSearch(skuOpr);
				skuOpr.setProductSkuList(skuOprRet.getProductSkuList());
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
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();
		if (!(validator.validateNull(productSkuRecord.getCode()) || validator.validateNull(productSkuRecord.getName())
				|| validator.validateNull(productSkuRecord.getDescription())
				|| validator.validateNull(productSkuRecord.getProductRecord().getCode())
				|| validator.validateNull(productSkuRecord.getStatusRecord().getCode()))) {
			addToErrorList(propertiesReaderCommon.getValueOfKey("all_fields_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		return validateFlag;
	}

	@Override
	public void executeSave(ActionEvent event) {
	}

	@Override
	public boolean validateSave() {
		return false;
	}

	@Override
	public void editDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionSearchBB :: editDetails starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		SkuOpr skuOprSent = new SkuOpr();
		skuOprSent.getProductSkuRecord().setId(selectedSkuRecord.getId());
		skuOprSent.getProductSkuRecord().getDefaultImageRecord()
				.setThumbnailImageURL(selectedSkuRecord.getDefaultImageRecord().getThumbnailImageURL());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB_OPR,
				skuOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");

	}

	public void createNewSKU() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionSearchBB :: createNewSKU starts ");

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		SkuOpr skuOprSent = new SkuOpr();

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB_OPR,
				skuOprSent);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EDIT_DETAILS", "SKU");
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionSearchBB :: retrieveData starts ");

		allOptions = new OptionsDVO();

		if (allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.getAllOptionsValues().put("statusList", new SkuDefinitionBF().getStatusCodeList());
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		myLog.debug("In SkuDefinitionSearchBB :: retrieveData ends ");
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

	public void executeEnlargeImage() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Sku Definition Search BB :: executeEnlargeImage starts ");

		ImageDVO imageRecord = new ImageDVO(getSelectedSkuRecord().getDefaultImageRecord().getZoomImageURL());
		myLog.debug("In Sku Definition Search BB :: zoom image url::"
				+ getSelectedSkuRecord().getDefaultImageRecord().getZoomImageURL());

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.IMAGE_DVO,
				imageRecord);
	}

	private void populateData() throws FrameworkException, BusinessException {
		HashMap<Boolean, String> yesNoValuesMap = new HashMap<Boolean, String>();
		ArrayList<Object> yesNoList = new SkuDefinitionBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!skuOpr.getProductSkuList().isEmpty()) {
			for (ProductSkuDVO productSkuRecord : skuOpr.getProductSkuList()) {
				String activeDescription = yesNoValuesMap.get(productSkuRecord.getActive());
				productSkuRecord.setActiveDescription(activeDescription);
				if (productSkuRecord.getDefaultSku() == null) {
					productSkuRecord.setDefaultSku(false);
				}
				productSkuRecord.setDefaultSkuDescription(yesNoValuesMap.get(productSkuRecord.getDefaultSku()));
			}
		}
	}

	public void clearPopUpPage(ActionEvent event) {
		skuOpr = new SkuOpr();
	}

}
