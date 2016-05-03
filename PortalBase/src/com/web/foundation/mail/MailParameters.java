package com.web.foundation.mail;

import java.io.Serializable;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.web.common.dvo.util.File;

public class MailParameters implements Serializable {

	private static final long serialVersionUID = -3809273959414553598L;
	/**
	 * Collection of multiple email ids To be populated when mail needs to be
	 * sent to more than one user
	 */
	private InternetAddress[] mailRecipients;
	private InternetAddress[] mailRecipientsCC;
	private InternetAddress[] mailRecipientsBCC;
	private InternetAddress[] mailReplyTo;

	private List<File> attachments;
	/**
	 * Single mail id To be populated when mail needs to be sent to one user
	 */
	private String mailRecipient;
	/**
	 * Subject of the email Should ideally be populated from properties file or
	 * database
	 */
	String mailSubject;
	/**
	 * The TEXT content of the mail
	 */
	String mailMessage;
	/**
	 * Email id of mail sender Can be specified as portaldemo@tsd.co.in or - Do
	 * Not Reply <portaldemo@tsd.co.in>
	 */
	String mailFrom;
	/**
	 * Key prefix for each customer, by which the properties in property files
	 * are identified by for e.g. if property files entry is tsd_from = Do Not
	 * Reply <portaldemo@tsd.co.in> then key is tsd
	 */
	String customerKey;
	/**
	 * the format in which the mail needs to be send sample values are
	 * text/plain and text/html
	 */
	String mailFormat;

	Object mailDVOObject;

	String routingKey;

	String messageQueue;

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	public InternetAddress[] getMailReplyTo() {
		return mailReplyTo;
	}

	public void setMailReplyTo(InternetAddress[] mailReplyTo) {
		this.mailReplyTo = mailReplyTo;
	}

	public InternetAddress[] getMailRecipients() {
		return mailRecipients;
	}

	public void setMailRecipients(InternetAddress[] mailRecipients) {
		this.mailRecipients = mailRecipients;
	}

	public String getMailRecipient() {
		return mailRecipient;
	}

	public void setMailRecipient(String mailRecipient) {
		this.mailRecipient = mailRecipient;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(String mailMessage) {
		this.mailMessage = mailMessage;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getMailFormat() {
		return mailFormat;
	}

	public void setMailFormat(String mailFormat) {
		this.mailFormat = mailFormat;
	}

	public InternetAddress[] getMailRecipientsCC() {
		return mailRecipientsCC;
	}

	public void setMailRecipientsCC(InternetAddress[] mailRecipientsCC) {
		this.mailRecipientsCC = mailRecipientsCC;
	}

	public InternetAddress[] getMailRecipientsBCC() {
		return mailRecipientsBCC;
	}

	public void setMailRecipientsBCC(InternetAddress[] mailRecipientsBCC) {
		this.mailRecipientsBCC = mailRecipientsBCC;
	}

	public Object getMailDVOObject() {
		return mailDVOObject;
	}

	public void setMailDVOObject(Object mailDVOObject) {
		this.mailDVOObject = mailDVOObject;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(String messageQueue) {
		this.messageQueue = messageQueue;
	}

}
