package org.easydata.config;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.easydata.config.CSVModelDefinitionReader;
import org.easydata.config.IModelDefinitionReader;
import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CSVModelDefinitionParserTest {

	private static String path = Paths.get(".").toAbsolutePath().normalize().toString() + 
			"/src/test/resources/CSVModelDefinitionParserTest";
	
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
		assertEquals("Expected different number of classes", 2, model.classes.size());
		
		EasyClass empClass = model.getClassByName("Employee");
		assertEquals("Expected different numbe of fields", 4, empClass.fields.size());
		
		EasyClass addClass = model.getClassByName("Address");
		assertEquals("Expected different numbe of fields", 5, addClass.fields.size());
		
	}
	
}
