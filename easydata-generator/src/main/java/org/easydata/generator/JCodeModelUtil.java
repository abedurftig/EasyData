package org.easydata.generator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;

public class JCodeModelUtil {

	public static JType getStringHashSetType(JCodeModel cm) {
		return cm.ref(java.util.HashSet.class).narrow(String.class);
	}
	
}
