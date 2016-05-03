package com.web.foundation.dao;

import java.io.Serializable;

public class SeqTypeValueMapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private int iSeq;
	private Object strValue;
	private int strType;

	/**
	 * Default constructor
	 * 
	 */
	public SeqTypeValueMapper() {

	}

	/**
	 * Newly Added Constructor to set the seq, datatype and value Dated
	 * 20-Nov-2008 by shrikant
	 * 
	 * @param iSeq
	 * @param strType
	 * @param objValue
	 */
	public SeqTypeValueMapper(int iSeq, int strType, Object objValue) {
		this.iSeq = iSeq;
		this.strType = strType;
		this.strValue = objValue;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return iSeq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(int seq) {
		iSeq = seq;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return strType;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		strType = type;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return strValue;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		strValue = value;
	}

}
