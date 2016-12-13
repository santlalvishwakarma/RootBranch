package com.web.bb.retail.module.browseproducts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.primefaces.event.SlideEndEvent;

import com.web.bf.retail.modules.browseproducts.BrowseProductsBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.WebsiteDVO;
import com.web.common.dvo.opr.retail.BrowseProductsOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.deepcopy.DeepCopy;

public class BrowseProductsBB extends BackingBean {

	private static final long serialVersionUID = 8345657925407357751L;
	private String propertiesLocation = "com/web/bb/retail/module/browseproducts/browseproducts";
	private BrowseProductsOpr browseProductsOpr;
	private BrowseProductsOpr placeHolderBrowseProductsOpr;
	private Float basePriceFrom;
	private Float basePriceTo;
	private transient Integer numberOfRowsInPage = 12;
	private transient Integer totalNumberOfItems;
	private Integer gridColumnCount;
	private Integer scrollerPage;
	private Integer scrollerPageList;
	private String catalogCode;
	private String hierarchyName;
	private String categoryCodeLevel1;
	private String categoryCodeLevel2;
	private String categoryCodeLevel3;
	private String categoryCodeLevel4;
	private BrowseProductsOpr addToCartProductRecord;
	private BrowseProductsOpr browseProductsOprOrg;
	private boolean selectAll = true;
	private boolean filter14K;
	private boolean filter18K;
	private boolean filterVs;
	private boolean filterSi;
	private boolean catalog = true;
	private boolean timer = true;
	private transient HtmlPanelGroup htmlPanelGroup;
	private String browseTitle;
	private String filterBy;
	private String countryName;
	private boolean symbolFlag;
	private Float conversionRate;
	private String convertedCurrencySymbol;
	private HashMap<Long, CategoryDVO> selectedCategoryMap;
	private HashMap<Long, ArrayList<ProductSkuDVO>> categoryProductListMap;
	private BrowseProductsOpr priceFilterOpr;

	public HashMap<Long, CategoryDVO> getSelectedCategoryMap() {
		if (selectedCategoryMap == null) {
			selectedCategoryMap = new HashMap<Long, CategoryDVO>();
		}
		return selectedCategoryMap;
	}

	public void setSelectedCategoryMap(HashMap<Long, CategoryDVO> selectedCategoryMap) {
		this.selectedCategoryMap = selectedCategoryMap;
	}

	public HashMap<Long, ArrayList<ProductSkuDVO>> getCategoryProductListMap() {
		if (categoryProductListMap == null) {
			categoryProductListMap = new HashMap<Long, ArrayList<ProductSkuDVO>>();
		}
		return categoryProductListMap;
	}

	public void setCategoryProductListMap(HashMap<Long, ArrayList<ProductSkuDVO>> categoryProductListMap) {
		this.categoryProductListMap = categoryProductListMap;
	}

	public Float getConversionRate() {
		return conversionRate;
	}

	public String getConvertedCurrencySymbol() {
		return convertedCurrencySymbol;
	}

	public void setConvertedCurrencySymbol(String convertedCurrencySymbol) {
		this.convertedCurrencySymbol = convertedCurrencySymbol;
	}

