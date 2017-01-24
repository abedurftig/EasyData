package org.easydata.model;

import java.io.File;
import java.nio.file.Paths;

public class EasyTestBase {

	
	
	private static EasyModel MODEL = null;
	
	static {
		
		String folderPath = Paths.get("src/test/resources/definition/").toAbsolutePath().toString();
		MODEL = new EasyModel();
		MODEL.fromJSON(new File(folderPath));
		
	}
	
	protected EasyModel getModel() {
		return MODEL;
	}

}
