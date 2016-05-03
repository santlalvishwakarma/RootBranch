package com.tsd.retail.backingbeans.modules.procurement.purchaseorder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.primefaces.context.RequestContext;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;
import com.web.util.deepcopy.DeepCopy;

/** @author NIRAJ */
public class PurchaseOrderDefinitionAddEditBB extends BackingBean {

	private static final long serialVersionUID = -1562419558871250692L;
	private String propertiesLocation = "com/tsd/retail/backingbeans/modules/procurement/purchaseorder/purchaseorderdefinition";
	private PurchaseOrderOpr purchaseOrderOpr;
	private PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecord;
	private PurchaseOrderRMDetailsDVO purchaseOrderRMDetailsRecord;
	private OptionsDVO allOptions;
	private Long purchaseOrderId;
	private String poActions;
	private boolean renderForAmmendment;
	private ArrayList<Object> vendorList;
	private transient BaseDVOConverter baseDVOConverter;
	private PurchaseOrderItemBomDetailsDVO itemBOMHeaderRecord;
	private boolean disableForIssueTags;

	public PurchaseOrderItemBomDetailsDVO getItemBOMHeaderRecord() {
		if (itemBOMHeaderRecord == null) {
			itemBOMHeaderRecord = new PurchaseOrderItemBomDetailsDVO();
		}
		return itemBOMHeaderRecord;
	}

	public void setItemBOMHeaderRecord(PurchaseOrderItemBomDetailsDVO itemBOMHeaderRecord) {
		this.itemBOMHeaderRecord = itemBOMHeaderRecord;
	}

	public PurchaseOrderOpr getPurchaseOrderOpr() {
		if (purchaseOrderOpr == null) {
			purchaseOrderOpr = new PurchaseOrderOpr();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {

			purchaseOrderOpr = (PurchaseOrderOpr) FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);

			// retrive initial data
			retrieveData();

			// String successMessage = (String)
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			// .get("SUCCESS_MESSAGE");
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("SUCCESS_MESSAGE");
			// setSuccessMsg(successMessage);
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("ADD_PRODUCT_DETAILS") != null) {

			purchaseOrderProductDetailsRecord = (PurchaseOrderProductDetailsDVO) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get("ADD_PRODUCT_DETAILS");
			Long purchaseOrderProductDetailsId = purchaseOrderProductDetailsRecord.getId();
			if (purchaseOrderProductDetailsId != null) {

				boolean isRecordPresent = false;
				int size = purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList().size();
				for (int i = 0; i < size; i++) {
					PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsDVO = purchaseOrderOpr
							.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList().get(i);
					if (purchaseOrderProductDetailsDVO.getId() != null
							&& purchaseOrderProductDetailsId.equals(purchaseOrderProductDetailsDVO.getId())) {
						purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList()
								.set(i, purchaseOrderProductDetailsRecord);
						isRecordPresent = true;
						break;
					}
				}
				if (!isRecordPresent) {
					purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList()
							.add(purchaseOrderProductDetailsRecord);
				}
			}
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("ADD_PRODUCT_DETAILS");
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("ADD_RM_DETAILS") != null) {

			purchaseOrderRMDetailsRecord = (PurchaseOrderRMDetailsDVO) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get("ADD_RM_DETAILS");

			Long purchaseOrderRMDetailsId = purchaseOrderRMDetailsRecord.getId();
			if (purchaseOrderRMDetailsId != null) {

				boolean isRecordPresent = false;

				int size = purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderRMDetailsList().size();
				for (int i = 0; i < size; i++) {
					PurchaseOrderRMDetailsDVO purchaseOrderRMDetailsDVO = purchaseOrderOpr.getPurchaseOrderRecord()
							.getPurchaseOrderRMDetailsList().get(i);

					if (purchaseOrderRMDetailsDVO.getId() != null
							&& purchaseOrderRMDetailsId.equals(purchaseOrderRMDetailsDVO.getId())) {
						purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderRMDetailsList()
								.set(i, purchaseOrderRMDetailsRecord);
						isRecordPresent = true;
						break;
					}
				}
				if (!isRecordPresent) {
					purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderRMDetailsList()
							.add(purchaseOrderRMDetailsRecord);
				}
			}
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("ADD_RM_DETAILS");
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("PO_HEADER_DETAILS") != null) {
			PurchaseOrderHeaderDVO purchaseOrderRecord = (PurchaseOrderHeaderDVO) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get("PO_HEADER_DETAILS");

			purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(purchaseOrderRecord.getAuditAttributes());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("PO_HEADER_DETAILS");
		}
		if (purchaseOrderOpr == null) {
			purchaseOrderOpr = new PurchaseOrderOpr();
		}

		return purchaseOrderOpr;
	}

	public void setPurchaseOrderOpr(PurchaseOrderOpr purchaseOrderOpr) {
		this.purchaseOrderOpr = purchaseOrderOpr;
	}

	public PurchaseOrderProductDetailsDVO getPurchaseOrderProductDetailsRecord() {
		if (purchaseOrderProductDetailsRecord == null) {
			purchaseOrderProductDetailsRecord = new PurchaseOrderProductDetailsDVO();
		}
		return purchaseOrderProductDetailsRecord;
	}

	public void setPurchaseOrderProductDetailsRecord(PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecord) {
		// this is done to avoid passing the row content by reference. The
		// suggestion box is overriding the contents
		this.purchaseOrderProductDetailsRecord = (PurchaseOrderProductDetailsDVO) DeepCopy
				.copy(purchaseOrderProductDetailsRecord);
	}

	public PurchaseOrderRMDetailsDVO getPurchaseOrderRMDetailsRecord() {
		if (purchaseOrderRMDetailsRecord == null) {
			purchaseOrderRMDetailsRecord = new PurchaseOrderRMDetailsDVO();
		}
		return purchaseOrderRMDetailsRecord;
	}

