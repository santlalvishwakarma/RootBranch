package com.web.common.jsf.converters;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

/**
 * @author NIRAJ
 * 
 */
public class ProductCategoryConverter implements Converter {

	private static final ITSDLogger myLog = TSDLogger.getLogger(ProductCategoryConverter.class.getName());

	protected String beanName;

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {

		myLog.info("getAsObject :: submittedValue : " + submittedValue);
		myLog.info("getAsObject :: beanName : " + beanName);
		if (submittedValue == null || submittedValue.trim().equals("")) {
			return null;
		} else {

			if (beanName != null) {
				if (FacesContext.getCurrentInstance().getViewRoot().getViewMap().containsKey(beanName)) {
					FacesContext ctx = FacesContext.getCurrentInstance();
					ValueExpression vex = ctx.getApplication().getExpressionFactory()
							.createValueExpression(ctx.getELContext(), "#{" + beanName + "}", BackingBean.class);
					BackingBean bean = (BackingBean) vex.getValue(ctx.getELContext());
					List<CategoryDVO> productCategoryList = bean.productCategoriesForAutoSuggest;
					myLog.info("getAsObject :: productCategoryList size : " + productCategoryList.size());

					for (CategoryDVO productCategoryRecord : productCategoryList) {
						myLog.info("getAsObject :: Category Id : " + productCategoryRecord.getId());
						if (productCategoryRecord.getId() != null
								&& productCategoryRecord.getId().toString().equals(submittedValue)) {
							return productCategoryRecord;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		myLog.info("getAsString :: value : " + value);
		CategoryDVO productCategoryRecord = (CategoryDVO) value;
		Long categoryId = productCategoryRecord.getId();
		myLog.info("getAsString :: value id : " + categoryId);
		if (value == null || value.equals("")) {
			return "";

		} else if (categoryId == null) {
			return "";

		} else {
			return categoryId.toString();
		}

	}

}
