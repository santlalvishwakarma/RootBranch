package com.web.common.dvo.util;

import com.web.common.dvo.common.BaseDVO;

public class File extends BaseDVO {

	private static final long serialVersionUID = 4081282534283464711L;
	private String Name;
	private String mime;
	private long length;
	private byte[] data;
	private Integer sizeInKB;
	private String filePath;
	private String extension;
	private String contentType;

	public Integer getSizeInKB() {
		sizeInKB = data.length / 1024;
		return sizeInKB;
	}

	public void setSizeInKB(Integer sizeInKB) {
		this.sizeInKB = sizeInKB;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	@Override
	public String getName() {
		return Name;
	}

	@Override
	public void setName(String name) {
		Name = name;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}