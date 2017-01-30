package org.easydata.config;

import java.io.File;
import java.io.IOException;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyField;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyRelation;

import static org.easydata.model.EasyRelation.RelationType;

public class JSONModelDefinitionReader implements IModelDefinitionReader {

	@Override
	public EasyModel readAndParseModelDefinitions(File inputFolder) 
			throws IllegalArgumentException, IOException {
		
		if (inputFolder.isFile()) {
			throw new IllegalArgumentException("targetDir is not a directory!");
		}
		
		EasyModel model = new EasyModel();
		model.fromJSON(inputFolder);
		addRelationships(model);
		return model;
		
	}
	
	protected void addRelationships(EasyModel model) {
		
		for (EasyClass clazz : model.classes) {
			
			for (EasyField field : clazz.getRefs()) {
				
				EasyClass other = model.getClassByName(field.ref);
				if (other != null) {
					if (other.hasRefTo(clazz.targetClassName)) {
						clazz.relations.add(new EasyRelation(RelationType.BI_DI_ONE_TO_ONE, other.targetClassName));
					} else {
						other.relations.add(new EasyRelation(RelationType.ONE_TO_MANY, clazz.targetClassName));
						clazz.relations.add(new EasyRelation(RelationType.MANY_TO_ONE, other.targetClassName, field.targetFieldName));
					}
				} else {
					new IllegalStateException("class " + clazz.targetClassName + "references class " +
							field.ref + " via attr '" + field.targetFieldName + 
							"', but the referenced class in not contained in the model.");
				}
				
			}
			
		}
		
	}

}
