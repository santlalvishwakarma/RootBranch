package com.web.common.constants;


/*
 * Created CommonConstant as interface.
 * In this, nest all related constants into an sub-interface
 * 
 * */
public interface CommonConstant {

	public static final String SYSTEM_DATASOURCE = "WebDS";

	public static final String CONTEXT_LOOKUP_ROOT = "java:comp/env/";

	public static final String CONTEXT_PATH = "/p";

	public static final String DEFAULT_ERROR_MESSAGE = "default_error_message";

	public static final String LOGGED_USER_KEY = "LOGGED_USER_KEY";

	public static final String GLOBAL_MENU_NAVIGATION_CACHE = "PORTAL_BASE_GLOBAL_MENU_NAVIGATION_CACHE";

	public static final String GLOBAL_MENU_NAVIGATION_ELEMENT = "PORTAL_BASE_GLOBAL_MENU_NAVIGATION_ELEMENT";

	// in ehcache settings, 0 represents unlimited for cache expiry period
	public static final int FOREVER = 0;

	public static final String GLOBAL_ROLE_CACHE = "PORTAL_BASE_GLOBAL_ROLE_CACHE";

	public static final String GLOBAL_ROLE_ELEMENT = "PORTAL_BASE_GLOBAL_ROLE_ELEMENT";

	public static final String LOGGED_USER_NAME = "LOGGED_USER_NAME";

	public static final String LOGGED_USER_DATA = "LOGGED_USER_DATA";

	public static final String LOGGED_USER_ROLES = "LOGGED_USER_ROLES";

	public static final String DOAMIN_KEY = "DOAMIN_KEY";

	public static final String LOGIN_TYPE = "LOGIN_TYPE";

	public static final String SYSTEMOWNER_LOGIN_TYPE = "SYSTEMOWNER_LOGIN_TYPE";

	public static final String RETAIL_LOGIN_TYPE = "RETAIL_LOGIN_TYPE";

	public static final String WHOLESALE_LOGIN_TYPE = "WHOLESALE_LOGIN_TYPE";

	public static final String PAYPAL_TOKEN = "PAYPAL_TOKEN";

	public static final String PAYPAL_ACK = "PAYPAL_ACK";

	public static final String ACTIVE_TAB = "ACTIVE_TAB";

	public static final String ACTIVE_TAB_OPR = "ACTIVE_TAB_OPR";

	public static final String RE_INITIALIZE_OPR = "RE_INITIALIZE_OPR";

	public static final String WEBSITE_MASTER_OPR = "WEBSITE_MASTER_OPR";

	public static final String RE_INITIALIZE_MODAL_OPR = "RE_INITIALIZE_MODAL_OPR";

	public static final String GLOBAL_WEBSITE_CACHE = "PORTAL_BASE_GLOBAL_WEBSITE_CACHE";

	public static final String GLOBAL_WEBSITE_ELEMENT = "PORTAL_BASE_GLOBAL_WEBSITE_ELEMENT";

	public static final int DESCRIPTION_DISPLAY_LENGTH = 200;

	public static final String EXCLUSIVITY_FILTER_WEBSITE_ID = "EXCLUSIVITY_FILTER_WEBSITE_ID";

	public static final String CURRENCY_FLAG = "CURRENCY_FLAG";

	public static final String CURRENCY_CONVERSION_RATE = "CURRENCY_CONVERSION_RATE";

	public static final String CONVERTED_CURRENCY_SYMBOL = "CONVERTED_CURRENCY_SYMBOL";

	public static final String BROWSE_PRODUCTS_PAGE = "BROWSE_PRODUCTS_PAGE";

	public static final String BROWSE_PRODUCTS_URL = "/products/";

	public static final String SHOPPING_CART_OPR = "SHOPPING_CART_OPR";

	public static final String INVOICE_OPR = "INVOICE_OPR";

	public static final String OFFLINE_INVOICE_OPR = "OFFLINE_INVOICE_OPR";

	public static final String IMAGE_DVO = "imageDVOToDisplay";

	public static final String READ_MORE_PRODUCT_DVO = "READ_MORE_PRODUCT_DVO";

	public static final String READ_MORE_PAGE = "/retail/product";

	public static final String REFRESH_SAVED_PRODUCTS = "REFRESH_SAVED_PRODUCTS";

	public static final String SIZE_LIST = "sizeList";

	public static final String FROM_SHOPPING_CART_PAGE = "FROM_SHOPPING_CART_PAGE";

	public static final String GUEST_USER = "guest";

	public static final String WEBSITE_URL = "WEBSITE_URL";

	public static final String PAYMENT_OPR = "PAYMENT_OPR";

	public static final String FROM_WISHLIST = "FROM_WISHLIST";

	public static final String WISHLIST_SHOPPING_CART_FLAG = "WISHLIST_SHOPPING_CART_FLAG";

	public static final String RERENDER_COMPONENT = "RERENDER_COMPONENT";

	public static final String ROLE_CODE_SEPARATOR = ",";

	public static final String SYSTEM_OWNER = "SYSTEM_OWNER";

	public static final String ADMINISTRATOR = "ADMINISTRATOR";

	public static final String IMAGE_DVO_LIST = "IMAGE_DVO_LIST";

