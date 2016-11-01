package com.web.common.parents;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;

import com.ocpsoft.pretty.PrettyContext;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.systemowner.PageNavigationDVO;
import com.web.common.dvo.util.File;
import com.web.common.dvo.util.OptionsDVO;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.CommonUtil;
import com.web.util.PropertiesReader;

public abstract class ParentBackingBean implements Serializable {

	private static final long serialVersionUID = -3081402747466826920L;

	private String popupDateFormat;

	private String popupDateTimeFormat;

	private int paginationNumber = 10;

	private TimeZone timeZone;

	protected Integer firstRowIndexOfDataTable = 0;

	protected Integer lastRowIndexOfDataTable = 0;

	private ArrayList<String> errorList = new ArrayList<String>();

	private String successMsg;

	public String generalMsg;

	protected OptionsDVO allOptions;

	protected String commonMessages = CommonConstant.MessageLocation.COMMON_MESSAGES;

	private String userLogin;

	private int modalWidth = 1250;

	private int modalHeight = 575;

	protected Boolean disableSaveButton;

	private BaseDVO baseDvoForSelect;

	private String shoppingCartString;

	private String shoppingBagStyleString = "";

	public void addToErrorList(String message) {
		if ((message != null) && !message.trim().equals("")) {
			errorList.add(message);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
		}
	}

	public abstract OptionsDVO getAllOptions();

	public abstract void setAllOptions(OptionsDVO allOptions);

	public void closeModalWindow(CloseEvent event) {
		if (errorList != null) {
			errorList.clear();
		}
		successMsg = "";
	}

	public void closeModalWindow(ActionEvent event) {
		if (errorList != null) {
			errorList.clear();
		}
		successMsg = "";
	}

