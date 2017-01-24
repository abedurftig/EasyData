package org.easydata.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.easydata.model.EasyModel;
import org.easydata.model.impl.JSONModelDefinitionParser;
import org.junit.Test;

import com.sun.codemodel.JCodeModel;

public class EasyPojoGeneratorTest {

	private static EasyModel MODEL = null;
	
	static {
		init();
	}
	
	public static void init() {
		
		String in = Paths.get("src/test/resources/json/").toAbsolutePath().toString();
		File inFile = new File(in);
		try {
			MODEL = new JSONModelDefinitionParser().parseModelDefinitions(inFile);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void firstTest() throws IOException {
		
		String out = Paths.get("src/test/java/").toAbsolutePath().toString();
		File outFile = new File(out);
		FileUtils.forceMkdir(outFile);
		
		JCodeModel jModel = new JCodeModel();
		EasyCodeGenerator genModel = new EasyPojoGenerator();
		genModel.generateCode(MODEL, jModel);
		
		EasyCodeGenerator genRepo = new EasyRepositoryGenerator();
		genRepo.generateCode(MODEL, jModel);
		
		jModel.build(outFile);
		
	}
	
}
