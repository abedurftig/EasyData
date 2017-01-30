package org.easydata.config;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyType;
import org.junit.Test;

public class JSONModelDefinitionReaderTest {

	private static String path = Paths.get(".").toAbsolutePath().normalize().toString() + 
			"/src/test/resources/JSONModelDefinitionReaderTest/";
	
	@Test
	public void parseEmployee() throws IllegalArgumentException, IOException {
		
		JSONModelDefinitionReader parser = new JSONModelDefinitionReader();
		EasyModel em = parser.readAndParseModelDefinitions(new File(path));
		EasyClass ec = em.getClassByName("Employee");
		
		assertEquals("Expected different type", EasyType.TEXT, ec.getFieldByName("FirstName").type);
		assertEquals("Expected different type", EasyType.DATE, ec.getFieldByName("DOB").type);
		assertEquals("Expected different type", EasyType.INTEGER, ec.getFieldByName("Legs").type);
		
	}
	
}
