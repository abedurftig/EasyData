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
		
		if (type.equals(EasyType.TEXT)) {
			return java.lang.String.class;
		} else if (type.equals(EasyType.DATE)) {
			return java.util.Date.class;
		} else if (type.equals(EasyType.INTEGER)) {
			return java.lang.Integer.class;
		} else if (type.equals(EasyType.BOOLEAN)) {
			return java.lang.Boolean.class;
		} else if (type.equals(EasyType.DECIMAL)) {
			return java.math.BigDecimal.class;
		}
		
		return java.lang.String.class;
		
	} 
	
}
