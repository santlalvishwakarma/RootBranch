package com.web.common.dvo.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.common.dvo.common.ImplDataValueObject;

public class OptionsDVO extends ImplDataValueObject {

	private static final long serialVersionUID = -3598235814156322958L;
	HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();

	public HashMap<String, ArrayList<Object>> getAllOptionsValues() {
		if (allOptionsValues == null) {
			allOptionsValues = new HashMap<String, ArrayList<Object>>();
		}
		return allOptionsValues;
	}

	public void setAllOptionsValues(HashMap<String, ArrayList<Object>> allOptionsValues) {
		this.allOptionsValues = allOptionsValues;
	}

}
