package org.easydata.model;

import java.io.File;
import java.io.IOException;

public interface IModelDefinitionParser {

	public EasyModel parseModelDefinitions(File inputFolder) 
			throws IllegalArgumentException, IOException;
	
}