	public void setConversionRate(Float conversionRate) {
		this.conversionRate = conversionRate;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public boolean isSymbolFlag() {
		return symbolFlag;
	}

	public void setSymbolFlag(boolean symbolFlag) {
		this.symbolFlag = symbolFlag;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public Float getBasePriceFrom() {
		// if
		// (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
		// .get(CommonConstant.DO_NOT_CLEAR_PRICE_FILTER) == null) {
		// basePriceFrom = Float.valueOf("0.0");
		// }
		return basePriceFrom;
	}

	public void setBasePriceFrom(Float basePriceFrom) {
		this.basePriceFrom = basePriceFrom;
	}

	public Float getBasePriceTo() {
		// if
		// (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
		// .get(CommonConstant.DO_NOT_CLEAR_PRICE_FILTER) == null) {
		// basePriceTo = Float.valueOf("99999.0");
		// }
		return basePriceTo;
	}

	public void setBasePriceTo(Float basePriceTo) {
		this.basePriceTo = basePriceTo;
	}

	public BrowseProductsOpr getBrowseProductsOprOrg() {
		if (browseProductsOprOrg == null) {
			browseProductsOprOrg = new BrowseProductsOpr();
		}
		return browseProductsOprOrg;
	}

	public void setBrowseProductsOprOrg(BrowseProductsOpr browseProductsOprOrg) {
		this.browseProductsOprOrg = browseProductsOprOrg;
	}

	public BrowseProductsOpr getPlaceHolderBrowseProductsOpr() {
		if (placeHolderBrowseProductsOpr == null) {
			placeHolderBrowseProductsOpr = new BrowseProductsOpr();
		}
		return placeHolderBrowseProductsOpr;
	}

	public void setPlaceHolderBrowseProductsOpr(BrowseProductsOpr placeHolderBrowseProductsOpr) {
		this.placeHolderBrowseProductsOpr = placeHolderBrowseProductsOpr;
	}

	// public void searchProductsOnHierarchy(ActionEvent ae) {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// myLog.debug("action Listener invoked");
	// myLog.debug("action fired by component with id " +
	// ae.getComponent().getId());
	// searchProducts();
	// }

	// private ArrayList<ProductDVO> searchProducts() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// ProductIntegrationOpr productIntegrationOpr = new
	// ProductIntegrationOpr();
	// if ((selectedCategory == null) || (selectedCategory.trim().length() ==
	// 0)) {
	// addToErrorList(new
	// PropertiesReader(propertiesLocation).getValueOfKey("choose_category_filter"));
	// } else {
	// String selectedMenu = selectedCategory;
	// myLog.debug("action fired by component with value " + selectedMenu);
	// String selectedMenuLabel = null;
	// if (selectedMenu != null) {
	// StringTokenizer stringTokenizer = new StringTokenizer(selectedMenu,
	// CommonConstant.MENU_ID_PREFIX);
	// int i = 0;
	// ProductIntegrationOpr searchIntegrationOpr = new ProductIntegrationOpr();
	// while (stringTokenizer.hasMoreTokens()) {
	// i++;
	// String selectedHierarchy = stringTokenizer.nextToken();
	// myLog.debug("selectedHierarchy " + selectedHierarchy);
	// switch (i) {
	// case 1:
	// searchIntegrationOpr.getProductRecord().getHierarchyLevelOne()
	// .setId(Long.valueOf(selectedHierarchy));
	// break;
	// case 2:
	// searchIntegrationOpr.getProductRecord().getHierarchyLevelTwo()
	// .setId(Long.valueOf(selectedHierarchy));
	// break;
	// case 3:
	// searchIntegrationOpr.getProductRecord().getHierarchyLevelThree()
	// .setId(Long.valueOf(selectedHierarchy));
	// break;
	// case 4:
	// searchIntegrationOpr.getProductRecord().getHierarchyLevelFour()
	// .setId(Long.valueOf(selectedHierarchy));
	// break;
	// }
	// }
	// searchIntegrationOpr.getProductRecord().setBasePriceFrom(
	// basePriceFrom == null ? Float.valueOf("0.0") : basePriceFrom);
	// searchIntegrationOpr.getProductRecord().setBasePriceTo(
	// basePriceTo == null ? Float.valueOf("0.0") : basePriceTo);
	//
	// try {
	// if (searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .get(CommonConstant.USE_EXCLUSIVITY_FILTER) == null) {
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.USE_EXCLUSIVITY_FILTER,
	// CommonConstant.USE_EXCLUSIVITY_FILTER);
	// searchIntegrationOpr
	// .getApplicationFlags()
	// .getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_USER_LOGIN,
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get(CommonConstant.LOGGED_USER_KEY));
	// String websiteURL = ((HttpServletRequest)
	// FacesContext.getCurrentInstance()
	// .getExternalContext().getRequest()).getServerName();
	// myLog.debug("websiteURL " + websiteURL);
	// CacheManager cacheManager = CacheManager.create();
	// Cache globalWebSiteCache =
	// cacheManager.getCache(CommonConstant.GLOBAL_WEBSITE_CACHE);
	// if (globalWebSiteCache != null) {
	// myLog.debug("got cache");
	// Element globalWebSiteElement = globalWebSiteCache
	// .get(CommonConstant.GLOBAL_WEBSITE_ELEMENT);
	// if (globalWebSiteElement != null) {
	// myLog.debug("got element");
	// HashMap<String, WebsiteDVO> websiteMap = (HashMap<String, WebsiteDVO>)
	// globalWebSiteElement
	// .getObjectValue();
	// myLog.debug("websiteMap " + websiteMap);
	// if (websiteMap != null) {
	// myLog.debug("got website");
	// WebsiteDVO websiteDVO = websiteMap.get(websiteURL);
	// if (websiteDVO != null) {
	// Long webSiteId = websiteDVO.getId();
	// myLog.debug("webSiteId " + webSiteId);
	// if (webSiteId != null) {
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_WEBSITE_ID, webSiteId);
	// }
	// }
	// }
	// }
	// }
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_COMPANY_ID, null);
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_RETAILER_GROUP_ID, null);
	// }
	// productIntegrationOpr = new
	// ProductIntegration().searchProductsOnHierarchy(searchIntegrationOpr);
	// if (productIntegrationOpr != null) {
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.PRODUCT_SEARCH_RESULTS,
	// productIntegrationOpr.getProductList());
	// } else {
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.PRODUCT_SEARCH_RESULTS, new ArrayList<ProductDVO>());
	// }
	// } catch (FrameworkException e) {
	// myLog.error("Exception occured " + e);
	// // handleException(e,
	// // CommonConstant.COMMON_MESSAGE_LOCATION);
	// } catch (BusinessException e) {
	// myLog.error("Exception occured " + e);
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.PRODUCT_SEARCH_RESULTS, new ArrayList<ProductDVO>());
	// // handleException(e,
	// // CommonConstant.COMMON_MESSAGE_LOCATION);
	// }
	// }
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.RETAIL_MENU_SELECTION_ID, selectedCategory);
	// myLog.debug("selectedCategory " + selectedCategory);
	// for (int i = 0; i <
	// allOptions.getAllOptionsValues().get("detailCategories").size(); i++) {
	// SelectItem selectItem = (SelectItem)
	// allOptions.getAllOptionsValues().get("detailCategories").get(i);
	// if (selectItem.getValue().equals(selectedCategory)) {
	// selectedMenuLabel = selectItem.getLabel();
	// break;
	// }
	// }
	// myLog.debug("selectedMenuLabel " + selectedMenuLabel);
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.RETAIL_MENU_SELECTION_LABEL, selectedMenuLabel);
	// }
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.DO_NOT_CLEAR_CATEGORY_FILTER,
	// CommonConstant.DO_NOT_CLEAR_CATEGORY_FILTER);
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.DO_NOT_CLEAR_PRICE_FILTER,
	// CommonConstant.DO_NOT_CLEAR_PRICE_FILTER);
	// return productIntegrationOpr.getProductList();
	// }

	public BrowseProductsOpr getPriceFilterOpr() {
		if (priceFilterOpr == null) {
			priceFilterOpr = new BrowseProductsOpr();
		}
		return priceFilterOpr;
	}

	public void setPriceFilterOpr(BrowseProductsOpr priceFilterOpr) {
		this.priceFilterOpr = priceFilterOpr;
	}

	public Integer getScrollerPage() {
		if (scrollerPage == null) {
			scrollerPage = 1;
		}
		return scrollerPage;
	}

	public void setScrollerPage(Integer scrollerPage) {
		this.scrollerPage = scrollerPage;
	}

	public Integer getScrollerPageList() {
		if (scrollerPageList == null) {
			scrollerPageList = 1;
		}
		return scrollerPageList;
	}

	public void setScrollerPageList(Integer scrollerPageList) {
		this.scrollerPageList = scrollerPageList;
	}

	public List getPagesToScroll() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		List list = new ArrayList();
		int productCount = browseProductsOpr.getProductSkuList().size();
		if (productCount <= numberOfRowsInPage) {
			SelectItem item = new SelectItem(Integer.valueOf(1));
			list.add(item);
		} else {
			int quotient = productCount / numberOfRowsInPage;
			int remainder = productCount % numberOfRowsInPage;
			if (remainder > 0) {
				quotient += 1;
			}
			for (int i = 1; i <= quotient; i++) {
				SelectItem item = new SelectItem(Integer.valueOf(i));
				list.add(item);
			}
		}

		return list;
	}

	public Integer getGridColumnCount() {
		if (browseProductsOpr != null) {
			if (browseProductsOpr.getProductSkuList() != null) {
				if (browseProductsOpr.getProductSkuList().size() < 3) {
					gridColumnCount = browseProductsOpr.getProductSkuList().size();
				}
			}
		} else {
			gridColumnCount = 3;
		}
		return gridColumnCount;
	}

	public void setGridColumnCount(Integer gridColumnCount) {
		this.gridColumnCount = gridColumnCount;
	}

	public Integer getNumberOfRowsInPage() {
		return numberOfRowsInPage;
	}

	public void setNumberOfRowsInPage(Integer numberOfRowsInPage) {
		this.numberOfRowsInPage = numberOfRowsInPage;
	}

	public Integer getTotalNumberOfItems() {
		return browseProductsOpr.getProductSkuList() == null ? 0 : browseProductsOpr.getProductSkuList().size();
	}

	public void setTotalNumberOfItems(Integer totalNumberOfItems) {
		this.totalNumberOfItems = totalNumberOfItems;
	}

	public String reRenderTable() {
		scrollerPage = null;
		return null;
	}

	public BrowseProductsOpr getBrowseProductsOpr() {
		if (browseProductsOpr == null) {
			browseProductsOpr = new BrowseProductsOpr();
		}

		return browseProductsOpr;
	}

	public void setBrowseProductsOpr(BrowseProductsOpr browseProductsOpr) {
		this.browseProductsOpr = browseProductsOpr;
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

	@Override
	public void editDetails() {

	}

	@Override
	public void executeSave(ActionEvent event) {

	}

	@Override
	public void executeSearch(ActionEvent event) {

	}

	@Override
	public boolean validateSave() {
		return false;
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	// THIS METHOD USED IN PRICE SLIDER TO FILTER PRODUCTS ON PRICE
	public String doProductSearchActionWrapper() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("basePriceFrom :: " + basePriceFrom);
		myLog.debug("basePriceTo :: " + basePriceTo);

		Float conversionRate = 0.0F;
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.Currency.CURRENCY_CONVERSION_RATE) != null
				&& (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.Currency.CURRENCY_CONVERSION_RATE) != 0.0F) {
			conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.Currency.CURRENCY_CONVERSION_RATE);
		}

		myLog.debug("originalBasePriceFrom===" + basePriceFrom);
		basePriceFrom = basePriceFrom / conversionRate;
		basePriceTo = basePriceTo / conversionRate;

		browseProductsOpr.getProductSkuRecord().setMinPrice(basePriceFrom);
		browseProductsOpr.getProductSkuRecord().setBasePriceFrom(basePriceFrom);
		browseProductsOpr.getProductSkuRecord().setMaxPrice(basePriceTo);
		browseProductsOpr.getProductSkuRecord().setBasePriceTo(basePriceTo);
		doSearch(browseProductsOpr);
		return null;
	}

	// THIS METHOD USED IN PRETTY CONFIG TO LOAD PRODUCTS BASED ON HIERARCHY
	// AND/OR CATEGORY
	public void doProductSearch() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("doProductSearch :: hierarchyName :: " + hierarchyName);

		// RESET PRICE RANGE FOR SESSION BEFORE SEARCH
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MinPrice");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MaxPrice");

		BrowseProductsOpr searchBrowseProductsOpr = new BrowseProductsOpr();
		myLog.debug("hierarchyname::::" + hierarchyName);
		myLog.debug("categoryCodeLevel1::::" + categoryCodeLevel1);
		myLog.debug("categoryCodeLevel2::::" + categoryCodeLevel2);
		myLog.debug("categoryCodeLevel3::::" + categoryCodeLevel3);
		myLog.debug("categoryCodeLevel4::::" + categoryCodeLevel4);

		searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getHierarchyRecord()
				.setName(hierarchyName);
		searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
				.setCode(categoryCodeLevel1);
		searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
				.setCode(categoryCodeLevel2);
		searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
				.setCode(categoryCodeLevel3);
		searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
				.setCode(categoryCodeLevel4);

		// MAKE A GETTER CALL LATER AFTER CODE CLEANUP
		if (browseProductsOpr == null) {
			browseProductsOpr = new BrowseProductsOpr();
		}

		selectedCategoryMap = new HashMap<Long, CategoryDVO>();
		categoryProductListMap = new HashMap<Long, ArrayList<ProductSkuDVO>>();

		browseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getHierarchyRecord()
				.setName(hierarchyName);
		browseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
				.setCode(categoryCodeLevel1);
		browseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
				.setCode(categoryCodeLevel2);
		browseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
				.setCode(categoryCodeLevel3);
		browseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
				.setCode(categoryCodeLevel4);

		// THIS ENTRY FOR PAGE DISPLAY NAME - FROM TOP TO BOTTOM
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequestMap()
				.put("product.categories",
						(hierarchyName == null ? "" : hierarchyName)
								+ (categoryCodeLevel1 == null ? "" : " | " + categoryCodeLevel1)
								+ (categoryCodeLevel2 == null ? "" : " | " + categoryCodeLevel2)
								+ (categoryCodeLevel3 == null ? "" : " | " + categoryCodeLevel3)
								+ (categoryCodeLevel4 == null ? "" : " | " + categoryCodeLevel4));

		// GET TEMP CATEGORY CODES FOR DISPLAY
		// ELSE IT MESSES WITH THE NAVIGATION ON FILTER
		String tempCategoryCodeLevel4 = categoryCodeLevel4;
		String tempCategoryCodeLevel3 = categoryCodeLevel3;
		String tempCategoryCodeLevel2 = categoryCodeLevel2;
		String tempCategoryCodeLevel1 = categoryCodeLevel1;

		if (tempCategoryCodeLevel4 == null) {
			tempCategoryCodeLevel4 = "";
		} else {
			// browseTitle = tempCategoryCodeLevel4;
			browseTitle = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getCategoryLevelFourRecord().getName();
		}

		myLog.debug("4:" + browseTitle);
		if (tempCategoryCodeLevel3 == null) {
			tempCategoryCodeLevel3 = "";
		} else {
			if (browseTitle == null) {
				browseTitle = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
						.getCategoryLevelThreeRecord().getName();
			}
			if (tempCategoryCodeLevel4 != "") {
				tempCategoryCodeLevel3 = " | " + tempCategoryCodeLevel3;
			}
		}
		myLog.debug("3:" + browseTitle);

		if (tempCategoryCodeLevel2 == null) {
			tempCategoryCodeLevel2 = "";
		} else {
			if (browseTitle == null) {
				browseTitle = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
						.getCategoryLevelTwoRecord().getName();
			}
			if (tempCategoryCodeLevel3 != "") {
				tempCategoryCodeLevel2 = " | " + tempCategoryCodeLevel2;
			}
		}
		myLog.debug("2:" + browseTitle);

		if (tempCategoryCodeLevel1 == null) {
			tempCategoryCodeLevel1 = "";
		} else {
			if (browseTitle == null) {
				browseTitle = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
						.getCategoryLevelOneRecord().getName();
			}
			if (tempCategoryCodeLevel2 != "") {
				tempCategoryCodeLevel1 = " | " + tempCategoryCodeLevel1;
			}
		}
		myLog.debug("1:" + browseTitle);

		// THIS ENTRY FOR PAGE TITLE IN REVERSE ORDER - FROM BOTTOM TO
		// TOP
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequestMap()
				.put("product.categories.reverse",
						tempCategoryCodeLevel4 + tempCategoryCodeLevel3 + tempCategoryCodeLevel2
								+ tempCategoryCodeLevel1 + (hierarchyName == null ? "" : " | " + hierarchyName));

		myLog.debug("before doSearch :: categoryCodeLevel1 :: " + categoryCodeLevel1);

		doSearch(searchBrowseProductsOpr);

		myLog.debug("after doSearch :: categoryCodeLevel1 :: " + categoryCodeLevel1);
		// if
		// (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .get(CommonConstant.CURRENCY_CONVERSION_RATE) != null
		// && (Float)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .get(CommonConstant.CURRENCY_CONVERSION_RATE) != 0.0F) {
		// myLog.debug("Inside conversion rate not null");
		// conversionRate = (Float)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .get(CommonConstant.CURRENCY_CONVERSION_RATE);
		// }

		// this is done to get data in request map in parent backing bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("catalog.page.code", CommonConstant.BROWSE_PRODUCTS_PAGE);

		placeHolderBrowseProductsOpr = (BrowseProductsOpr) DeepCopy.copy(browseProductsOpr);
		myLog.debug("Getting min price::" + placeHolderBrowseProductsOpr.getProductSkuRecord().getMinPrice());
		myLog.debug("Getting min price::" + placeHolderBrowseProductsOpr.getProductSkuRecord().getMaxPrice());
		// if (conversionRate != null && !conversionRate.equals(0.0F)) {
		// // GEOPLUGIN- for conversion of max and min price range
		// Float convertedMaxPrice = 0.0F;
		//
		// Float convertedMinPrice = 0.0F;
		// if ((placeHolderBrowseProductsOpr.getProductSkuRecord().getMaxPrice()
		// != null &&
		// !placeHolderBrowseProductsOpr
		// .getProductSkuRecord().getMaxPrice().equals(0.0))
		// && (placeHolderBrowseProductsOpr.getProductSkuRecord().getMinPrice()
		// != null && !placeHolderBrowseProductsOpr
		// .getProductSkuRecord().getMinPrice().equals(0.0))) {
		// convertedMaxPrice =
		// placeHolderBrowseProductsOpr.getProductSkuRecord().getMaxPrice();
		// myLog.debug("Getting max price" + convertedMaxPrice);
		// convertedMaxPrice = convertedMaxPrice * conversionRate;
		// myLog.debug("Getting max price after multiply::" +
		// convertedMaxPrice);
		// placeHolderBrowseProductsOpr.getProductSkuRecord().setMaxPrice(convertedMaxPrice);
		//
		// convertedMinPrice =
		// placeHolderBrowseProductsOpr.getProductSkuRecord().getMinPrice();
		// convertedMinPrice = convertedMinPrice * conversionRate;
		// placeHolderBrowseProductsOpr.getProductSkuRecord().setMinPrice(convertedMinPrice);
		// }
		// }
	}

	// THIS METHOD IS THE ACTUAL SEARCH METHOD THAT PERFORMS ALL THE SEARCHES
	// WE JUST TO ENSURE THAT THE RIGHT PARAMS ARE PASSED TO THIS
	public void doSearch(BrowseProductsOpr searchBrowseProductsOpr) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside doSearch::: ");
		try {

			searchBrowseProductsOpr.getProductSkuRecord().setUserLogin(
					getUserLogin(FacesContext.getCurrentInstance().getExternalContext()));
			String websiteURL = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest()).getServerName();
			myLog.debug("websiteURL " + websiteURL);
			CacheManager cacheManager = CacheManager.create();
			Cache globalWebSiteCache = cacheManager.getCache(CommonConstant.GLOBAL_WEBSITE_CACHE);
			if (globalWebSiteCache != null) {
				myLog.debug("got cache");
				Element globalWebSiteElement = globalWebSiteCache.get(CommonConstant.GLOBAL_WEBSITE_ELEMENT);
				if (globalWebSiteElement != null) {
					myLog.debug("got element");
					HashMap<String, WebsiteDVO> websiteMap = (HashMap<String, WebsiteDVO>) globalWebSiteElement
							.getObjectValue();
					myLog.debug("websiteMap " + websiteMap);
					if (websiteMap != null) {
						myLog.debug("got website");
						WebsiteDVO websiteDVO = websiteMap.get(websiteURL);
						if (websiteDVO != null) {
							Long webSiteId = websiteDVO.getId();
							myLog.debug("webSiteId " + webSiteId);
							if (webSiteId != null) {
								searchBrowseProductsOpr.getApplicationFlags().getApplicationFlagMap()
										.put(CommonConstant.EXCLUSIVITY_FILTER_WEBSITE_ID, webSiteId);
							}
						}
					}
				}
			}
			// if there is at least one category code, search on category code
			// if
			// (searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
			// .getCategoryLevelOneRecord().getCode() != null) {
			myLog.debug("M IN CATERGORY CODE SEARCH=========");
			myLog.debug("before searchProductsOnCategoryCodes :: categoryCodeLevel1 :: " + categoryCodeLevel1);
			BrowseProductsOpr browseProductsOprRet = new BrowseProductsBF()
					.searchProductsOnCategoryCodes(searchBrowseProductsOpr);
			browseProductsOpr.setProductSkuList(browseProductsOprRet.getProductSkuList());

			if (browseProductsOpr.getProductSkuList() != null && !browseProductsOpr.getProductSkuList().isEmpty()) {
				ProductSkuDVO productSkuDVO = browseProductsOpr.getProductSkuList().get(0);

				browseProductsOpr
						.getProductSkuRecord()
						.getHierarchyCategoryMappingRecord()
						.getCategoryLevelOneRecord()
						.setName(
								productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName());

				browseProductsOpr
						.getProductSkuRecord()
						.getHierarchyCategoryMappingRecord()
						.getCategoryLevelTwoRecord()
						.setName(
								productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName());

				browseProductsOpr
						.getProductSkuRecord()
						.getHierarchyCategoryMappingRecord()
						.getCategoryLevelThreeRecord()
						.setName(
								productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
										.getName());

				browseProductsOpr
						.getProductSkuRecord()
						.getHierarchyCategoryMappingRecord()
						.getCategoryLevelFourRecord()
						.setName(
								productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
										.getName());
			}

			myLog.debug("browseProductsOpr.getProductList().size():: " + browseProductsOpr.getProductSkuList().size());

			myLog.debug("after searchProductsOnCategoryCodes :: categoryCodeLevel1 :: " + categoryCodeLevel1);

			BrowseProductsOpr categoryListBrowseProductsOprRet = new BrowseProductsBF()
					.searchSubCategory(searchBrowseProductsOpr);
			browseProductsOpr.setSubCategoryList(categoryListBrowseProductsOprRet.getSubCategoryList());

			myLog.debug("before searchMappingOnCategoryCodes :: categoryCodeLevel1 :: " + categoryCodeLevel1);

			myLog.debug("M Back IN CATERGORY CODE SEARCH=========");
			// }
			// else {
			// myLog.debug("M IN ELSE SEARCH=========");
			// ProductOpr searchProductOpr = new ProductOpr();
			// searchProductOpr.getProductRecord().setProductSkuRecord(searchBrowseProductsOpr.getProductSkuRecord());
			// ProductOpr returnProductOpr = new
			// CoreSF().searchProductsOnHierarchy(searchProductOpr);
			//
			// browseProductsOpr.setProductSkuList((ArrayList<ProductSkuDVO>)
			// returnProductOpr.getProductRecord()
			// .getProductSkuList());
			//
			// }

			// calculation of min price and max price

			calculateMinAndMaxPrice();
			populateBreadCrum();

			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("MinPrice") == null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put("MinPrice", browseProductsOpr.getProductSkuRecord().getMinPrice());

			}
			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("MaxPrice") == null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put("MaxPrice", browseProductsOpr.getProductSkuRecord().getMaxPrice());

			}
		} catch (FrameworkException e) {
			myLog.error("Exception occured " + e);
			// handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
		} catch (BusinessException e) {
			myLog.error("Exception occured " + e);
			// handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
		}

		// in case the call fails for whatever reason avoid a null pointer
		// if (productIntegrationOpr != null) {
		// browseProductsOpr.setProductList(productIntegrationOpr.getProductList());
		// browseProductsOpr.setProductRecord(productIntegrationOpr.getProductRecord());
		// browseProductsOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord()
		// .setCode(categoryCodeLevel1);
		// browseProductsOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord()
		// .setCode(categoryCodeLevel2);
		// browseProductsOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord()
		// .setCode(categoryCodeLe vel3);
		// browseProductsOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord()
		// .setCode(categoryCodeLevel4);
		// } else {
		// myLog.debug("productIntegrationOpr is null :: not a good sign ::");
		// }

		// in case the call fails for whatever reason avoid a null pointer
		myLog.debug("before convertedPrice browseProductsOpr.getProductList().size():: "
				+ browseProductsOpr.getProductSkuList().size());
		// convertedPrice(browseProductsOpr);
		myLog.debug("after convertedPrice browseProductsOpr.getProductList().size():: "
				+ browseProductsOpr.getProductSkuList().size());

		// for (int i = 0; i < browseProductsOpr.getProductSkuList().size();
		// i++) {
		// myLog.debug("product name : " +
		// browseProductsOpr.getProductSkuList().get(i).getName());
		// myLog.debug("base price : " +
		// browseProductsOpr.getProductSkuList().get(i).getBasePrice());
		// }

		// if (commentsOpr != null) {
		// browseProductsOpr.setCommentsList(commentsOpr.getCommentsList());
		// myLog.debug("size of comments:::::::::" +
		// browseProductsOpr.getCommentsList().size());
		// } else {
		// myLog.debug("no comments added by any user::");
		// }

		browseProductsOprOrg = (BrowseProductsOpr) DeepCopy.copy(browseProductsOpr);
		myLog.debug("before exit doSearch :: categoryCodeLevel1 :: " + categoryCodeLevel1);
	}

	public void calculateMinAndMaxPrice() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside calculateMinAndMaxPrice :: ");

		ArrayList<ProductSkuDVO> productSkuList = browseProductsOpr.getProductSkuList();

		if (productSkuList != null && !productSkuList.isEmpty()) {
			Float minPrice = productSkuList.get(0).getFinalBasePrice();
			Float maxPrice = 0.0F;
			for (ProductSkuDVO productSkuRecord : productSkuList) {
				if (productSkuRecord.getFinalBasePrice() > maxPrice) {
					maxPrice = productSkuRecord.getFinalBasePrice();
				}

				if (productSkuRecord.getFinalBasePrice() < minPrice) {
					minPrice = productSkuRecord.getFinalBasePrice();
				}
			}

			browseProductsOpr.getProductSkuRecord().setMaxPrice(maxPrice);
			browseProductsOpr.getProductSkuRecord().setMinPrice(minPrice);
			getPlaceHolderBrowseProductsOpr().getProductSkuRecord().setMaxPrice(maxPrice);
			placeHolderBrowseProductsOpr.getProductSkuRecord().setMinPrice(minPrice);
		}
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	// THIS METHOD USED IN PRETTY CONFIG TO LOAD ALL PRODUCTS FOR A CATALOG
	// public void getProductsInCatalog() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	//
	// myLog.debug("after click it on catalog code::::::" + getCatalogCode());
	// //
	// getProductRecord().getHierarchyLevelOne().getCategoryRecord().getCode()
	// // catalog = false;
	// if (catalogCode.equalsIgnoreCase("DailyDreams")) {
	// timer = false;
	// }
	// ProductIntegrationOpr searchIntegrationOpr = new ProductIntegrationOpr();
	// // browseProductsOpr.setProductList(new ArrayList<ProductDVO>());
	// ProductIntegrationOpr productIntegrationOpr = null;
	// BrowseProductsOpr categoryMappingOpr = new BrowseProductsOpr();
	// categoryMappingOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord().setCode("Catalog");
	// try {
	//
	// searchIntegrationOpr.setProductRecord(getBrowseProductsOpr().getProductSkuRecord());
	// searchIntegrationOpr.getProductRecord().setBasePriceFrom(getBasePriceFrom());
	// searchIntegrationOpr.getProductRecord().setBasePriceTo(getBasePriceTo());
	//
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.CATALOG_CODE_FOR_SEARCH, getCatalogCode());
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("catalog.uri",
	// getCatalogCode());
	//
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.USE_EXCLUSIVITY_FILTER,
	// CommonConstant.USE_EXCLUSIVITY_FILTER);
	// searchIntegrationOpr
	// .getApplicationFlags()
	// .getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_USER_LOGIN,
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get(CommonConstant.LOGGED_USER_KEY));
	// String websiteURL = ((HttpServletRequest)
	// FacesContext.getCurrentInstance().getExternalContext()
	// .getRequest()).getServerName();
	// myLog.debug("websiteURL " + websiteURL);
	// CacheManager cacheManager = CacheManager.create();
	// Cache globalWebSiteCache =
	// cacheManager.getCache(CommonConstant.GLOBAL_WEBSITE_CACHE);
	// if (globalWebSiteCache != null) {
	// myLog.debug("got cache");
	// Element globalWebSiteElement =
	// globalWebSiteCache.get(CommonConstant.GLOBAL_WEBSITE_ELEMENT);
	// if (globalWebSiteElement != null) {
	// myLog.debug("got element");
	// HashMap<String, WebsiteDVO> websiteMap = (HashMap<String, WebsiteDVO>)
	// globalWebSiteElement
	// .getObjectValue();
	// myLog.debug("websiteMap " + websiteMap);
	// if (websiteMap != null) {
	// myLog.debug("got website");
	// WebsiteDVO websiteDVO = websiteMap.get(websiteURL);
	// if (websiteDVO != null) {
	// Long webSiteId = websiteDVO.getId();
	// myLog.debug("webSiteId " + webSiteId);
	// if (webSiteId != null) {
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_WEBSITE_ID, webSiteId);
	// }
	// }
	// }
	// }
	// }
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_COMPANY_ID, null);
	// searchIntegrationOpr.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.EXCLUSIVITY_FILTER_RETAILER_GROUP_ID, null);
	//
	// productIntegrationOpr = new
	// ProductIntegration().getProductsInCatalog(searchIntegrationOpr);
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put("catalog",
	// productIntegrationOpr.getProductRecord().getCatalogRecord().getName());
	// getBrowseProductsOpr().setProductList(productIntegrationOpr.getProductList());
	// categoryMappingOpr = (BrowseProductsOpr) new BrowseProductsBF()
	// .searchUserCommentsForCatalog(categoryMappingOpr);
	//
	// if (categoryMappingOpr != null) {
	// browseProductsOpr.setCommentsList(categoryMappingOpr.getCommentsList());
	// browseProductsOpr.setCategoryImageList(categoryMappingOpr.getCategoryImageList());
	// } else {
	// myLog.debug("categoryMappingOpr is null :: not a good sign ::");
	// }
	//
	// } catch (FrameworkException e) {
	// myLog.error("Exception occured " + e);
	// // handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
	// } catch (BusinessException e) {
	// myLog.error("Exception occured " + e);
	// // handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
	// }
	//
	// if (productIntegrationOpr == null) {
	// productIntegrationOpr = new ProductIntegrationOpr();
	// }
	//
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put(CommonConstant.PRODUCT_SEARCH_RESULTS,
	// productIntegrationOpr.getProductList());
	//
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .put("ACTIVE_PAGE", CommonConstant.BROWSE_PRODUCTS_PAGE);
	// //
	// // FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// // .put("ACTIVE_LAYOUT_PAGE", CommonConstant.INNER_LAYOUT_PAGE);
	//
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put("catalog.page.code", "CommonConstant.BROWSE_CATALOG_PAGE");
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// .put("catalog.page.code", CommonConstant.BROWSE_PRODUCTS_PAGE);
	// FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("catalog.uri",
	// catalogCode);
	// convertedPrice(browseProductsOpr);
	//
	// placeHolderBrowseProductsOpr = (BrowseProductsOpr)
	// DeepCopy.copy(browseProductsOpr);
	// placeHolderBrowseProductsOpr = (BrowseProductsOpr)
	// DeepCopy.copy(browseProductsOpr);
	// myLog.debug("GETPANEL categoryCodeLevel1" + categoryCodeLevel1);
	// if (categoryCodeLevel1 == null) {
	// if (htmlPanelGroup == null) {
	// htmlPanelGroup = new HtmlPanelGroup();
	// }
	// myLog.debug("IN IF categoryCodeLevel1" + categoryCodeLevel1);
	// HtmlGraphicImage htmlGraphicImage5 = new HtmlGraphicImage();
	// htmlGraphicImage5.setAlt("Description Five");
	// htmlGraphicImage5.setId("imageFive");
	// htmlGraphicImage5.setValue("/portalrepository/category/category-5.jpg");
	// htmlGraphicImage5.setHeight("86px");
	// htmlGraphicImage5.setWidth("740px");
	// htmlGraphicImage5.setStyle("border:0px;vertical-align:middle;text-align:center;");
	// htmlPanelGroup.getChildren().add(htmlGraphicImage5);
	// }
	//
	// }

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getCategoryCodeLevel1() {
		return categoryCodeLevel1;
	}

	public void setCategoryCodeLevel1(String categoryCodeLevel1) {
		this.categoryCodeLevel1 = categoryCodeLevel1;
	}

	public String getCategoryCodeLevel2() {
		return categoryCodeLevel2;
	}

	public void setCategoryCodeLevel2(String categoryCodeLevel2) {
		this.categoryCodeLevel2 = categoryCodeLevel2;
	}

	public String getCategoryCodeLevel3() {
		return categoryCodeLevel3;
	}

	public void setCategoryCodeLevel3(String categoryCodeLevel3) {
		this.categoryCodeLevel3 = categoryCodeLevel3;
	}

	public String getCategoryCodeLevel4() {
		return categoryCodeLevel4;
	}

	public void setCategoryCodeLevel4(String categoryCodeLevel4) {
		this.categoryCodeLevel4 = categoryCodeLevel4;
	}

	public void addToCart() {
		// ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getSessionMap().get(CommonConstant.SHOPPING_CART_OPR);
		// if (shoppingCartOpr == null) {
		// shoppingCartOpr = new ShoppingCartOpr();
		// }
		//
		// shoppingCartOpr.getShoppingCartProductList().add(addToCartProductRecord.getShoppingCartProduct());
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .put(CommonConstant.SHOPPING_CART_OPR, shoppingCartOpr);
		// String[] messageArguments = {
		// addToCartProductRecord.getShoppingCartProduct().getProductRecord().getName()
		// };
		// setSuccessMsg(MessageFormatter.getFormattedMessage(
		// new
		// PropertiesReader(propertiesLocation).getValueOfKey("product_added_to_cart"),
		// messageArguments));
		//
		// addToCartProductRecord = new BrowseProductsOpr();
	}

	public BrowseProductsOpr getAddToCartProductRecord() {
		if (addToCartProductRecord == null) {
			addToCartProductRecord = new BrowseProductsOpr();
		}
		return addToCartProductRecord;
	}

	public void setAddToCartProductRecord(BrowseProductsOpr addToCartProductRecord) {
		this.addToCartProductRecord = addToCartProductRecord;
	}

	public void fileterOnCategories(CategoryDVO categoryDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("fileterOnCategories called ::: ");
		selectAll = false;
		myLog.debug("category name ::: " + categoryDVO.getName());
		myLog.debug("category id ::: " + categoryDVO.getId());
		ArrayList<ProductSkuDVO> filteredProductSkuList = new ArrayList<ProductSkuDVO>();
		ArrayList<ProductSkuDVO> productListToPut = new ArrayList<ProductSkuDVO>();

		// selectedCategoryMap contains only selected category

		if (categoryDVO.getSelectedCategory() != null && categoryDVO.getSelectedCategory()) {
			if (!getSelectedCategoryMap().containsKey(categoryDVO.getId())) {
				selectedCategoryMap.put(categoryDVO.getId(), categoryDVO);
			}
		} else {
			if (getSelectedCategoryMap().containsKey(categoryDVO.getId())) {
				selectedCategoryMap.remove(categoryDVO.getId());
			}
		}

		myLog.debug("selectedCategoryMap size::" + selectedCategoryMap.size());

		if (!selectedCategoryMap.isEmpty() && selectedCategoryMap.size() > 0) {
			for (Map.Entry<Long, CategoryDVO> mapIterator : selectedCategoryMap.entrySet()) {
				myLog.debug("mapIterator key::" + mapIterator.getKey());
				if (getCategoryProductListMap().containsKey(mapIterator.getKey())) {
					filteredProductSkuList.addAll(getCategoryProductListMap().get(mapIterator.getKey()));
				} else {
					for (int i = 0; i < browseProductsOprOrg.getProductSkuList().size(); i++) {
						ProductSkuDVO productSkuRecord = browseProductsOprOrg.getProductSkuList().get(i);
						if (categoryCodeLevel3 != null) {
							if (productSkuRecord.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
									.getId() != null
									&& productSkuRecord.getHierarchyCategoryMappingRecord()
											.getCategoryLevelFourRecord().getId().equals(mapIterator.getKey())) {
								productListToPut.add(productSkuRecord);
								filteredProductSkuList.add(productSkuRecord);
							}
						}

						if (categoryCodeLevel2 != null && categoryCodeLevel3 == null) {
							if (productSkuRecord.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
									.getId() != null
									&& productSkuRecord.getHierarchyCategoryMappingRecord()
											.getCategoryLevelThreeRecord().getId().equals(mapIterator.getKey())) {
								productListToPut.add(productSkuRecord);
								filteredProductSkuList.add(productSkuRecord);
							}
						}

						if (categoryCodeLevel1 != null && categoryCodeLevel2 == null) {
							myLog.debug("level2 category id::"
									+ productSkuRecord.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
											.getId());
							if (productSkuRecord.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
									.getId() != null
									&& productSkuRecord.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
											.getId().equals(mapIterator.getKey())) {
								productListToPut.add(productSkuRecord);
								filteredProductSkuList.add(productSkuRecord);
							}
						}
					}

					// after list populated put in categoryProductListMap

					getCategoryProductListMap().put(mapIterator.getKey(), productListToPut);
				}
			}

			if (filteredProductSkuList.size() > 0) {
				browseProductsOpr.setProductSkuList(filteredProductSkuList);
				myLog.debug("filteredProductSkuList size::" + filteredProductSkuList.size());
			} else {
				browseProductsOpr.setProductSkuList(new ArrayList<ProductSkuDVO>());
			}
			getPriceFilterOpr().setProductSkuList(browseProductsOpr.getProductSkuList());
		} else {
			// else part if user unselect all then All flag must be checked and
			// product should be display
			browseProductsOpr.setProductSkuList(new ArrayList<ProductSkuDVO>());
			getPriceFilterOpr().setProductSkuList(new ArrayList<ProductSkuDVO>());
			browseProductsOpr.setProductSkuList(browseProductsOprOrg.getProductSkuList());
			selectAll = true;
		}
		calculateMinAndMaxPrice();

	}

	public void selectAllProduct(AjaxBehaviorEvent event) {
		browseProductsOpr.setProductSkuList(new ArrayList<ProductSkuDVO>());
		browseProductsOpr.setProductSkuList(browseProductsOprOrg.getProductSkuList());
		selectedCategoryMap = new HashMap<Long, CategoryDVO>();
		if (browseProductsOpr.getSubCategoryList().size() > 0) {
			for (CategoryDVO categoryDVO : browseProductsOpr.getSubCategoryList()) {
				categoryDVO.setSelectedCategory(false);
			}
		}
	}

	public void sortByNewlyArrived(ActionEvent event) {
		Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.DATE_DESCENDING_ORDER);
	}

	public void sortByPrice(ActionEvent event) {
		Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.PRICE_ASCENDING_ORDER);
	}

	public boolean isFilter14K() {
		return filter14K;
	}

	public void setFilter14K(boolean filter14k) {
		filter14K = filter14k;
	}

	public boolean isFilter18K() {
		return filter18K;
	}

	public void setFilter18K(boolean filter18k) {
		filter18K = filter18k;
	}

	public boolean isFilterVs() {
		return filterVs;
	}

	public void setFilterVs(boolean filterVs) {
		this.filterVs = filterVs;
	}

	public boolean isFilterSi() {
		return filterSi;
	}

	public void setFilterSi(boolean filterSi) {
		this.filterSi = filterSi;
	}

	public void select14K(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside select14K ::: ");
		setFilter18K(false);
		if (!filter14K) {
			setFilter14K(true);
		}
		String productCode = "14 KT~";
		if (filterSi) {
			productCode = productCode + "SI~";
		} else if (filterVs) {
			productCode = productCode + "VS~";
		}
		filterOnAdvancedSearch(productCode);
	}

	public void select18K(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside select18K ::: ");
		setFilter14K(false);
		if (!filter18K) {
			setFilter18K(true);
		}
		String productCode = "18 KT~";
		if (filterSi) {
			productCode = productCode + "SI~";
		} else if (filterVs) {
			productCode = productCode + "VS~";
		}
		filterOnAdvancedSearch(productCode);
	}

	public void selectVS(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside selectVS ::: ");
		setFilterSi(false);
		if (!filterVs) {
			setFilterVs(true);
		}
		String productCode = "VS~";
		if (filter14K) {
			productCode = "14 KT~" + productCode;
		} else if (filter18K) {
			productCode = "18 KT~" + productCode;
		}
		filterOnAdvancedSearch(productCode);
	}

	public void selectSI(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside selectSI ::: ");
		setFilterVs(false);
		if (!filterSi) {
			setFilterSi(true);
		}
		String productCode = "SI~";
		if (filter14K) {
			productCode = "14 KT~" + productCode;
		} else if (filter18K) {
			productCode = "18 KT~" + productCode;
		}
		filterOnAdvancedSearch(productCode);
	}

	public void filterOnAdvancedSearch(String propertyCode) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("filterOnAdvancedSearch : processing for propertyCode :: " + propertyCode);

		// for (int i = 0; i < browseProductsOpr.getProductSkuList().size();
		// i++) {
		// ProductSkuDVO currentRecord =
		// browseProductsOpr.getProductSkuList().get(i);
		// ProductSkuDVO replacementRecord = new ProductSkuDVO();
		//
		// myLog.debug("filterOnAdvancedSearch :: getFilterCodeMap :: " +
		// currentRecord.getFilterCodeMap());
		//
		// if (currentRecord.getFilterCodeMap().containsKey(propertyCode)) {
		// myLog.debug("filterOnAdvancedSearch :: propertyCode matched :: " +
		// currentRecord.getId()
		// + " and variant :: " + currentRecord.getVariantId());
		//
		// replacementRecord =
		// currentRecord.getFilterCodeMap().get(propertyCode);
		// myLog.debug("filterOnAdvancedSearch :: replacementRecord :: product id :: "
		// + replacementRecord.getId());
		// myLog.debug("filterOnAdvancedSearch :: replacementRecord :: variant id :: "
		// + replacementRecord.getVariantId());
		// myLog.debug("filterOnAdvancedSearch :: replacementRecord :: base price :: "
		// + replacementRecord.getBasePrice());
		// myLog.debug("filterOnAdvancedSearch :: currentRecord :: id ::" +
		// currentRecord.getId()
		// + " AND base price :: " + currentRecord.getBasePrice());
		//
		// currentRecord.setId(replacementRecord.getId());
		// currentRecord.setVariantId(replacementRecord.getVariantId());
		// currentRecord.setBasePrice(convertCurrency(replacementRecord.getBasePrice()));
		// currentRecord.setDiscountPrice(convertCurrency(replacementRecord.getDiscountPrice()));
		//
		// browseProductsOpr.getProductList().set(i, currentRecord);
		// } else {
		// myLog.debug("for advanced serarched :: propertyCode not matched :: "
		// + currentRecord.getId()
		// + " and variant :: " + currentRecord.getVariantId());
		// }
		// }
	}

	public boolean isCatalog() {
		return catalog;
	}

	public void setCatalog(boolean catalog) {
		this.catalog = catalog;
	}

	public boolean isTimer() {
		return timer;
	}

	public void setTimer(boolean timer) {
		this.timer = timer;
	}

	// private void convertedPrice(BrowseProductsOpr browseProductsOpr) {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// Float conversionRate = 1.0F;
	// Float convertedBasePrice = 0.0F;
	// Float convertedDiscountPrice = 0.0F;
	// if
	// (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get(CommonConstant.CURRENCY_CONVERSION_RATE) != null) {
	// conversionRate = (Float)
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get(CommonConstant.CURRENCY_CONVERSION_RATE);
	// }
	// myLog.debug("conersion rate ==" + conversionRate);

	// for (int i = 0; i < browseProductsOpr.getProductSkuList().size();
	// i++) {
	// convertedBasePrice = 0.0F;
	// convertedDiscountPrice = 0.0F;
	// convertedBasePrice =
	// browseProductsOpr.getProductSkuList().get(i).getBasePrice() *
	// conversionRate;
	// browseProductsOpr.getProductSkuList().get(i).setBasePrice(convertedBasePrice);
	//
	// if (browseProductsOpr.getProductSkuList().get(i).getDiscountPrice()
	// != null
	// && browseProductsOpr.getProductSkuList().get(i).getDiscountPrice() >
	// 0.0) {
	// convertedDiscountPrice =
	// browseProductsOpr.getProductSkuList().get(i).getDiscountPrice()
	// * conversionRate;
	// browseProductsOpr.getProductSkuList().get(i).setDiscountPrice(convertedDiscountPrice);
	// }
	// }
	// }

	private Float convertCurrency(Float originalValue) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside currencyBasedConversion():: " + originalValue);
		Float convertedValue = 0.0F;
		Float conversionRate = 1.0F;

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.CURRENCY_CONVERSION_RATE) != null) {
			conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.CURRENCY_CONVERSION_RATE);
			myLog.debug("conversionRate:: " + conversionRate);

			convertedValue = originalValue * conversionRate;
		}
		myLog.debug("convertedValue:: " + convertedValue);

		return convertedValue;
	}

	public void getCountryDependentCurrencyConversion() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside getCountryDependentData():: countryname== " + countryName);
		BrowseProductsOpr browseProductsCountryOpr = new BrowseProductsOpr();

		myLog.debug("Inside getCountryDependentData() CURRENCY_CONVERSION_RATE null :: ");
		if (countryName != null) {
			myLog.debug("Inside countryName is not null :: ");
			if (countryName.equals("Asia")) {
				myLog.debug("Inside countryName if asia :: ");
				browseProductsCountryOpr.getCountryRecord().setName("Asia");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.CURRENCY_FLAG, true);
			} else {
				browseProductsCountryOpr.getCountryRecord().setName("Rest of the world");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.CURRENCY_FLAG, false);
			}
		}

		try {
			browseProductsCountryOpr = new BrowseProductsBF().getCountryDependentData(browseProductsCountryOpr);
		} catch (FrameworkException e) {
			myLog.error("Exception occured " + e);
			// handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
		}

		myLog.debug("inside getting currency conversion rate browse product bb ::: "
				+ browseProductsCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
		Float conversionMultiplier = 1.0F;
		String currencySymbol = "Rs.";

		if (browseProductsCountryOpr != null) {
			if (browseProductsCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier() > 0) {
				conversionMultiplier = browseProductsCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier();
				setConversionRate(conversionMultiplier);
				myLog.debug("inside getting currency conversion rate browse product bb ::: " + conversionMultiplier);
			}

			if (browseProductsCountryOpr.getCurrencyRecord().getCurrencySymbol() != null
					&& browseProductsCountryOpr.getCurrencyRecord().getCurrencySymbol().trim().length() > 0) {
				currencySymbol = browseProductsCountryOpr.getCurrencyRecord().getCurrencySymbol();
				setConvertedCurrencySymbol(currencySymbol);
				myLog.debug("currency symbol ::: " + convertedCurrencySymbol);
			}
		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.CURRENCY_CONVERSION_RATE, conversionMultiplier);

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.CONVERTED_CURRENCY_SYMBOL, currencySymbol);

		myLog.debug("conversion factor got :::: "
				+ browseProductsCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
	}

	public void getCurrencyForBrowse() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside Browse Product getCurrencyForBrowse:: " + countryName);
		getCountryDependentCurrencyConversion();
		if (categoryCodeLevel1 != null && categoryCodeLevel1.trim().length() > 0) {
			doProductSearch();
		}
		if (getCatalogCode() != null && getCatalogCode().trim().length() > 0) {
			// getProductsInCatalog();
		}

	}

	public String getBrowseTitle() {
		return browseTitle;
	}

	public void setBrowseTitle(String browseTitle) {
		this.browseTitle = browseTitle;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	public void filterOnSelection(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside Browse Product filterOnSelection:: ");
		if (filterBy.equalsIgnoreCase("Newly Arrived")) {
			Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.DATE_DESCENDING_ORDER);
		} else if (filterBy.equalsIgnoreCase("LToH")) {
			Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.PRICE_ASCENDING_ORDER);
		} else if (filterBy.equalsIgnoreCase("HToL")) {
			Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.PRICE_DESCENDING_ORDER);
		} else if (filterBy.equalsIgnoreCase("Alphabetic")) {
			Collections.sort(browseProductsOpr.getProductSkuList(), ProductSkuDVO.ALPHABETIC_ORDER);
		} else {

		}
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	public void populateBreadCrum() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = browseProductsOpr.getProductSkuRecord()
				.getHierarchyCategoryMappingRecord();
		String hierarchicalBreadcrumbTitle = null;

		String hierarchyUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/"
				+ (hierarchyCategoryMappingRecord.getHierarchyRecord().getName() == null ? ""
						: hierarchyCategoryMappingRecord.getHierarchyRecord().getName())
				+ CommonConstant.BROWSE_PRODUCTS_URL;

		String levelOneUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/"
				+ (hierarchyCategoryMappingRecord.getHierarchyRecord().getName() == null ? ""
						: hierarchyCategoryMappingRecord.getHierarchyRecord().getName())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode())
				+ CommonConstant.BROWSE_PRODUCTS_URL;

		myLog.debug("levelOneUrl:::::" + levelOneUrl);
		String levelTwoUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/"
				+ (hierarchyCategoryMappingRecord.getHierarchyRecord().getName() == null ? ""
						: hierarchyCategoryMappingRecord.getHierarchyRecord().getName())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode())
				+ CommonConstant.BROWSE_PRODUCTS_URL;
		myLog.debug("levelTwoUrl:::::" + levelTwoUrl);
		String levelThreeUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/"
				+ (hierarchyCategoryMappingRecord.getHierarchyRecord().getName() == null ? ""
						: hierarchyCategoryMappingRecord.getHierarchyRecord().getName())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode())
				+ (hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode() == null ? "" : "/"
						+ hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode())
				+ CommonConstant.BROWSE_PRODUCTS_URL;
		myLog.debug("levelThreeUrl:::::" + levelThreeUrl);

		if (hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() == null) {
			hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" + hierarchyUrl + "\">"
					+ hierarchyCategoryMappingRecord.getHierarchyRecord().getName() + "</a></li>";
		} else if (hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode() == null
				&& hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() != null) {
			hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" + hierarchyUrl + "\">"
					+ hierarchyCategoryMappingRecord.getHierarchyRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"last\"><a href=\"" + levelOneUrl + "\">"
					+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getName() + "</a></li>";
		} else if (hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode() == null
				&& hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode() != null) {
			hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" + hierarchyUrl + "\">"
					+ hierarchyCategoryMappingRecord.getHierarchyRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"\"><a href=\"" + levelOneUrl + "\">"
					+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"last\"><a href=\"" + levelTwoUrl + "\">"
					+ hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getName() + "</a></li>";
		} else if (hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode() != null) {

			hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" + hierarchyUrl + "\">"
					+ hierarchyCategoryMappingRecord.getHierarchyRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"\"><a href=\"" + levelOneUrl + "\">"
					+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"\"><a href=\"" + levelTwoUrl + "\">"
					+ hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getName() + "</a></li>"
					+ "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"last\"><a href=\"" + levelThreeUrl
					+ "\">" + hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getName() + "</a></li>";

		}

		myLog.debug("hierarchicalBreadcrumbTitle:::::" + hierarchicalBreadcrumbTitle);

		browseProductsOpr.getProductSkuRecord().setHierarchicalBreadcrumbTitle(hierarchicalBreadcrumbTitle);
	}

	public void onSlideEnd(SlideEndEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside onSlideEnd:::");

		ArrayList<ProductSkuDVO> priceFilterList = new ArrayList<ProductSkuDVO>();
		myLog.debug("original list size::" + browseProductsOprOrg.getProductSkuList().size());
		myLog.debug("filter opr list size::" + getPriceFilterOpr().getProductSkuList().size());

		ArrayList<ProductSkuDVO> categoryFilteredList = getPriceFilterOpr().getProductSkuList();
		ArrayList<ProductSkuDVO> originalProductList = browseProductsOprOrg.getProductSkuList();

		if (!categoryFilteredList.isEmpty()) {
			myLog.debug("inside if:::");
			browseProductsOpr.setProductSkuList(categoryFilteredList);
		} else {
			myLog.debug("inside else:::");
			browseProductsOpr.setProductSkuList(originalProductList);
		}

		myLog.debug("1111111111:::");

		if (!browseProductsOpr.getProductSkuList().isEmpty()) {
			for (ProductSkuDVO productSkuDVO : browseProductsOpr.getProductSkuList()) {
				if (productSkuDVO.getFinalBasePrice() >= browseProductsOpr.getProductSkuRecord().getMinPrice()
						&& productSkuDVO.getFinalBasePrice() <= browseProductsOpr.getProductSkuRecord().getMaxPrice()) {
					priceFilterList.add(productSkuDVO);
				}
			}
		}

		browseProductsOpr.setProductSkuList(priceFilterList);
	}
}
