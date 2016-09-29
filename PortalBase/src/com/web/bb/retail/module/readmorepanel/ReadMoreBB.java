package com.web.bb.retail.module.readmorepanel;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import com.web.bf.retail.modules.readmorepanel.ReadMoreBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.ReadMoreOpr;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.systemowner.CurrencyDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.MessageFormatter;
import com.web.util.PropertiesReader;
import com.web.util.deepcopy.DeepCopy;

public class ReadMoreBB extends BackingBean {
	private static final long serialVersionUID = -2773027344327036324L;
	private String propertiesLocation = "/com/web/bb/retail/module/readmorepanel/readmore";
	private ReadMoreOpr readMoreOpr;
	private ReadMoreOpr readMoreOriginalOpr;
	private ReadMoreOpr productImageRecord;
	private ReadMoreOpr addToCartProductRecord;
	private ReadMoreOpr makeEnquiryProductRecord;
	private ReadMoreOpr priceAlertProductRecord;
	private ReadMoreOpr tellAFriendProductRecord;
	private ReadMoreOpr convertedPriceReadMoreOpr;
	private boolean showActionLinks;
	private boolean fromBrowseProductsPage;
	private String currentProductId;
	private ProductSkuDVO productToSave;
	private CurrencyDVO selectedCurrencyRecord;
	private ReadMoreOpr priceConversionRecord;
	private boolean addProductSuccessful;
	private boolean sizeSelect;
	private boolean navigatePanel;
	// temporary place holders for engraving
	// applied after "apply now" is clicked
	private String engraveText;
	private String engraveFont = "Lucida Handwriting";
	private String engraveTextClass;
	private String sizeCode = "";
	private Float increasePercent;
	private Float originalBasePrice;
	private boolean selectEngrave = false;
	private String engraveTextSilverClass;
	private Float originalDiscountPrice;
	private Float emiPrice;
	private String countryName;
	private boolean symbolFlag;
	private Float conversionRate;

	// end temporary place holders for engraving

	public Float getConversionRate() {
		return conversionRate;
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

	public ReadMoreOpr getConvertedPriceReadMoreOpr() {
		if (convertedPriceReadMoreOpr == null) {
			convertedPriceReadMoreOpr = new ReadMoreOpr();
		}
		return convertedPriceReadMoreOpr;
	}

	public void setConvertedPriceReadMoreOpr(ReadMoreOpr convertedPriceReadMoreOpr) {
		this.convertedPriceReadMoreOpr = convertedPriceReadMoreOpr;
	}

	public boolean isFromBrowseProductsPage() {
		return fromBrowseProductsPage;
	}

	public void setFromBrowseProductsPage(boolean fromBrowseProductsPage) {
		this.fromBrowseProductsPage = fromBrowseProductsPage;
	}

	public ReadMoreOpr getPriceAlertProductRecord() {
		if (priceAlertProductRecord == null) {
			priceAlertProductRecord = new ReadMoreOpr();
		}
		return priceAlertProductRecord;
	}

	public void setPriceAlertProductRecord(ReadMoreOpr priceAlertProductRecord) {
		this.priceAlertProductRecord = priceAlertProductRecord;
	}

	public ReadMoreOpr getTellAFriendProductRecord() {
		if (tellAFriendProductRecord == null) {
			tellAFriendProductRecord = new ReadMoreOpr();
		}
		return tellAFriendProductRecord;
	}

	public void setTellAFriendProductRecord(ReadMoreOpr tellAFriendProductRecord) {
		this.tellAFriendProductRecord = tellAFriendProductRecord;
	}

	public String getCurrentProductId() {
		return currentProductId;
	}

	public void setCurrentProductId(String currentProductId) {
		this.currentProductId = currentProductId;
	}

	public void loadProductFromURL() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside loadProductFromURL ::: " + currentProductId);
		if (currentProductId != null) {
			myLog.debug("current product id is " + currentProductId);
			ProductSkuDVO productSkuDVO = new ProductSkuDVO();

			StringTokenizer st = new StringTokenizer(currentProductId, "-");
			myLog.debug("split string is " + st);
			int i = 0;
			String productNamePlaceHolder = null;
			try {
				while (st.hasMoreElements()) {
					i = i + 1;
					myLog.debug("counter is " + i);
					if (i == 1) {
						myLog.debug("i is " + 1);
						productSkuDVO.getProductRecord().setId(Long.valueOf(st.nextElement().toString()));
					} else if (i == 2) {
						myLog.debug("i is " + 1);
						productSkuDVO.setId(Long.valueOf(st.nextElement().toString()));
					} else {
						myLog.debug("productNamePlaceHolder - before - " + productNamePlaceHolder);
						productNamePlaceHolder = st.nextElement().toString()
								+ (productNamePlaceHolder == null ? "" : (" | " + productNamePlaceHolder));
						myLog.debug("productNamePlaceHolder - after - " + productNamePlaceHolder);
					}
				}
			} catch (NoSuchElementException e) {
				// TODO: handle exception
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}

			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put("product.name", productNamePlaceHolder);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product.uri", currentProductId);

			// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			// .put(CommonConstant.READ_MORE_PRODUCT_DVO, productDVO);
			// FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			// .put(CommonConstant.COMPLEMENTARY_PRODUCT_DVO, productDVO);
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			// .put(CommonConstant.ACTIVE_PAGE, CommonConstant.READ_MORE_PAGE);
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			// .put(CommonConstant.NO_REDIRECT_TO_HOME_PAGE,
			// CommonConstant.NO_REDIRECT_TO_HOME_PAGE);

			getReadMoreOpr().setProductSkuRecord(productSkuDVO);

			fetchProductDetails();

			// shift size to all options
			// getAllOptions().getAllOptionsValues().put(CommonConstant.SIZE_LIST,
			// readMoreOpr.getProductSkuRecord().getSizeRecord().getAllOptions());

		}
	}

	public void addToCart() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		boolean validateVariant = false;
		String skuCode = readMoreOpr.getProductSkuRecord().getId().toString() + "~";

