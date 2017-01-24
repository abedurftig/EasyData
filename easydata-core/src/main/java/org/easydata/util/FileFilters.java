package org.easydata.util;

import java.io.File;
import java.io.FileFilter;

public abstract class FileFilters {

	public static final FileFilter CSV_FILE_FILTER = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			return file.isFile() && file.getName().endsWith(".csv");
		}
		
	}; 
	
}
