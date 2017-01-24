package org.easydata.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyField;
import org.easydata.model.EasyModel;
import org.easydata.util.FileFilters;

public class CSVModelDefinitionParser implements IModelDefinitionParser {

	private static final String _SEPERATOR = ",";
	
	@Override
	public EasyModel parseModelDefinitions(File inputFolder) 
			throws IllegalArgumentException, IOException {
		
		if (inputFolder.isFile()) {
			throw new IllegalArgumentException("targetDir is not a directory!");
		}
		
		EasyModel model = new EasyModel();
		
		File[] csvFiles = inputFolder.listFiles(FileFilters.CSV_FILE_FILTER);
		for (File csvFile : csvFiles) {
			createClassFromCSVFile(csvFile, model);
		}
		
		return model;
		
	}
	
	private void createClassFromCSVFile(File csvFile, EasyModel model) throws IOException {
		
		String header = readFirstLine(csvFile);
		String[] headerNames = header.split(_SEPERATOR);
		
		EasyClass clazz = new EasyClass();
		clazz.fileName = csvFile.getName();
		clazz.targetClassName = csvFile.getName().replace(".csv", "");
		
		for (int i = 0; i < headerNames.length; i++) {
			
			String fieldName = headerNames[i];
			EasyField field = new EasyField();
			field.index = i;
			field.targetFieldName = fieldName;
			field.type = "text";
			
			clazz.fields.add(field);
			
		}
		
		model.classes.add(clazz);
		
	}
	
	private String readFirstLine(File file) throws IOException {
        
		String result = null;
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
            result = reader.readLine();
			
        } finally {
        	
        	if (reader != null) {
        		reader.close();
        	}
        	
        } 
		
		return result;
		
    }

}
