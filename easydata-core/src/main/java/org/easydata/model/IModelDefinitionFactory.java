package org.easydata.model;

import java.io.File;
import java.io.IOException;

public interface IModelDefinitionFactory {

	/**
	 * Create a supported set of model definitions in the specified directory.
	 * 
	 * @param targetDir
	 */
	public void createModelDefinitions(EasyModel model, File targetDir) 
			throws IOException, IllegalArgumentException;
	
}
