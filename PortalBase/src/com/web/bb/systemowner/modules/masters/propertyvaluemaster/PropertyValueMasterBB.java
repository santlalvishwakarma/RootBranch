package com.web.bb.systemowner.modules.masters.propertyvaluemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.masters.propertyvaluemaster.PropertyValueMasterBF;
import com.web.bf.systemowner.modules.masters.sizemaster.SizeMasterBF;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.PropertyValueMappingOpr;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAddRow(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public OptionsDVO getAllOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
		// TODO Auto-generated method stub

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

}
