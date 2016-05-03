package com.web.bb.retail.module.enlargeimagepanel;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;

public class EnlargeImageBB extends BackingBean {

	private static final long serialVersionUID = -6000103308925261180L;
	private ImageDVO imageRecord;

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

	public ImageDVO getImageRecord() {
		if (imageRecord == null) {
			imageRecord = new ImageDVO();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.containsKey(CommonConstant.IMAGE_DVO)) {
			imageRecord = (ImageDVO) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get(CommonConstant.IMAGE_DVO);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove(CommonConstant.IMAGE_DVO);
		}

		return imageRecord;
	}

	public void setImageRecord(ImageDVO imageRecord) {
		this.imageRecord = imageRecord;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