		myLog.debug("check for selected size:::::" + readMoreOpr.getProductSkuRecord().getSizeRecord().getCode());
		myLog.debug("addToCart :: skuCode ::" + skuCode);
		myLog.debug("current sku code   " + readMoreOpr.getProductSkuRecord().getSkuCodeMap());

		if (readMoreOpr.getProductSkuRecord().getSkuCodeMap().isEmpty()) {
			validateVariant = true;
			addProductSuccessful = true;
			myLog.debug("addToCart :: skuCode match not needed :: map empty");
		} else {
			if (readMoreOpr.getProductSkuRecord().getSkuCodeMap().containsKey(skuCode)) {
				myLog.debug("addToCart :: skuCode matched ::");
				validateVariant = true;
				addProductSuccessful = true;
			} else {
				addProductSuccessful = false;
				myLog.error("addToCart :: skuCode match not found :: " + skuCode);
			}
		}
		if (getAllOptions().getAllOptionsValues().get(CommonConstant.SIZE_LIST) != null
				&& !allOptions.getAllOptionsValues().get(CommonConstant.SIZE_LIST).isEmpty()) {
			myLog.debug("addToCart :: select size can be insert null ::");
			if (sizeCode == null || sizeCode.trim().length() == 0) {
				myLog.debug("inside for for size check and put in opr1111111");
				// addToErrorList(new
				// PropertiesReader(propertiesLocation).getValueOfKey("select_size"));
				validateVariant = false;
				sizeSelect = false;
			} else if (sizeCode != null && sizeCode.trim().length() >= 0) {
				sizeSelect = true;
				myLog.debug("inside for for size check and put in opr:::::::");
				readMoreOpr.getProductSkuRecord().getSizeRecord().setCode(sizeCode.trim());
			}
		} else {
			sizeSelect = true;
		}

		if (addProductSuccessful && sizeSelect) {
			navigatePanel = true;
		} else {
			navigatePanel = false;
		}
		// if (sizeCode != null && sizeCode.trim().length() >= 0) {
		// myLog.debug("inside for for size check and put in opr:::::::");
		// readMoreOpr.getProductSkuRecord().getSizeRecord().setCode(sizeCode.trim());
		// }

		if (validateVariant) {
			ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get(CommonConstant.SHOPPING_CART_OPR);
			if (shoppingCartOpr == null) {
				shoppingCartOpr = new ShoppingCartOpr();
			}
			myLog.debug("check for engrave and seleced size:::::"
					+ addToCartProductRecord.getShoppingCartProduct().getProductSkuRecord().getEngraveText() + "AND"
					+ addToCartProductRecord.getShoppingCartProduct().getProductSkuRecord().getSizeRecord().getCode());

			myLog.debug("check for original discount price in readmore bb:::::::"
					+ addToCartProductRecord.getProductSkuRecord().getOriginalDiscountPrice());

			// myLog.debug("Delivery time in readmore BB ::"
			// +
			// readMoreOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord().getDeliveryTime());
			// addToCartProductRecord
			// .getShoppingCartProduct()
			// .getProductSkuRecord()
			// .getHierarchyLevelOne()
			// .getCategoryRecord()
			// .setDeliveryTime(
			// readMoreOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord()
			// .getDeliveryTime());
			// myLog.debug("Delivery time in readmore BB ::"
			// +
			// addToCartProductRecord.getShoppingCartProduct().getProductSkuRecord().getHierarchyLevelOne()
			// .getCategoryRecord().getDeliveryTime());
			// addToCartProductRecord
			// .getShoppingCartProduct()
			// .getProductSkuRecord()
			// .getHierarchyLevelTwo()
			// .getCategoryRecord()
			// .setDeliveryTime(
			// readMoreOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord()
			// .getDeliveryTime());
			// addToCartProductRecord
			// .getShoppingCartProduct()
			// .getProductSkuRecord()
			// .getHierarchyLevelThree()
			// .getCategoryRecord()
			// .setDeliveryTime(
			// readMoreOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord()
			// .getDeliveryTime());
			// addToCartProductRecord
			// .getShoppingCartProduct()
			// .getProductSkuRecord()
			// .getHierarchyLevelFour()
			// .getCategoryRecord()
			// .setDeliveryTime(
			// readMoreOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord()
			// .getDeliveryTime());

			// GEOPLUGIN- To set original price per piece
			addToCartProductRecord.getProductSkuRecord().setOriginalBasePrice(
					readMoreOpr.getProductSkuRecord().getOriginalBasePrice());
			addToCartProductRecord.getProductSkuRecord().setOriginalDiscountPrice(
					readMoreOpr.getProductSkuRecord().getOriginalDiscountPrice());
			addToCartProductRecord.getProductSkuRecord().setOriginalCurrencyRecord(
					readMoreOpr.getProductSkuRecord().getOriginalCurrencyRecord());
			addToCartProductRecord.getShoppingCartProduct().getProductSkuRecord()
					.setProductSkuStockLevelRecord(readMoreOpr.getProductSkuRecord().getProductSkuStockLevelRecord());
			myLog.debug("READ MORE:: stock  "
					+ readMoreOpr.getProductSkuRecord().getProductSkuStockLevelRecord().getAvailableQuantity());

			myLog.debug("check for original base price in readmore bb:::::::"
					+ addToCartProductRecord.getProductSkuRecord().getOriginalBasePrice());
			shoppingCartOpr.getShoppingCartProductList().add(addToCartProductRecord.getShoppingCartProduct());

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.SHOPPING_CART_OPR, shoppingCartOpr);
			// String[] messageArguments = {
			// addToCartProductRecord.getShoppingCartProduct().getProductSkuRecord().getName()
			// };
			// setSuccessMsg(MessageFormatter.getFormattedMessage(
			// new
			// PropertiesReader(propertiesLocation).getValueOfKey("product_added_to_cart"),
			// messageArguments));

