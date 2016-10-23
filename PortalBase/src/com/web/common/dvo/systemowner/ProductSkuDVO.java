package com.web.common.dvo.systemowner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javax.faces.context.FacesContext;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.CatalogDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.common.StatusDVO;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.CommonUtil;

public class ProductSkuDVO extends BaseDVO {

	private static final long serialVersionUID = 5144307489164534722L;
	private boolean defaultVariant;
	private ImageDVO defaultImageRecord;
	private boolean productRetired;
	private HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord;
	private Float basePrice;
	private Float finalBasePrice;
	private CurrencyDVO currencyRecord;
	private ArrayList<CategoryPropertyMappingDVO> categoryPropertiesList;
	private ArrayList<ProductSkuStockLevelDVO> productSkuStockLevelList;
	private ArrayList<ComplementaryProductSkuDVO> complementaryProductList;
	private boolean exclusivelyOurs;
	private boolean newArrival;
	private boolean topSelling;
	private boolean inStock;
	private ArrayList<ProductDVO> variantList;
	private boolean priceMultiplierEnabled;
	private Float priceMultiplier;
	private String productURL;
	private String hierarchicalProductTitle;
	private Float basePriceFrom;
	private Float basePriceTo;
	private Float minPrice;
	private Float maxPrice;
	private boolean renderedDiscountPrice;
	private String strikeStyle = null;
	private Parameter productCertifications;
	private String productCertification;
	private Float savePrice;
	private boolean jewelleryValuationRequired;
	private String jewelleryValuationDisplayText;
	private String styleClass;
	private ArrayList<ProductSkuPropertyMappingDVO> productSkuPropertiesMappingList;
	private CategoryPropertyMappingDVO categoryPropertiesMappingRecord;
	private ProductSkuImageMappingDVO productSkuImageMappingDVO;
	private ArrayList<ProductSkuImageMappingDVO> productSkuImageMappingList;
	private boolean customizable;
	private Integer addDeliveryTime;
	private Float makingCharges;
	private String partDescription; // this is used in productgrid.xhtml for
									// partial description rendering
	private String engraveText;
	private String engraveFont;
	private boolean select;
	HashMap<String, ProductDVO> skuCodeMap;
	private String skuCode;
	private double rating;
	HashMap<String, ProductDVO> filterCodeMap;
	private SizeDVO sizeRecord;
	private ArrayList<SizeDVO> sizeList;
	private ArrayList<CategorySizeMappingDVO> mappedSizeList;
	private Integer sequenceNumber;
	private CatalogDVO catalogRecord;
	private boolean giftVoucherDiscountApplicable;
	// GEOPLUGIN - To save original base price product
	private Float originalBasePrice;
	// GEOPLUGIN - To save original discount price product
	private Float originalDiscountPrice;
	private Float conversionRate;
	// GEOPLUGIN - To save original currency
	private CurrencyDVO originalCurrencyRecord;
	private boolean currencySymbolFlag;

	// added by Dheeraj
	private String material;
	private CategoryPropertyMappingDVO sizePropertyRecord;
	private CategoryPropertyMappingDVO colorPropertyRecord;
	private String hierarchicalProductTitleForReadMore;
	private boolean onlineProduct;
	private ProductSkuStockLevelDVO productSkuStockLevelRecord;
	private Float percentDiscount;

	private String hierarchicalBreadcrumbTitle;
	private String productPdfURL;
	private Integer productQty;
	private DeliveryChargesDVO deliverChargesRecord;
	private Float discountAmount;
	private Float discountPercent;
	private ProductDVO productRecord;

	// Added By Deepak
	private String skuPropertyText;
	private String skuSEOTitle;
	private String skuSEOKeyword;
	private String skuSEODescription;
	private ArrayList<ProductSizeMappingDVO> productSizeMappingList;
	private StatusDVO statusRecord;
	private ProductSkuImageMappingDVO defaultProductSkuImageMappingDVO;

	private Boolean defaultSku;

