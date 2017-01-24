package org.easydata.config;

import java.io.File;
import java.io.IOException;

import org.easydata.model.EasyModel;

public interface IModelDefinitionParser {

	public EasyModel parseModelDefinitions(File inputFolder) 
			throws IllegalArgumentException, IOException;
	
}
