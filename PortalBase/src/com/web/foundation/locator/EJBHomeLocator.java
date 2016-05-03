package com.web.foundation.locator;

import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.web.foundation.exception.LocatorException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class EJBHomeLocator implements ILocator {
	private Object ejbHomeRef;// Newly added..

	/**
	 * Reads the properties files.
	 */
	private ResourceBundle propsRead;

	public Object locate() {
		/*
		 * return null; commented..
		 */
		return ejbHomeRef;
	}

	// Add the required parameters here
	// these params can be read from the config/properties files etc.
	public EJBHomeLocator(ResourceRequest resourceReq) throws LocatorException {
		/*
		 * throws LocatorException is added to the above constructor. below try
		 * catch block is newly added.
		 */
		Hashtable<String, String> jndiprops = new Hashtable<String, String>();
		propsRead = ResourceBundle.getBundle("resources/jndi", Locale.getDefault());
		jndiprops.put(Context.INITIAL_CONTEXT_FACTORY, propsRead.getString("initial_context_factory"));
		String namespace = (String) resourceReq.getHmProperties().get("JNDI_NAMESPACE");
		String provider_url = null;
		if (namespace != null) {
			provider_url = propsRead.getString(namespace + "_" + "provider_url");
		} else {
			provider_url = propsRead.getString("provider_url");
		}

		jndiprops.put(Context.PROVIDER_URL, provider_url);
		Object objref = null;
		InitialContext initialContext;
		try {
			initialContext = new InitialContext(jndiprops);
			objref = initialContext.lookup((String) resourceReq.getHmProperties().get("JNDI_NAME"));
		} catch (NamingException namingExcep) {
			throw new LocatorException("locator_excep_msg", namingExcep);
		}
		myLog.debug("object ref" + objref);
		ejbHomeRef = PortableRemoteObject.narrow(objref, (Class<?>) resourceReq.getObjRequest());
		myLog.debug("AuthEJBHOme refff" + ejbHomeRef);

	}

	private transient ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
}
