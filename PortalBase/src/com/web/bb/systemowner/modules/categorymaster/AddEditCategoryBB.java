package com.web.bb.systemowner.modules.categorymaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import com.web.bf.systemowner.modules.categorymaster.CategoryMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.CategoryLevelDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.util.File;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class AddEditCategoryBB extends BackingBean {

	private static final long serialVersionUID = -207056909000545895L;

	private String propertiesLocation = "/com/web/bb/systemowner/modules/categorymaster/category";
	private int activeTabIndex;

	private CategoryOpr addEditCategoryOpr;
	private transient BaseDVOConverter baseDVOConverter;

	public int getActiveTabIndex() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getActiveTabIndex: ");
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB) != null) {
			activeTabIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.ACTIVE_TAB);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(CommonConstant.ACTIVE_TAB);
		}
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" executeSave starts: ");
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		boolean validate = validateSave();

		if (validate) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditCategoryOpr.getCategoryRecord().setUserLogin(userLogin);
				addEditCategoryOpr = new CategoryMasterBF().executeSave(addEditCategoryOpr);
				setSuccessMsg(propertiesReader.getValueOfKey("category_save"));
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	@Override
	public boolean validateSave() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside validateSearch: ");

		boolean validate = true;

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		getErrorList().clear();
		setSuccessMsg("");

		boolean isCategoryName = validator.validateNull(addEditCategoryOpr.getCategoryRecord().getName());
		boolean isCategoryCode = validator.validateNull(addEditCategoryOpr.getCategoryRecord().getCode());

		if (!isCategoryCode) {
			addToErrorList(propertiesReader.getValueOfKey("category_code_null"));
			validate = false;
		}

		if (!isCategoryName) {
			addToErrorList(propertiesReader.getValueOfKey("category_name_null"));
			validate = false;
		}

		return validate;
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
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

	public String setCategoryForEdit() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" setCategoryForEdit starts: ");

		// if
		// (FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB)
		// != null) {
		// activeTabIndex = (Integer)
		// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
		// .get(CommonConstant.ACTIVE_TAB);
		// FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(CommonConstant.ACTIVE_TAB);
		// }

		// CategoryOpr editCategoryOpr = (CategoryOpr)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequestMap().get(CommonConstant.ACTIVE_TAB_OPR);
		// myLog.debug("addEditCategoryOpr: " + addEditCategoryOpr);
		// myLog.debug("editCategoryOpr: " + editCategoryOpr);
		//
		// addEditCategoryOpr.setCategoryRecord(editCategoryOpr.getCategoryRecord());
		// myLog.debug(" Category Id: " +
		// editCategoryOpr.getCategoryRecord().getId());
		// myLog.debug(" Category Code: " +
		// editCategoryOpr.getCategoryRecord().getCode());
		// myLog.debug(" Category Name: " +
		// editCategoryOpr.getCategoryRecord().getName());

		myLog.debug("My name is "
				+ FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("MY_NAME"));

		return null;
	}

	public CategoryOpr getAddEditCategoryOpr() {
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.ACTIVE_TAB_OPR)) {
			CategoryOpr selectedAddEditCategoryOpr = (CategoryOpr) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get(CommonConstant.ACTIVE_TAB_OPR);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.remove(CommonConstant.ACTIVE_TAB_OPR);
			addEditCategoryOpr.setCategoryRecord(selectedAddEditCategoryOpr.getSelectedCategoryRecord());
		}
		if (addEditCategoryOpr == null) {
			addEditCategoryOpr = new CategoryOpr();
		}
		return addEditCategoryOpr;
	}

	public void setAddEditCategoryOpr(CategoryOpr addEditCategoryOpr) {
		this.addEditCategoryOpr = addEditCategoryOpr;
	}

	public void handleFileUploadForCategoryImage(FileUploadEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside handleFileUploadForCategoryImage: ");

		String fileName = event.getFile().getFileName();
		myLog.debug("fileName " + fileName);
		String contextPath = "product/";

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean uploadFlag = false;

		File uploadFile = extractUploadedFileData(event);

		String code = addEditCategoryOpr.getCategoryRecord().getCode();
		code = code.replace(" ", "_");
		String zoomFileName = code + "_0_z." + uploadFile.getExtension();
		myLog.debug("zoom fileName " + uploadFile.getName());
		uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.ZOOM_IMAGE_WIDTH,
				CommonConstant.ImageAttributes.ZOOM_IMAGE_HEIGHT, contextPath);
		myLog.debug("uploadFlag zoom :: " + uploadFlag);

		if (uploadFlag) {

			// call upload for regular image
			String regularFileName = code + "_0_r." + uploadFile.getExtension();
			String regularFileUrl = contextPath + regularFileName;
			myLog.debug("regularFileUrl " + regularFileUrl);
			addEditCategoryOpr.getCategoryRecord().setImageUrl(regularFileUrl);
			uploadFile.setName(regularFileName);
			uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.IMAGE_WIDTH,
					CommonConstant.ImageAttributes.IMAGE_HEIGHT, contextPath);
			myLog.debug("uploadFlag regular :: " + uploadFlag);
			// end call upload for regular image

			if (uploadFlag) {
				// call upload for thumbnail image
				String thumbFileName = code + "_0_t." + uploadFile.getExtension();
				String thumbFileUrl = contextPath + thumbFileName;
				myLog.debug("thumbFileUrl " + thumbFileUrl);
				uploadFile.setName(thumbFileName);
				uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.THUMB_IMAGE_WIDTH,
						CommonConstant.ImageAttributes.THUMB_IMAGE_HEIGHT, contextPath);
				myLog.debug("uploadFlag thumbnail :: " + uploadFlag);
				// end call upload for thumbnail image

				if (uploadFlag) {
					// call upload for report image
					String reportFileName = code + "_0_rpt." + uploadFile.getExtension();
					String reportFileUrl = contextPath + reportFileName;
					myLog.debug("reportFileUrl " + reportFileUrl);
					uploadFile.setName(reportFileName);
					uploadFlag = uploadImage(uploadFile, CommonConstant.ImageAttributes.REPORT_IMAGE_WIDTH,
							CommonConstant.ImageAttributes.REPORT_IMAGE_HEIGHT, contextPath);
					myLog.debug("uploadFlag report :: " + uploadFlag);
					// end call upload for report image

					if (uploadFlag) {
						myLog.debug("All files uploaded successfully :::::::::: ");
						setSuccessMsg(propertiesReader.getValueOfKey("upload_successful"));
					} else {
						myLog.debug("ERROR uploading thumbnail :::::::::: ");
						addToErrorList(propertiesReader.getValueOfKey("upload_error"));
					}
				} else {
					myLog.debug("ERROR uploading thumbnail :::::::::: ");
					addToErrorList(propertiesReader.getValueOfKey("upload_error"));
				}
			} else {
				myLog.debug("ERROR uploading regular image :::::::::: ");
				addToErrorList(propertiesReader.getValueOfKey("upload_error"));
			}

		}
	}

	public void tabChanged(TabChangeEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" In tabChanged : activeTabIndex : " + activeTabIndex);

		Tab activeTab = event.getTab();
		String tabId = activeTab.getId();
		myLog.debug(" tabChanged : tab id : " + tabId);

		if (tabId.equals("searchListCategory")) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(CommonConstant.RE_INITIALIZE_OPR, new CategoryOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 0);
			// RequestContext.getCurrentInstance().execute("searchCustomerPurchaseBill();");

		} else if (tabId.equals("addeditCategory")) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(CommonConstant.ACTIVE_TAB_OPR, new CategoryOpr());
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(CommonConstant.ACTIVE_TAB, 1);
			// RequestContext.getCurrentInstance().execute("addEditCustomerPurchaseBill();");
		}
	}

	public void getMappedCategoryLevel(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside getMappedCategoryLevel : ");

		try {
			addEditCategoryOpr = new CategoryMasterBF().getMappedCategoryLevel(addEditCategoryOpr);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("categoryLevelMapping", addEditCategoryOpr.getCategoryRecord().getCategoryLevels());
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public List<CategoryLevelDVO> getSuggestedCategoryLevel(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside getSuggestedCategoryLevel : ");

		List<CategoryLevelDVO> newCategoryLevels = new ArrayList<CategoryLevelDVO>();
		if (query != null) {

			char[] queryCharacterArray = query.toCharArray();
			for (int i = 0; i < queryCharacterArray.length; i++) {
				if (!Character.isDigit(queryCharacterArray[i])) {
					return null;
				}
			}

			Integer inputLevel = Integer.parseInt(query);
			boolean isAlreadyMapped = false;
			boolean levelExceed = false;
			List<CategoryLevelDVO> categoryLevels = addEditCategoryOpr.getCategoryRecord().getCategoryLevels();

			for (CategoryLevelDVO categoryLevelRecord : categoryLevels) {

				Integer levelNo = categoryLevelRecord.getLevelNo();

				if (levelNo != null && levelNo.equals(inputLevel)) {
					isAlreadyMapped = true;
				}
			}
			if (inputLevel > 3) {
				levelExceed = true;
			}
			if (!levelExceed && !isAlreadyMapped) {
				CategoryLevelDVO categoryLevelRecord = new CategoryLevelDVO();
				categoryLevelRecord.setCode(addEditCategoryOpr.getCategoryRecord().getCode() + "-" + inputLevel);
				categoryLevelRecord.setLevelNo(inputLevel);
				categoryLevelRecord.setCategoryRecord(addEditCategoryOpr.getCategoryRecord());
				newCategoryLevels.add(categoryLevelRecord);
				categoryLevels.add(categoryLevelRecord);
			}
			myLog.debug("size::: " + categoryLevels.size());
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("categoryLevelMapping", categoryLevels);
		}

		return newCategoryLevels;
	}

	public void mapCategoryToLevels(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside mapCategoryToLevels : ");

		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			addEditCategoryOpr.getCategoryRecord().setUserLogin(userLogin);
			addEditCategoryOpr = new CategoryMasterBF().mapCategoryToLevels(addEditCategoryOpr);
			setSuccessMsg("Category mapped to level(s)");
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public ArrayList<Object> getSuggestedHierarchyMappingForCode(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getSuggestedHierarchyMappingForCode::: ");

		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());

		if (query == null || query.trim().length() == 0) {
			return null;
		}

		if (addEditCategoryOpr.getHierarchyCategoryMappingLevelNo() == null
				|| addEditCategoryOpr.getHierarchyCategoryMappingLevelNo() == 0) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_not_selected"));
			return null;
		}

		ArrayList<Object> hierarchyList = new ArrayList<Object>();

		try {
			hierarchyList = new CategoryMasterBF().getSuggestedHierarchyMappingForCode(addEditCategoryOpr, query);
			myLog.debug(" hierarchyList: " + hierarchyList);
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("categoryHierarchyMappingAutoComplete", hierarchyList);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}

		return hierarchyList;
	}

	public void getMappedHierarchyCategory(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getMappedHierarchyCategory::: ");
		addEditCategoryOpr.getCategoryRecord().getHierarchyCategoryMappingList().clear();

		try {
			addEditCategoryOpr = new CategoryMasterBF().getMappedHierarchyCategory(addEditCategoryOpr);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void openHierarchyCategoryMappingDialogBox(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside openHierarchyCategoryMappingDialogBox::: ");

		addEditCategoryOpr.getCategoryRecord().setHierarchyCategoryMappingList(null);
		addEditCategoryOpr.setMappedHierarchyCategoryMappingList(null);
		addEditCategoryOpr.setHierarchyCategoryMappingLevelNo(null);

		try {
			addEditCategoryOpr = new CategoryMasterBF().getMappedCategoryLevel(addEditCategoryOpr);
			List<CategoryLevelDVO> categoryLevels = addEditCategoryOpr.getCategoryRecord().getCategoryLevels();

			ArrayList<Object> levelsMapped = new ArrayList<Object>();

			int categoryLevelSize = categoryLevels != null ? categoryLevels.size() : 0;
			myLog.debug(" categoryLevelSize::: " + categoryLevelSize);

			for (int i = 0; i < categoryLevelSize; i++) {
				levelsMapped.add(categoryLevels.get(i).getLevelNo());
			}
			getAllOptions().getAllOptionsValues().put("categoryMappedLevelList", levelsMapped);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void setHierarchyCategoryMapping(SelectEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside setHierarchyCategoryMapping::: ");

		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());

		if (addEditCategoryOpr.getHierarchyCategoryMappingLevelNo() == 0) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_not_selected"));
			return;
		}

		addEditCategoryOpr.getMappedHierarchyCategoryMappingList().clear();

		Integer categoryLevel = addEditCategoryOpr.getHierarchyCategoryMappingLevelNo();

		HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = addEditCategoryOpr
				.getSelectedHierarchyCategoryMappingRecord();
		addEditCategoryOpr.getMappedHierarchyCategoryMappingList().add(hierarchyCategoryMappingRecord);
		addEditCategoryOpr.setSelectedHierarchyCategoryMappingRecord(null);

		if (categoryLevel == 1) {
			hierarchyCategoryMappingRecord.setCategoryLevelOneRecord(addEditCategoryOpr.getCategoryRecord());
		} else if (categoryLevel == 2) {
			hierarchyCategoryMappingRecord.setCategoryLevelTwoRecord(addEditCategoryOpr.getCategoryRecord());
		} else if (categoryLevel == 3) {
			hierarchyCategoryMappingRecord.setCategoryLevelThreeRecord(addEditCategoryOpr.getCategoryRecord());
		} else if (categoryLevel == 4) {
			hierarchyCategoryMappingRecord.setCategoryLevelFourRecord(addEditCategoryOpr.getCategoryRecord());
		}

	}

	public void mapHierarchyToCategory(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside mapHierarchyToCategory::: ");

		try {
			String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
			addEditCategoryOpr.getCategoryRecord().setUserLogin(userLogin);
			addEditCategoryOpr = new CategoryMasterBF().mapHierarchyToCategory(addEditCategoryOpr);

			addEditCategoryOpr.getMappedHierarchyCategoryMappingList().clear();
			addEditCategoryOpr.getCategoryRecord().getHierarchyCategoryMappingList().clear();
			addEditCategoryOpr = new CategoryMasterBF().getMappedHierarchyCategory(addEditCategoryOpr);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public String clearFields() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside clearFields::: ");
		allOptions.getAllOptionsValues().remove("categoryMappedLevelList");
		addEditCategoryOpr.getCategoryRecord().getHierarchyCategoryMappingList().clear();
		addEditCategoryOpr.getMappedHierarchyCategoryMappingList().clear();
		addEditCategoryOpr.setHierarchyCategoryMappingLevelNo(null);
		return null;
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

	public void opePublishToHomeDialog(ActionEvent event) throws BusinessException, FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside AddEditCategoryBB  opePublishToHomeDialog start::");

		CategoryOpr categoryOprRet = new CategoryMasterBF().getPublishToHomeCategoryList();

		addEditCategoryOpr.setPublishToHomeCategoryList(categoryOprRet.getPublishToHomeCategoryList());

		if (!addEditCategoryOpr.getPublishToHomeCategoryList().isEmpty()) {
			List<Object> categoryList = new ArrayList<Object>();

			for (CategoryDVO categoryDVO : addEditCategoryOpr.getPublishToHomeCategoryList()) {
				categoryList.add(categoryDVO.getPublishCategoryRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("categoryNameAutoComplete", categoryList);

		}
	}

	public void executeCategoryPublishAddRow(ActionEvent event) {
		addEditCategoryOpr.getPublishToHomeCategoryList().add(new CategoryDVO());
	}

	public List<Object> getSuggestedCategoryForName(String query) throws BusinessException, FrameworkException {
		List<Object> categoryList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();

			CategoryDVO categoryDVO = new CategoryDVO();
			categoryDVO.setName(query);
			categoryDVO.setActive(true);

			categoryList = new CategoryMasterBF().getSuggestedCategories(categoryDVO);

			FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("categoryNameAutoComplete", categoryList);

		}
		return categoryList;
	}

	public void executeSavePublishCategory(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveHierarchyMapping starts ");

		if (validateSavePublishCategory()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				addEditCategoryOpr.getCategoryRecord().setUserLogin(userLogin);

				CategoryOpr categoryOprRecd = new CategoryMasterBF().executeSavePublishCategory(addEditCategoryOpr);

				addEditCategoryOpr.setPublishToHomeCategoryList(categoryOprRecd.getPublishToHomeCategoryList());
				myLog.debug("last modified date::"
						+ categoryOprRecd.getCategoryRecord().getAuditAttributes().getLastModifiedDate());
				addEditCategoryOpr.getCategoryRecord().setAuditAttributes(
						categoryOprRecd.getCategoryRecord().getAuditAttributes());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("publish_category_save_success"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	private boolean validateSavePublishCategory() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside validateSavePublishCategory: ");

		boolean validateFlag = true;

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

		setErrorList(new ArrayList<String>());
		int j = 1;
		if (!addEditCategoryOpr.getPublishToHomeCategoryList().isEmpty()) {
			for (int i = 0; i < addEditCategoryOpr.getPublishToHomeCategoryList().size(); i++) {
				if (addEditCategoryOpr.getPublishToHomeCategoryList().get(i).getPublishCategoryRecord()
						.getOperationalAttributes().getRecordDeleted() == null
						|| !addEditCategoryOpr.getPublishToHomeCategoryList().get(i).getPublishCategoryRecord()
								.getOperationalAttributes().getRecordDeleted()) {

					if (!validator.validateLongObjectNull(addEditCategoryOpr.getPublishToHomeCategoryList().get(i)
							.getPublishCategoryRecord().getId())) {
						validateFlag = false;
						addToErrorList(propertiesReader.getValueOfKey("category_is_missing") + (i + 1));
					}

					if (!validator.validateIntegerObjectNull(addEditCategoryOpr.getPublishToHomeCategoryList().get(i)
							.getPublishCategoryRecord().getPublishPosition())) {
						validateFlag = false;
						addToErrorList(propertiesReader.getValueOfKey("sequence_is_missing") + (i + 1));
					}

					if (validateFlag
							&& !addEditCategoryOpr.getPublishToHomeCategoryList().get(i).getPublishCategoryRecord()
									.getPublishPosition().equals(j)) {
						addToErrorList(propertiesReader.getValueOfKey("publish_position_missing") + (i + 1)
								+ CommonConstant.BLANK_SPACE + ("should_be_in_sequence"));
						break;
					}
					j++;
				}
			}
		} else {
			addToErrorList(propertiesReader.getValueOfKey("publish_category_empty"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		return validateFlag;
	}
}
