package org.easydata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EasyField extends EasyObj {

	public int index = 0;
	public boolean key = false;
	public String targetFieldName = null;
	public EasyType type = EasyType.TEXT;
	public String ref = null;
	
	public EasyField() {}
	
	@JsonIgnore
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
