package org.easydata.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EasyObjectMapper {

	private static ObjectMapper objMapper = new ObjectMapper();
	
	static {
		objMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private EasyObjectMapper() {}
	
	public static String produceJSON(Object obj) {
		
		try {
			return objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void writeToJSONFile(File file, Object obj) {
		
		try {
			objMapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
