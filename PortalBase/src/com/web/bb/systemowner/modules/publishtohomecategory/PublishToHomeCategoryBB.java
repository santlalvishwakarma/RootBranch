package com.web.bb.systemowner.modules.publishtohomecategory;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.publishtohomecategory.PublishToHomeCategoryBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.PublishToHomeCategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.PublishToHomeCategoryDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.jsf.converters.BaseDVOConverter;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class PublishToHomeCategoryBB extends BackingBean {

	private static final long serialVersionUID = 921981743859749987L;
	private String propertiesLocation = "com/web/bb/systemowner/modules/publishtohomecategory/publishtohomecategory";
	private PublishToHomeCategoryOpr publishToHomeCategoryOpr;
	private List<Object> hierarchyListForAutoSuggest;
	private List<CategoryDVO> categoryListForAutoSuggest;
	private transient BaseDVOConverter baseDVOConverter;

	public PublishToHomeCategoryOpr getPublishToHomeCategoryOpr() {
		if (publishToHomeCategoryOpr == null) {
			publishToHomeCategoryOpr = new PublishToHomeCategoryOpr();
		}
		return publishToHomeCategoryOpr;
	}

	public void setPublishToHomeCategoryOpr(PublishToHomeCategoryOpr publishToHomeCategoryOpr) {
		this.publishToHomeCategoryOpr = publishToHomeCategoryOpr;
	}

	public List<Object> getHierarchyListForAutoSuggest() {
		if (hierarchyListForAutoSuggest == null) {
			hierarchyListForAutoSuggest = new ArrayList<Object>();
		}
		return hierarchyListForAutoSuggest;
	}

	public void setHierarchyListForAutoSuggest(List<Object> hierarchyListForAutoSuggest) {
		this.hierarchyListForAutoSuggest = hierarchyListForAutoSuggest;
	}

	public List<CategoryDVO> getCategoryListForAutoSuggest() {
		if (categoryListForAutoSuggest == null) {
			categoryListForAutoSuggest = new ArrayList<CategoryDVO>();
		}
		return categoryListForAutoSuggest;
	}

	public void setCategoryListForAutoSuggest(List<CategoryDVO> categoryListForAutoSuggest) {
		this.categoryListForAutoSuggest = categoryListForAutoSuggest;
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

	public void executeCategoryPublishAddRow(ActionEvent event) {
		getPublishToHomeCategoryOpr().getPublishToHomeCategoryList().add(new PublishToHomeCategoryDVO());
	}

	@Override
	public void executeSearch(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSearch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveData() {
		try {
			hierarchyListForAutoSuggest = new PublishToHomeCategoryBF().getSuggestedHierarchies(new HierarchyDVO());

			categoryListForAutoSuggest = new PublishToHomeCategoryBF().getAllCategories();

			getPublishToHomeCategoryOpr().setPublishToHomeCategoryList(null);

			publishToHomeCategoryOpr = new PublishToHomeCategoryBF().getPublishToHomeCategories();

			populateSuggestionBox();
		} catch (FrameworkException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private void populateSuggestionBox() {
		if (!publishToHomeCategoryOpr.getPublishToHomeCategoryList().isEmpty()) {
			List<Object> hierarchyList = new ArrayList<Object>();

			for (PublishToHomeCategoryDVO publishToHomeCategoryDVO : publishToHomeCategoryOpr
					.getPublishToHomeCategoryList()) {
				hierarchyList.add(publishToHomeCategoryDVO.getHierarchyRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("categoryHierarchyCodeAutoComplete", hierarchyList);

			List<Object> categoryLevel1List = new ArrayList<Object>();
			for (PublishToHomeCategoryDVO publishToHomeCategoryDVO : publishToHomeCategoryOpr
					.getPublishToHomeCategoryList()) {
				categoryLevel1List.add(publishToHomeCategoryDVO.getCategoryLevelOneRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productCategoryLevel1AutoComplete", categoryLevel1List);

			List<Object> categoryLevel2List = new ArrayList<Object>();
			for (PublishToHomeCategoryDVO publishToHomeCategoryDVO : publishToHomeCategoryOpr
					.getPublishToHomeCategoryList()) {
				categoryLevel2List.add(publishToHomeCategoryDVO.getCategoryLevelTwoRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productCategoryLevel2AutoComplete", categoryLevel2List);

			List<Object> categoryLevel3List = new ArrayList<Object>();
			for (PublishToHomeCategoryDVO publishToHomeCategoryDVO : publishToHomeCategoryOpr
					.getPublishToHomeCategoryList()) {
				categoryLevel3List.add(publishToHomeCategoryDVO.getCategoryLevelThreeRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productCategoryLevel3AutoComplete", categoryLevel3List);

			List<Object> categoryLevel4List = new ArrayList<Object>();
			for (PublishToHomeCategoryDVO publishToHomeCategoryDVO : publishToHomeCategoryOpr
					.getPublishToHomeCategoryList()) {
				categoryLevel4List.add(publishToHomeCategoryDVO.getCategoryLevelFourRecord());
			}
			FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.put("productCategoryLevel4AutoComplete", categoryLevel4List);
		}
	}

	@Override
	public void executeAddRow(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public OptionsDVO getAllOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
		// TODO Auto-generated method stub

	}

	public List<Object> getSuggestedHierarchiesForCode(String query) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: getSuggestedHierarchiesForCode starts ");
		ArrayList<Object> productHierarchyList = new ArrayList<Object>();
		if (query != null) {
			query = query.toUpperCase();
			for (Object object : hierarchyListForAutoSuggest) {
				HierarchyDVO productHierarchyRecord = (HierarchyDVO) object;
				String code = productHierarchyRecord.getCode();
				myLog.debug("hierarchy id:: " + productHierarchyRecord.getId());

				if (code.toUpperCase().startsWith(query)) {
					productHierarchyList.add(productHierarchyRecord);
				}
			}
		}

		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.put("categoryHierarchyCodeAutoComplete", hierarchyListForAutoSuggest);

		myLog.debug("filter size::" + productHierarchyList.size());
		return productHierarchyList;
	}

	public void hierarchyChanged(PublishToHomeCategoryDVO publishToHomeCategoryDVO) {
		publishToHomeCategoryDVO.setCategoryLevelOneRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelTwoRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelThreeRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelFourRecord(null);
	}

	public void categoryLevel1Changed(PublishToHomeCategoryDVO publishToHomeCategoryDVO) {
		publishToHomeCategoryDVO.setCategoryLevelTwoRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelThreeRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelFourRecord(null);
	}

	public void categoryLevel2Changed(PublishToHomeCategoryDVO publishToHomeCategoryDVO) {
		publishToHomeCategoryDVO.setCategoryLevelThreeRecord(null);
		publishToHomeCategoryDVO.setCategoryLevelFourRecord(null);
	}

	public void categoryLevel3Changed(PublishToHomeCategoryDVO publishToHomeCategoryDVO) {
		publishToHomeCategoryDVO.setCategoryLevelFourRecord(null);
	}

	public void executeSavePublishCategory(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: executeSaveHierarchyMapping starts ");

		if (validateSavePublishCategory()) {
			try {
				String userLogin = getUserLogin(FacesContext.getCurrentInstance().getExternalContext());
				publishToHomeCategoryOpr.getPublishToHomeCategoryRecord().setUserLogin(userLogin);

				PublishToHomeCategoryOpr publishToHomeCategoryOprRecd = new PublishToHomeCategoryBF()
						.executeSavePublishCategory(publishToHomeCategoryOpr);

				publishToHomeCategoryOpr.setPublishToHomeCategoryList(publishToHomeCategoryOprRecd
						.getPublishToHomeCategoryList());

				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("publish_category_save_success"));
				populateSuggestionBox();

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
		if (!publishToHomeCategoryOpr.getPublishToHomeCategoryList().isEmpty()) {
			for (int i = 0; i < publishToHomeCategoryOpr.getPublishToHomeCategoryList().size(); i++) {
				if (publishToHomeCategoryOpr.getPublishToHomeCategoryList().get(i).getOperationalAttributes()
						.getRecordDeleted() == null
						|| !publishToHomeCategoryOpr.getPublishToHomeCategoryList().get(i).getOperationalAttributes()
								.getRecordDeleted()) {

					if (!validator.validateLongObjectNull(publishToHomeCategoryOpr.getPublishToHomeCategoryList()
							.get(i).getHierarchyRecord().getId())) {
						validateFlag = false;
						addToErrorList(propertiesReader.getValueOfKey("hierarchy_is_missing") + (i + 1));
					}

					if (!validator.validateLongObjectNull(publishToHomeCategoryOpr.getPublishToHomeCategoryList()
							.get(i).getCategoryLevelOneRecord().getId())) {
						validateFlag = false;
						addToErrorList(propertiesReader.getValueOfKey("category_level_1_is_missing") + (i + 1));
					}

					if (!validator.validateIntegerObjectNull(publishToHomeCategoryOpr.getPublishToHomeCategoryList()
							.get(i).getPublishPosition())) {
						validateFlag = false;
						addToErrorList(propertiesReader.getValueOfKey("sequence_is_missing") + (i + 1));
					}

					if (validateFlag
							&& !publishToHomeCategoryOpr.getPublishToHomeCategoryList().get(i).getPublishPosition()
									.equals(j)) {
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

	public List<Object> getSuggestedCategoryLevel1BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition Add Edit BB :: getSuggestedCategoryLevel1BasedOnHierarchy starts ");
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		myLog.debug("hierarchy id::" + hierarchyId);
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (hierarchyId == null) {
			addToErrorList(propertiesReader.getValueOfKey("hierarchy_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(1);

				List<Object> categoryLevel1List = new PublishToHomeCategoryBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel1AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel1List == null || categoryLevel1List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_1_no_records"));
				}
				return categoryLevel1List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		return null;
	}

	public List<Object> getSuggestedCategoryLevel2BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category1Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_1_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(2);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);

				List<Object> categoryLevel2List = new PublishToHomeCategoryBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel2AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel2List == null || categoryLevel2List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_2_no_records"));
				}

				return categoryLevel2List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
		return null;
	}

	public List<Object> getSuggestedCategoryLevel3BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");
		Object category2Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category2Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category2Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_2_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}

		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(3);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);
				if (category2Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelTwoRecord().setId((Long) category2Id);

				List<Object> categoryLevel3List = new PublishToHomeCategoryBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel3AutoComplete", categoryListForAutoSuggest);

				if (categoryLevel3List == null || categoryLevel3List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_3_no_records"));
				}
				return categoryLevel3List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}

		addToErrorList(propertiesReader.getValueOfKey("category_level_3_no_records"));
		return null;
	}

	public List<Object> getSuggestedCategoryLevel4BasedOnHierarchy(String query) throws FrameworkException,
			BusinessException {
		Object hierarchyId = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("hierarchyId");
		Object category1Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category1Id");
		Object category2Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category2Id");
		Object category3Id = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes()
				.get("category3Id");

		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		if (category3Id == null) {
			addToErrorList(propertiesReader.getValueOfKey("category_level_3_id_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		} else {
			validateFlag = true;
		}
		if (query != null && validateFlag) {
			try {
				HierarchyCategoryMappingDVO hierarchyCategoryMappingDVO = new HierarchyCategoryMappingDVO();
				hierarchyCategoryMappingDVO.getCategoryRecord().setCode(query);
				hierarchyCategoryMappingDVO.getHierarchyRecord().setId((Long) hierarchyId);
				hierarchyCategoryMappingDVO.setCategoryLevel(4);
				if (category1Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelOneRecord().setId((Long) category1Id);
				if (category2Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelTwoRecord().setId((Long) category2Id);
				if (category3Id != null)
					hierarchyCategoryMappingDVO.getCategoryLevelThreeRecord().setId((Long) category3Id);

				List<Object> categoryLevel4List = new PublishToHomeCategoryBF()
						.getSuggestedCategoriesBasedOnCategoryAndLevel(hierarchyCategoryMappingDVO);

				FacesContext.getCurrentInstance().getViewRoot().getViewMap()
						.put("productCategoryLevel4AutoComplete", categoryListForAutoSuggest);
				if (categoryLevel4List == null || categoryLevel4List.isEmpty()) {
					addToErrorList(propertiesReader.getValueOfKey("category_level_4_no_records"));
				}
				return categoryLevel4List;

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}

		}
		addToErrorList(propertiesReader.getValueOfKey("category_level_4_no_records"));
		return null;
	}

}