	public interface MessageLocation {

		public static final String COMMON_MESSAGES = "com/web/common/parents/messages";

		public static final String EXCEPTION_MESSAGES = "com/web/common/parents/exceptionMsgs";
	}

	public interface MimeType {

		public static final String TEXT_PLAIN = "text/plain";

		public static final String TEXT_HTML = "text/html";

		public static final String APP_OCTET_STREAM = "application/octet-stream";
	}

	public interface YesNoValues {

		public static final String YES = "Yes"; // true

		public static final String NO = "No"; // false
	}

	public interface ParameterCode {
		public static final String MAX_LEVEL_LIMIT = "MAX_LEVEL_LIMIT";
		public static final String COUNSELING_TYPE = "COUNSELING_TYPE";
		public static final String GENDER_TYPE = "GENDER_TYPE";
		public static final String PRODUCT_PROPERTIES_CONDITIONS = "PRODUCT_PROPERTIES_CONDITIONS";
		public static final String PRODUCT_STATUS = "PRODUCT_STATUS";
	}

	public interface ParamDatatype {
		public static final String STRING = "S";
		public static final String DATE = "D";
		public static final String NUMBER = "N";
	}

	public interface ParameterSequenceNumber {
		public static final Integer ONE = 1;
		public static final Integer TWO = 2;
		public static final Integer THREE = 3;
		public static final Integer FOUR = 4;
		public static final Integer FIVE = 5;
		public static final Integer SIX = 6;
		public static final Integer SEVEN = 7;
	}

	public interface ServerName {
		public static final String SERVER_NAME = "www.portalbase.com";
	}

	public interface HttpSchemes {
		public static final String HTTP = "http://";
	}

	public interface Urls {
		public static final String CONTEXT_NAME = "/p";
		public static final String WEBDAV_CONTEXT_NAME = "/webdav/images/";
		public static final String BARCODE_URL_PARAM = "&barcode_url=";
		public static final String BARCODE_URL = "/barcode4j/genbc";
		public static final String LOGIN = "/login/";
		public static final String HOME = "/home/";
	}

	public interface ImageAttributes {
		public static final int THUMB_IMAGE_HEIGHT = 60;
		public static final int THUMB_IMAGE_WIDTH = 60;
		public static final int IMAGE_HEIGHT = 250;
		public static final int IMAGE_WIDTH = 250;
		public static final int ZOOM_IMAGE_HEIGHT = 350;
		public static final int ZOOM_IMAGE_WIDTH = 350;
		public static final int REPORT_IMAGE_HEIGHT = 150;
		public static final int REPORT_IMAGE_WIDTH = 150;
	}

	public interface Currency {
		public static final String CURRENCY_FLAG = "CURRENCY_FLAG";
		public static final String CURRENCY_CONVERSION_RATE = "CURRENCY_CONVERSION_RATE";
		public static final String CONVERTED_CURRENCY_SYMBOL = "CONVERTED_CURRENCY_SYMBOL";
	}

	public static final String RETAIL_DETAILS_MENU_ELEMENT = "PORTAL_BASE_RETAIL_DETAILS_MENU_ELEMENT";

	public interface StatusCodes {
		public static final String NEW = "NEW";
		public static final String PENDING_APPROVAL = "PENDING_APPROVAL";
		public static final String APPROVED = "APPROVED";
		public static final String INACTIVE = "INACTIVE";
		public static final String REJECT = "REJECT";
		public static final String REWORK = "REWORK";
		public static final String CLOSED = "CLOSED";
		public static final String PARTIALLY_FULFILLED = "PARTIALLY_FULFILLED";
		public static final String PENDING = "PENDING";
		public static final String CONFIRMED = "CONFIRMED";
		public static final String INTRANSIT = "INTRANSIT";
		public static final String RECEIVED = "RECEIVED";
		public static final String CANCELLED = "CANCELLED";
		public static final String AVAILABLE = "AVAILABLE";
		public static final String GENERATED = "GENERATED";
		public static final String FULLY_SETTLED = "FULLY_SETTLED";
		public static final String FULFILLED = "FULFILLED";
		public static final String NOT_GENERATED = "NOT_GENERATED";
	}

	public interface PopulateFacebookData {
		public static final String PRODUCT_IMAGE_URL = "PRODUCT_IMAGE_URL";
		public static final String HEADER_LOGO_IMAGE_URL = "/p/staticcontent/retail/images/scs_banner.jpg";
		public static final String PRODUCT_TITLE = "PRODUCT_TITLE";
		public static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
		public static final String PRODUCT_READMORE_URL = "PRODUCT_READMORE_URL";
	}

	public interface UserRoles {
		public static final String RETAIL_USER = "RETAIL_USER";
		public static final String WHOLESALE_USER = "WHOLESALE_USER";
		public static final String OPERATIONAL_USER = "OPERATIONAL_USER";
		public static final String SYSTEM_OWNER = "SYSTEM_OWNER";
		public static final String ADMINISTRATOR = "ADMINISTRATOR";
		public static final String COMPANY_ADMINISTRATOR = "COMPANY_ADMINISTRATOR";
		public static final String REPORTS_USER = "REPORTS_USER";
	}

}
