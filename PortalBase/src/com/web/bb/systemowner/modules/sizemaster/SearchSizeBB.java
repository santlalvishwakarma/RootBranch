package com.web.bb.systemowner.modules.sizemaster;

import javax.faces.event.ActionEvent;

import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;

public class SearchSizeBB extends BackingBean {

	private static final long serialVersionUID = -8929105258866627750L;
	private OptionsDVO allOptions;
	private SizeOpr sizeOpr;

	public SizeOpr getSizeOpr() {
		if (sizeOpr == null) {
			sizeOpr = new SizeOpr();
		}
		return sizeOpr;
	}

	public void setSizeOpr(SizeOpr sizeOpr) {
		this.sizeOpr = sizeOpr;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAddRow(ActionEvent event) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