	public void setPercentDiscount(Float percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public Float getOriginalBasePrice() {
		return originalBasePrice;
	}

	public void setOriginalBasePrice(Float originalBasePrice) {
		this.originalBasePrice = originalBasePrice;
	}

	public Float getOriginalDiscountPrice() {
		return originalDiscountPrice;
	}

	public void setOriginalDiscountPrice(Float originalDiscountPrice) {
		this.originalDiscountPrice = originalDiscountPrice;
	}

	public Float getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Float conversionRate) {
		this.conversionRate = conversionRate;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public Float getPriceForComparison() {
		if (getFinalBasePrice() == null || getFinalBasePrice().equals(Float.valueOf(0))) {
			return getBasePrice();
		} else {
			return getFinalBasePrice();
		}
	}

	public static final Comparator<ProductSkuDVO> PRICE_ASCENDING_ORDER = new Comparator<ProductSkuDVO>() {
		public int compare(ProductSkuDVO p1, ProductSkuDVO p2) {
			return -1 * (p2.getPriceForComparison().compareTo(p1.getPriceForComparison()));
		}
	};

	// USED TO SORT BY LATEST ADDED PRODUCTS, HENCE ON MODIFIED DATE
	public static final Comparator<ProductSkuDVO> DATE_DESCENDING_ORDER = new Comparator<ProductSkuDVO>() {
		public int compare(ProductSkuDVO p1, ProductSkuDVO p2) {
			return p2.getAuditAttributes().getLastModifiedDate()
					.compareTo(p1.getAuditAttributes().getLastModifiedDate());
		}
	};

	public String getHierarchicalProductTitle() {

		String levelOneUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/"
				+ (getHierarchyCategoryMappingRecord().getHierarchyRecord().getName() == null ? ""
						: getHierarchyCategoryMappingRecord().getHierarchyRecord().getName())
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getCode() == null ? "" : "/"
						+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getCode());

		String levelTwoUrl = levelOneUrl
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getCode() == null ? "" : "/"
						+ getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getCode());

