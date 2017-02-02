package org.easydata.config;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyField;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyType;
import org.easydata.util.CSVFileUtil;
import org.easydata.util.FileFilters;

public class CsvModelDefinitionReader implements IModelDefinitionReader {

	private static final String _SEPERATOR = ",";
	
	@Override
	public EasyModel readAndParseModelDefinitions(File inputFolder) 
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
		
		String header = readHeader(csvFile);
		String[] headerNames = header.split(_SEPERATOR);
		
		String data = readFirstDataRow(csvFile);
		String[] datas = null;
		if (data != null) {
			datas = data.split(_SEPERATOR);
		}
		
		EasyClass clazz = new EasyClass();
		clazz.fileName = csvFile.getName();
		clazz.targetClassName = csvFile.getName().replace(".csv", "");
		
		for (int i = 0; i < headerNames.length; i++) {
			
			String fieldName = headerNames[i];
			EasyField field = new EasyField();
			field.index = i;
			field.targetFieldName = fieldName;
			field.type = EasyType.TEXT;
			
			if (datas != null) {
				String fieldContent = datas[i];
				field.type = determineType(fieldContent);
			} 
			
			clazz.fields.add(field);
			
		}
		
		model.classes.add(clazz);
		
	}
	
	private String readHeader(File file) throws IOException {
        
		CSVFileUtil csvUtil = new CSVFileUtil(file); 
		return csvUtil.readLineAt(0);
		
    }
	
	private String readFirstDataRow(File file) throws IOException {
        
		CSVFileUtil csvUtil = new CSVFileUtil(file); 
		return csvUtil.readLineAt(1);
		
    }
	
	private EasyType determineType(String content) {
		
		if (content.equals("1") ||
			content.equals("0") ||
			content.equalsIgnoreCase("TRUE") ||
			content.equalsIgnoreCase("FALSE")) {
			return EasyType.BOOLEAN;
		}
		
		try {
			Integer.parseInt(content);
			return EasyType.INTEGER;
		} catch (NumberFormatException e) {
			//System.out.println("could not parse to int: " + content);
		}
		
		try {
			
			String replaced = content.replaceAll("\\.", "").replaceAll(",", "");
			Integer.parseInt(replaced);
		
			DecimalFormat df = new DecimalFormat();
			df.setParseBigDecimal(true);
			
			df.parse(content);
			return EasyType.DECIMAL;
			
		} catch (ParseException | NumberFormatException e) {
			//System.out.println("could not parse to decimal: " + content);
		}
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.parse(content);
			return EasyType.DATE;
			
		} catch (ParseException e) {
			//System.out.println("could not parse to date: " + content);
		}
		
		return EasyType.TEXT;
		
	}

}