	protected void handleException(Exception e, String resBundleName) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propsRead = new PropertiesReader();
		Throwable cause = null;
		if (e != null) {
			cause = e.getCause();
		}
		myLog.error("An exception has occurred " + e);
		if (cause != null) {
			myLog.error("Cause of exception is " + cause);
		}
		if (e != null) {
			myLog.error("An exception has occurred -------->" + e.getMessage());
			e.printStackTrace();
		}
		String messageCode = null;
		if (e != null) {
			messageCode = e.getMessage();
		}
		String message = null;
		if (messageCode == null) {
			propsRead.setResourceBundle(CommonConstant.MessageLocation.EXCEPTION_MESSAGES);
			message = propsRead.getValueOfKey(CommonConstant.DEFAULT_ERROR_MESSAGE);

		} else {
			// to be used for displaying multiple error messages, use ;; as
			// delimeter
			if (messageCode.startsWith("Column index out of range")) {

				message = "Database operation unsuccessful. Please contact System Administrator";

			} else if (messageCode.contains(";;")) {
				String[] errorMessageArray = messageCode.split(";;");
				for (String msg : errorMessageArray)
					addToErrorList(msg);

			} else if (resBundleName != null) {
				propsRead.setResourceBundle(resBundleName);
				try {
					message = propsRead.getValueOfKey(messageCode);
				} catch (MissingResourceException e1) {
					propsRead.setResourceBundle(CommonConstant.MessageLocation.EXCEPTION_MESSAGES);
					try {
						message = propsRead.getValueOfKey(messageCode);
						if (message == null) {
							message = messageCode;
						}
					} catch (MissingResourceException e2) {
						if (message == null) {
							message = messageCode;
						}
					}
				}
			} else {
				message = messageCode;
			}
		}
		if (message != null) {
			addToErrorList(message);
		}
	}

	protected ArrayList<String> handleCommonMessages(String messageCode, ArrayList<String> errorList,
			String resBundleName) {
		PropertiesReader propsRead = new PropertiesReader();
		String message = null;

		if (messageCode.length() == 0) {
			propsRead.setResourceBundle(CommonConstant.MessageLocation.COMMON_MESSAGES);
			message = propsRead.getValueOfKey(CommonConstant.DEFAULT_ERROR_MESSAGE);
		} else {
			propsRead.setResourceBundle(resBundleName);
			try {
				message = propsRead.getValueOfKey(messageCode);

			} catch (MissingResourceException e1) {
				propsRead.setResourceBundle(CommonConstant.MessageLocation.COMMON_MESSAGES);
				try {
					message = propsRead.getValueOfKey(messageCode);
					if (message == null) {
						message = messageCode;
					}
				} catch (MissingResourceException e2) {
					if (message == null) {
						message = messageCode;
					}
				}
			}
		}
		addToErrorList(message);
		return errorList;
	}

	public int getPaginationNumber() {
		return paginationNumber;
	}

	public String getPopupDateFormat() {
		if (popupDateFormat == null) {
			popupDateFormat = "dd/MM/yyyy";
		}
		return popupDateFormat;
	}

	public void setPopupDateFormat(String popupDateFormat) {
		this.popupDateFormat = popupDateFormat;
	}

	public TimeZone getTimeZone() {
		if (timeZone == null) {
			timeZone = TimeZone.getDefault();
		}
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	// note : getters for these two variables are individual backing beans. It
	// had to be written this way since
	// the getter deals with an HTMLDatatable and JSF objects cannot be shared
	// across jars.
	public void setFirstRowIndexOfDataTable(Integer firstRowIndexOfDataTable) {
		this.firstRowIndexOfDataTable = firstRowIndexOfDataTable;
	}

	public void setLastRowIndexOfDataTable(Integer lastRowIndexOfDataTable) {
		this.lastRowIndexOfDataTable = lastRowIndexOfDataTable;
	}

	public ArrayList<String> getErrorList() {
		if (errorList == null) {
			errorList = new ArrayList<String>();
		}
		return errorList;
	}

	public void setErrorList(ArrayList<String> errorList) {
		this.errorList = errorList;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		if ((successMsg != null) && !successMsg.trim().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, successMsg, successMsg));
		}
		this.successMsg = successMsg;
	}

	public String getGeneralMsg() {
		return generalMsg;
	}

	public void setGeneralMsg(String generalMsg) {
		this.generalMsg = generalMsg;
	}

	public String getUserLogin(ExternalContext externalContext) throws BusinessException {
		userLogin = (String) externalContext.getSessionMap().get(CommonConstant.LOGGED_USER_KEY);
		if (userLogin == null || userLogin.trim().length() == 0) {
			userLogin = "guest";
		}
		return userLogin;
	}

	public String getUserLoginFromSession(ExternalContext externalContext) throws BusinessException {
		userLogin = (String) externalContext.getSessionMap().get(CommonConstant.LOGGED_USER_KEY);
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String clearPage() {
		return "pretty:";
	}

	public int getModalWidth() {
		return modalWidth;
	}

	public void setModalWidth(int modalWidth) {
		this.modalWidth = modalWidth;
	}

	public int getModalHeight() {
		return modalHeight;
	}

	public void setModalHeight(int modalHeight) {
		this.modalHeight = modalHeight;
	}

	public Boolean getDisableSaveButton() {
		return disableSaveButton;
	}

	public void setDisableSaveButton(Boolean disableSaveButton) {
		this.disableSaveButton = disableSaveButton;
	}

	protected void putObjectInCache(String key, Object value) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("USER LOGIN PARENT::::"
				+ (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.LOGGED_USER_KEY));
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	protected Object getObjectFromCache(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	protected Object removeObjectFromCache(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	protected void removeUserCache(String userLogin) {
	}

	protected boolean validateForValueChange(Object value1, Object value2) {
		boolean validateFlag = false;

		if (value1 != null && value2 != null && !value1.equals(value2))
			validateFlag = true;

		if (value1 != null && value2 == null)
			validateFlag = true;

		if (value1 == null && value2 != null)
			validateFlag = true;

		return validateFlag;
	}

	protected File extractUploadedFileData(FileUploadEvent event) {
		String fileName = event.getFile().getFileName();

		File uploadFile = new File();
		uploadFile.setContentType(event.getFile().getContentType());
		uploadFile.setData(event.getFile().getContents());
		uploadFile.setLength(event.getFile().getContents().length);

		int dotLocation = fileName.lastIndexOf('.');
		if (dotLocation > 0) {
			String extension = fileName.substring(dotLocation + 1);

			if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
				uploadFile.setMime("image/jpeg");
				uploadFile.setExtension("jpg");

			} else if ("png".equals(extension)) {
				uploadFile.setMime("image/png");
				uploadFile.setExtension("png");

			} else {
				uploadFile.setMime("image/unknown");
				uploadFile.setExtension(extension);
			}
		}
		uploadFile.setName(fileName);

		return uploadFile;
	}

	protected boolean uploadImage(File uploadedFile, Integer width, Integer height, String contextPath) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		boolean uploadFlag = false;
		// take incoming file for uploading
		File fileToUpload = uploadedFile;
		URL url = null;
		HttpURLConnection urlConnection = null;

		try {

			// NEW SCALED IMAGE GENERATION CODE - THUMBNAILATOR

			// TODO: uncomment this
			if (width != null && height != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream is = new ByteArrayInputStream(fileToUpload.getData());
				Thumbnails.of(is).alphaInterpolation(AlphaInterpolation.QUALITY).size(width, height)
						.outputFormat(fileToUpload.getExtension()).toOutputStream(baos);
				fileToUpload.setData(baos.toByteArray());
				baos.close();
			}

			// END NEW SCALED IMAGE GENERATION CODE

			// String urlString =
			// "http://www.retail.com/retailwebdav/images/product/";
			String serverUrl = CommonUtil.getServerUrl();
			String urlString = CommonConstant.HttpSchemes.HTTP + serverUrl + CommonConstant.Urls.WEBDAV_CONTEXT_NAME
					+ contextPath + URLEncoder.encode(fileToUpload.getName(), "UTF-8");
			myLog.debug("urlString " + urlString);

			// CHECK IF FILE EXISTS, IF YES DELETE
			url = new URL(urlString);
			myLog.debug(" url 1 : " + url.getPath());
			myLog.debug(" url 2 : " + url.getFile());
			myLog.debug(" url 3 : " + url);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("HEAD");
			myLog.debug("urlConnection set :: response code :: " + urlConnection.getResponseCode());

			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// disconnect earlier connection to get headers
				urlConnection.disconnect();
				// new connection for delete
				urlConnection = (HttpURLConnection) url.openConnection();
				myLog.debug("file already exists :: deleting it :: ");
				urlConnection.setDoOutput(true);
				urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				urlConnection.setRequestMethod("DELETE");
				urlConnection.connect();

				int responseCode = urlConnection.getResponseCode();
				myLog.debug("file already exists :: responseCode :: " + responseCode);

				// disconnect existing connection
				urlConnection.disconnect();

				if (responseCode == 204) {
					myLog.debug(urlString + "successfully deleted existing file from file system :::: ");
				} else {
					myLog.debug(urlString + "could not be successfully delete existing file from file system :::: ");
				}
			}
			// END CHECK IF FILE EXISTS, IF YES DELETE

			// url = new URL(urlString);
			// spawn new connection for file upload
			urlConnection = (HttpURLConnection) url.openConnection();
			myLog.debug("urlConnection set :: ");
			// urlConnection.setInstanceFollowRedirects(true);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setRequestProperty("Host", "localhost");
			// avoid that the web server is waiting for further requests on the
			// same network connection
			urlConnection.setRequestProperty("Connection", "close");
			// Don't use a cached copy.
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", fileToUpload.getContentType());

			myLog.debug("Content-type::" + fileToUpload.getContentType());
			urlConnection.setRequestMethod("PUT");
			urlConnection.setRequestProperty("Content-Length", String.valueOf(fileToUpload.getLength()));
			myLog.debug("urlConnection data length " + fileToUpload.getLength());

			urlConnection.getOutputStream().write(fileToUpload.getData());
			urlConnection.getOutputStream().flush();
			myLog.debug("sent data");

			int responseCode = urlConnection.getResponseCode();
			myLog.debug(" PUT responseCode " + responseCode);

			// 201 is the http responsecode for successful creation of resource
			if (responseCode == 201) {
				uploadFlag = true;
			} else {
				uploadFlag = false;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
			url = null;
		}

		// reset all variables
		fileToUpload = new File();
		return uploadFlag;
	}

	public Date getCurrentDate() {
		return new Date();
	}

	public void openPopup(String popupUrl) throws BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String serverUrl = CommonUtil.getServerUrl();
		String popupReportUrl = CommonConstant.HttpSchemes.HTTP + serverUrl + CommonConstant.Urls.CONTEXT_NAME
				+ popupUrl;
		myLog.debug(" :: Popup URL to be opened :: " + popupReportUrl);

		String reportURL = "window.open('" + popupReportUrl
				+ "', 'newWindow', 'height=400,width=800,toolbar=no,resizable=1,status=1,menubar=no,scrollbars=yes');";
		RequestContext.getCurrentInstance().execute(reportURL);

	}

	public BaseDVO getBaseDvoForSelect() {
		if (baseDvoForSelect == null) {
			baseDvoForSelect = new BaseDVO() {
				private static final long serialVersionUID = 3689106216748121387L;
			};
		}
		return baseDvoForSelect;
	}

	public void setBaseDvoForSelect(BaseDVO baseDvoForSelect) {
		this.baseDvoForSelect = baseDvoForSelect;
	}

	protected ArrayList<?> markAllSelected(ArrayList<?> listObject) {
		int rowcount = listObject.size();

		if (rowcount > 0) {
			for (int i = 0; i < rowcount; i++) {
				Object o = listObject.get(i);
				if (o != null && o instanceof BaseDVO) {
					BaseDVO baseDVO = (BaseDVO) o;
					baseDVO.getOperationalAttributes().setRecordSelected(true);
				}
			}
		}
		return listObject;
	}

	protected ArrayList<?> markAllDeSelected(ArrayList<?> listObject) {
		int rowcount = listObject.size();

		if (rowcount > 0) {
			for (int i = 0; i < rowcount; i++) {
				Object o = listObject.get(i);
				if (o != null && o instanceof BaseDVO) {
					BaseDVO baseDVO = (BaseDVO) o;
					baseDVO.getOperationalAttributes().setRecordSelected(false);
				}
			}
		}
		return listObject;
	}

	public String getBarCodeUrl() throws BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String serverUrl = CommonUtil.getServerUrl();
		String barCodeUrl = CommonConstant.Urls.BARCODE_URL_PARAM + CommonConstant.HttpSchemes.HTTP + serverUrl
				+ CommonConstant.Urls.BARCODE_URL;
		myLog.debug(" :: Bar code URL :: " + barCodeUrl);
		return barCodeUrl;
	}

	// printing file
	protected void printFile(String filePath, String printerName) {
		// Copy C:\Tag_Print\TagsForPrint.txt \\360degrees18\Citizen_CL-S621Z
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(commonMessages);
		myLog.debug(":::FILE PATH:::" + filePath + ":::PRINTER NAME::::" + printerName);

		if (filePath != null && filePath.trim().length() > 0 && printerName != null && printerName.trim().length() > 0) {
			try {
				FileInputStream textStream = new FileInputStream(filePath);
				DataInputStream in = new DataInputStream(textStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (line != null) {
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
				String everything = sb.toString();
				myLog.debug("ZEBRA CODE :::" + everything);

				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

				byte[] by = everything.getBytes();
				DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
				Doc doc = new SimpleDoc(by, flavor, null);

				DocPrintJob job = null;
				if (printerName != null) {
					PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, aset);
					myLog.debug(":: printServices.length ::" + printServices.length);
					if (printServices != null && printServices.length > 0) {
						for (PrintService printService : printServices) {
							String printerNameAvailable = printService.getName();
							myLog.debug(":: printer names ::" + printerNameAvailable);
							if (printerNameAvailable != null && printerNameAvailable.equals(printerName)) {
								// print using user mapped printer
								job = printService.createPrintJob();
								job.print(doc, aset);
								break;
							}
						}
					}
				}

				if (job == null) {
					addToErrorList(propertiesReader.getValueOfKey("no_printer_found"));
				}
			} catch (Exception ex) {
				handleException(ex, commonMessages);
			}
		}
	}

	public String getPopupDateTimeFormat() {
		if (popupDateTimeFormat == null) {
			popupDateTimeFormat = "dd/MM/yyyy HH:mm:ss";
		}
		return popupDateTimeFormat;
	}

	public void setPopupDateTimeFormat(String popupDateTimeFormat) {
		this.popupDateTimeFormat = popupDateTimeFormat;
	}

	protected Float getItemWeightFromSession() {
		Float itemWeight = null;
		String userLogin = null;

		try {
			userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		if (userLogin != null) {
			String weightKey = "ITEM_WEIGHT_KEY" + userLogin;

			System.out.println(" getting item weight :  "
					+ FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(weightKey));

			if (FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(weightKey) != null)
				itemWeight = (Float) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
						.get(weightKey);
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove(weightKey);
		}
		if (itemWeight == null)
			itemWeight = 0.0F;
		return itemWeight;
	}

	protected void moveFileToDestination(java.io.File sourceFile, String destinationDirectoryPath) throws IOException {

		java.io.File dir = new java.io.File(destinationDirectoryPath);
		java.io.File destinationFile = new java.io.File(dir, sourceFile.getName());

		// check if the destination file exists or no
		if (!destinationFile.exists()) {
			// code to move the file from current directory to history
			sourceFile.renameTo(destinationFile);

		} else {
			System.out.println("Uploaded file exists at the destination. so, deleting it.");
			// delete the source file.
			sourceFile.delete();
		}
	}

	public void populateProductSearchWebAttributes() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String productCategories = "";
		String productCategoriesReverse = "";
		if (externalContext.getRequestMap().get("product.categories") != null) {
			productCategories = (String) externalContext.getRequestMap().get("product.categories");
		}

		if (externalContext.getRequestMap().get("product.categories.reverse") != null) {
			productCategoriesReverse = (String) externalContext.getRequestMap().get("product.categories.reverse");
		}

		String productCategoriesUri = "";
		if (externalContext.getRequestMap().get("product.categories.uri") != null) {
			productCategoriesUri = (String) externalContext.getRequestMap().get("product.categories.uri");
		}
		String productPageCode = "";
		if (externalContext.getRequestMap().get("catalog.page.code") != null) {
			productPageCode = (String) externalContext.getRequestMap().get("catalog.page.code");
		}

		myLog.debug("===productCategories===" + productCategories);
		myLog.debug("===productCategoriesUri===" + productCategoriesUri);
		myLog.debug("===productPageCode===" + productPageCode);

		populateWebResourceAttributes(productPageCode);

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_DISPLAY_NAME",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME")).replaceAll(
						"~product.categories~", productCategories));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_TITLE",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_TITLE")).replaceAll(
						"~product.categories.reverse~", productCategoriesReverse));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_DESCRIPTION",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_DESCRIPTION")).replaceAll(
						"~product.categories~", productCategories));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_KEYWORDS",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_KEYWORDS")).replaceAll(
						"~product.categories~", productCategories));

		String url = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath() + PrettyContext.getCurrentInstance().getRequestURL();

		String contextPath = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath();

		myLog.debug("URL in browsebbbbb==" + url);

		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL,
				contextPath + CommonConstant.PopulateFacebookData.HEADER_LOGO_IMAGE_URL);
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_TITLE, productCategories);
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION, productCategories);
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL, url);
		myLog.debug("diamondere image url"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL));
		myLog.debug("product title"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_TITLE));
		myLog.debug("discription"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION));
		myLog.debug("page url=="
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL));

	}

	public void populateWebResourceAttributes(String pageName) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		myLog.debug("populateWebResourceAttributes : pageName " + pageName);

		String contextPath = ((ServletContext) externalContext.getContext()).getContextPath();

		if (pageName != null) {
			if (((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
					.getAttribute("PAGE_NAVIGATION_MAP") != null
					&& ((HashMap<String, PageNavigationDVO>) ((ServletContext) FacesContext.getCurrentInstance()
							.getExternalContext().getContext()).getAttribute("PAGE_NAVIGATION_MAP"))
							.containsKey(pageName)) {
				PageNavigationDVO pageNavigation = ((HashMap<String, PageNavigationDVO>) ((ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext()).getAttribute("PAGE_NAVIGATION_MAP"))
						.get(pageName);

				externalContext.getRequestMap().put("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME",
						pageNavigation.getPageDisplayName());
				externalContext.getRequestMap().put("WEB_ATTRIBUTES_PAGE_TITLE",
						pageNavigation.getWebResourceAttributes().getTitle());
				externalContext.getRequestMap().put("WEB_ATTRIBUTES_DESCRIPTION",
						pageNavigation.getWebResourceAttributes().getResourceDescription());
				externalContext.getRequestMap().put("WEB_ATTRIBUTES_KEYWORDS",
						pageNavigation.getWebResourceAttributes().getResourceKeywords());
				externalContext.getRequestMap().put("WEB_ATTRIBUTES_ROBOTS",
						pageNavigation.getWebResourceAttributes().getWebBotDirectives());
				externalContext.getRequestMap().put("WEB_ATTRIBUTES_CANONICAL_HREF",
						contextPath + pageNavigation.getWebResourceAttributes().getCanonicalURI());

				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();

				myLog.debug("Parent backing bean" + contextPath
						+ pageNavigation.getWebResourceAttributes().getCanonicalURI());
				String context = "http://"
						+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
								.getServerName();

				myLog.debug("inside parent bean if null");
				externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL,
						context + CommonConstant.PopulateFacebookData.HEADER_LOGO_IMAGE_URL);

				externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_TITLE,
						pageNavigation.getWebResourceAttributes().getTitle());
				externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION,
						pageNavigation.getWebResourceAttributes().getResourceDescription());

				externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL, context);
				myLog.debug("product url inside parent bean=="
						+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL));

			}

		}
	}

	public String getShoppingCartString() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("getShoppingBagStyleString1111 " + shoppingCartString);
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(CommonConstant.SHOPPING_CART_OPR);
		if (shoppingCartOpr == null) {
			shoppingCartString = "Shopping Bag ( 0 items )";
			myLog.debug("getShoppingBagStyleString2222 " + shoppingCartString);
		} else {
			shoppingCartString = "Shopping Bag ( " + shoppingCartOpr.getShoppingCartProductList().size() + " items )";
		}
		myLog.debug("getShoppingBagStyleString3333333 " + shoppingCartString);
		return shoppingCartString;
	}

	public void setShoppingCartString(String shoppingCartString) {
		this.shoppingCartString = shoppingCartString;
	}

	public String getShoppingBagStyleString() {
		if (shoppingBagStyleString != null && shoppingBagStyleString.trim().length() == 0) {
			shoppingBagStyleString = "empty";
			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.containsKey(CommonConstant.SHOPPING_CART_OPR)
					&& ((ShoppingCartOpr) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get(CommonConstant.SHOPPING_CART_OPR))).getShoppingCartProductList().size() > 0) {
				shoppingBagStyleString = "shoppingcartfull";
			}
		}
		return shoppingBagStyleString;
	}

	public void setShoppingBagStyleString(String shoppingBagStyleString) {
		this.shoppingBagStyleString = shoppingBagStyleString;
	}

}
