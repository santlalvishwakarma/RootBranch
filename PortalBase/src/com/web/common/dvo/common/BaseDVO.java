package com.web.common.dvo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDVO extends ImplDataValueObject {

	private static final long serialVersionUID = 3832835498961840907L;
	protected Long id;
	protected String code;
	protected String name;
	protected String description;
	protected Date effectiveFrom;
	protected Date effectiveTo;
	protected String idString;
	protected Boolean active;
	protected String activeDescription;
	protected String userLogin;
	protected WebResourceAttributes webResourceAttributes;

	public BaseDVO() {
	}

	public BaseDVO(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public WebResourceAttributes getWebResourceAttributes() {
		if (webResourceAttributes == null) {
			webResourceAttributes = new WebResourceAttributes();
		}
		return webResourceAttributes;
	}

	public void setWebResourceAttributes(WebResourceAttributes webResourceAttributes) {
		this.webResourceAttributes = webResourceAttributes;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the effectiveFrom
	 */
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	/**
	 * @param effectiveFrom
	 *            the effectiveFrom to set
	 */
	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	/**
	 * @return the effectiveTo
	 */
	public Date getEffectiveTo() {
		return effectiveTo;
	}

	/**
	 * @param effectiveTo
	 *            the effectiveTo to set
	 */
	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getEffectiveFromString() {
		String effectiveFromString;
		if (effectiveFrom != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			effectiveFromString = sdf.format(effectiveFrom);
		} else {
			effectiveFromString = "";
		}
		return effectiveFromString;
	}

	public void setEffectiveFromString(String effectiveFromString) {
		if (effectiveFromString != null) {
			try {
				effectiveFrom = new SimpleDateFormat().parse(effectiveFromString);
			} catch (ParseException e) {

			}
		}
	}

	public String getEffectiveToString() {
		String effectiveToString;
		if (effectiveTo != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			effectiveToString = sdf.format(effectiveTo);
		} else {
			effectiveToString = "";
		}
		return effectiveToString;
	}

	public void setEffectiveToString(String effectiveToString) {
		if (effectiveToString != null) {
			try {
				effectiveTo = new SimpleDateFormat().parse(effectiveToString);
			} catch (ParseException e) {

			}
		}
	}

	public Boolean getActive() {
		if (active == null) {
			active = Boolean.FALSE;
		}
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// to make rich:select work with long value (for successfully retrieving
	// state id which is long), the value is set
	// into string
	public String getIdString() {
		if (id != null && id > 0) {
			idString = String.valueOf(id.longValue());
		}
		// if (id != null && id > 0) {
		// idString = String.valueOf(id.longValue());
		// } else if (id == null || (id != null && id == 0)) {
		// idString = null;
		// }
		return idString;
	}

	public void setIdString(String idString) {
		if (idString != null && idString.trim().length() > 0) {
			id = Long.valueOf(idString);
		} else {
			id = null;
		}
		this.idString = idString;
	}

	public String getActiveDescription() {
		return activeDescription;
	}

	public void setActiveDescription(String activeDescription) {
		this.activeDescription = activeDescription;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
}
