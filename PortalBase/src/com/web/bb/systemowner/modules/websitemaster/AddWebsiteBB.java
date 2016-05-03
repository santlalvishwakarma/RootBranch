package com.web.bb.systemowner.modules.websitemaster;

import javax.faces.event.ActionEvent;

import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;

public class AddWebsiteBB extends BackingBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1754472963711543374L;
	private WebsiteOpr addWebsiteOpr;

	public WebsiteOpr getAddWebsiteOpr() {
		if (addWebsiteOpr == null) {
			addWebsiteOpr = new WebsiteOpr();
		}
		return addWebsiteOpr;
	}

	public void setAddWebsiteOpr(WebsiteOpr addWebsiteOpr) {
		this.addWebsiteOpr = addWebsiteOpr;
	}

	@Override
	public OptionsDVO getAllOptions() {
		return null;
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

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
