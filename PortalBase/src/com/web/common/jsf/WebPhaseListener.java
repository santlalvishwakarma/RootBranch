package com.web.common.jsf;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

public class WebPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -3409805086068596163L;

	private ITSDLogger log = TSDLoggerFactory.getLoggerInstance(this.getClass().getName());

	@Override
	public void afterPhase(PhaseEvent event) {
		log.info("After phase : " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		log.info("Before phase : " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
