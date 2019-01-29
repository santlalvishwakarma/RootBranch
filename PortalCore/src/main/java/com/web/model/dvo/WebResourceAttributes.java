/*
 * OperationalAttributes : DVO for operational attributes.
 * com.tsd.dvo.transactions.base.OperationalAttributes
 * Version 1.0
 * Date Created:15/02/2008
 * Last Modified Date:15/02/2008
 * Modification Log:
 * SNo 	| Change Date 	| Changed By    | Change Description
 * ****	|*************	|***************|********************
 * 1   	| 15/02/2008  	| Manoj.C Nair  | Created Class.
 */
/**
 *
 */
package com.web.model.dvo;

/**
 * This DVO is used to store operational attributes of all transactions
 */
public class WebResourceAttributes extends DataValueObject {

	private static final long serialVersionUID = -422143526640279853L;

	private String title;
	private String contentsResourceURI;
	private String contentsResourceTitle;
	private String startResourceURI;
	private String startResourceTitle;
	private String canonicalURI;
	private String canonicalURITitle;
	private String resourceDescription;
	private String resourceKeywords;
	private String resourceAbstract;
	private String webBotDirectives;
	private String pageType;

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentsResourceURI() {
		return contentsResourceURI;
	}

	public void setContentsResourceURI(String contentsResourceURI) {
		this.contentsResourceURI = contentsResourceURI;
	}

	public String getContentsResourceTitle() {
		return contentsResourceTitle;
	}

	public void setContentsResourceTitle(String contentsResourceTitle) {
		this.contentsResourceTitle = contentsResourceTitle;
	}

	public String getStartResourceURI() {
		return startResourceURI;
	}

	public void setStartResourceURI(String startResourceURI) {
		this.startResourceURI = startResourceURI;
	}

	public String getStartResourceTitle() {
		return startResourceTitle;
	}

	public void setStartResourceTitle(String startResourceTitle) {
		this.startResourceTitle = startResourceTitle;
	}

	public String getCanonicalURI() {
		return canonicalURI;
	}

	public void setCanonicalURI(String canonicalURI) {
		this.canonicalURI = canonicalURI;
	}

	public String getCanonicalURITitle() {
		return canonicalURITitle;
	}

	public void setCanonicalURITitle(String canonicalURITitle) {
		this.canonicalURITitle = canonicalURITitle;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public String getResourceKeywords() {
		return resourceKeywords;
	}

	public void setResourceKeywords(String resourceKeywords) {
		this.resourceKeywords = resourceKeywords;
	}

	public String getResourceAbstract() {
		return resourceAbstract;
	}

	public void setResourceAbstract(String resourceAbstract) {
		this.resourceAbstract = resourceAbstract;
	}

	public String getWebBotDirectives() {
		return webBotDirectives;
	}

	public void setWebBotDirectives(String webBotDirectives) {
		this.webBotDirectives = webBotDirectives;
	}

}