			addToCartProductRecord = new ReadMoreOpr();
			sizeCode = "";
		} else {
			myLog.debug("Variant does not exist :::::: ");
		}

	}

	// public String makeEnquiryClicked() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// // make a call to audit
	//
	// ExternalContext externalContext =
	// FacesContext.getCurrentInstance().getExternalContext();
	// externalContext.getRequestMap().put(CommonConstant.SEND_ENQUIRY_PRODUCT_DVO,
	// makeEnquiryProductRecord.getProductSkuRecord());
	//
	// myLog.debug("make enquiry clicked");
	// return null;
	// }

	// public String setPriceAlertClicked() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// myLog.debug("priceAlertProductRecord.getProductSkuRecord() " +
	// priceAlertProductRecord.getProductSkuRecord());
	// // make a call to audit
	// ExternalContext externalContext =
	// FacesContext.getCurrentInstance().getExternalContext();
	// externalContext.getRequestMap().put(CommonConstant.PRICE_ALERT_PRODUCT_DVO,
	// priceAlertProductRecord.getProductSkuRecord());
	// myLog.debug("set price alert clicked");
	// return null;
	// }

	// public String tellAFriendClicked() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// // make a call to audit
	// myLog.debug("tell a friend id" +
	// tellAFriendProductRecord.getProductSkuRecord().getId());
	// ExternalContext externalContext =
	// FacesContext.getCurrentInstance().getExternalContext();
	// externalContext.getRequestMap().put(CommonConstant.TELL_A_FRIEND_PRODUCT_DVO,
	// tellAFriendProductRecord.getProductSkuRecord());
	//
	// return null;
	// }

	public ReadMoreOpr getAddToCartProductRecord() {
		if (addToCartProductRecord == null) {
			addToCartProductRecord = new ReadMoreOpr();
		}
		return addToCartProductRecord;
	}

	public void setAddToCartProductRecord(ReadMoreOpr addToCartProductRecord) {
		this.addToCartProductRecord = addToCartProductRecord;
	}

	public ReadMoreOpr getMakeEnquiryProductRecord() {
		if (makeEnquiryProductRecord == null) {
			makeEnquiryProductRecord = new ReadMoreOpr();
		}
		return makeEnquiryProductRecord;
	}

	public void setMakeEnquiryProductRecord(ReadMoreOpr makeEnquiryProductRecord) {
		this.makeEnquiryProductRecord = makeEnquiryProductRecord;
	}

	// public ReadMoreOpr getReadMoreOriginalOpr() {
	// if (readMoreOriginalOpr == null) {
	// readMoreOriginalOpr = new ReadMoreOpr();
	// }
	// return readMoreOriginalOpr;
	// }
	//
	// public void setReadMoreOriginalOpr(ReadMoreOpr readMoreOriginalOpr) {
	// this.readMoreOriginalOpr = readMoreOriginalOpr;
	// }

	public void enlargeImageClicked() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.IMAGE_DVO, productImageRecord.getProductSkuRecord().getImageRecord());
	}

	public ReadMoreOpr getProductImageRecord() {
		if (productImageRecord == null) {
			productImageRecord = new ReadMoreOpr();
		}
		return productImageRecord;
	}

	public void setProductImageRecord(ReadMoreOpr productImageRecord) {
		this.productImageRecord = productImageRecord;
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

	public ReadMoreOpr getReadMoreOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ReadMoreBF readMoreBF = new ReadMoreBF();

		if (readMoreOpr == null) {
			readMoreOpr = new ReadMoreOpr();
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get(CommonConstant.READ_MORE_PRODUCT_DVO) != null) {
			readMoreOpr.setProductSkuRecord((ProductSkuDVO) FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().get(CommonConstant.READ_MORE_PRODUCT_DVO));
			myLog.debug("set readmoreOpr from facescontext");
		}

		if ((FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get(CommonConstant.READ_MORE_PRODUCT_DVO) != null)
				|| ((readMoreOpr.getProductSkuRecord().getId() != null) && (FacesContext.getCurrentInstance()
						.getExternalContext().getRequestMap().get(CommonConstant.RE_INITIALIZE_OPR) != null))) {
			myLog.debug("getReadMoreOpr ::: inside if block :::");
			// if
			// (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			// .get(CommonConstant.READ_MORE_FROM_BROWSE_PRODUCTS_PAGE) != null)
			// {
			// fromBrowseProductsPage = true;
			// }
			// if
			// (!readMoreOpr.getProductSkuRecord().getOperationalAttributes().getRecordPopulated())
			// {
			// try {
			// myLog.debug("calling bf");
			// readMoreOpr =
			// readMoreBF.getProductDetailsForReadMore(readMoreOpr);
			//
			// myLog.debug("Display productlist size==========" +
			// readMoreOpr.getDisplayProductList().size());
			//
			// // add the main product dvo as the first one in the list
			// ProductVariantsImageMappingDVO productVariantsImageMappedRecord =
			// new ProductVariantsImageMappingDVO();
			// productVariantsImageMappedRecord.setId(null);
			// productVariantsImageMappedRecord.setMultiImageUrl(readMoreOpr.getProductSkuRecord()
			// .getImageRecord().getImageURL());
			// productVariantsImageMappedRecord.setThumbnailImageURL(readMoreOpr.getProductSkuRecord()
			// .getImageRecord().getThumbnailImageURL());
			// productVariantsImageMappedRecord.setZoomImageURL(readMoreOpr.getProductSkuRecord().getImageRecord()
			// .getZoomImageURL());
			// productVariantsImageMappedRecord.setSequenceNumber(Integer.valueOf(-1));
			// readMoreOpr.getProductSkuRecord().getProductVariantsImageMappingList()
			// .add(0, productVariantsImageMappedRecord);
			// // set record populated
			// readMoreOpr.getProductSkuRecord().getOperationalAttributes().setRecordPopulated(true);
			// } catch (FrameworkException e) {
			// // handle framework exception
			// handleException(e, propertiesLocation);
			// } catch (BusinessException e) {
			// // handle BusinessException;
			// handleException(e, propertiesLocation);
			// }
			// }
		}

		//
		// myLog.debug("readMoreOpr.getProductSkuRecord().getImageRecord().getImageURL() "
		// + readMoreOpr.getProductSkuRecord().getImageRecord().getImageURL());
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get(CommonConstant.READ_MORE_PRODUCT_DVO) != null) {
			readMoreOriginalOpr = (ReadMoreOpr) DeepCopy.copy(readMoreOpr);
		}

		// if
		// (readMoreOpr.getProductSkuRecord().getApplicationFlags().getApplicationFlagMap()
		// .get("NullPropertiesRemoved") == null) {
		// ArrayList<ProductPropertiesMappingDVO> ppl = new
		// ArrayList<ProductPropertiesMappingDVO>();
		// myLog.debug("=== Property List Size ===="
		// +
		// readMoreOpr.getProductSkuRecord().getProductPropertiesList().size());
		// for (int i = 0; i <
		// readMoreOpr.getProductSkuRecord().getProductPropertiesList().size();
		// i++) {
		// myLog.debug("=== Property List Size VALUE ===="
		// +
		// readMoreOpr.getProductSkuRecord().getProductPropertiesList().get(i).getPropertyValueText());
		// if
		// (!(((readMoreOpr.getProductSkuRecord().getProductPropertiesList().get(i).getUomRecord().getCode()
		// == null)
		// || (readMoreOpr
		// .getProductSkuRecord().getProductPropertiesList().get(i).getUomRecord().getCode().trim()
		// .length() == 0)) &&
		// ((readMoreOpr.getProductSkuRecord().getProductPropertiesList().get(i)
		// .getPropertyValueText() == null) ||
		// (readMoreOpr.getProductSkuRecord()
		// .getProductPropertiesList().get(i).getPropertyValueText().trim().length()
		// == 0)))) {
		// ppl.add(readMoreOpr.getProductSkuRecord().getProductPropertiesList().get(i));
		// }
		// }
		// readMoreOpr.getProductSkuRecord().setProductPropertiesList(ppl);
		// myLog.debug("=== VARIANT List Size  in READMORE OPR===="
		// + readMoreOpr.getProductSkuRecord().getVariantList().size());
		// for (int j = 0; j <
		// readMoreOpr.getProductSkuRecord().getVariantList().size(); j++) {
		// ArrayList<ProductPropertiesMappingDVO> pplVariant = new
		// ArrayList<ProductPropertiesMappingDVO>();
		//
		// for (int i = 0; i <
		//
		// readMoreOpr.getProductSkuRecord().getVariantList().get(j).getProductPropertiesList().size();
		// i++) {
		// if
		//
		// (!(((readMoreOpr.getProductSkuRecord().getVariantList().get(j).getProductPropertiesList().get(i)
		// .getUomRecord().getCode() == null) ||
		// (readMoreOpr.getProductSkuRecord().getVariantList()
		//
		// .get(j).getProductPropertiesList().get(i).getUomRecord().getCode().trim().length()
		// == 0)) && ((readMoreOpr
		//
		// .getProductSkuRecord().getVariantList().get(j).getProductPropertiesList().get(i)
		// .getPropertyValueText() == null) ||
		// (readMoreOpr.getProductSkuRecord().getVariantList()
		// .get(j)
		//
		// .getProductPropertiesList().get(i).getPropertyValueText().trim().length()
		// == 0)))) {
		//
		// pplVariant.add(readMoreOpr.getProductSkuRecord().getVariantList().get(j)
		// .getProductPropertiesList().get(i));
		// }
		// }
		//
		// readMoreOpr.getProductSkuRecord().getVariantList().get(j).setProductPropertiesList(pplVariant);
		// }
		//
		// readMoreOpr.getProductSkuRecord().getApplicationFlags().getApplicationFlagMap()
		// .put("NullPropertiesRemoved", "NullPropertiesRemoved");
		// }

		// sortProperties();
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.remove(CommonConstant.READ_MORE_PRODUCT_DVO);
		return readMoreOpr;
	}

	/**
	 * @param readMoreOpr
	 *            the readMoreOpr to set
	 */
	public void setReadMoreOpr(ReadMoreOpr readMoreOpr) {
		this.readMoreOpr = readMoreOpr;
	}

	public void populateWebAttributes() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String pageName = null;
		pageName = CommonConstant.READ_MORE_PAGE;

		String productName = "";
		if (externalContext.getRequestMap().get("product.name") != null) {
			productName = (String) externalContext.getRequestMap().get("product.name");
		}
		String productUri = "";
		if (externalContext.getRequestMap().get("product.uri") != null) {
			productUri = (String) externalContext.getRequestMap().get("product.uri");
		}

		myLog.debug("pageName " + pageName);

		populateWebResourceAttributes(pageName);

		myLog.debug("after parent backing bean call ");
		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_DISPLAY_NAME",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME")).replaceAll(
						"~product.name~", productName));
		myLog.debug("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME "
				+ externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_TITLE",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_TITLE")).replaceAll(
						"~product.name~", productName));
		myLog.debug("WEB_ATTRIBUTES_PAGE_TITLE " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_TITLE"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_DESCRIPTION",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_DESCRIPTION")).replaceAll(
						"~product.name~", productName));
		myLog.debug("WEB_ATTRIBUTES_DESCRIPTION " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_DESCRIPTION"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_KEYWORDS",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_KEYWORDS")).replaceAll("~product.name~",
						productName));
		myLog.debug("WEB_ATTRIBUTES_KEYWORDS " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_KEYWORDS"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_CANONICAL_HREF",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_CANONICAL_HREF")).replaceAll(
						"~product.name~", productName));
		myLog.debug("WEB_ATTRIBUTES_CANONICAL_HREF "
				+ externalContext.getRequestMap().get("WEB_ATTRIBUTES_CANONICAL_HREF"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_DISPLAY_NAME",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME")).replaceAll(
						"~product.name~", productUri));
		myLog.debug("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME "
				+ externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_DISPLAY_NAME"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_PAGE_TITLE",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_TITLE")).replaceAll("~product.uri~",
						productUri));
		myLog.debug("WEB_ATTRIBUTES_PAGE_TITLE " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_PAGE_TITLE"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_DESCRIPTION",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_DESCRIPTION")).replaceAll(
						"~product.uri~", productUri));
		myLog.debug("WEB_ATTRIBUTES_DESCRIPTION " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_DESCRIPTION"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_KEYWORDS",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_KEYWORDS")).replaceAll("~product.uri~",
						productUri));
		myLog.debug("WEB_ATTRIBUTES_KEYWORDS " + externalContext.getRequestMap().get("WEB_ATTRIBUTES_KEYWORDS"));

		externalContext.getRequestMap().put(
				"WEB_ATTRIBUTES_CANONICAL_HREF",
				((String) externalContext.getRequestMap().get("WEB_ATTRIBUTES_CANONICAL_HREF")).replaceAll(
						"~product.uri~", productUri));
		myLog.debug("WEB_ATTRIBUTES_CANONICAL_HREF "
				+ externalContext.getRequestMap().get("WEB_ATTRIBUTES_CANONICAL_HREF"));
		myLog.debug("after updation");

		String context = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath();
		myLog.debug("context==" + context);
		myLog.debug("image url==" + getReadMoreOpr().getProductSkuRecord().getImageRecord().getImageURL());
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL,
				context + getReadMoreOpr().getProductSkuRecord().getImageRecord().getImageURL());
		myLog.debug("product image url1"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL));
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_TITLE,
				readMoreOpr.getProductSkuRecord().getName());
		myLog.debug("product title1"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_TITLE));
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION,
				readMoreOpr.getProductSkuRecord().getDescription());
		myLog.debug("product description1"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION));
		externalContext.getRequestMap().put(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL,
				context + readMoreOpr.getProductSkuRecord().getProductURL());
		myLog.debug("product image url"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL));
		myLog.debug("product title"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_TITLE));
		myLog.debug("product description"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_DESCRIPTION));
		myLog.debug("product url"
				+ externalContext.getRequestMap().get(CommonConstant.PopulateFacebookData.PRODUCT_READMORE_URL));

	}

	public String getPrintReadmorePage() {
		return "ui/retail/modules/readmorepanel/printreadmorepanel.jsf";
	}

	public void printReadmore(ActionEvent actionEvent) {
		ITSDLogger myLog = TSDLogger.getLogger(getClass().getName());
		myLog.debug("LeastSellingProductsBB :: printReport is clicked");
		if (readMoreOpr != null) {
			myLog.debug("readMoreOpr is not null");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("readMoreDetails", readMoreOpr);
		}
		myLog.debug("outside READMOREBB :: printReadmore()");
	}

	public String saveProductForLater() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.1
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		try {
			ReadMoreOpr readMoreSaveOpr = new ReadMoreOpr();
			ShoppingCartProductDVO shoppingCartProductDVO = new ShoppingCartProductDVO();
			shoppingCartProductDVO.setProductSkuRecord(productToSave);
			shoppingCartProductDVO.getUserRecord().setUserLogin(
					(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get(CommonConstant.LOGGED_USER_KEY));
			readMoreSaveOpr.setShoppingCartProduct(shoppingCartProductDVO);
			new ReadMoreBF().saveProductForLater(readMoreSaveOpr);
			String[] messageArguments = { shoppingCartProductDVO.getProductSkuRecord().getName() };
			setSuccessMsg(MessageFormatter.getFormattedMessage(
					new PropertiesReader(propertiesLocation).getValueOfKey("save_success"), messageArguments));
			myLog.debug("productToSave.hashcode() " + productToSave.hashCode());
			// for (int i = 0; i < readMoreOpr.get().size(); i++) {
			// if (productToSave.hashCode() ==
			// shoppingCartOpr.getShoppingCartProductList().get(i).hashCode()) {
			// myLog.debug("shoppingCartOpr.getShoppingCartProductList().get(i).hashCode() "
			// +
			// shoppingCartOpr.getShoppingCartProductList().get(i).hashCode());
			// shoppingCartOpr.getShoppingCartProductList().remove(i);
			// }
			// }
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			// handle business exception
			handleException(e, propertiesLocation);
		}
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.REFRESH_SAVED_PRODUCTS, CommonConstant.REFRESH_SAVED_PRODUCTS);

		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.put(CommonConstant.REFRESH_SAVED_PRODUCTS, CommonConstant.REFRESH_SAVED_PRODUCTS);

		return null;
	}

	public ProductSkuDVO getProductToSave() {
		if (productToSave == null) {
			productToSave = new ProductSkuDVO();
		}
		return productToSave;
	}

	public void setProductToSave(ProductSkuDVO productToSave) {
		this.productToSave = productToSave;
	}

	public CurrencyDVO getSelectedCurrencyRecord() {
		if (selectedCurrencyRecord == null) {
			selectedCurrencyRecord = new CurrencyDVO();
		}
		return selectedCurrencyRecord;
	}

	public void setSelectedCurrencyRecord(CurrencyDVO selectedCurrencyRecord) {
		this.selectedCurrencyRecord = selectedCurrencyRecord;
	}

	// public void makeEnquiry(ActionEvent ae) {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// setErrorList(new ArrayList<String>());
	// setSuccessMsg("");
	// PortalValidator portalValidator = new PortalValidator();
	// PropertiesReader propertiesReader = new
	// PropertiesReader(propertiesLocation);
	// String errorMessage = null;
	// if
	// (!portalValidator.validateNull(makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().getFirstName()))
	// {
	// addToErrorList(propertiesReader.getValueOfKey("make_enquiry_name_null"));
	// }
	// if
	// (!portalValidator.validateNull(makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().getEmail()))
	// {
	// addToErrorList(propertiesReader.getValueOfKey("make_enquiry_email_null"));
	// } else if
	// (!portalValidator.validateEmail(makeEnquiryProductRecord.getSendEnquiryRecord().getAddress()
	// .getEmail())) {
	// addToErrorList(propertiesReader.getValueOfKey("make_enquiry_email_invalid"));
	// }
	//
	// String userLogin = (String)
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .get(CommonConstant.LOGGED_USER_KEY);
	// if (userLogin == null) {
	// userLogin = CommonConstant.GUEST_USER;
	// }
	//
	// if (getErrorList().size() == 0) {
	// try {
	// makeEnquiryProductRecord.getApplicationFlags().getApplicationFlagMap()
	// .put(CommonConstant.LOGGED_USER_KEY, userLogin);
	// new ReadMoreBF().makeEnquiry(makeEnquiryProductRecord);
	// errorMessage = propertiesReader.getValueOfKey("make_enquiry_successful");
	// setSuccessMsg(errorMessage);
	// makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().setFirstName(null);
	// makeEnquiryProductRecord.getSendEnquiryRecord().setComments(null);
	// makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().setEmail(null);
	// } catch (FrameworkException e) {
	// // handle framework exception
	// handleException(e, propertiesLocation);
	// }
	// }
	// }

	public ReadMoreOpr getPriceConversionRecord() {
		if (priceConversionRecord == null) {
			priceConversionRecord = new ReadMoreOpr();
		}
		return priceConversionRecord;
	}

	public void setPriceConversionRecord(ReadMoreOpr priceConversionRecord) {
		this.priceConversionRecord = priceConversionRecord;
	}

	public boolean isAddProductSuccessful() {
		return addProductSuccessful;
	}

	public void setAddProductSuccessful(boolean addProductSuccessful) {
		this.addProductSuccessful = addProductSuccessful;
	}

	// public void valueChangedListener(AjaxBehaviorEvent abe) {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// String skuCode = readMoreOpr.getProductSkuRecord().getId().toString() +
	// "~";
	// myLog.debug("check sku code before for loop:::::" + skuCode);
	// selectEngrave = false;
	// sizeCode = "";
	// for (int i = 0; i <
	// readMoreOpr.getProductSkuRecord().getEditablePropertiesList().size();
	// i++) {
	// if
	// (readMoreOpr.getProductSkuRecord().getEditablePropertiesList().get(i).getChosenValue().equals("Yellow"))
	// {
	// selectEngrave = true;
	// }
	// skuCode +=
	// readMoreOpr.getProductSkuRecord().getEditablePropertiesList().get(i).getChosenValue()
	// + "~";
	// myLog.debug("check sku code inside for loop:::::" + skuCode);
	// }
	//
	// myLog.debug("valueChangedListener :: skuCode ::" + skuCode);
	//
	// if
	// (readMoreOpr.getProductSkuRecord().getSkuCodeMap().containsKey(skuCode))
	// {
	// myLog.debug("valueChangedListener :: skuCode matched ::");
	// addProductSuccessful = true;
	//
	// ProductDVO productDVO =
	// readMoreOpr.getProductSkuRecord().getSkuCodeMap().get(skuCode);
	// myLog.debug("valueChangedListener :: product id :: " +
	// productDVO.getId());
	// myLog.debug("valueChangedListener :: variant id :: " +
	// productDVO.getVariantId());
	//
	// readMoreOpr = new ReadMoreOpr();
	//
	// getReadMoreOpr().setProductRecord(productDVO);
	// fetchProductDetails();
	// } else {
	// addProductSuccessful = false;
	// myLog.error("valueChangedListener :: skuCode match not found :: " +
	// skuCode);
	// }
	//
	// }

	private void fetchProductDetails() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		try {
			myLog.debug("calling bf");
			readMoreOpr = new ReadMoreBF().getProductDetailsForReadMore(readMoreOpr);

			readMoreOpr = new ReadMoreBF().getCategoriesForProduct(readMoreOpr);

			readMoreOpr = new ReadMoreBF().getProductAlternativeImages(readMoreOpr);

			readMoreOpr = new ReadMoreBF().getProductSizes(readMoreOpr);

			String imageUrl = null;
			if (readMoreOpr.getProductSkuRecord().getImageRecord().getImageURL() != null) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getRequestMap()
						.put(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL,
								readMoreOpr.getProductSkuRecord().getImageRecord().getImageURL());
			} else {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getRequestMap()
						.put(CommonConstant.PopulateFacebookData.PRODUCT_IMAGE_URL,
								CommonConstant.PopulateFacebookData.HEADER_LOGO_IMAGE_URL);
				imageUrl = CommonConstant.PopulateFacebookData.HEADER_LOGO_IMAGE_URL;
			}

			myLog.debug("image url here :::::::::::::::" + imageUrl);

			convertedPrice(readMoreOpr);

			originalBasePrice = readMoreOpr.getProductSkuRecord().getBasePrice();
			if (readMoreOpr.getProductSkuRecord().getDiscountPrice() != null
					&& readMoreOpr.getProductSkuRecord().getDiscountPrice() > 0.0f) {
				originalDiscountPrice = readMoreOpr.getProductSkuRecord().getDiscountPrice();
			}

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public String getEngraveText() {
		if (engraveText == null || engraveText.trim().length() == 0) {
			engraveText = "Your Message here ...";
		}
		return engraveText;
	}

	public void setEngraveText(String engraveText) {
		this.engraveText = engraveText;
	}

	public String getEngraveFont() {
		if (engraveFont == null || engraveFont.trim().length() == 0) {
			engraveFont = "Lucida Handwriting";
		}
		return engraveFont;
	}

	public void setEngraveFont(String engraveFont) {
		this.engraveFont = engraveFont;
	}

	public String getEngraveTextClass() {
		if (engraveTextClass == null || engraveTextClass.trim().length() == 0) {
			engraveTextClass = "messageTextLucidaHandwriting";
		}
		return engraveTextClass;
	}

	public void setEngraveTextClass(String engraveTextClass) {
		this.engraveTextClass = engraveTextClass;
	}

	public void updateEngraving(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside updateEngraving :: engraveText :: " + engraveText);
		myLog.debug("inside updateEngraving :: engraveFont :: " + engraveFont);
		readMoreOpr.getProductSkuRecord().setEngraveText(engraveText);
		readMoreOpr.getProductSkuRecord().setEngraveFont(engraveFont);
	}

	public void sizeChangeListner(AjaxBehaviorEvent abe) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String sCode = getSizeCode().trim();
		myLog.debug("Inside sizeChangeListner:::::" + sCode);
		// readMoreOpr.getProductSkuRecord().getSizeRecord().setCode(sCode);
		myLog.debug("readMoreOpr.getProductSkuRecord().getSizeRecord()"
				+ readMoreOpr.getProductSkuRecord().getSizeRecord().getCode());
		// readMoreOpr.getProductSkuRecord().setBasePrice(originalBasePrice);
		Float baseprice = originalBasePrice;
		Float discountprice = originalDiscountPrice;
		Float increasePrice = null;

		myLog.debug("Base price:::" + baseprice);
		myLog.debug("Discount price:::" + discountprice);
		myLog.debug("mapped size list:::" + readMoreOpr.getProductSkuRecord().getMappedSizeList().size());
		for (int i = 0; i < readMoreOpr.getProductSkuRecord().getMappedSizeList().size(); i++) {
			if (sCode != null
					&& sCode.length() > 0
					&& sCode.equals(readMoreOpr.getProductSkuRecord().getMappedSizeList().get(i).getSizeRecord()
							.getCode())) {
				increasePercent = readMoreOpr.getProductSkuRecord().getMappedSizeList().get(i).getSizeRecord()
						.getIncreasingPriceInPercent();
				break;
			} else {
				// sizeCode = "";
				increasePercent = 0.0f;
				readMoreOpr.getProductSkuRecord().setBasePrice(originalBasePrice);
			}
			myLog.debug("check percent value:::::" + increasePercent);
		}

		if (discountprice != null && discountprice > 0.0f) {
			increasePrice = (discountprice * increasePercent) / 100;
			discountprice = discountprice + increasePrice;
			myLog.debug("inside if discount price" + discountprice);
			readMoreOpr.getProductSkuRecord().setDiscountPrice(convertCurrency(discountprice));
			readMoreOpr.getProductSkuRecord().setOriginalDiscountPrice(convertCurrency(discountprice));
			myLog.debug("Original discount price" + readMoreOpr.getProductSkuRecord().getOriginalDiscountPrice());
			// baseprice = 0.0f;
			// if (baseprice != null && baseprice > 0.0f) {
			//
			// increasePrice = (baseprice * increasePercent) / 100;
			// baseprice = baseprice + increasePrice;
			// readMoreOpr.getProductSkuRecord().setBasePrice(baseprice);
			// }

		}
		if (baseprice != null && baseprice > 0.0f) {
			increasePrice = (baseprice * increasePercent) / 100;
			baseprice = baseprice + increasePrice;
			myLog.debug("Inside else if base price" + baseprice);
			readMoreOpr.getProductSkuRecord().setBasePrice(convertCurrency(baseprice));
			readMoreOpr.getProductSkuRecord().setOriginalBasePrice(convertCurrency(baseprice));
			myLog.debug("Original base price" + readMoreOpr.getProductSkuRecord().getOriginalBasePrice());
		}
		myLog.debug("before convertedPrice base price" + readMoreOpr.getProductSkuRecord().getBasePrice());
		convertedPrice(readMoreOpr);
		myLog.debug("after convertedPrice base price" + readMoreOpr.getProductSkuRecord().getBasePrice());
		// readMoreOpr.getProductSkuRecord().getSizeRecord().setCode(sCode);
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public Float getIncreasePercent() {
		return increasePercent;
	}

	public void setIncreasePercent(Float increasePercent) {
		this.increasePercent = increasePercent;
	}

	public Float getOriginalBasePrice() {
		return originalBasePrice;
	}

	public void setOriginalBasePrice(Float originalBasePrice) {
		this.originalBasePrice = originalBasePrice;
	}

	public boolean isSelectEngrave() {
		return selectEngrave;
	}

	public void setSelectEngrave(boolean selectEngrave) {
		this.selectEngrave = selectEngrave;
	}

	public String getEngraveTextSilverClass() {
		if (engraveTextSilverClass == null || engraveTextSilverClass.trim().length() == 0) {
			engraveTextSilverClass = "messageTextRockwellSilver";
		}
		return engraveTextSilverClass;
	}

	public void setEngraveTextSilverClass(String engraveTextSilverClass) {
		this.engraveTextSilverClass = engraveTextSilverClass;
	}

	public void applyNewSilverFont() {
		if (engraveFont.equals("Rockwell")) {
			engraveTextSilverClass = "messageTextRockwellSilver";
		} else if (engraveFont.equals("Berlin Sans fb")) {
			engraveTextSilverClass = "messageTextBerlinSilver";
		} else if (engraveFont.equals("Lucida Handwriting")) {
			engraveTextSilverClass = "messageTextLucidaHandwritingSilver";
		} else if (engraveFont.equals("Verdana")) {
			engraveTextSilverClass = "messageTextVerdanaSilver";
		}
	}

	public void applyNewFont() {
		if (engraveFont.equals("Rockwell")) {
			engraveTextClass = "messageTextRockwell";
			engraveTextSilverClass = "messageTextRockwell";
		} else if (engraveFont.equals("Berlin Sans fb")) {
			engraveTextClass = "messageTextBerlin";
			engraveTextSilverClass = "messageTextBerlin";
		} else if (engraveFont.equals("Lucida Handwriting")) {
			engraveTextClass = "messageTextLucidaHandwriting";
			engraveTextSilverClass = "messageTextLucidaHandwriting";
		} else if (engraveFont.equals("Verdana")) {
			engraveTextClass = "messageTextVerdana";
			engraveTextSilverClass = "messageTextVerdana";
		}
	}

	public Float getOriginalDiscountPrice() {
		return originalDiscountPrice;
	}

	public void setOriginalDiscountPrice(Float originalDiscountPrice) {
		this.originalDiscountPrice = originalDiscountPrice;
	}

	public boolean isSizeSelect() {
		return sizeSelect;
	}

	public void setSizeSelect(boolean sizeSelect) {
		this.sizeSelect = sizeSelect;
	}

	public boolean isNavigatePanel() {
		return navigatePanel;
	}

	public void setNavigatePanel(boolean navigatePanel) {
		this.navigatePanel = navigatePanel;
	}

	public void clearEngrave(AjaxBehaviorEvent abe) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("After click in text box:::::::");
		setEngraveText("");

	}

	private Float convertCurrency(Float originalValue) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside currencyBasedConversion()::");
		Float convertedValue = originalValue;

		Float conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.CURRENCY_CONVERSION_RATE);
		myLog.debug("Conversion Rate==" + conversionRate);
		myLog.debug("convertedValue==1:::" + convertedValue);
		convertedValue = convertedValue / conversionRate;
		myLog.debug("convertedValue==2:::" + convertedValue);
		return convertedValue;
	}

	private void convertedPrice(ReadMoreOpr readMoreOpr) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside convertedPrice ::: ");
		convertedPriceReadMoreOpr = (ReadMoreOpr) DeepCopy.copy(readMoreOpr);
		Float conversionRate = 0.0F;
		Float convertedBasePrice = 0.0F;
		Float convertedDiscountPrice = 0.0F;
		String currencySymbol = null;
		boolean currencyFlag = false;

		// GEOPLUGIN - to set original base, discount prices and base and
		// converted currencies

		readMoreOpr.getProductSkuRecord().setOriginalBasePrice(readMoreOpr.getProductSkuRecord().getBasePrice());
		readMoreOpr.getProductSkuRecord()
				.setOriginalDiscountPrice(readMoreOpr.getProductSkuRecord().getDiscountPrice());
		readMoreOpr.getProductSkuRecord().setOriginalCurrencyRecord(
				readMoreOpr.getProductSkuRecord().getCurrencyRecord());

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL) != null) {
			currencySymbol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL);
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(CommonConstant.CURRENCY_FLAG) != null) {
			currencyFlag = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.CURRENCY_FLAG);
		}

		// GEOPLUGIN - to set converted currency symbol
		readMoreOpr.getProductSkuRecord().getCurrencyRecord().setCurrencySymbol(currencySymbol);

		myLog.debug("currency flag=="
				+ FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.CURRENCY_FLAG));

		myLog.debug("Product Base Price" + convertedPriceReadMoreOpr.getProductSkuRecord().getBasePrice());
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.CURRENCY_CONVERSION_RATE) != null) {
			conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.CURRENCY_CONVERSION_RATE);
			myLog.debug("Conversion Rate==" + conversionRate);
			convertedBasePrice = convertedPriceReadMoreOpr.getProductSkuRecord().getBasePrice();
			if (convertedPriceReadMoreOpr.getProductSkuRecord().getDiscountPrice() != null
					&& convertedPriceReadMoreOpr.getProductSkuRecord().getDiscountPrice() > 0.0) {
				convertedDiscountPrice = convertedPriceReadMoreOpr.getProductSkuRecord().getDiscountPrice();
			}

			convertedBasePrice = convertedBasePrice * conversionRate;
			convertedDiscountPrice = convertedDiscountPrice * conversionRate;
			readMoreOpr.getProductSkuRecord().setBasePrice(convertedBasePrice);
			readMoreOpr.getProductSkuRecord().setDiscountPrice(convertedDiscountPrice);
			myLog.debug("convertedBasePrice==" + convertedBasePrice);
			myLog.debug("convertedDiscountPrice==" + convertedDiscountPrice);
			if (convertedDiscountPrice != null && convertedDiscountPrice > 0.0f) {
				emiPrice = convertedDiscountPrice / 3;
			} else {
				emiPrice = convertedBasePrice / 3;
			}

			myLog.debug("Product price for emi:::::" + emiPrice);
		}
	}

	public void getCountryDependentCurrencyConversion() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside getCountryDependentData():: ");
		ReadMoreOpr readMoreCountryOpr = new ReadMoreOpr();

		myLog.debug("Inside getCountryDependentData() CURRENCY_CONVERSION_RATE null :: ");

		if (countryName != null) {
			myLog.debug("Inside countryName is not null :: ");
			if (countryName.equals("Asia")) {
				myLog.debug("Inside countryName if asia :: ");
				readMoreCountryOpr.getCountryRecord().setName("Asia");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.CURRENCY_FLAG, true);
			} else {
				readMoreCountryOpr.getCountryRecord().setName("Rest of the world");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.CURRENCY_FLAG, false);
			}
		}

		try {
			readMoreCountryOpr = new ReadMoreBF().getCountryDependentData(readMoreCountryOpr);
		} catch (FrameworkException e) {
			myLog.error("Exception occured " + e);
			// handleException(e, CommonConstant.COMMON_MESSAGE_LOCATION);
		}

		myLog.debug("inside getting currency conversion rate browse product bb ::: "
				+ readMoreCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
		Float conversionMultiplier = 1.0F;
		String currencySymbol = "Rs.";

		if (readMoreCountryOpr != null) {
			if (readMoreCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier() > 0) {
				conversionMultiplier = readMoreCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier();
				setConversionRate(conversionMultiplier);
			}

			if (readMoreCountryOpr.getCurrencyRecord().getCurrencySymbol() != null
					&& readMoreCountryOpr.getCurrencyRecord().getCurrencySymbol().trim().length() > 0) {
				currencySymbol = readMoreCountryOpr.getCurrencyRecord().getCurrencySymbol();
			}
		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.CURRENCY_CONVERSION_RATE, conversionMultiplier);

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.CONVERTED_CURRENCY_SYMBOL, currencySymbol);

		myLog.debug("conversion factor got :::: "
				+ readMoreCountryOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
	}

	public void getCurrencyForReadMore() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside Readmore getCurrencyForReadMore:: " + countryName);
		getCountryDependentCurrencyConversion();
		loadProductFromURL();

	}

	public Float getEmiPrice() {
		return emiPrice;
	}

	public void setEmiPrice(Float emiPrice) {
		this.emiPrice = emiPrice;
	}

	private Float convertCurrencyForSeeAlso(Float originalValue) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside currencyBasedConversion()::");
		Float convertedValue = originalValue;
		if (conversionRate != null && !conversionRate.equals(0.0)) {
			myLog.debug("Inside currencyBasedConversion() if conversion rate not null::");
			myLog.debug("Conversion Rate==" + conversionRate);
			myLog.debug("convertedValue==" + convertedValue);
			convertedValue = convertedValue * conversionRate;
		}
		return convertedValue;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
