package org.easydata.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EasyType {

	TEXT("text"),
	INTEGER("int"),
	DATE("date"),
	DECIMAL("decimal"),
	BOOLEAN("boolean");
	
	private String _typeString = null;
	
	private EasyType(String type) {
		this._typeString = type;
	}
	
	@JsonValue
	public String getType() {
		return this._typeString;
	}
	
	public Class<?> getJavaType() {
		
		if (_typeString.equals(TEXT.getType())) {
			return java.lang.String.class;
		} else if (_typeString.equals(DATE.getType())) {
			return java.util.Date.class;
		} else if (_typeString.equals(INTEGER.getType())) {
			return java.lang.Integer.class;
		} else if (_typeString.equals(BOOLEAN.getType())) {
			return java.lang.Boolean.class;
		} else if (_typeString.equals(DECIMAL.getType())) {
			return java.math.BigDecimal.class;
		}
		
		return java.lang.String.class;
		
	} 
	
}
