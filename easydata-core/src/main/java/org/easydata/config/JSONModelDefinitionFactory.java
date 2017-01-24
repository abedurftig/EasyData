package org.easydata.config;

import static org.easydata.util.EasyLogger.getEasyLogger;

import java.io.File;
import java.io.IOException;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.easydata.util.EasyObjectMapper;

public class JSONModelDefinitionFactory implements IModelDefinitionFactory {

	@Override
	public void createModelDefinitions(EasyModel model, File targetDir) 
			throws IOException, IllegalArgumentException {
		
		if (targetDir.isFile()) {
			throw new IllegalArgumentException("targetDir is not a directory!");
		}
		
		for (EasyClass cl : model.classes) {
			
			File out = new File(targetDir.getAbsolutePath() + "/" + cl.targetClassName + ".json");
			EasyObjectMapper.writeToJSONFile(out, cl);
			getEasyLogger().info("created JSON model definition for class " + cl.targetClassName);
			
		}
		
	}

}