		String levelThreeUrl = levelTwoUrl
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode() == null ? "" : "/"
						+ getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode());

		if (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode() == null
				&& getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getCode() != null) {
			hierarchicalProductTitle = "<li><a href=\""
					+ levelOneUrl
					+ "\">"
					+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName()
					+ "</a></li>"
					+ "<li class=\"\"><a>"
					+ ">"
					+ "</a></li>"
					+ (getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName() == null ? ""
							: "<li><a href=\"" + levelTwoUrl + "\">"
									+ getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName()
									+ "</a></li>") + "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"last\">"
					+ getName() + "</li>";
		} else if (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode() != null) {
			hierarchicalProductTitle = "<li><a href=\""
					+ levelOneUrl
					+ "\">"
					+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName()
					+ "</a></li>"
					+ "<li class=\"\"><a>"
					+ ">"
					+ "</a></li>"
					+ (getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName() == null ? ""
							: "<li><a href=\"" + levelTwoUrl + "\">"
									+ getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName()
									+ "</a></li>")
					+ "<li class=\"\"><a>"
					+ ">"
					+ "</a></li>"
					+ (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName() == null ? ""
							: "<li><a href=\"" + levelThreeUrl + "\">"
									+ getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName()
									+ "</a></li>") + "<li class=\"\"><a>" + ">" + "</a></li>" + "<li class=\"last\">"
					+ getName() + "</li>";

		}

		return hierarchicalProductTitle;
	}

	public void setHierarchicalProductTitle(String hierarchicalProductTitle) {
		this.hierarchicalProductTitle = hierarchicalProductTitle;
	}

	@SuppressWarnings("deprecation")
	public String getProductURL() {
		String urlString = null;
		if (id != null && getProductRecord().getId() != null) {
			try {
				urlString = "/"
						+ getProductRecord().getId().toString()
						+ "-"
						+ getId().toString()
						+ URLEncoder.encode(
								(getHierarchyCategoryMappingRecord().getHierarchyRecord().getName() == null ? "" : "-"
										+ CommonUtil
												.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
														.getHierarchyRecord().getName())), "UTF-8")
						+ URLEncoder
								.encode((getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName() == null ? ""
										: "-"
												+ CommonUtil
														.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
																.getCategoryLevelOneRecord().getName())), "UTF-8")
						+ URLEncoder
								.encode((getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName() == null ? ""
										: "-"
												+ CommonUtil
														.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
																.getCategoryLevelTwoRecord().getName())), "UTF-8")
						+ URLEncoder
								.encode((getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName() == null ? ""
										: "-"
												+ CommonUtil
														.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
																.getCategoryLevelThreeRecord().getName())), "UTF-8")
						+ URLEncoder
								.encode((getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getName() == null ? ""
										: "_"
												+ CommonUtil
														.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
																.getCategoryLevelFourRecord().getName())), "UTF-8")
						+ URLEncoder
								.encode((getName() == null ? "" : "-"
										+ CommonUtil.removeSpecialCharactersFromString(getName())), "UTF-8");
			} catch (Exception e) {
				if (e instanceof UnsupportedEncodingException) {
					urlString = getId().toString()
							+ "-"
							+ URLEncoder
									.encode((getHierarchyCategoryMappingRecord().getHierarchyRecord().getName() == null ? ""
											: "-"
													+ CommonUtil
															.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
																	.getHierarchyRecord().getName())))
							+ URLEncoder.encode((getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
									.getName() == null ? "" : "-"
									+ CommonUtil.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
											.getCategoryLevelOneRecord().getName())))
							+ URLEncoder.encode((getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
									.getName() == null ? "" : "-"
									+ CommonUtil.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
											.getCategoryLevelTwoRecord().getName())))
							+ URLEncoder.encode((getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
									.getName() == null ? "" : "-"
									+ CommonUtil.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
											.getCategoryLevelThreeRecord().getName())))
							+ ((getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getName() == null ? ""
									: "_"
											+ CommonUtil
													.removeSpecialCharactersFromString(getHierarchyCategoryMappingRecord()
															.getCategoryLevelFourRecord().getName())))
							+ URLEncoder.encode((getName() == null ? "" : "-"
									+ CommonUtil.removeSpecialCharactersFromString(getName())));
				}
			}
		}
		return urlString;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public boolean isDefaultVariant() {
		return defaultVariant;
	}

	public void setDefaultVariant(boolean defaultVariant) {
		this.defaultVariant = defaultVariant;
	}

	public boolean isPriceMultiplierEnabled() {
		return priceMultiplierEnabled;
	}

	public void setPriceMultiplierEnabled(boolean priceMultiplierEnabled) {
		this.priceMultiplierEnabled = priceMultiplierEnabled;
	}

	public Float getPriceMultiplier() {
		return priceMultiplier;
	}

	public void setPriceMultiplier(Float priceMultiplier) {
		this.priceMultiplier = priceMultiplier;
	}

	public ArrayList<ProductDVO> getVariantList() {
		if (variantList == null) {
			variantList = new ArrayList<ProductDVO>();
		}
		return variantList;
	}

	public void setVariantList(ArrayList<ProductDVO> variantList) {
		this.variantList = variantList;
	}

	// retail price is a wrapper method for base price field
	public Float getRetailPrice() {
		return basePrice;
	}

	public void setRetailPrice(Float retailPrice) {
		basePrice = retailPrice;
	}

	public Float getBasePriceFrom() {
		return basePriceFrom;
	}

	public void setBasePriceFrom(Float basePriceFrom) {
		this.basePriceFrom = basePriceFrom;
	}

	public Float getBasePriceTo() {
		return basePriceTo;
	}

	public void setBasePriceTo(Float basePriceTo) {
		this.basePriceTo = basePriceTo;
	}

	public boolean isInStock() {
		inStock = false;
		if (getProductSkuStockLevelList().size() > 0) {
			if (getProductSkuStockLevelList().get(0).getAvailableQuantity() > 0) {
				inStock = true;
			}
		}
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isExclusivelyOurs() {
		return exclusivelyOurs;
	}

	public void setExclusivelyOurs(boolean exclusivelyOurs) {
		this.exclusivelyOurs = exclusivelyOurs;
	}

	public boolean isNewArrival() {
		return newArrival;
	}

	public void setNewArrival(boolean newArrival) {
		this.newArrival = newArrival;
	}

	public boolean isTopSelling() {
		return topSelling;
	}

	public void setTopSelling(boolean topSelling) {
		this.topSelling = topSelling;
	}

	public boolean isProductRetired() {
		return productRetired;
	}

	public void setProductRetired(boolean productRetired) {
		this.productRetired = productRetired;
	}

	public HierarchyCategoryMappingDVO getHierarchyCategoryMappingRecord() {
		if (hierarchyCategoryMappingRecord == null) {
			hierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();
		}
		return hierarchyCategoryMappingRecord;
	}

	public void setHierarchyCategoryMappingRecord(HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord) {
		this.hierarchyCategoryMappingRecord = hierarchyCategoryMappingRecord;
	}

	public Float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public CurrencyDVO getCurrencyRecord() {
		if (currencyRecord == null) {
			currencyRecord = new CurrencyDVO();
		}
		return currencyRecord;
	}

	public void setCurrencyRecord(CurrencyDVO currencyRecord) {
		this.currencyRecord = currencyRecord;
	}

	public ArrayList<CategoryPropertyMappingDVO> getCategoryPropertiesList() {
		if (categoryPropertiesList == null) {
			categoryPropertiesList = new ArrayList<CategoryPropertyMappingDVO>();
		}
		return categoryPropertiesList;
	}

	public void setCategoryPropertiesList(ArrayList<CategoryPropertyMappingDVO> categoryPropertiesList) {
		this.categoryPropertiesList = categoryPropertiesList;
	}

	public ArrayList<ProductSkuStockLevelDVO> getProductSkuStockLevelList() {
		if (productSkuStockLevelList == null) {
			productSkuStockLevelList = new ArrayList<ProductSkuStockLevelDVO>();
		}
		return productSkuStockLevelList;
	}

	public void setProductSkuStockLevelList(ArrayList<ProductSkuStockLevelDVO> productSkuStockLevelList) {
		this.productSkuStockLevelList = productSkuStockLevelList;
	}

	public ArrayList<ComplementaryProductSkuDVO> getComplementaryProductList() {
		if (complementaryProductList == null) {
			complementaryProductList = new ArrayList<ComplementaryProductSkuDVO>();
		}
		return complementaryProductList;
	}

	public void setComplementaryProductList(ArrayList<ComplementaryProductSkuDVO> complementaryProductList) {
		this.complementaryProductList = complementaryProductList;
	}

	public ImageDVO getDefaultImageRecord() {
		if (defaultImageRecord == null) {
			defaultImageRecord = new ImageDVO();
		}
		return defaultImageRecord;
	}

	public void setDefaultImageRecord(ImageDVO defaultImageRecord) {
		this.defaultImageRecord = defaultImageRecord;
	}

	public Float getMinPrice() {

		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {

		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getBasePriceIntValue() {
		Integer basePriceIntValue = null;
		if (basePrice != null) {
			basePriceIntValue = Integer.valueOf(basePrice.intValue());
		}

		return basePriceIntValue;
	}

	public void setBasePriceIntValue(Integer basePriceIntValue) {
		if (basePriceIntValue != null) {
			basePrice = Float.valueOf(basePriceIntValue.floatValue());
		}
	}

	public Float getFinalBasePrice() {
		return finalBasePrice;
	}

	public void setFinalBasePrice(Float finalBasePrice) {
		this.finalBasePrice = finalBasePrice;
	}

	public boolean isRenderedDiscountPrice() {
		return renderedDiscountPrice;
	}

	public void setRenderedDiscountPrice(boolean renderedDiscountPrice) {
		this.renderedDiscountPrice = renderedDiscountPrice;
	}

	public String getStrikeStyle() {
		return strikeStyle;
	}

	public void setStrikeStyle(String strikeStyle) {
		this.strikeStyle = strikeStyle;
	}

	public Integer getSavePriceIntValue() {
		Integer savePriceIntValue = null;
		if (savePrice != null) {
			savePriceIntValue = Integer.valueOf(savePrice.intValue());
		}
		return savePriceIntValue;
	}

	public void setSavePriceIntValue(Integer savePriceIntValue) {
		if (savePriceIntValue != null) {
			savePrice = Float.valueOf(savePriceIntValue.floatValue());
		}
	}

	public String getStyleClass() {
		if (renderedDiscountPrice) {
			styleClass = "price_old";
		} else {
			styleClass = "price";
		}
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public ArrayList<ProductSkuPropertyMappingDVO> getProductSkuPropertiesMappingList() {
		if (productSkuPropertiesMappingList == null) {
			productSkuPropertiesMappingList = new ArrayList<ProductSkuPropertyMappingDVO>();
		}
		return productSkuPropertiesMappingList;
	}

	public void setProductSkuPropertiesMappingList(
			ArrayList<ProductSkuPropertyMappingDVO> productSkuPropertiesMappingList) {
		this.productSkuPropertiesMappingList = productSkuPropertiesMappingList;
	}

	public CategoryPropertyMappingDVO getCategoryPropertiesMappingRecord() {
		if (categoryPropertiesMappingRecord == null) {
			categoryPropertiesMappingRecord = new CategoryPropertyMappingDVO();
		}
		return categoryPropertiesMappingRecord;
	}

	public void setCategoryPropertiesMappingRecord(CategoryPropertyMappingDVO categoryPropertiesMappingRecord) {
		this.categoryPropertiesMappingRecord = categoryPropertiesMappingRecord;
	}

	public boolean isCustomizable() {
		return customizable;
	}

	public void setCustomizable(boolean customizable) {
		this.customizable = customizable;
	}

	public Integer getAddDeliveryTime() {
		return addDeliveryTime;
	}

	public void setAddDeliveryTime(Integer addDeliveryTime) {
		this.addDeliveryTime = addDeliveryTime;
	}

	public Parameter getProductCertifications() {
		if (productCertifications == null) {
			productCertifications = new Parameter();
		}
		return productCertifications;
	}

	public void setProductCertifications(Parameter productCertifications) {
		this.productCertifications = productCertifications;
	}

	public String getProductCertification() {
		return productCertification;
	}

	public void setProductCertification(String productCertification) {
		this.productCertification = productCertification;
	}

	public boolean isJewelleryValuationRequired() {
		return jewelleryValuationRequired;
	}

	public void setJewelleryValuationRequired(boolean jewelleryValuationRequired) {
		this.jewelleryValuationRequired = jewelleryValuationRequired;
	}

	public String getJewelleryValuationDisplayText() {
		return jewelleryValuationDisplayText;
	}

	public void setJewelleryValuationDisplayText(String jewelleryValuationDisplayText) {
		this.jewelleryValuationDisplayText = jewelleryValuationDisplayText;
	}

	public Float getMakingCharges() {
		return makingCharges;
	}

	public void setMakingCharges(Float makingCharges) {
		this.makingCharges = makingCharges;
	}

	public ProductSkuImageMappingDVO getProductSkuImageMappingDVO() {
		if (productSkuImageMappingDVO == null) {
			productSkuImageMappingDVO = new ProductSkuImageMappingDVO();
		}
		return productSkuImageMappingDVO;
	}

	public void setProductSkuImageMappingDVO(ProductSkuImageMappingDVO productSkuImageMappingDVO) {
		this.productSkuImageMappingDVO = productSkuImageMappingDVO;
	}

	public ArrayList<ProductSkuImageMappingDVO> getProductSkuImageMappingList() {
		if (productSkuImageMappingList == null) {
			productSkuImageMappingList = new ArrayList<ProductSkuImageMappingDVO>();
		}
		return productSkuImageMappingList;
	}

	public void setProductSkuImageMappingList(ArrayList<ProductSkuImageMappingDVO> productSkuImageMappingList) {
		this.productSkuImageMappingList = productSkuImageMappingList;
	}

	public String getPartDescription() {
		if (description != null && description.trim().length() > CommonConstant.DESCRIPTION_DISPLAY_LENGTH) {
			partDescription = description.substring(0, CommonConstant.DESCRIPTION_DISPLAY_LENGTH) + " ... ";
		} else {
			partDescription = description;
		}
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getEngraveText() {
		return engraveText;
	}

	public void setEngraveText(String engraveText) {
		this.engraveText = engraveText;
	}

	public String getEngraveFont() {
		return engraveFont;
	}

	public void setEngraveFont(String engraveFont) {
		this.engraveFont = engraveFont;
	}

	public HashMap<String, ProductDVO> getSkuCodeMap() {
		if (skuCodeMap == null) {
			skuCodeMap = new HashMap<String, ProductDVO>();
		}
		return skuCodeMap;
	}

	public void setSkuCodeMap(HashMap<String, ProductDVO> skuCodeMap) {
		this.skuCodeMap = skuCodeMap;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public HashMap<String, ProductDVO> getFilterCodeMap() {
		if (filterCodeMap == null) {
			filterCodeMap = new HashMap<String, ProductDVO>();
		}
		return filterCodeMap;
	}

	public void setFilterCodeMap(HashMap<String, ProductDVO> filterCodeMap) {
		this.filterCodeMap = filterCodeMap;
	}

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
	}

	public ArrayList<SizeDVO> getSizeList() {
		if (sizeList == null) {
			sizeList = new ArrayList<SizeDVO>();
		}
		return sizeList;
	}

	public void setSizeList(ArrayList<SizeDVO> sizeList) {
		this.sizeList = sizeList;
	}

	public ArrayList<CategorySizeMappingDVO> getMappedSizeList() {
		if (mappedSizeList == null) {
			mappedSizeList = new ArrayList<CategorySizeMappingDVO>();
		}
		return mappedSizeList;
	}

	public void setMappedSizeList(ArrayList<CategorySizeMappingDVO> mappedSizeList) {
		this.mappedSizeList = mappedSizeList;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public synchronized CatalogDVO getCatalogRecord() {
		if (catalogRecord == null) {
			catalogRecord = new CatalogDVO();
		}
		return catalogRecord;
	}

	public synchronized void setCatalogRecord(CatalogDVO catalogRecord) {
		this.catalogRecord = catalogRecord;
	}

	public boolean isGiftVoucherDiscountApplicable() {
		return giftVoucherDiscountApplicable;
	}

	public void setGiftVoucherDiscountApplicable(boolean giftVoucherDiscountApplicable) {
		this.giftVoucherDiscountApplicable = giftVoucherDiscountApplicable;
	}

	public CurrencyDVO getOriginalCurrencyRecord() {
		if (originalCurrencyRecord == null) {
			originalCurrencyRecord = new CurrencyDVO();
		}
		return originalCurrencyRecord;
	}

	public void setOriginalCurrencyRecord(CurrencyDVO originalCurrencyRecord) {
		this.originalCurrencyRecord = originalCurrencyRecord;
	}

	public Boolean getCurrencySymbolFlag() {
		return currencySymbolFlag;
	}

	public void setCurrencySymbolFlag(Boolean currencySymbolFlag) {
		this.currencySymbolFlag = currencySymbolFlag;
	}

	public CategoryPropertyMappingDVO getSizePropertyRecord() {
		return sizePropertyRecord;
	}

	public void setSizePropertyRecord(CategoryPropertyMappingDVO sizePropertyRecord) {
		this.sizePropertyRecord = sizePropertyRecord;
	}

	public void setCurrencySymbolFlag(boolean currencySymbolFlag) {
		this.currencySymbolFlag = currencySymbolFlag;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public CategoryPropertyMappingDVO getColorPropertyRecord() {
		if (colorPropertyRecord == null) {
			colorPropertyRecord = new CategoryPropertyMappingDVO();
		}
		return colorPropertyRecord;
	}

	public void setColorPropertyRecord(CategoryPropertyMappingDVO colorPropertyRecord) {
		this.colorPropertyRecord = colorPropertyRecord;
	}

	public String getHierarchicalProductTitleForReadMore() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// #{facesContext.externalContext.requestContextPath}

		myLog.debug("hierarchy level 1 :::: name ::"
				+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName());

		hierarchicalProductTitleForReadMore = "<li class=\"levelOne\">"
				+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName()
				+ "</li>"
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName() == null ? ""
						: "<li><a "
								+ (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName() == null ? "class=\"last\""
										: "") + "href=\""
								+ FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
								+ "/retail/products/"
								+ getHierarchyCategoryMappingRecord().getHierarchyRecord().getName() + "/"
								+ getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getName() + "/"
								+ getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName() + "\">"
								+ getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getName()
								+ "</a></li>")
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName() == null ? ""
						: "<li><a class=\"last\" href=\"#\">"
								+ getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getName()
								+ "</a></li>")
				+ (getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getName() == null ? ""
						: "<li><a href=\"#\">"
								+ getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getName()
								+ "</a></li>") + "<li class=\"last\">" + "</li>";

		myLog.debug("hierarchical product title :::::" + hierarchicalProductTitleForReadMore);
		return hierarchicalProductTitleForReadMore;
	}

	public void setHierarchicalProductTitle1(String hierarchicalProductTitleForReadMore) {
		this.hierarchicalProductTitleForReadMore = hierarchicalProductTitleForReadMore;
	}

	public boolean isOnlineProduct() {
		return onlineProduct;
	}

	public void setOnlineProduct(boolean onlineProduct) {
		this.onlineProduct = onlineProduct;
	}

	public ProductSkuStockLevelDVO getProductSkuStockLevelRecord() {
		if (productSkuStockLevelRecord == null) {
			productSkuStockLevelRecord = new ProductSkuStockLevelDVO();
		}
		return productSkuStockLevelRecord;
	}

	public void setProductSkuStockLevelRecord(ProductSkuStockLevelDVO productSkuStockLevelRecord) {
		this.productSkuStockLevelRecord = productSkuStockLevelRecord;
	}

	public static final Comparator<ProductSkuDVO> PRICE_DESCENDING_ORDER = new Comparator<ProductSkuDVO>() {
		public int compare(ProductSkuDVO p1, ProductSkuDVO p2) {
			// ITSDLogger myLog =
			// TSDLogger.getLogger(this.getClass().getName());
			// myLog.debug("p2 :: " + p2.getPriceForComparison() + " :: p1 ::" +
			// p1.getPriceForComparison());
			return (p2.getPriceForComparison().compareTo(p1.getPriceForComparison()));
		}
	};

	public static final Comparator<ProductSkuDVO> ALPHABETIC_ORDER = new Comparator<ProductSkuDVO>() {
		public int compare(ProductSkuDVO p1, ProductSkuDVO p2) {
			// ITSDLogger myLog =
			// TSDLogger.getLogger(this.getClass().getName());
			// myLog.debug("p2 :: " + p2.getPriceForComparison() + " :: p1 ::" +
			// p1.getPriceForComparison());
			return -1 * (p2.getName().compareTo(p1.getName()));
		}
	};

	// public String getHierarchicalBreadcrumbTitle() {
	//
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	//
	// hierarchicalBreadcrumbTitle = "<li class=\"levelOne\">"
	// + getHierarchyLevelOne().getCategoryRecord().getCode()
	// + "</li>"
	// + (getHierarchyLevelTwo().getCategoryRecord().getCode() == null ? "" :
	// "<li><a "
	// + (getHierarchyLevelThree().getCategoryRecord().getCode() == null ?
	// "class=\"last\"" : "")
	// + "href=\"" +
	// FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
	// + "/retail/products/" + getHierarchyRecord().getCode() + "/"
	// + getHierarchyLevelOne().getCategoryRecord().getCode() + "/"
	// + getHierarchyLevelTwo().getCategoryRecord().getCode() + "\">"
	// + getHierarchyLevelTwo().getCategoryRecord().getCode() + "</a></li>")
	// + (getHierarchyLevelThree().getCategoryRecord().getCode() == null ? ""
	// : "<li><a class=\"last\" href=\"#\">" +
	// getHierarchyLevelThree().getCategoryRecord().getCode()
	// + "</a></li>")
	// + (getHierarchyLevelFour().getCategoryRecord().getCode() == null ? "" :
	// "<li><a href=\"#\">"
	// + getHierarchyLevelFour().getCategoryRecord().getCode() + "</a></li>") +
	// "<li class=\"last\">"
	// + "</li>";
	//
	// // KEPT AT TWO LEVELS AS REQUIRED FOR DIAMONDERE ONLY
	// // String levelOneUrl =
	// FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
	// // + CommonConstant.PRODUCT_URL
	// // + (getHierarchyRecord().getName() == null ? "" :
	// getHierarchyRecord().getName())
	// // + (getHierarchyLevelOne().getCategoryRecord().getCode() == null ? "" :
	// "/"
	// // +
	// CommonUtil.removeSpecialCharactersFromString(getHierarchyLevelOne().getCategoryRecord()
	// // .getCode()));
	// // hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" +
	// levelOneUrl + "\">"
	// // + getHierarchyLevelOne().getCategoryRecord().getCode()
	// // + getHierarchyLevelTwo().getCategoryRecord().getCode() + "</a></li>";
	//
	// // myLog.debug("getHierarchicalBreadcrumbTitle:::" +
	// getHierarchyLevelTwo().getCategoryRecord().getCode());
	// //
	// // String levelTwoUrl = levelOneUrl
	// // + (getHierarchyLevelTwo().getCategoryRecord().getName() == null ? "" :
	// "/"
	// // +
	// CommonUtil.removeSpecialCharactersFromString(getHierarchyLevelTwo().getCategoryRecord()
	// // .getName()));
	// // myLog.debug("getHierarchicalBreadcrumbTitle:::" + levelTwoUrl);
	// // if (getHierarchyLevelTwo().getCategoryRecord().getName() == null) {
	// // hierarchicalBreadcrumbTitle = "<li class=\"last\"><a href=\"" +
	// levelOneUrl + "\">"
	// // + getHierarchyLevelOne().getCategoryRecord().getName() + "</a></li>";
	// // } else {
	// // hierarchicalBreadcrumbTitle = "<li><a href=\""
	// // + levelOneUrl
	// // + "\">"
	// // + getHierarchyLevelOne().getCategoryRecord().getName()
	// // + "</a></li>"
	// // + (getHierarchyLevelTwo().getCategoryRecord().getName() == null ? ""
	// // : "<li class=\"last\"><a href=\"" + levelTwoUrl + "\">"
	// // + getHierarchyLevelTwo().getCategoryRecord().getName() + "</a></li>");
	// // }
	//
	// // hierarchicalBreadcrumbTitle = "<li><a href=\""
	// // + levelOneUrl
	// // + "\">"
	// // + getHierarchyLevelOne().getCategoryRecord().getName()
	// // + "</a></li>"
	// // + (getHierarchyLevelTwo().getCategoryRecord().getName() == null ? ""
	// // : "<li><a href=\"" + levelTwoUrl
	// // + "\">" + getHierarchyLevelTwo().getCategoryRecord().getName() +
	// // "</a></li>")
	// // + "<li class=\"last\">" + getName() + "</li>";
	//
	// myLog.debug("getHierarchicalBreadcrumbTitle :: hierarchicalBreadcrumbTitle :: "
	// + hierarchicalBreadcrumbTitle);
	//
	// return hierarchicalBreadcrumbTitle;
	// }

	public String getHierarchicalBreadcrumbTitle() {
		return hierarchicalBreadcrumbTitle;
	}

	public void setHierarchicalBreadcrumbTitle(String hierarchicalBreadcrumbTitle) {
		this.hierarchicalBreadcrumbTitle = hierarchicalBreadcrumbTitle;
	}

	public String getProductPdfURL() {
		return productPdfURL;
	}

	public void setProductPdfURL(String productPdfURL) {
		this.productPdfURL = productPdfURL;
	}

	public Integer getProductQty() {
		return productQty;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	public DeliveryChargesDVO getDeliverChargesRecord() {
		if (deliverChargesRecord == null) {
			deliverChargesRecord = new DeliveryChargesDVO();
		}
		return deliverChargesRecord;
	}

	public void setDeliverChargesRecord(DeliveryChargesDVO deliverChargesRecord) {
		this.deliverChargesRecord = deliverChargesRecord;
	}

	public Float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
	}

	public String getSkuPropertyText() {
		return skuPropertyText;
	}

	public void setSkuPropertyText(String skuPropertyText) {
		this.skuPropertyText = skuPropertyText;
	}

	public String getSkuSEOTitle() {
		return skuSEOTitle;
	}

	public void setSkuSEOTitle(String skuSEOTitle) {
		this.skuSEOTitle = skuSEOTitle;
	}

	public String getSkuSEOKeyword() {
		return skuSEOKeyword;
	}

	public void setSkuSEOKeyword(String skuSEOKeyword) {
		this.skuSEOKeyword = skuSEOKeyword;
	}

	public String getSkuSEODescription() {
		return skuSEODescription;
	}

	public void setSkuSEODescription(String skuSEODescription) {
		this.skuSEODescription = skuSEODescription;
	}

	public ArrayList<ProductSizeMappingDVO> getProductSizeMappingList() {
		if (productSizeMappingList == null) {
			productSizeMappingList = new ArrayList<ProductSizeMappingDVO>();
		}
		return productSizeMappingList;
	}

	public void setProductSizeMappingList(ArrayList<ProductSizeMappingDVO> productSizeMappingList) {
		this.productSizeMappingList = productSizeMappingList;
	}

	public StatusDVO getStatusRecord() {
		if (statusRecord == null) {
			statusRecord = new StatusDVO();
		}
		return statusRecord;
	}

	public void setStatusRecord(StatusDVO statusRecord) {
		this.statusRecord = statusRecord;
	}

	public ProductSkuImageMappingDVO getDefaultProductSkuImageMappingDVO() {
		if (defaultProductSkuImageMappingDVO == null) {
			defaultProductSkuImageMappingDVO = new ProductSkuImageMappingDVO();
		}
		return defaultProductSkuImageMappingDVO;
	}

	public void setDefaultProductSkuImageMappingDVO(ProductSkuImageMappingDVO defaultProductSkuImageMappingDVO) {
		this.defaultProductSkuImageMappingDVO = defaultProductSkuImageMappingDVO;
	}

	public Boolean getDefaultSku() {
		return defaultSku;
	}

	public void setDefaultSku(Boolean defaultSku) {
		this.defaultSku = defaultSku;
	}

}
