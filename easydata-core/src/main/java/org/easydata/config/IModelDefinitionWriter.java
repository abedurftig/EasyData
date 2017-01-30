package org.easydata.config;

import java.io.File;
import java.io.IOException;

import org.easydata.model.EasyModel;

public interface IModelDefinitionWriter {

	/**
	 * Create a supported set of model definitions in the specified directory.
	 * 
	 * @param targetDir
	 */
	public void writeOutModelDefinition(EasyModel model, File targetDir) 
			throws IOException, IllegalArgumentException;
	
}
