package com.web.common.dvo.opr.common;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.common.Parameter;

public class ParameterOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 3865876413382285048L;

	private Parameter parameter;
	private ArrayList<Parameter> parameterList;
	private HashMap<String, ArrayList<Object>> parameterOptionsMap;

	public Parameter getParameter() {
		if (parameter == null) {
			parameter = new Parameter();
		}
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public ArrayList<Parameter> getParameterList() {
		if (parameterList == null) {
			parameterList = new ArrayList<Parameter>();
		}
		return parameterList;
	}

	public void setParameterList(ArrayList<Parameter> parameterList) {
		this.parameterList = parameterList;
	}

	public HashMap<String, ArrayList<Object>> getParameterOptionsMap() {
		if (parameterOptionsMap == null) {
			parameterOptionsMap = new HashMap<String, ArrayList<Object>>();
		}
		return parameterOptionsMap;
	}

	public void setParameterOptionsMap(HashMap<String, ArrayList<Object>> parameterOptionsMap) {
		this.parameterOptionsMap = parameterOptionsMap;
	}

}
