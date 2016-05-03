package com.web.common.jsf.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.web.common.dvo.common.BaseDVO;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

/**
 * @author NIRAJ
 * 
 */
public class BaseDVOConverter implements Converter {

	private static final ITSDLogger myLog = TSDLogger.getLogger(BaseDVOConverter.class.getName());

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {

		myLog.info("getAsObject :: submittedValue : " + submittedValue);

		if (submittedValue == null || submittedValue.trim().equals("")) {
			return null;
		} else {

			// NOTE: this component Id is the id of the autoComplete suggestion
			// box. Put the List against the
			// autoComplete id of xhtml

			String componentId = component.getId();
			myLog.info(" component id : " + componentId);
			if (componentId != null) {
				Object objectList = FacesContext.getCurrentInstance().getViewRoot().getViewMap().get(componentId);

				if (objectList != null) {
					if (objectList instanceof List) {

						@SuppressWarnings("unchecked")
						List<Object> baseDVOList = (List<Object>) objectList;
						myLog.info("getAsObject :: baseDVOList size : " + baseDVOList.size());

						for (Object object : baseDVOList) {
							BaseDVO baseRecord = (BaseDVO) object;
							if (baseRecord.getId() != null && baseRecord.getId().toString().equals(submittedValue)) {
								return baseRecord;

							} else if (baseRecord.getId() == null && baseRecord.getCode() != null
									&& baseRecord.getCode().equals(submittedValue)) {
								return baseRecord;
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		myLog.info("getAsString :: value : " + value);
		BaseDVO baseRecord = (BaseDVO) value;
		Long baseId = baseRecord.getId();
		String baseCode = baseRecord.getCode();

		if (value == null || value.equals("")) {
			return "";

		} else if (baseId == null && baseCode == null) {
			return "";

		} else if (baseId != null) {
			return baseId.toString();

		} else {
			return baseCode;
		}

	}

}
