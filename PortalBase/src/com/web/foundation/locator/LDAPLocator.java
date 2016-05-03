package com.web.foundation.locator;

public class LDAPLocator implements ILocator {
	// Hashtable ldapEnv = null;

	// DirContext dirContext = null;

	// public LDAPLocator(ResourceRequest resourceReq) throws LocatorException {
	// PropertiesReader propsReader = new PropertiesReader();
	// ldapEnv = new Hashtable();
	// propsReader.setResourceBundle("resources/externalDependencies",
	// Locale.getDefault());
	// ConfigurationReader configurationReader = new ConfigurationReader();
	// ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,
	// propsReader.getValueOfKey("initial_context_factory"));
	//
	// ldapEnv.put(Context.PROVIDER_URL,
	// configurationReader.getOptimusPropertyFile("provider_url"));
	//
	// ldapEnv.put(Context.SECURITY_AUTHENTICATION,
	// propsReader.getValueOfKey("security_authentication"));
	//
	// ldapEnv.put(Context.SECURITY_PRINCIPAL,
	// propsReader.getValueOfKey("security_principal"));
	//
	// ldapEnv.put(Context.SECURITY_CREDENTIALS,
	// propsReader.getValueOfKey("security_credentials"));
	// try {
	// dirContext = new InitialDirContext(ldapEnv);
	// } catch (NamingException namingExcep) {
	// throw new LocatorException("locator_excep_msg", namingExcep);
	// }
	// }

	public Object locate() {
		return null; // dirContext;
	}
}
