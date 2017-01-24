package org.easydata.model;

public enum EasyType {

	TEXT("text"),
	INTEGER("int"),
	DATE("date");
	
	private String _typeString = null;
	
	private EasyType(String type) {
		this._typeString = type;
	}
	
	public String getType() {
		return this._typeString;
	}
	
	public EasyType from(String type) {
		
		if (type.equalsIgnoreCase("text")) {
			return EasyType.TEXT;
		}
		
		if (type.equalsIgnoreCase("date")) {
			return EasyType.DATE;
		}
		
		if (type.equalsIgnoreCase("int")) {
			return EasyType.INTEGER;
		}
		
		return null;
		
	}
	
}
