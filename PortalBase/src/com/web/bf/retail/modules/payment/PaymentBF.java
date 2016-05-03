package com.web.bf.retail.modules.payment;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.retail.modules.payment.PaymentBC;
import com.web.bf.retail.modules.registrationpanel.RegistrationPanelBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.RegistrationPanelOpr;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class PaymentBF extends BusinessFacade {

	public OptionsDVO getAllOptions(OptionsDVO options) throws FrameworkException, BusinessException {
		// TODO Auto-generated method stub
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		OptionsHelperBC optionsHelperBC = new OptionsHelperBC();
		HashMap<String, ArrayList<SelectItem>> allOptionsMap = new HashMap<String, ArrayList<SelectItem>>();

		return options;
	}

	public void cancelOrder(ShoppingCartOpr shoppingCartOpr) throws FrameworkException, BusinessException {
		new PaymentBC().cancelOrder(shoppingCartOpr);
	}

	public void updateOrderStatusPendingDispatch(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().updateOrderStatusPendingDispatch(shoppingCartOpr);
	}

	public void saveProductForLater(ShoppingCartOpr shoppingCartSaveOpr) throws FrameworkException, BusinessException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		// UserTransaction userTransaction = startUserTransaction();
		try {
			// add your BC call here
			// new PaymentBC().saveProductForLater(shoppingCartSaveOpr);
			// added integration
			String userLogin = shoppingCartSaveOpr.getRetailUserDetails().getUserLogin();

			WishlistIntegrationOpr wishlistIntegrationOpr = new WishlistIntegrationOpr();

			wishlistIntegrationOpr.getWishlistRecord().getUserRecord().setUserLogin(userLogin);
			if (shoppingCartSaveOpr.getShoppingCartProductList() != null
					&& !shoppingCartSaveOpr.getShoppingCartProductList().isEmpty()) {
				for (int i = 0; i < shoppingCartSaveOpr.getShoppingCartProductList().size(); i++) {
					WishlistDVO wishlistDVO = new WishlistDVO();
					wishlistDVO.setProductRecord(shoppingCartSaveOpr.getShoppingCartProductList().get(i)
							.getProductRecord());
					wishlistIntegrationOpr.getWishlistRecordList().add(wishlistDVO);
				}
			}

			wishlistIntegrationOpr = new WishlistIntegration().saveWishlistProduct(wishlistIntegrationOpr);

			new PaymentBC().updateOrderStatusSavedToWishlist(shoppingCartSaveOpr);
			// setCommitFlag(true);
		} catch (FrameworkException e) {
			setCommitFlag(false);
			// handleUserTransaction(userTransaction);
			throw e;
		}
		// handleUserTransaction(userTransaction);

	}

	public ShoppingCartOpr executeRegister(ShoppingCartOpr shoppingcartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("check for application flagmap:::::::"
				+ shoppingcartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL));

		RegistrationPanelOpr registrationPanelOpr = new RegistrationPanelOpr();

		// set registration data
		myLog.debug("bfore calling integration:::::");
		registrationPanelOpr.getUserDetails().setFirstName(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getFirstName());
		registrationPanelOpr.getUserDetails().setLastName(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getLastName());
		registrationPanelOpr.getUserDetails().setPrimaryEmailId(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getEmail1());
		registrationPanelOpr.getUserDetails().setPrimaryPhoneNumber(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getPhone1());
		registrationPanelOpr.setNewPassword(shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getPhone1());
		registrationPanelOpr.getUserDetails().setUserLogin(shoppingcartOpr.getRetailUserDetails().getUserLogin());
		registrationPanelOpr.getUserDetails().setConditionAccepted(
				shoppingcartOpr.getRetailUserDetails().isConditionAccepted());
		registrationPanelOpr.getApplicationFlags().setApplicationFlagMap(
				shoppingcartOpr.getApplicationFlags().getApplicationFlagMap());

		RegistrationPanelOpr registrationPanelOprRet = new RegistrationPanelBF().executeRegister(registrationPanelOpr);

		// roleIntegrationOpr.getUserRecord().setId(registrationPanelOprRet.getRetailUserDetails().getId());
		// roleIntegrationOpr = new
		// RoleIntegration().getRolesForUser(roleIntegrationOpr);
		// String resultRoleList = "";
		// ArrayList<UserRoleMappingDVO> roleList =
		// roleIntegrationOpr.getUserRecord().getUserRolesMappingList();
		// myLog.debug("check rolelist size for " + roleList.size());
		// for (int i = 0; i < roleList.size(); i++) {
		// if (resultRoleList == "") {
		// resultRoleList = roleList.get(i).getRoleRecord().getCode();
		// } else {
		// resultRoleList = resultRoleList +
		// CommonConstant.ROLE_CODE_SEPARATOR
		// + roleList.get(i).getRoleRecord().getCode();
		// }
		// }
		// returnRegistrationOpr.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.LOGGED_USER_ROLES, resultRoleList);

		return shoppingcartOpr;
	}

	public ShoppingCartOpr checkUserAvailability(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		PaymentBC paymentBC = new PaymentBC();
		ShoppingCartOpr returnshShoppingCartOpr = new ShoppingCartOpr();
		returnshShoppingCartOpr = paymentBC.checkUserAvailability(queryParameters);
		return returnshShoppingCartOpr;
	}

	public ShoppingCartOpr updateOrderStatusAsPaymentInitiated(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		return new PaymentBC().updateOrderStatusAsPaymentInitiated(shoppingCartOpr);

	}

	public ShoppingCartOpr updateOrderPaymentType(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new PaymentBC().updateOrderPaymentType(shoppingCartOpr);

	}

	// added for combining the functionality of multiple payment gateway
	// integration
	public ShoppingCartOpr saveInitialPaymentTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		return new PaymentBC().saveInitialPaymentTransaction(shoppingCartOpr);
	}

	public ShoppingCartOpr updateOrderStatusAsPaymentOffline(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		return new PaymentBC().updateOrderStatusAsPaymentOffline(shoppingCartOpr);

	}

	public void saveCCAvenueInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().saveCCAvenueInitTransaction(shoppingCartOpr);
	}

	public Long getSavedCCAvenueTransactionPK(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		return new PaymentBC().getSavedCCAvenueTransactionPK(shoppingCartOpr);
	}

	public void updateCCAvenuetransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().updateCCAvenuetransaction(shoppingCartOpr);
	}

	public ShoppingCartOpr insertCCAvenuePaymentGatewayDetails(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException {
		return new PaymentBC().insertCCAvenuePaymentGatewayDetails(shoppingCartOpr);
	}

	public ShoppingCartOpr getReceivedOrderIdOnPaymentTransactionIdForCCAvenue(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		return new PaymentBC().getReceivedOrderIdOnPaymentTransactionIdForCCAvenue(shoppingCartOpr);
	}

	public ShoppingCartOpr getPaymentCurrency(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new PaymentBC().getPaymentCurrency(shoppingCartOpr);
	}

	public void savePayPalInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().savePayPalInitTransaction(shoppingCartOpr);
	}

	public void updatePayPaltransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().updatePayPaltransaction(shoppingCartOpr);
	}

	public ShoppingCartOpr getDetailsForDoExpressAndInvoice(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new PaymentBC().getDetailsForDoExpressAndInvoice(shoppingCartOpr);
	}

	public ShoppingCartOpr updateForTransactionIdPayPal(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new PaymentBC().updateForTransactionIdPayPal(shoppingCartOpr);
	}

	public void updateErrorForPayPal(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().updateErrorForPayPal(shoppingCartOpr);
	}

	public void updatePayUtransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().updatePayUtransaction(shoppingCartOpr);
	}

	public void savePayuInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new PaymentBC().savePayuInitTransaction(shoppingCartOpr);
	}
}
