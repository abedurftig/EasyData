package org.easydata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileUtil {

	private File _csvFile = null;
	
	public CSVFileUtil(File csvFile) {
		this._csvFile = csvFile;
	}
	
	public void setCsvFile(File csvFile) {
		this._csvFile = csvFile;
	}
	
	public String readLineAt(int index) throws IOException {
        
		String result = null;
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(this._csvFile));
			int i = 0;
			while (i < index) {
				reader.readLine();
				i++;
			}
            result = reader.readLine();
			
        } finally {
        	
        	if (reader != null) {
        		reader.close();
        	}
        	
        } 
		
		return result;
		
    }
	
}
