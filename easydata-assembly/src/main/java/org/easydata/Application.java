package org.easydata;

import java.io.File;

public class Application {

	public static void main(String[] args) {
		
		if (args.length == 0) {
			
			System.err.println("no argument provided!");
			System.exit(-1);
			
		}
		
	}
	
	private static void generateJSONFromCSV(File path) {}
	
	private static void generateJavaFromJSON(File path) {}
	
}
