package com.web.foundation.locator;

import com.web.foundation.exception.LocatorException;
import com.web.foundation.logger.ITSDConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

/**
 * 
 * This is a invoker in the command pattern. which will invoke the given locator
 * command.
 */
public final class ResourceFacade {

	private ResourceFacade() {
	}

	/**
	 * Gives the resource object.
	 * 
	 * @param resourceReq
	 *            -request resource
	 * @return - resource object
	 * @throws LocatorException
	 */
	public static Object getResource(ResourceRequest resourceReq) throws LocatorException {

		// from the resource request prepare the command
		// invoker.invoke(ILocator);
		tsdLogger.debug("GETRESOUCRE=============RT" + resourceReq.getResourceType());

		ILocator objLocator = LocatorFactory.getLocator(resourceReq);
		tsdLogger.debug("GETRESOUCRE=============" + objLocator);

		return objLocator.locate();

	}

	private static ITSDLogger tsdLogger = TSDLoggerFactory.getLoggerInstance(ITSDConstant.DAO_LOG);

}