	public void setPurchaseOrderRMDetailsRecord(PurchaseOrderRMDetailsDVO purchaseOrderRMDetailsRecord) {
		this.purchaseOrderRMDetailsRecord = purchaseOrderRMDetailsRecord;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPoActions() {
		return poActions;
	}

	public void setPoActions(String poActions) {
		this.poActions = poActions;
	}

	public boolean getRenderForAmmendment() {
		return renderForAmmendment;
	}

	public void setRenderForAmmendment(boolean renderForAmmendment) {
		this.renderForAmmendment = renderForAmmendment;
	}

	public ArrayList<Object> getVendorList() {
		if (vendorList == null) {
			vendorList = new ArrayList<Object>();
		}
		return vendorList;
	}

	public void setVendorList(ArrayList<Object> vendorList) {
		this.vendorList = vendorList;
	}

	public BaseDVOConverter getBaseDVOConverter() {
		if (baseDVOConverter == null) {
			baseDVOConverter = new BaseDVOConverter();
		}
		return baseDVOConverter;
	}

	public void setBaseDVOConverter(BaseDVOConverter baseDVOConverter) {
		this.baseDVOConverter = baseDVOConverter;
	}

	public boolean isDisableForIssueTags() {
		return disableForIssueTags;
	}

	public void setDisableForIssueTags(boolean disableForIssueTags) {
		this.disableForIssueTags = disableForIssueTags;
	}

	@Override
	public void executeSearch(ActionEvent event) {

	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	@Override
	public void clearPage(ActionEvent event) {
	}

	@Override
	public void resetPage(ActionEvent event) {
	}

	@Override
	public void executeSave(ActionEvent event) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: executeSave starts ");

		if (validateSave()) {

			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderOpr = new PurchaseOrderBD().executeSavePurchaseOrderHeader(purchaseOrderOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("purchase_order_save_success"));
				poLocationChanged(null);
				populateData();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		PurchaseOrderHeaderDVO purchaseOrderRecord = purchaseOrderOpr.getPurchaseOrderRecord();

		String poLocationCode = purchaseOrderRecord.getLocationRecord().getCode();
		String poRaisedLocationCode = purchaseOrderRecord.getRaisedLocationRecord().getCode();
		Integer goodsType = purchaseOrderRecord.getGoodsType().getParameterID();
		Integer purchaseType = purchaseOrderRecord.getPurchaseType().getParameterID();
		Integer stockType = purchaseOrderRecord.getStockType().getParameterID();
		Long vendorId = purchaseOrderRecord.getVendorRecord().getId();
		String raisedUser = purchaseOrderRecord.getRaisedUserRecord().getSystemUserLogin();
		Date dueDate = purchaseOrderRecord.getDueDate();

		if (!validator.validateNull(poLocationCode)) {
			addToErrorList(propertiesReader.getValueOfKey("location_code_null"));
		}
		if (!validator.validateNull(poRaisedLocationCode)) {
			addToErrorList(propertiesReader.getValueOfKey("raised_location_code_null"));
		}
		if (!validator.validateIntegerObjectNull(goodsType)) {
			addToErrorList(propertiesReader.getValueOfKey("goods_type_null"));
		}
		if (!validator.validateIntegerObjectNull(purchaseType)) {
			addToErrorList(propertiesReader.getValueOfKey("purchase_type_null"));
		}
		if (!validator.validateIntegerObjectNull(stockType)) {
			addToErrorList(propertiesReader.getValueOfKey("stock_type_null"));
		}
		if (!validator.validateLongObjectNull(vendorId)) {
			addToErrorList(propertiesReader.getValueOfKey("vendor_code_null"));
		}
		if (!validator.validateNull(raisedUser)) {
			addToErrorList(propertiesReader.getValueOfKey("raised_user_null"));
		}
		if (!validator.validateDateNull(dueDate)) {
			addToErrorList(propertiesReader.getValueOfKey("due_date_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	@Override
	public void addEditTabClicked(ActionEvent event) {
	}

	@Override
	public void searchTabClicked(ActionEvent event) {
	}

	@Override
	public void editDetails() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("PO_HEADER", purchaseOrderOpr.getPurchaseOrderRecord());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("EDIT_PRODUCT_DETAILS", purchaseOrderProductDetailsRecord);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DISABLE_SAVE", disableSaveButton);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("RENDER_FOR_AMMENDMENT", renderForAmmendment);
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase order Definition Add Edit BB :: retrieveData starts ");

		poActions = null;
		getPurchaseOrderHeaderDetails();

		String fetchAllOptions = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("FETCH_ALL_OPTIONS_VALUES");

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("FETCH_ALL_OPTIONS_VALUES");

		if (allOptions.getAllOptionsValues().isEmpty()
				|| (fetchAllOptions != null && fetchAllOptions.equals("FETCH_ALL_OPTIONS_VALUES"))) {
			try {
				PurchaseOrderBD PurchaseOrderBD = new PurchaseOrderBD();
				allOptions.setAllOptionsValues(PurchaseOrderBD.getAllOptionsValues());

				// active location list mapped to user
				@SuppressWarnings("unchecked")
				ArrayList<Object> locationList = (ArrayList<Object>) getObjectFromCache(CommonConstant.ACTIVE_LOCATION_LIST);
				allOptions.getAllOptionsValues().put("locationList", locationList);

				if (getPurchaseOrderOpr().getPurchaseOrderRecord().getId() == null) {
					// user selected default location from login screen
					String locationCode = (String) getObjectFromCache(CommonConstant.DEFAULT_SELECTED_LOCATION);
					purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().setCode(locationCode);

					// default set user login
					String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
					purchaseOrderOpr.getPurchaseOrderRecord().getRaisedUserRecord().setSystemUserLogin(userLogin);

					ArrayList<Object> poOriginList = allOptions.getAllOptionsValues().get("poOriginList");

					for (Object object : poOriginList) {
						Parameter parameter = (Parameter) object;
						if (CommonConstant.ParameterSequenceNumber.THREE.equals(parameter.getSequenceNumber())) {
							purchaseOrderOpr.getPurchaseOrderRecord().getOriginType()
									.setParameterID(parameter.getParameterID());
							break;
						}
					}
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		poLocationChanged(null);
		populateEnableDisableButtons();
		populateData();

		myLog.debug("In Purchase order Definition Add Edit BB :: retrieveData ends ");
	}

	@Override
	public void executeAddRow(ActionEvent event) {

		if (validateSave()) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("PO_HEADER", purchaseOrderOpr.getPurchaseOrderRecord());

			purchaseOrderProductDetailsRecord = new PurchaseOrderProductDetailsDVO();
			// getPurchaseOrderProductDetailsRecord().setId(null);
			purchaseOrderProductDetailsRecord.getSettlementType().setParameterID(
					purchaseOrderOpr.getPurchaseOrderRecord().getSettlementType().getParameterID());
			purchaseOrderProductDetailsRecord.setDueDate(purchaseOrderOpr.getPurchaseOrderRecord().getDueDate());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("EDIT_PRODUCT_DETAILS", purchaseOrderProductDetailsRecord);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("DISABLE_SAVE", disableSaveButton);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("RENDER_FOR_AMMENDMENT", renderForAmmendment);
			RequestContext.getCurrentInstance().execute("openEditProductDetailsDialog();");
		}
	}

	@Override
	public OptionsDVO getAllOptions() {
		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}
		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	private void populateOrganizationBasedOnLocation() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String locationCode = purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getCode();
		myLog.debug("In populateOrganizationBasedOnLocation :: locationCode ---> " + locationCode);
		ArrayList<Object> locationList = allOptions.getAllOptionsValues().get("locationList");

		if (locationCode != null && locationList != null) {
			for (Object object : locationList) {
				LocationDVO locationRecord = (LocationDVO) object;
				String code = locationRecord.getCode();

				if (code != null && code.equals(locationCode)) {
					purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getOrganizationRecord()
							.setCode(locationRecord.getOrganizationRecord().getCode());
					myLog.debug("In populateOrganizationBasedOnLocation :: org Code ---> "
							+ locationRecord.getOrganizationRecord().getCode());
					break;
				}
			}
		}
	}

	public void poLocationChanged(AjaxBehaviorEvent event) {
		vendorList = null;
		try {
			// allOptions.getAllOptionsValues().put("vendorList", null);
			// get vendor based on location
			populateOrganizationBasedOnLocation();

			if (purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getCode() != null) {
				vendorList = new PurchaseOrderBD().getVendorListBasedOnLocation(purchaseOrderOpr
						.getPurchaseOrderRecord().getLocationRecord());

				if (event != null)
					purchaseOrderOpr.getPurchaseOrderRecord().setVendorRecord(null);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("vendorCodeAutoComplete", vendorList);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("vendorNameAutoComplete", vendorList);

		try {
			allOptions.getAllOptionsValues().put("screenAccessControlList", null);

			if (purchaseOrderOpr.getPurchaseOrderRecord().getId() != null
					&& purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getCode() != null) {

				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());

				ScreenAccessControlDVO screenAccessControlDVO = new ScreenAccessControlDVO();
				screenAccessControlDVO.getLocationRecord().setCode(
						purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getCode());
				screenAccessControlDVO.getModuleRecord().setCode(CommonConstant.Modules.PROCUREMENT);
				screenAccessControlDVO.setUserLogin(userLogin);
				screenAccessControlDVO.setScreenCode("PURCHASE_ORDER_ACTION_DROP_DOWN");

				ArrayList<Object> screenAccessControlList = new PurchaseOrderBD()
						.getUserAccessResources(screenAccessControlDVO);

				boolean displayApprove = true;
				String statusCode = purchaseOrderOpr.getPurchaseOrderRecord().getStatusRecord().getCode();
				String receiptStatusCode = purchaseOrderOpr.getPurchaseOrderRecord().getReceiptStatusRecord().getCode();
				if (CommonConstant.StatusCodes.PENDING_APPROVAL.equals(statusCode)) {
					String transactionCode = CommonConstant.TransactionCodes.PO_SFA;
					Long screenId = purchaseOrderOpr.getPurchaseOrderRecord().getId();

					WorkflowDVO workflowRecord = new WorkflowDVO();
					workflowRecord.getOrganizationRecord().setCode(
							purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getOrganizationRecord()
									.getCode());
					workflowRecord.getModuleRecord().setCode(CommonConstant.Modules.PROCUREMENT);
					workflowRecord.getTransactionRecord().setCode(transactionCode);
					workflowRecord.setId(screenId);
					workflowRecord.setUserLogin(userLogin);

					List<WorkflowDVO> workflowList = new PurchaseOrderBD().getWorkflowDetails(workflowRecord);
					if (!workflowList.isEmpty()) {
						purchaseOrderOpr.getPurchaseOrderRecord().setWorkflowRecord(workflowList.get(0));
						displayApprove = false;
					}
				}

				renderForAmmendment = false;
				ArrayList<Object> screenAccessControlObjList = new ArrayList<Object>();
				for (Object object : screenAccessControlList) {
					ScreenAccessControlDVO screenAccessControlRecord = (ScreenAccessControlDVO) object;
					String resourceTypeCode = screenAccessControlRecord.getResourceTypeCode();
					boolean recordToBeAdded = true;

					if (resourceTypeCode.equals("APPROVE")) {
						if (displayApprove)
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("REJECT")) {
						if (displayApprove)
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("REWORK")) {
						if (displayApprove)
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("CLOSE")) {
						if (!CommonConstant.StatusCodes.APPROVED.equals(statusCode))
							recordToBeAdded = false;
						if (CommonConstant.StatusCodes.CLOSED.equals(receiptStatusCode))
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("SEND_FOR_APPROVAL")) {
						if (!CommonConstant.StatusCodes.NEW.equals(statusCode))
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("DELETE_PRODUCT_ITEMS")) {
						if (!CommonConstant.StatusCodes.NEW.equals(statusCode))
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("AMEND")) {
						recordToBeAdded = false;

						if (CommonConstant.StatusCodes.APPROVED.equals(statusCode)
								&& !CommonConstant.StatusCodes.CLOSED.equals(receiptStatusCode))
							renderForAmmendment = true;

					} else if (resourceTypeCode.equals("PRINT_PO")) {
						if (!CommonConstant.StatusCodes.APPROVED.equals(statusCode))
							recordToBeAdded = false;

					} else if (resourceTypeCode.equals("PRINT_PO_TRANSACTION")) {
						if (!CommonConstant.StatusCodes.APPROVED.equals(statusCode))
							recordToBeAdded = false;
					} else if (resourceTypeCode.equals("CONFIRM_ITEM_ISSUE")) {
						if (CommonConstant.StatusCodes.NEW.equals(statusCode))
							recordToBeAdded = false;
					}

					if (recordToBeAdded)
						screenAccessControlObjList.add(screenAccessControlRecord);
				}

				allOptions.getAllOptionsValues().put("screenAccessControlList", screenAccessControlObjList);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void executeUpdatePOStatus(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: executeUpdatePOStatus starts ");

		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator validator = new FoundationValidator();

		String reasonCode = purchaseOrderOpr.getPurchaseOrderRecord().getReasonRecord().getCode();

		if (!validator.validateNull(reasonCode))
			addToErrorList(propertiesReader.getValueOfKey("reason_null"));

		if (getErrorList().size() == 0) {
			try {
				myLog.debug(" poActions ---> " + poActions);
				if (poActions != null) {

					String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
					String transactionCode = null;
					// Long screenId =
					// purchaseOrderOpr.getPurchaseOrderRecord().getWorkflowRecord().getId();
					Long screenId = purchaseOrderOpr.getPurchaseOrderRecord().getId();
					String statusCode = null;

					if (poActions.equals("APPROVE")) {
						transactionCode = CommonConstant.TransactionCodes.PO_APPROVED;
						statusCode = CommonConstant.StatusCodes.APPROVED;

					} else if (poActions.equals("REJECT")) {
						transactionCode = CommonConstant.TransactionCodes.PO_REJECT;
						statusCode = CommonConstant.StatusCodes.REJECT;

					} else if (poActions.equals("REWORK")) {
						transactionCode = CommonConstant.TransactionCodes.PO_REWORK;
						statusCode = CommonConstant.StatusCodes.REWORK;

					} else if (poActions.equals("CLOSE")) {
						transactionCode = CommonConstant.TransactionCodes.PO_CLOSED;
						statusCode = CommonConstant.StatusCodes.CLOSED;
					}

					WorkflowDVO workflowRecord = new WorkflowDVO();
					workflowRecord.getOrganizationRecord().setCode(
							purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getOrganizationRecord()
									.getCode());
					workflowRecord.getModuleRecord().setCode(CommonConstant.Modules.PROCUREMENT);
					workflowRecord.getTransactionRecord().setCode(transactionCode);
					workflowRecord.setId(screenId);
					workflowRecord.getStatusRecord().setCode(statusCode);
					workflowRecord.getReasonRecord().setCode(reasonCode);
					workflowRecord.setComments(purchaseOrderOpr.getPurchaseOrderRecord().getStatusComments());
					workflowRecord.setUserLogin(userLogin);

					new PurchaseOrderBD().updateTransactionStatus(workflowRecord);
					// retrieveData();
					getPurchaseOrderHeaderDetails();
					populateData();
					poLocationChanged(null);
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void executePOActions(ActionEvent event) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: executePOActions starts ");
		myLog.debug(" poActions ---> " + poActions);
		setErrorList(new ArrayList<String>());

		if (poActions != null) {
			allOptions.getAllOptionsValues().put("reasonList", null);
			String transactionCode = null;

			if (poActions.equals("APPROVE")) {
				transactionCode = CommonConstant.TransactionCodes.PO_APPROVED;

			} else if (poActions.equals("REJECT")) {
				transactionCode = CommonConstant.TransactionCodes.PO_REJECT;

			} else if (poActions.equals("REWORK")) {
				transactionCode = CommonConstant.TransactionCodes.PO_REWORK;

			} else if (poActions.equals("CLOSE")) {
				transactionCode = CommonConstant.TransactionCodes.PO_CLOSED;

			} else if (poActions.equals("SEND_FOR_APPROVAL")) {
				sendForApproval();

			} else if (poActions.equals("DELETE_PRODUCT_ITEMS")) {
				deleteProductItemDetails();

			} else if (poActions.equals("PRINT_PO")) {
				printReport();

			} else if (poActions.equals("PRINT_PO_TRANSACTION")) {
				printPOTransactionReport();

			} else if (poActions.equals("CONFIRM_ITEM_ISSUE")) {
				executeConfirmItemIssue();
			}

			if (transactionCode != null) {
				try {

					allOptions.getAllOptionsValues().put("reasonList",
							new PurchaseOrderBD().getReasonCodeList(transactionCode));

				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);

				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			}
			// poLocationChanged(null);
			if (getErrorList().size() == 0)
				RequestContext.getCurrentInstance().addCallbackParam("poActions", poActions);
		}

		myLog.debug("In Purchase order Definition Add Edit BB :: executePOActions ends ");
	}

	private void sendForApproval() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: sendForApproval starts ");

		if (validateSendForApproval()) {
			try {

				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				String transactionCode = CommonConstant.TransactionCodes.PO_SFA;
				Long screenId = purchaseOrderOpr.getPurchaseOrderRecord().getId();

				WorkflowDVO workflowRecord = new WorkflowDVO();
				workflowRecord.getOrganizationRecord()
						.setCode(
								purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getOrganizationRecord()
										.getCode());
				workflowRecord.getModuleRecord().setCode(CommonConstant.Modules.PROCUREMENT);
				workflowRecord.getTransactionRecord().setCode(transactionCode);
				workflowRecord.setId(screenId);
				workflowRecord.setUserLogin(userLogin);

				new PurchaseOrderBD().sendForApproval(workflowRecord);
				// retrieveData();
				getPurchaseOrderHeaderDetails();
				populateData();
				poLocationChanged(null);
				populateEnableDisableButtons();

				generateMailForVendor();
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("purchase_order_sfa_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	private void generateMailForVendor() throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside generateMailForVendor:::");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());
		// get vendor email id
		VendorDVO vendorRecord = new VendorDVO();
		try {
			vendorRecord = new PurchaseOrderBD().getVendorDetailsBasedOnId(purchaseOrderOpr.getPurchaseOrderRecord()
					.getVendorRecord());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		myLog.debug("vendor email 1:::" + vendorRecord.getCorporateAddres().getEmail1());
		myLog.debug("vendor email 2:::" + vendorRecord.getCorporateAddres().getEmail2());

		String email1 = vendorRecord.getCorporateAddres().getEmail1();
		String email2 = vendorRecord.getCorporateAddres().getEmail2();

		if (email1 == null && email2 == null) {
			addToErrorList(propertiesReader.getValueOfKey("vendor_email_id_null")
					+ purchaseOrderOpr.getPurchaseOrderRecord().getVendorRecord().getName());
		}

		if (getErrorList().isEmpty()) {

			// send email
			PropertiesReader propertiesReaderCommon = new PropertiesReader(
					CommonConstant.MessageLocation.COMMON_MESSAGES);
			MailParameters mailParameters = new MailParameters();

			// InternetAddress[] addressTo = new InternetAddress[2];
			// InternetAddress[] addressCC = new InternetAddress[1];

			if (email1 != null && email2 != null) {
				InternetAddress[] addressTo = new InternetAddress[2];

				InternetAddress ia = new InternetAddress();
				ia.setAddress(email1);
				addressTo[0] = ia;

				InternetAddress ia1 = new InternetAddress();
				ia1.setAddress(email2);
				addressTo[1] = ia1;

				mailParameters.setMailRecipients(addressTo);
			} else if (email1 != null) {
				InternetAddress[] addressTo = new InternetAddress[1];

				InternetAddress ia = new InternetAddress();
				ia.setAddress(email1);
				addressTo[0] = ia;

				mailParameters.setMailRecipients(addressTo);
			} else if (email2 != null) {
				InternetAddress[] addressTo = new InternetAddress[1];
				InternetAddress ia = new InternetAddress();
				ia.setAddress(email2);
				addressTo[0] = ia;

				mailParameters.setMailRecipients(addressTo);
			}

			// InternetAddress iaCC = new InternetAddress();
			// iaCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			// addressCC[0] = iaCC;
			//
			// mailParameters.setMailRecipientsCC(addressCC);
			String[] messageSubjectArguments = { "Test Subject" };
			String subject = MessageFormatter.getFormattedMessage(
					new PropertiesReader(propertiesLocation).getValueOfKey("mail_subject"), messageSubjectArguments);
			mailParameters.setMailSubject(subject);
			String[] messageBodyArguments = { "mail test body" };
			String mailMessage = MessageFormatter.getFormattedMessage(
					new PropertiesReader(propertiesLocation).getValueOfKey("mail_body"), messageBodyArguments);
			mailParameters.setMailMessage(mailMessage);
			// mailParameters.setMailDVOObject(shoppingCartOpr);

			mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_PLAIN);
			// mailParameters.setRoutingKey(propertiesReaderCommon
			// .getValueOfKey("rabbitmq_email_routing_key"));
			mailParameters.setMessageQueue(propertiesReaderCommon.getValueOfKey("rabbitmq_email_queue_name"));

			String fileNameWithoutExtension = "PurchaseOrder";
			String extension = "pdf";

			java.io.File fileObj = generatePdfForReport(generateBirtUrl(), fileNameWithoutExtension, extension);
			String fileName = getFileName(fileNameWithoutExtension, extension);
			myLog.debug("birtUrl:::::" + generateBirtUrl());
			myLog.debug("filename:::::" + fileName);
			myLog.debug("filename2:::::" + fileObj.getName());

			// ByteArrayOutputStream ous = null;
			InputStream ios = null;
			myLog.debug("before length:::");
			byte[] buffer = new byte[(int) fileObj.length()];
			try {

				// ous = new ByteArrayOutputStream();
				ios = new FileInputStream(fileObj);
				ios.read(buffer);
				myLog.debug("check length:::" + buffer.length);
			} catch (Exception e) {
				e.printStackTrace();
			}

			File file = new File();
			file.setData(buffer);
			file.setMime(CommonConstant.MimeType.APP_OCTET_STREAM);
			file.setName(fileName);

			ArrayList<File> attachments = new ArrayList<File>();
			attachments.add(file);
			mailParameters.setAttachments(attachments);

			try {
				RetailMail portalMail = new RetailMail(mailParameters);
				portalMail.sendMultipleMail();
				setSuccessMsg(propertiesReader.getValueOfKey("mail_sent_success"));
			} catch (MessagingException e) {
			}
		}
	}

	private void deleteProductItemDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: deleteProductItemDetails starts ");

		List<PurchaseOrderProductDetailsDVO> productRecordsToDeleted = new ArrayList<PurchaseOrderProductDetailsDVO>();
		List<PurchaseOrderRMDetailsDVO> rmRecordsToDeleted = new ArrayList<PurchaseOrderRMDetailsDVO>();
		if (!purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList().isEmpty()) {
			for (PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsDVO : purchaseOrderOpr
					.getPurchaseOrderRecord().getPurchaseOrderProductDetailsList()) {
				if (purchaseOrderProductDetailsDVO.getOperationalAttributes().getRecordPopulated()) {
					productRecordsToDeleted.add(purchaseOrderProductDetailsDVO);
				}
			}
		}
		if (!purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderRMDetailsList().isEmpty()) {
			for (PurchaseOrderRMDetailsDVO purchaseOrderRMDetailsDVO : purchaseOrderOpr.getPurchaseOrderRecord()
					.getPurchaseOrderRMDetailsList()) {
				if (purchaseOrderRMDetailsDVO.getOperationalAttributes().getRecordPopulated()) {
					rmRecordsToDeleted.add(purchaseOrderRMDetailsDVO);
				}
			}
		}

		try {

			PurchaseOrderHeaderDVO purchaseOrderRecord = new PurchaseOrderHeaderDVO();

			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			purchaseOrderRecord.setId(purchaseOrderOpr.getPurchaseOrderRecord().getId());
			purchaseOrderRecord.setPurchaseOrderProductDetailsList(productRecordsToDeleted);
			purchaseOrderRecord.setPurchaseOrderRMDetailsList(rmRecordsToDeleted);

			purchaseOrderRecord.getAuditAttributes().setLastModifiedDate(
					purchaseOrderOpr.getPurchaseOrderRecord().getAuditAttributes().getLastModifiedDate());

			purchaseOrderRecord.setUserLogin(userLogin);

			purchaseOrderRecord = new PurchaseOrderBD().deleteProductItemDetails(purchaseOrderRecord);

			purchaseOrderOpr.getPurchaseOrderRecord().getAuditAttributes()
					.setLastModifiedDate(purchaseOrderRecord.getAuditAttributes().getLastModifiedDate());
			// retrieveData();
			getPurchaseOrderHeaderDetails();
			populateData();

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("purchase_order_deleted_success"));

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	private void populateEnableDisableButtons() {
		disableSaveButton = true;
		String statusCode = purchaseOrderOpr.getPurchaseOrderRecord().getStatusRecord().getCode();

		if (statusCode == null || CommonConstant.StatusCodes.NEW.equals(statusCode)) {
			disableSaveButton = false;
		}
	}

	private void populateData() {
		// poGoodsTypeList
		ArrayList<Object> poGoodsTypeList = allOptions.getAllOptionsValues().get("poGoodsTypeList");
		PurchaseOrderHeaderDVO purchaseOrderRecord = purchaseOrderOpr.getPurchaseOrderRecord();
		Integer goodsType = purchaseOrderRecord.getGoodsType().getParameterID();

		if (goodsType != null) {
			for (Object object : poGoodsTypeList) {
				Parameter parameter = (Parameter) object;
				if (goodsType.equals(parameter.getParameterID())) {
					purchaseOrderRecord.getGoodsType().setSequenceNumber(parameter.getSequenceNumber());
					break;
				}
			}
		}
	}

	public void executeRMAddRow(ActionEvent event) {
		if (validateSave()) {
			purchaseOrderRMDetailsRecord = new PurchaseOrderRMDetailsDVO();
			purchaseOrderRMDetailsRecord.setDueDate(purchaseOrderOpr.getPurchaseOrderRecord().getDueDate());

			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("PO_HEADER_RM", purchaseOrderOpr.getPurchaseOrderRecord());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("EDIT_RM_DETAILS", purchaseOrderRMDetailsRecord);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("DISABLE_SAVE_RM", disableSaveButton);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("RENDER_FOR_AMMENDMENT_RM", renderForAmmendment);
			RequestContext.getCurrentInstance().execute("openRmMappingDetailsDialog();");
		}
	}

	public void editRMDetails() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("PO_HEADER_RM", purchaseOrderOpr.getPurchaseOrderRecord());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("EDIT_RM_DETAILS", purchaseOrderRMDetailsRecord);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("DISABLE_SAVE_RM", disableSaveButton);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("RENDER_FOR_AMMENDMENT_RM", renderForAmmendment);

	}

	private void getPurchaseOrderHeaderDetails() {
		purchaseOrderId = purchaseOrderOpr.getPurchaseOrderRecord().getId();
		if (purchaseOrderId != null) {
			try {
				PurchaseOrderHeaderDVO purchaseOrderHeaderDVO = new PurchaseOrderHeaderDVO();
				purchaseOrderHeaderDVO.setId(purchaseOrderId);
				purchaseOrderOpr = new PurchaseOrderBD().getPurchaseOrderHeader(purchaseOrderHeaderDVO);

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		} else {
			try {
				DatabaseSetupDVO databaseSetupDVO = new DatabaseSetupDVO();
				databaseSetupDVO = new PurchaseOrderBD().getDatabaseSetupDetails(databaseSetupDVO);
				purchaseOrderOpr.getPurchaseOrderRecord().setRaisedLocationRecord(databaseSetupDVO.getLocationRecord());

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateSendForApproval() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		PurchaseOrderHeaderDVO purchaseOrderRecord = purchaseOrderOpr.getPurchaseOrderRecord();

		int size = purchaseOrderRecord.getPurchaseOrderProductDetailsList().size();
		for (int i = 0; i < size; i++) {
			PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecord = purchaseOrderRecord
					.getPurchaseOrderProductDetailsList().get(i);

			Long productId = purchaseOrderProductDetailsRecord.getProductRecord().getId();
			Integer allocationBasedOn = null;
			Float quantityOrdered = purchaseOrderProductDetailsRecord.getQuantityOrdered();
			String quantityUomCode = purchaseOrderProductDetailsRecord.getQuantityUomRecord().getCode();
			Float weightOrdered = purchaseOrderProductDetailsRecord.getWeightOrdered();
			String weightUomCode = purchaseOrderProductDetailsRecord.getWeightUomRecord().getCode();

			if (productId != null)
				allocationBasedOn = purchaseOrderProductDetailsRecord.getProductRecord().getAllocationBasedOn()
						.getSequenceNumber();
			else
				allocationBasedOn = purchaseOrderProductDetailsRecord.getProductCategoryRecord().getAllocationBasedOn()
						.getSequenceNumber();

			// allocation validations
			if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
				// Quantity validations
				if (!validator.validateFloatObjectNull(quantityOrdered)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_null") + " at row " + (i + 1));
				}

				if (!validator.validateNull(quantityUomCode)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null") + " at row " + (i + 1));
				}

			} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
				// Weight validations
				if (!validator.validateFloatObjectNull(weightOrdered)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_null") + " at row " + (i + 1));
				}
				if (!validator.validateNull(weightUomCode)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_uom_null") + " at row " + (i + 1));
				}

			} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
				// both validations
				if (!validator.validateFloatObjectNull(quantityOrdered)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_null") + " at row " + (i + 1));
				}
				if (!validator.validateNull(quantityUomCode)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null") + " at row " + (i + 1));
				}

				if (!validator.validateFloatObjectNull(weightOrdered)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_null") + " at row " + (i + 1));
				}
				if (!validator.validateNull(weightUomCode)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_uom_null") + " at row " + (i + 1));
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public List<Object> getSuggestedVendorListForCode(String query) {

		if (query != null) {
			query = query.toUpperCase();
			List<Object> vendorObjList = new ArrayList<Object>();

			for (Object object : getVendorList()) {
				VendorDVO vendorDVO = (VendorDVO) object;
				String code = vendorDVO.getCode();
				if (code != null && code.toUpperCase().startsWith(query)) {
					vendorObjList.add(vendorDVO);
				}
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("vendorCodeAutoComplete", vendorObjList);
			return vendorObjList;
		}
		return null;
	}

	public List<Object> getSuggestedVendorListForName(String query) {
		if (query != null) {
			query = query.toUpperCase();
			List<Object> vendorObjList = new ArrayList<Object>();

			for (Object object : getVendorList()) {
				VendorDVO vendorDVO = (VendorDVO) object;
				String name = vendorDVO.getName();
				if (name != null && name.toUpperCase().startsWith(query)) {
					vendorObjList.add(vendorDVO);
				}
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("vendorNameAutoComplete", vendorObjList);
			return vendorObjList;
		}
		return null;
	}

	private void printReport() {
		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			StringBuffer parameters = new StringBuffer();
			parameters.append("&p_purchase_order_id=");
			parameters.append(purchaseOrderOpr.getPurchaseOrderRecord().getId());
			parameters.append("&p_user_login=");
			parameters.append(userLogin);
			parameters.append(getBarCodeUrl());

			openReportPopup("report/procurement/purchaseorder/purchase_order_product_details.rptdesign",
					parameters.toString());
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	private String generateBirtUrl() {
		String birtUrl = null;
		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			StringBuffer parameters = new StringBuffer();
			parameters.append("&p_purchase_order_id=");
			parameters.append(purchaseOrderOpr.getPurchaseOrderRecord().getId());
			parameters.append("&p_user_login=");
			parameters.append(userLogin);
			parameters.append(getBarCodeUrl());

			String reportUrl = "report/procurement/purchaseorder/purchase_order_product_details.rptdesign";
			parameters.append("&__format=pdf");

			birtUrl = getReportUrl(reportUrl, parameters.toString());
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		return birtUrl;
	}

	private void printPOTransactionReport() {
		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			StringBuffer parameters = new StringBuffer();
			parameters.append("&p_purchase_order_number=");
			parameters.append(purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseOrderNumber());
			parameters.append("&p_location_code=");
			parameters.append(purchaseOrderOpr.getPurchaseOrderRecord().getLocationRecord().getCode());
			parameters.append("&p_user_login=");
			parameters.append(userLogin);

			openReportPopup("report/procurement/po_txn_status/po_txn_status.rptdesign", parameters.toString());
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void goodsTypeChanged(AjaxBehaviorEvent event) {
		populateData();

		if (purchaseOrderOpr.getPurchaseOrderRecord().getGoodsType().getSequenceNumber() != null
				&& purchaseOrderOpr.getPurchaseOrderRecord().getGoodsType().getSequenceNumber().intValue() == 2) {
			if (purchaseOrderOpr.getPurchaseOrderRecord().getId() == null) {
				// default value for Type of purchase
				if (allOptions.getAllOptionsValues().get("poTypeList") != null
						&& allOptions.getAllOptionsValues().get("poTypeList").size() > 0) {
					for (Object paramObj : allOptions.getAllOptionsValues().get("poTypeList")) {
						Parameter parameter = (Parameter) paramObj;
						Integer seqNumber = parameter.getSequenceNumber();
						if (seqNumber != null && seqNumber.intValue() == 1) {
							purchaseOrderOpr.getPurchaseOrderRecord().getPurchaseType()
									.setParameterID(parameter.getParameterID());
						}
					}
				}

				// default value for Type of stock
				if (allOptions.getAllOptionsValues().get("poStockTypeList") != null
						&& allOptions.getAllOptionsValues().get("poStockTypeList").size() > 0) {
					for (Object paramObj : allOptions.getAllOptionsValues().get("poStockTypeList")) {
						Parameter parameter = (Parameter) paramObj;
						Integer seqNumber = parameter.getSequenceNumber();
						if (seqNumber != null && seqNumber.intValue() == 2) {
							purchaseOrderOpr.getPurchaseOrderRecord().getStockType()
									.setParameterID(parameter.getParameterID());
						}
					}
				}

				// default value for Type of settlement
				if (allOptions.getAllOptionsValues().get("settlementTypeList") != null
						&& allOptions.getAllOptionsValues().get("settlementTypeList").size() > 0) {
					for (Object paramObj : allOptions.getAllOptionsValues().get("settlementTypeList")) {
						Parameter parameter = (Parameter) paramObj;
						Integer seqNumber = parameter.getSequenceNumber();
						if (seqNumber != null && seqNumber.intValue() == 1) {
							purchaseOrderOpr.getPurchaseOrderRecord().getSettlementType()
									.setParameterID(parameter.getParameterID());
						}
					}
				}
			}
		}
	}

	public void executeAddRowItemBOMDetails(ActionEvent event) {
		itemBOMHeaderRecord = new PurchaseOrderItemBomDetailsDVO();
	}

	public String editItemBOMDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Receipt Request From Vendor Add Edit BB :: executeSaveItemBOMDetails starts ");
		ArrayList<Object> itemList = new ArrayList<Object>();
		itemList.add(itemBOMHeaderRecord.getItemRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemCodeAutoComplete", itemList);

		ArrayList<Object> itemCategoryList = new ArrayList<Object>();
		itemCategoryList.add(itemBOMHeaderRecord.getItemCategoryRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("itemCategoryCodeAutoComplete", itemCategoryList);

		// combination details
		try {
			Long itemPropertiesCombinationHeaderId = itemBOMHeaderRecord.getItemRecord()
					.getItemPropertiesCombinationHeaderRecord().getId();
			myLog.debug(" itemPropertiesCombinationHeaderId ---> " + itemPropertiesCombinationHeaderId);
			itemBOMHeaderRecord.getItemRecord().getItemPropertiesCombinationHeaderRecord()
					.setItemPropertiesCombinationDetailsList(null);

			if (itemPropertiesCombinationHeaderId != null) {
				ItemDVO itemDVO = new PurchaseOrderBD().getItemPropertyValueListBasedOnCombination(itemBOMHeaderRecord
						.getItemRecord());
				itemBOMHeaderRecord
						.getItemRecord()
						.getItemPropertiesCombinationHeaderRecord()
						.setItemPropertiesCombinationDetailsList(
								itemDVO.getItemPropertiesCombinationHeaderRecord()
										.getItemPropertiesCombinationDetailsList());
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		return null;
	}

	public List<Object> getSuggestedItemCategoryListForName(String query) {
		if (query != null) {
			try {
				ItemCategoryDVO itemCategoryDVO = new ItemCategoryDVO();
				itemCategoryDVO.setName(query);
				ArrayList<Object> itemCategoryList = new PurchaseOrderBD()
						.getSuggestedItemCategoryListBasedOnLevel(itemCategoryDVO);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("itemCategoryCodeAutoComplete", itemCategoryList);
				return itemCategoryList;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedItemsList(String query) {

		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		Long itemCategoryId = itemBOMHeaderRecord.getItemCategoryRecord().getId();
		if (itemCategoryId == null) {
			addToErrorList(propertiesReader.getValueOfKey("item_category_null"));
		}

		if (getErrorList().isEmpty()) {
			try {

				ItemDVO itemDVO = new ItemDVO();
				itemDVO.setName(query);
				// itemDVO.getStatusRecord().setCode(CommonConstant.StatusCodes.APPROVED);
				itemDVO.getApplicationFlags().getApplicationFlagMap()
						.put("APPROVED", CommonConstant.StatusCodes.APPROVED);
				itemDVO.getItemHierarchyRecord().getItemCategoryRecord().setId(itemCategoryId);

				ArrayList<Object> itemList = new PurchaseOrderBD().getSuggestedItemsListBasedOnItemCategory(itemDVO);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemCodeAutoComplete", itemList);
				return itemList;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public void itemCodeSelected(AjaxBehaviorEvent event) {
		try {

			ItemDVO itemDVO = new PurchaseOrderBD().getItemPropetyListBasedOnItem(itemBOMHeaderRecord.getItemRecord());
			itemBOMHeaderRecord
					.getItemRecord()
					.getItemPropertiesCombinationHeaderRecord()
					.setItemPropertiesCombinationDetailsList(
							itemDVO.getItemPropertiesCombinationHeaderRecord()
									.getItemPropertiesCombinationDetailsList());

			itemBOMHeaderRecord.getItemQuantityUomRecord().setCode(
					itemBOMHeaderRecord.getItemRecord().getQuantityUomRecord().getCode());
			itemBOMHeaderRecord.getItemWeightUomRecord().setCode(
					itemBOMHeaderRecord.getItemRecord().getWeightUomRecord().getCode());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public boolean validateBOMDetails() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		String itemCode = itemBOMHeaderRecord.getItemRecord().getCode();
		Float itemQuantity = itemBOMHeaderRecord.getItemQuantity();
		Float itemWeight = itemBOMHeaderRecord.getItemWeight();
		Integer allocationBasedOn = itemBOMHeaderRecord.getItemRecord().getAllocationBasedOn().getSequenceNumber();
		Long itemCategoryId = itemBOMHeaderRecord.getItemCategoryRecord().getId();

		if (!validator.validateLongObjectNull(itemCategoryId)) {
			addToErrorList(propertiesReader.getValueOfKey("item_category_null"));
		}
		if (!validator.validateNull(itemCode)) {
			addToErrorList(propertiesReader.getValueOfKey("item_null"));
		}
		// allocation validations
		if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
			// Quantity validations
			if (!validator.validateFloatObjectNull(itemQuantity)) {
				addToErrorList(propertiesReader.getValueOfKey("quantity_null"));
			}

		} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
			// Weight validations
			if (!validator.validateFloatObjectNull(itemWeight)) {
				addToErrorList(propertiesReader.getValueOfKey("weight_null"));
			}

		} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
			// both validations
			if (!validator.validateFloatObjectNull(itemQuantity)) {
				addToErrorList(propertiesReader.getValueOfKey("quantity_null"));
			}

			if (!validator.validateFloatObjectNull(itemWeight)) {
				addToErrorList(propertiesReader.getValueOfKey("weight_null"));
			}
		}

		if (itemQuantity != null && itemQuantity <= 0) {
			addToErrorList(propertiesReader.getValueOfKey("quantity_should_be_positive"));
		}
		if (itemWeight != null && itemWeight <= 0) {
			addToErrorList(propertiesReader.getValueOfKey("weight_should_be_positive"));
		}

		int size = itemBOMHeaderRecord.getItemRecord().getItemPropertiesCombinationHeaderRecord()
				.getItemPropertiesCombinationDetailsList().size();
		for (int i = 0; i < size; i++) {

			ItemPropertiesCombinationDetailsDVO itemPropertiesCombinationDetailsDVO = itemBOMHeaderRecord
					.getItemRecord().getItemPropertiesCombinationHeaderRecord()
					.getItemPropertiesCombinationDetailsList().get(i);

			Boolean mandatory = itemPropertiesCombinationDetailsDVO.getItemPropertiesRecord().getMandatory();
			String itemPropertyValue = itemPropertiesCombinationDetailsDVO.getItemPropertyValue();
			if (mandatory) {
				if (!validator.validateNull(itemPropertyValue)) {
					addToErrorList(propertiesReader.getValueOfKey("item_property_value_null") + (i + 1));
				}
			}

		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeSaveItemBOMDetails(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" executeSaveItemBOMDetails ");

		try {
			if (validateBOMDetails()) {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderOpr.getPurchaseOrderRecord().setPurchaseOrderItemBomDetailsRecord(itemBOMHeaderRecord);
				purchaseOrderOpr = new PurchaseOrderBD().executeSaveItemBOMDetails(purchaseOrderOpr);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("po_bom_save_success"));
				openBOMDetailsDialog(null);
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

	public void openBOMDetailsDialog(ActionEvent event) {
		// purchaseOrderDefinitionAddEditBB.purchaseOrderOpr.purchaseOrderRecord.id
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("openBOMDetailsDialog ");

		try {
			PurchaseOrderOpr opr = new PurchaseOrderBD().openBOMDetailsDialog(purchaseOrderOpr);
			purchaseOrderOpr.getPurchaseOrderRecord().setPurchaseOrderItemBomDetailsList(
					opr.getPurchaseOrderRecord().getPurchaseOrderItemBomDetailsList());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

	}

	public void executeDeleteItemBOMDetails(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" executeDeleteItemBOMDetails ");
		try {
			purchaseOrderOpr = new PurchaseOrderBD().executeDeleteItemBOMDetails(purchaseOrderOpr);
			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("po_bom_delete_success"));
			openBOMDetailsDialog(null);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public String openOwnStockDialog() {
		purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
				.setParameterID(2);
		getIssueOfItemDetails();
		return null;
	}

	public String openCustomerGoodDialog() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
				.setParameterID(3);
		getIssueOfItemDetails();
		myLog.debug("after getIssueOfItemDetails :: issue type::"
				+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
						.getParameterID());
		return null;
	}

	public String getIssueOfItemDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" getIssueOfItemDetails ");
		myLog.debug("purchase order id::" + purchaseOrderOpr.getPurchaseOrderRecord().getId());
		purchaseOrderProductDetailsRecord.setPurchaseOrderIssueItemMappingList(null);
		purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().setId(
				purchaseOrderOpr.getPurchaseOrderRecord().getId());
		myLog.debug("inside getIssueOfItemDetails :: issue type::"
				+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
						.getParameterID());
		try {
			PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
					.getIssueOfItemDetails(purchaseOrderProductDetailsRecord);
			purchaseOrderProductDetailsRecord.setPurchaseOrderIssueItemMappingList(purchaseOrderProductDetailsRecordRet
					.getPurchaseOrderIssueItemMappingList());
			if (!purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingList().isEmpty()) {
				purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
						.setIssueInventoryLocationRecord(
								purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingList().get(0)
										.getIssueInventoryLocationRecord());
			} else {
				purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
						.setIssueInventoryLocationRecord(null);
			}

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
					.setParameterStringValue("Issue Stock");

			List<Object> issueInventoryLocationList = new ArrayList<Object>();
			issueInventoryLocationList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
					.getIssueInventoryLocationRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("issueInventoryLocationSuggestionBox", issueInventoryLocationList);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
		return null;
	}

	public String addEditIssueItem() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (validateAddEditIssueItem()) {
			InvInventoryLocationMappingDVO inventoryLocationMappingDVO = purchaseOrderProductDetailsRecord
					.getPurchaseOrderIssueItemMappingRecord().getIssueInventoryLocationRecord();
			Integer issueType = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
					.getIssueTypeRecord().getParameterID();
			myLog.debug("inside addEditIssueItem:::" + inventoryLocationMappingDVO.getId());
			purchaseOrderProductDetailsRecord
					.setPurchaseOrderIssueItemMappingRecord(new PurchaseOrderIssueItemMappingDVO());
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setIssueInventoryLocationRecord(
					inventoryLocationMappingDVO);
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getIssueTypeRecord()
					.setParameterID(issueType);
			myLog.debug("inside addEditIssueItem after set new:::"
					+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getIssueInventoryLocationRecord().getId());
			RequestContext.getCurrentInstance().execute("openIssueItemAddEditDialog();");
		}
		return null;
	}

	private boolean validateAddEditIssueItem() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		myLog.debug("inside validateAddEditIssueItem:::"
				+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
						.getIssueInventoryLocationRecord().getId());
		if (!validator.validateLongObjectNull(purchaseOrderProductDetailsRecord
				.getPurchaseOrderIssueItemMappingRecord().getIssueInventoryLocationRecord().getId())) {
			addToErrorList(propertiesReader.getValueOfKey("inv_inventory_location_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeIssueItemSave(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: executeIssueItemSave starts ");

		if (validateExecuteIssueItemSave()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderProductDetailsRecord.setPurchaseOrderRecord(purchaseOrderOpr.getPurchaseOrderRecord());
				PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
						.executeIssueItemSave(purchaseOrderProductDetailsRecord);
				purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setId(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderIssueItemMappingRecord().getId());
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("issue_item_save_success"));
				getIssueOfItemDetails();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public boolean validateExecuteIssueItemSave() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		String itemCode = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
				.getCode();
		Long itemCategoryId = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getItemCategoryRecord().getId();
		Integer allocationBasedOn = null;
		Float quantity = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getQuantity();
		String quantityUom = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getQuantityUomRecord().getCode();
		Float weight = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getWeight();
		String weightUom = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getWeightUomRecord().getCode();
		Integer issueTypeId = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getIssueTypeRecord().getParameterID();

		if (issueTypeId != null && issueTypeId.equals(3)) {

		} else {
			if (!validator.validateLongObjectNull(itemCategoryId)) {
				addToErrorList(propertiesReader.getValueOfKey("item_category_null"));
			}

			if (!validator.validateNull(itemCode)) {
				addToErrorList(propertiesReader.getValueOfKey("item_null"));
			} else {
				allocationBasedOn = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
						.getItemRecord().getAllocationBasedOn().getSequenceNumber();
			}

			if (CommonConstant.ParameterSequenceNumber.ONE.equals(allocationBasedOn)) {
				// Quantity validations
				if (!validator.validateFloatObjectNull(quantity)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_null"));
				}

				if (!validator.validateNull(quantityUom)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null"));
				}

			} else if (CommonConstant.ParameterSequenceNumber.TWO.equals(allocationBasedOn)) {
				// Weight validations
				if (!validator.validateFloatObjectNull(weight)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_null"));
				}
				if (!validator.validateNull(weightUom)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_uom_null"));
				}

			} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(allocationBasedOn)) {
				// both validations
				if (!validator.validateFloatObjectNull(quantity)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_null"));
				}
				if (!validator.validateNull(quantityUom)) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_uom_null"));
				}

				if (!validator.validateFloatObjectNull(weight)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_null"));
				}
				if (!validator.validateNull(weightUom)) {
					addToErrorList(propertiesReader.getValueOfKey("weight_uom_null"));
				}
			}

			if (validator.validateFloatObjectNull(quantity)) {
				if (quantity <= 0) {
					addToErrorList(propertiesReader.getValueOfKey("quantity_should_be_positive"));
				}
			}
			if (validator.validateFloatObjectNull(weight)) {
				if (weight <= 0) {
					addToErrorList(propertiesReader.getValueOfKey("weight_should_be_positive"));
				}
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public String executeAddIssueItemDetail() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		try {

			ItemDVO itemDVO = new PurchaseOrderBD().getItemPropetyListBasedOnItem(purchaseOrderProductDetailsRecord
					.getPurchaseOrderIssueItemMappingRecord().getItemRecord());

			myLog.debug(" > itemCodeSelected :: size is "
					+ itemDVO.getItemPropertiesCombinationHeaderRecord().getItemPropertiesCombinationDetailsList()
							.size());

			purchaseOrderProductDetailsRecord
					.getPurchaseOrderIssueItemMappingRecord()
					.getItemRecord()
					.getItemPropertiesCombinationHeaderRecord()
					.setItemPropertiesCombinationDetailsList(
							itemDVO.getItemPropertiesCombinationHeaderRecord()
									.getItemPropertiesCombinationDetailsList());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		List<Object> itemCodeList = new ArrayList<Object>();
		itemCodeList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemSuggestionBox", itemCodeList);

		List<Object> quantityUomList = new ArrayList<Object>();
		quantityUomList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getQuantityUomRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("quantitySuggestionBox", quantityUomList);

		List<Object> weightUomList = new ArrayList<Object>();
		weightUomList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getWeightUomRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("weightSuggestionBox", weightUomList);

		List<Object> itemCategoryList = new ArrayList<Object>();
		itemCategoryList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getItemCategoryRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemCategorySuggestionBox", itemCategoryList);

		List<Object> customerReceiptList = new ArrayList<Object>();
		customerReceiptList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getCustomerReceiptHeaderRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("customerReceiptNumberSuggestionBox", customerReceiptList);

		List<Object> customerReferenceList = new ArrayList<Object>();
		customerReferenceList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getCustomerReceiptProductDetailsRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("referenceNumberSuggestionBox", customerReferenceList);

		// List<Object> manualNumberList = new ArrayList<Object>();
		// manualNumberList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
		// .getCustomerReceiptHeaderRecord());
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("mannualReceiptNumberSuggestionBox", customerReceiptList);
		return null;
	}

	public List<Object> getSuggestedItemCategoryBasedOnLevels(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				ItemCategoryDVO itemCategoryDVO = new ItemCategoryDVO();
				itemCategoryDVO.setName(query);
				itemCategoryDVO.setActive(true);
				List<Object> list = new PurchaseOrderBD().getSuggestedItemCategoryBasedOnLevels(itemCategoryDVO);
				myLog.debug(" getSuggestedItemCategoryBasedOnLevels :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemCategorySuggestionBox", list);
				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedItemList(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		Long itemCategoryId = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getItemCategoryRecord().getId();
		myLog.debug("item category id:::" + itemCategoryId);
		if (itemCategoryId != null) {
			if (query != null) {
				try {
					ItemDVO itemRecord = new ItemDVO();
					itemRecord.setActive(true);
					itemRecord.setName(query);
					itemRecord.getApplicationFlags().getApplicationFlagMap()
							.put("APPROVED", CommonConstant.StatusCodes.APPROVED);
					itemRecord.getItemHierarchyRecord().getItemCategoryRecord().setId(itemCategoryId);
					List<Object> list = new PurchaseOrderBD().getSuggestedItemsListBasedOnItemCategory(itemRecord);

					FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemSuggestionBox", list);
					return list;
				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);

				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("item_category_null"));
		}
		return null;
	}

	public List<Object> getSuggestedQuantityUomList(String query) {
		if (query != null) {
			try {
				UomDVO uomDVO = new UomDVO();
				uomDVO.setName(query);
				uomDVO.getUomType().setSequenceNumber(CommonConstant.ParameterSequenceNumber.ONE);
				uomDVO.setActive(true);
				List<Object> uomList = new PurchaseOrderBD().getSuggestedUomList(uomDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("quantitySuggestionBox", uomList);
				return uomList;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedWeightUomList(String query) {
		if (query != null) {
			try {
				UomDVO uomDVO = new UomDVO();
				uomDVO.setName(query);
				uomDVO.setActive(true);
				uomDVO.getUomType().setSequenceNumber(CommonConstant.ParameterSequenceNumber.TWO);
				List<Object> uomList = new PurchaseOrderBD().getSuggestedUomList(uomDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("weightSuggestionBox", uomList);
				return uomList;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public void issueItemCodeSelected(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In InvIssueItemAddEditBB ::  "
				+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord().getCode());
		myLog.debug("quantity uom code::"
				+ purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
						.getQuantityUomRecord().getCode());

		Integer issueTypeId = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getIssueTypeRecord().getParameterID();

		if (issueTypeId != null && issueTypeId.equals(3)) {
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setItemCategoryRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getItemCategoryRecord());
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setItemRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getItemRecord());

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setQuantityUomRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getReceiptQuantityUom());

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setQuantity(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getReceiptQuantity());

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setWeightUomRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getReceiptWeightUom());

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setWeight(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
							.getCustomerReceiptProductDetailsRecord().getReceiptNetWeight());

		} else {

			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setQuantityUomRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
							.getQuantityUomRecord());
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setWeightUomRecord(
					purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
							.getWeightUomRecord());
		}

		try {
			purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
					.getItemPropertiesCombinationHeaderRecord().setItemPropertiesCombinationDetailsList(null);
			ItemDVO itemDVO = new PurchaseOrderBD().getItemPropetyListBasedOnItem(purchaseOrderProductDetailsRecord
					.getPurchaseOrderIssueItemMappingRecord().getItemRecord());

			myLog.debug(" > itemCodeSelected :: size is "
					+ itemDVO.getItemPropertiesCombinationHeaderRecord().getItemPropertiesCombinationDetailsList()
							.size());

			purchaseOrderProductDetailsRecord
					.getPurchaseOrderIssueItemMappingRecord()
					.getItemRecord()
					.getItemPropertiesCombinationHeaderRecord()
					.setItemPropertiesCombinationDetailsList(
							itemDVO.getItemPropertiesCombinationHeaderRecord()
									.getItemPropertiesCombinationDetailsList());

			List<Object> itemCodeList = new ArrayList<Object>();
			itemCodeList
					.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("itemSuggestionBox", itemCodeList);

			List<Object> quantityUomList = new ArrayList<Object>();
			quantityUomList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
					.getQuantityUomRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("quantitySuggestionBox", quantityUomList);

			List<Object> weightUomList = new ArrayList<Object>();
			weightUomList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
					.getWeightUomRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("weightSuggestionBox", weightUomList);

			List<Object> itemCategoryList = new ArrayList<Object>();
			itemCategoryList.add(purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
					.getItemCategoryRecord());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("itemCategorySuggestionBox", itemCategoryList);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void customerReceiptNumberSelected(AjaxBehaviorEvent event) {
		purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setCustomerReceiptTypeRecord(
				purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
						.getCustomerReceiptHeaderRecord().getCustomerReceiptType());
	}

	public void itemCategorySelected(AjaxBehaviorEvent event) {
		purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().getItemRecord()
				.getItemPropertiesCombinationHeaderRecord().setItemPropertiesCombinationDetailsList(null);
		purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord().setItemRecord(null);

	}

	public List<Object> getIssueInventoryLocationRecord(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());
		String issueLocationCode = purchaseOrderOpr.getPurchaseOrderRecord().getRaisedLocationRecord().getCode();
		if (query != null) {
			if (issueLocationCode != null) {
				try {
					myLog.debug("location code:::" + issueLocationCode);
					InvInventoryLocationDVO invInventoryLocationDVO = new InvInventoryLocationDVO();
					invInventoryLocationDVO.getLocationRecord().setCode(issueLocationCode);
					invInventoryLocationDVO.setName(query);
					invInventoryLocationDVO.setActive(true);
					List<Object> list = new PurchaseOrderBD().getIssueInventoryLocationRecord(invInventoryLocationDVO);
					myLog.debug(" getIssueInventoryLocationRecord :: list size" + list.size());
					FacesContext.getCurrentInstance().getViewRoot().getViewMap()
							.put("issueInventoryLocationSuggestionBox", list);
					return list;
				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);

				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			} else {
				addToErrorList(propertiesReader.getValueOfKey("issue_location_null"));
			}
		}
		return null;
	}

	public void executeDeleteIssueItem(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PurchaseOrderDefinitionAddEditBB:: executeDeleteIssueItem::");
		if (validateDeleteIssueItem()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderProductDetailsRecord.setPurchaseOrderRecord(purchaseOrderOpr.getPurchaseOrderRecord());
				PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
						.executeDeleteIssueItem(purchaseOrderProductDetailsRecord);
				purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().getAuditAttributes());
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("issue_item_delete_success"));
				getIssueOfItemDetails();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	private boolean validateDeleteIssueItem() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		ArrayList<PurchaseOrderIssueItemMappingDVO> purchaseOrderIssueItemMappingList = purchaseOrderProductDetailsRecord
				.getPurchaseOrderIssueItemMappingList();

		if (!purchaseOrderIssueItemMappingList.isEmpty()) {
			boolean recordSelected = false;
			for (PurchaseOrderIssueItemMappingDVO invItemIssueTxnDetailsRecord : purchaseOrderIssueItemMappingList) {
				if (invItemIssueTxnDetailsRecord.getOperationalAttributes().getRecordSelected()) {
					recordSelected = true;
					break;
				}
			}

			if (!recordSelected) {
				addToErrorList(propertiesReader.getValueOfKey("no_record_selected"));
			}
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	private void executeConfirmItemIssue() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Purchase Order Definition Add Edit BB :: deleteProductItemDetails starts ");

		try {

			PurchaseOrderHeaderDVO purchaseOrderRecord = new PurchaseOrderHeaderDVO();

			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			purchaseOrderRecord.setId(purchaseOrderOpr.getPurchaseOrderRecord().getId());

			purchaseOrderRecord.getAuditAttributes().setLastModifiedDate(
					purchaseOrderOpr.getPurchaseOrderRecord().getAuditAttributes().getLastModifiedDate());

			purchaseOrderRecord.setUserLogin(userLogin);

			purchaseOrderRecord = new PurchaseOrderBD().executeConfirmItemIssue(purchaseOrderRecord);

			purchaseOrderOpr.getPurchaseOrderRecord().getAuditAttributes()
					.setLastModifiedDate(purchaseOrderRecord.getAuditAttributes().getLastModifiedDate());
			// retrieveData();
			getPurchaseOrderHeaderDetails();
			populateData();

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("purchase_order_deleted_success"));

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public List<Object> getSuggestedReceiptNumberList(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				CustomerReceiptHeaderDVO customerReceiptHeaderDVO = new CustomerReceiptHeaderDVO();
				customerReceiptHeaderDVO.setCustomerReceiptNumber(query);
				customerReceiptHeaderDVO.getApplicationFlags().getApplicationFlagMap()
						.put("CUSTOMER_RECEIPT_TYPE", CommonConstant.ParameterSequenceNumber.ONE);
				customerReceiptHeaderDVO.getCustomerReceiptStatus().setCode(CommonConstant.StatusCodes.CONFIRMED);
				List<Object> list = new PurchaseOrderBD()
						.getSuggestedCustomerReceiptNumberList(customerReceiptHeaderDVO);
				myLog.debug(" getSuggestedReceiptNumberList :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("mannualReceiptNumberSuggestionBox", list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("customerReceiptNumberSuggestionBox", list);
				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedMannualNumberList(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (query != null) {
			try {
				CustomerReceiptHeaderDVO customerReceiptHeaderDVO = new CustomerReceiptHeaderDVO();
				customerReceiptHeaderDVO.setManualDocumentNumber(query);
				customerReceiptHeaderDVO.getApplicationFlags().getApplicationFlagMap()
						.put("CUSTOMER_RECEIPT_TYPE", CommonConstant.ParameterSequenceNumber.ONE);
				customerReceiptHeaderDVO.getCustomerReceiptStatus().setCode(CommonConstant.StatusCodes.CONFIRMED);
				List<Object> list = new PurchaseOrderBD()
						.getSuggestedCustomerReceiptNumberList(customerReceiptHeaderDVO);
				myLog.debug(" getSuggestedMannualNumberList :: list size" + list.size());
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("mannualReceiptNumberSuggestionBox", list);
				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("customerReceiptNumberSuggestionBox", list);
				return list;
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedCustomerPieceRefNumberList(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setErrorList(new ArrayList<String>());
		Long customerReceiptHeaderId = purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
				.getCustomerReceiptHeaderRecord().getId();

		if (customerReceiptHeaderId != null) {
			if (query != null) {
				try {
					CustomerReceiptProductDetailsDVO customerReceiptProductDetailsDVO = new CustomerReceiptProductDetailsDVO();
					customerReceiptProductDetailsDVO.getCustomerReceiptHeader().setId(customerReceiptHeaderId);
					customerReceiptProductDetailsDVO.setCustomerPieceReferenceNumber(query);
					customerReceiptProductDetailsDVO.getLocationRecord().setCode(
							purchaseOrderOpr.getPurchaseOrderRecord().getRaisedLocationRecord().getCode());
					customerReceiptProductDetailsDVO.getInventoryLocationRecord().setId(
							purchaseOrderProductDetailsRecord.getPurchaseOrderIssueItemMappingRecord()
									.getIssueInventoryLocationRecord().getId());
					customerReceiptProductDetailsDVO.getApplicationFlags().getApplicationFlagMap()
							.put("STATUS_AVAILABLE", "STATUS_AVAILABLE");
					customerReceiptProductDetailsDVO.getApplicationFlags().getApplicationFlagMap()
							.put("RECEIPT_TYPE", CommonConstant.ParameterSequenceNumber.SIX);
					List<Object> list = new PurchaseOrderBD()
							.getSuggestedCustomerPieceRefNumberList(customerReceiptProductDetailsDVO);
					myLog.debug(" getSuggestedCustomerPieceRefNumberList :: list size" + list.size());
					FacesContext.getCurrentInstance().getViewRoot().getViewMap()
							.put("referenceNumberSuggestionBox", list);
					return list;
				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);

				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("customer_receipt_null"));
		}
		return null;
	}

	public String openIssueTagToVendorDialog() {
		getIssueTagToVendorDetails();
		return null;
	}

	public String getIssueTagToVendorDetails() {

		try {
			purchaseOrderProductDetailsRecord.setPurchaseOrderRecord(purchaseOrderOpr.getPurchaseOrderRecord());
			purchaseOrderProductDetailsRecord.setPurchaseOrderVendorTagIssueDetailsList(null);
			PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
					.getIssueTagToVendorDetails(purchaseOrderProductDetailsRecord);
			purchaseOrderProductDetailsRecord
					.setPurchaseOrderVendorTagIssueDetailsList(purchaseOrderProductDetailsRecordRet
							.getPurchaseOrderVendorTagIssueDetailsList());

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);

		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		disableForIssueTags = false;
		if (!purchaseOrderProductDetailsRecord.getPurchaseOrderVendorTagIssueDetailsList().isEmpty()
				&& purchaseOrderProductDetailsRecord.getPurchaseOrderVendorTagIssueDetailsList().get(0).getIsConfirm()) {
			disableForIssueTags = true;
		}

		return null;
	}

	public void executeIssueTagToVendor(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PurchaseOrderDefinitionAddEditBB:: executeIssueTagToVendor ::");
		if (validateIssueTagToVendor()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderProductDetailsRecord.setPurchaseOrderRecord(purchaseOrderOpr.getPurchaseOrderRecord());
				PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
						.executeIssueTagToVendor(purchaseOrderProductDetailsRecord);

				purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.setPurchaseOrderVendorTagIssueDetailsRecord(null);

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("issue_to_vendor_success"));
				getIssueTagToVendorDetails();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	private boolean validateIssueTagToVendor() {
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		String tagNumber = purchaseOrderProductDetailsRecord.getPurchaseOrderVendorTagIssueDetailsRecord()
				.getTagNumber();
		Integer tagIssueType = purchaseOrderProductDetailsRecord.getPurchaseOrderVendorTagIssueDetailsRecord()
				.getTagIssueType().getParameterID();
		Integer tagIssueTypeSeqNo = null;

		if (!validator.validateNull(tagNumber))
			addToErrorList(propertiesReader.getValueOfKey("tag_number_null"));

		if (!validator.validateIntegerObjectNull(tagIssueType))
			addToErrorList(propertiesReader.getValueOfKey("tag_issue_type_null"));
		else {
			ArrayList<Object> poTagTypeList = allOptions.getAllOptionsValues().get("poTagTypeList");

			for (Object object : poTagTypeList) {
				Parameter parameter = (Parameter) object;
				if (tagIssueType.equals(parameter.getParameterID())) {
					tagIssueTypeSeqNo = parameter.getSequenceNumber();
					break;
				}
			}
		}

		// Validate only for Issue Type = Sample

		if (CommonConstant.ParameterSequenceNumber.TWO.equals(tagIssueTypeSeqNo)) {

			if (!purchaseOrderProductDetailsRecord.getPurchaseOrderVendorTagIssueDetailsList().isEmpty())
				addToErrorList(propertiesReader.getValueOfKey("tag_issue_more_than_one"));

			// if (purchaseOrderProductDetailsRecord.getQuantityOrdered() != 1)
			// addToErrorList(propertiesReader.getValueOfKey("po_quantity_not_equal_to_one"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		return validateFlag;
	}

	public void executeDeleteIssueTagToVendor(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PurchaseOrderDefinitionAddEditBB:: executeDeleteIssueTagToVendor ::");
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		int counter = 0;

		for (PurchaseOrderVendorTagIssueDetailsDVO purchaseOrderVendorTagIssueDetailsDVO : purchaseOrderProductDetailsRecord
				.getPurchaseOrderVendorTagIssueDetailsList()) {
			if (purchaseOrderVendorTagIssueDetailsDVO.getOperationalAttributes().getRecordSelected())
				counter++;
		}

		if (counter == 0)
			addToErrorList(propertiesReader.getValueOfKey("no_record_selected"));

		if (getErrorList().isEmpty()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				purchaseOrderOpr.getPurchaseOrderRecord().setUserLogin(userLogin);
				purchaseOrderProductDetailsRecord.setPurchaseOrderRecord(purchaseOrderOpr.getPurchaseOrderRecord());
				PurchaseOrderProductDetailsDVO purchaseOrderProductDetailsRecordRet = new PurchaseOrderBD()
						.executeDeleteIssueTagToVendor(purchaseOrderProductDetailsRecord);

				purchaseOrderOpr.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());
				purchaseOrderProductDetailsRecord.getPurchaseOrderRecord().setAuditAttributes(
						purchaseOrderProductDetailsRecordRet.getPurchaseOrderRecord().getAuditAttributes());

				// PropertiesReader propertiesReader = new
				// PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("issue_to_vendor_delete_success"));
				getIssueTagToVendorDetails();

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}
}
