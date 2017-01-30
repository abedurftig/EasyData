package org.easydata.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.easydata.builder.EasyClassBuilder;
import org.easydata.builder.EasyFieldBuilder;
import org.easydata.builder.EasyModelBuilder;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyType;
import org.junit.Test;

public class JSONModelDefinitionWriterTest {

	private static String path = Paths.get(".").toAbsolutePath().normalize().toString() + 
			"/src/test/resources/JSONModelDefinitionWriterTest";
	
	@Test
	public void writeOutJSONModel() throws IllegalArgumentException, IOException {
		
		// arrange
		EasyFieldBuilder fb1 = new EasyFieldBuilder().create()
				.withTargetName("Id")
				.makeKey();
		
		EasyFieldBuilder fb2 = new EasyFieldBuilder().create()
				.withTargetName("FirstName")
				.withType(EasyType.TEXT);
		
		EasyFieldBuilder fb3 = new EasyFieldBuilder().create()
				.withTargetName("DOB")
				.withType(EasyType.DATE);
		
		EasyFieldBuilder fb4 = new EasyFieldBuilder().create()
				.withTargetName("Legs")
				.withType(EasyType.INTEGER);
		
		EasyClassBuilder c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(fb1)
				.withEasyField(fb2)
				.withEasyField(fb3)
				.withEasyField(fb4);
		
		EasyModel em = new EasyModelBuilder().create().withEasyClass(c1).get();
		
		JSONModelDefinitionWriter writer = new JSONModelDefinitionWriter();
		writer.writeOutModelDefinition(em, new File(path));
		
	}
	
}
