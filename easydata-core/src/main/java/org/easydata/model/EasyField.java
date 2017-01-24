package org.easydata.model;

public class EasyField extends EasyObj {

	public int index = 0;
	public boolean key = false;
	public String targetFieldName = null;
	public String type = "text";
	public String ref = null;
	
	public EasyField() {}
	
	public String getJavaTypeAsString() {
		
		if (type.equals("text")) {
			return "String";
		} else if (type.equals("date")) {
			return "Date";
		} else if (type.equals("int")) {
			return "Integer";
		}
		
		return "String";
		
	}
	
	public Class<?> getJavaType() {
		
		if (type.equals("text")) {
			return java.lang.String.class;
		} else if (type.equals("date")) {
			return java.util.Date.class;
		} else if (type.equals("int")) {
			return java.lang.Integer.class;
		}
		
		return java.lang.String.class;
		
	} 
	
}
