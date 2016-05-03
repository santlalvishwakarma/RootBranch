package com.web.common.jsf;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

public class PostbackPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 2189184948072795989L;

	public static final String POSTBACK_ATTRIBUTE_NAME = PostbackPhaseListener.class.getName();

	private ITSDLogger log = TSDLoggerFactory.getLoggerInstance(this.getClass().getName());

	public void afterPhase(PhaseEvent event) {
	}

	public void beforePhase(PhaseEvent event) {
		log.info("Before phase in post back listener : " + event.getPhaseId());
		FacesContext facesContext = event.getFacesContext();
		Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
		requestMap.put(POSTBACK_ATTRIBUTE_NAME, Boolean.TRUE);
	}

	public PhaseId getPhaseId() {
		return PhaseId.APPLY_REQUEST_VALUES;
	}

	public static boolean isPostback() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			ExternalContext externalContext = facesContext.getExternalContext();
			if (externalContext != null) {
				return Boolean.TRUE.equals(externalContext.getRequestMap().get(POSTBACK_ATTRIBUTE_NAME));
			}
		}

		return false;
	}

}
