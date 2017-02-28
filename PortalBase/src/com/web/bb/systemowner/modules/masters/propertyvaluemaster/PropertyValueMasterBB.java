package com.web.bb.systemowner.modules.masters.propertyvaluemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.web.bf.systemowner.modules.masters.propertyvaluemaster.PropertyValueMasterBF;
import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.PropertyValueMappingOpr;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.dvo.systemowner.UnitDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class PropertyValueMasterBB extends BackingBean {

	private static final long serialVersionUID = -1325595320188580975L;
	private String propertiesLocation = "com/web/bb/systemowner/modules/masters/propertyvaluemaster/propertyvaluemaster";
	private PropertyValueMappingOpr propertyValueMappingOpr;
	private transient BaseDVOConverter baseDVOConverter;

	public PropertyValueMappingOpr getPropertyValueMappingOpr() {
		if (propertyValueMappingOpr == null) {
			propertyValueMappingOpr = new PropertyValueMappingOpr();
		}
		return propertyValueMappingOpr;
	}

	public void setPropertyValueMappingOpr(PropertyValueMappingOpr propertyValueMappingOpr) {
		this.propertyValueMappingOpr = propertyValueMappingOpr;
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

	@Override
	public void executeSearch(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		myLog.debug("In PropertyValueMasterBB :: executeSearch starts ");

		if (validateSearch()) {

			try {
				propertyValueMappingOpr.setPropertyValueMappingList(null);

				PropertyValueMappingOpr propertyValueMappingOprRet = new PropertyValueMasterBF()
						.executeSearch(propertyValueMappingOpr);
				propertyValueMappingOpr.setPropertyValueMappingList(propertyValueMappingOprRet
						.getPropertyValueMappingList());
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
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		SizeDVO sizeRecord = propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord();
		if (!(validator.validateLongObjectNull(sizeRecord.getId()))) {
			addToErrorList(propertiesReader.getValueOfKey("all_fields_null"));
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
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SizeMasterAddEditBB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().setUserLogin(userLogin);

				propertyValueMappingOpr = new PropertyValueMasterBF().executeSave(propertyValueMappingOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				executeSearch(null);
				RequestContext.getCurrentInstance().execute("updateSearchList();");
				setSuccessMsg(propertiesReader.getValueOfKey("property_value_mapping_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		PropertyValueMappingDVO propertyValueMappingRecord = propertyValueMappingOpr
				.getSelectedPropertyValueMappingRecord();

		Long sizeId = propertyValueMappingRecord.getSizeRecord().getId();
		String propertyValue = propertyValueMappingRecord.getPropertyValue();
		Long unitId = propertyValueMappingRecord.getUnitRecord().getId();

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (!validator.validateLongObjectNull(sizeId)) {
			addToErrorList(propertiesReader.getValueOfKey("size_id_null"));
		}

		if (!validator.validateNull(propertyValue)) {
			addToErrorList(propertiesReader.getValueOfKey("property_value_null"));
		}

		if (!validator.validateLongObjectNull(unitId)) {
			addToErrorList(propertiesReader.getValueOfKey("unit_id_null"));
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
		List<Object> unitList = new ArrayList<Object>();
		unitList.add(propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getUnitRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("unitSuggestionBox", unitList);

		List<Object> sizeList = new ArrayList<Object>();
		sizeList.add(propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getSizeRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertySuggestionBox", sizeList);
	}

	@Override
	public void retrieveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAddRow(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (validateSearch()) {
			propertyValueMappingOpr.setSelectedPropertyValueMappingRecord(new PropertyValueMappingDVO());
			myLog.debug(" size id::" + propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord().getId());
			propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getSizeRecord()
					.setId(propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord().getId());
			propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getSizeRecord()
					.setCode(propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord().getCode());
			propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getSizeRecord()
					.setName(propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord().getName());

			List<Object> sizeList = new ArrayList<Object>();
			sizeList.add(propertyValueMappingOpr.getSelectedPropertyValueMappingRecord().getSizeRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertySuggestionBox", sizeList);

			RequestContext.getCurrentInstance().execute("PF('addEditPropertyValueMappingDialog').show();");

		}

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

	public List<Object> getSuggestedSizeRecordForCode(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				SizeDVO sizeDVO = new SizeDVO();
				sizeDVO.setCode(query);
				sizeDVO.setActive(true);
				List<Object> list = new PropertyValueMasterBF().getSuggestedSizeRecord(sizeDVO);
				myLog.debug(" getSuggestedSizeRecord :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertyCodeSuggestionBox", list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertyNameSuggestionBox", list);
				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedSizeRecordForName(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				SizeDVO sizeDVO = new SizeDVO();
				sizeDVO.setCode(query);
				sizeDVO.setActive(true);
				List<Object> list = new PropertyValueMasterBF().getSuggestedSizeRecord(sizeDVO);
				myLog.debug(" getSuggestedSizeRecord :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertyCodeSuggestionBox", list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("propertyNameSuggestionBox", list);
				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	private void populateData() throws FrameworkException, BusinessException {
		HashMap<Boolean, String> yesNoValuesMap = new HashMap<Boolean, String>();
		ArrayList<Object> yesNoList = new SizeMasterBF().getAllOptionsValuesForSearch().get("yesNoList");

		if (yesNoList != null) {
			for (Object propertyValueType : yesNoList) {
				Parameter parameter = (Parameter) propertyValueType;
				yesNoValuesMap.put(parameter.getParameterBooleanValue(), parameter.getParameterCode());
			}
		}
		if (!propertyValueMappingOpr.getPropertyValueMappingList().isEmpty()) {
			for (PropertyValueMappingDVO propertyValueMappingRecord : propertyValueMappingOpr
					.getPropertyValueMappingList()) {
				String activeDescription = yesNoValuesMap.get(propertyValueMappingRecord.getActive());
				propertyValueMappingRecord.setActiveDescription(activeDescription);
			}
		}
	}

	public List<Object> getSuggestedUnitRecord(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				UnitDVO unitDVO = new UnitDVO();
				unitDVO.setCode(query);
				unitDVO.setActive(true);
				List<Object> list = new PropertyValueMasterBF().getSuggestedUnitRecord(unitDVO);
				myLog.debug(" getSuggestedUnitRecord :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("unitSuggestionBox", list);
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
