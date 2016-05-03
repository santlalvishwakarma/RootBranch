package com.web.common.parents;

import javax.faces.event.ActionEvent;

public interface IBackingBean {

	/**
	 * Generic method for any screen that has search button. The search button
	 * must and can only invoke this method
	 * 
	 * @param event
	 */
	public void executeSearch(ActionEvent event);

	/**
	 * Every page that has a search button must call this method for validating
	 * the fields of the search screen. In case there are no validations, there
	 * must be a empty implementation of this method that returns a true boolean
	 * value
	 * 
	 * @return boolean value, true - validation successful, false - validation
	 *         failure
	 */
	public boolean validateSearch();

	/**
	 * Generic method for any screen that has save button. The save button must
	 * and can only invoke this method
	 * 
	 * @param event
	 */
	public void executeSave(ActionEvent event);

	/**
	 * Every page that has a save button must call this method for validating
	 * the fields of the screen. In case there are no validations, there must be
	 * a empty implementation of this method that returns a true boolean value
	 * 
	 * @return boolean value, true - validation successful, false - validation
	 *         failure
	 */
	public boolean validateSave();

	/**
	 * This method is to be called whenever there is a EDIT functionality. For
	 * e.g. when the search result results into at least one record and the
	 * details of the record are in another tab
	 */
	public void editDetails();

	/**
	 * This method is to be called from pretty-config action method. This will
	 * fetch all the initial data required by the page
	 */
	public void retrieveData();

	/**
	 * This method is to be called from Add Row link
	 * 
	 */
	public void executeAddRow(ActionEvent event);

}
