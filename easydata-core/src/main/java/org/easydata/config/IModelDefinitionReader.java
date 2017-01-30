package org.easydata.config;

import java.io.File;
import java.io.IOException;

import org.easydata.model.EasyModel;

public interface IModelDefinitionReader {

	public EasyModel readAndParseModelDefinitions(File inputFolder) 
			throws IllegalArgumentException, IOException;
	
}
