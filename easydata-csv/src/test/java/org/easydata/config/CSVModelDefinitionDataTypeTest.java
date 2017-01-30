package org.easydata.config;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
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
		assertEquals("Expected different number of classes", 2, model.classes.size());
		
		EasyClass emClass = model.getClassByName("employee");
		assertEquals("Expected different numbe of fields", 4, emClass.fields.size());
		
	}
	
}
