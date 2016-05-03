package com.web.foundation.locator;

import com.web.foundation.exception.LocatorException;

public final class LocatorFactory {

	public static ILocator getLocator(ResourceRequest resourceReq) throws LocatorException {
		int iLocatorType = resourceReq.getResourceType();

		switch (iLocatorType) {

		case ILocatorConstants.LOCATOR_DATASOURCE:
			return new DataSourceLocator(resourceReq);
		case ILocatorConstants.LOCATOR_EJBHOME:
			return new EJBHomeLocator(resourceReq);
		case ILocatorConstants.LOCATOR_LDAPDIR:
			// return new LDAPLocator(resourceReq);
		default:
			throw new LocatorException("locator_excep_msg");
		}

	}

	private LocatorFactory() {
	}

}
