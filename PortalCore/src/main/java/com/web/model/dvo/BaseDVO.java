package com.web.model.dvo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseDVO extends ImplDataValueObject {

	private static final long serialVersionUID = 3832835498961840907L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	protected Long id;
	protected String code;
	protected String name;
	protected String description;
	@Transient
	protected String idString;
	protected Boolean active;
	@Transient
	protected String activeDescription;
	@Transient
	protected String userLogin;
	@Transient
	protected WebResourceAttributes webResourceAttributes;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	@Transient
	private String createdDateString;
	@Transient
	private String lastModifiedDateString;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getCreatedDateString() {
		if (createdDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			createdDateString = sdf.format(createdDate);
		} else {
			createdDateString = "";
		}
		return createdDateString;
	}

	public void setCreatedDateString(String createdDateString) {
		this.createdDateString = createdDateString;
	}

	public String getLastModifiedDateString() {
		if (modifiedDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			lastModifiedDateString = sdf.format(modifiedDate);
		} else {
			lastModifiedDateString = "";
		}
		return lastModifiedDateString;
	}

	public void setLastModifiedDateString(String lastModifiedDateString) {
		this.lastModifiedDateString = lastModifiedDateString;
	}
	
	
}
