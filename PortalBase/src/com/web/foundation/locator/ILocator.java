package com.web.foundation.locator;

import com.web.foundation.exception.LocatorException;

public interface ILocator {

	public Object locate() throws LocatorException;
}
