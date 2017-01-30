package org.easydata.generator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class JCodeModelUtil {

	public static JType getStringHashSetType(JCodeModel cm) {
		return cm.ref(java.util.HashSet.class).narrow(String.class);
	}
	
	public static JType getObject2ObjectMapType(JCodeModel cm) {
		
		return cm.ref(Object2ObjectMap.class).narrow(
				cm.ref(String.class),
				cm.ref(java.util.HashSet.class).narrow(String.class)
				);
		
	}
	
	public static JType getObject2ObjectMapImplType(JCodeModel cm) {
		
		return cm.ref(Object2ObjectOpenHashMap.class).narrow(
				cm.ref(String.class),
				cm.ref(java.util.HashSet.class).narrow(String.class)
				);
		
	}
	
}
