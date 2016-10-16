package com.web.common.dvo.systemowner;

import java.util.Date;

import com.web.common.dvo.common.BaseDVO;

public class ImageDVO extends BaseDVO {

	private static final long serialVersionUID = -4175375367976539143L;
	private String imageURL;
	private String thumbnailImageURL;
	private String zoomImageURL;
	private Long sequenceNumber;
	private String linkingUrl;
	private String displayThumbnailImageURL;

	public ImageDVO(String code, String description, String imageURL) {
		this.code = code;
		this.description = description;
		this.imageURL = imageURL;
	}

	public ImageDVO(String code, String description, String imageURL, String thumbnailImageURL, String zoomImageURL) {
		this.code = code;
		this.description = description;
		this.imageURL = imageURL;
		this.thumbnailImageURL = thumbnailImageURL;
		this.zoomImageURL = zoomImageURL;
	}

	public ImageDVO() {
		// TODO Auto-generated constructor stub
	}

	public ImageDVO(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	public void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	public String getZoomImageURL() {

		return zoomImageURL;
	}

	public void setZoomImageURL(String zoomImageURL) {
		this.zoomImageURL = zoomImageURL;
	}

	public String getThumbnailImageWrapper() {
		if (thumbnailImageURL == null) {
			return getImageURL();
		} else {
			return getThumbnailImageURL();
		}
	}

	public String getZoomImageWrapper() {
		if (zoomImageURL == null) {
			return getImageURL();
		} else {
			return getZoomImageURL();
		}
	}

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getLinkingUrl() {
		return linkingUrl;
	}

	public void setLinkingUrl(String linkingUrl) {
		this.linkingUrl = linkingUrl;
	}

	public String getDisplayThumbnailImageURL() {
		if (thumbnailImageURL != null) {
			displayThumbnailImageURL = thumbnailImageURL;

		}
		displayThumbnailImageURL += "?p=" + new Date();

		return displayThumbnailImageURL;
	}

	public void setDisplayThumbnailImageURL(String displayThumbnailImageURL) {
		this.displayThumbnailImageURL = displayThumbnailImageURL;
	}

}
