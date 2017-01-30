package org.easydata.config;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CSVModelDefinitionDataTypeTest {

	private static String path = Paths.get(".").toAbsolutePath().normalize().toString() + 
			"/src/test/resources/CSVModelDefinitionDataTypeTest";
	
	@BeforeClass
	public static void mkDir() throws IOException {
		FileUtils.forceMkdir(new File(path + "/out"));
	}
	
	@AfterClass
	public static void rmDir() throws IOException {
		FileUtils.deleteDirectory(new File(path + "/out"));
	}
	
	@Test
	public void parseCSV() throws IllegalArgumentException, IOException {
		
		IModelDefinitionReader parser = new CSVModelDefinitionReader();
		EasyModel model = parser.readAndParseModelDefinitions(new File(path));
		EasyClass clazz = model.getClassByName("Datatypes");
		
		assertEquals("Expected different type", EasyType.TEXT, clazz.getFieldByName("Long").type);
		assertEquals("Expected different type", EasyType.TEXT, clazz.getFieldByName("Text").type);
		assertEquals("Expected different type", EasyType.DATE, clazz.getFieldByName("Date").type);
		assertEquals("Expected different type", EasyType.INTEGER, clazz.getFieldByName("Number").type);
		assertEquals("Expected different type", EasyType.BOOLEAN, clazz.getFieldByName("BooleanDigitOne").type);
		assertEquals("Expected different type", EasyType.BOOLEAN, clazz.getFieldByName("BooleanDigitZero").type);
		assertEquals("Expected different type", EasyType.BOOLEAN, clazz.getFieldByName("BooleanLiteralTrue").type);
		assertEquals("Expected different type", EasyType.BOOLEAN, clazz.getFieldByName("BooleanLiteralFalse").type);
		assertEquals("Expected different type", EasyType.DECIMAL, clazz.getFieldByName("Float").type);
		
	}
	
}
