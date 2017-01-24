package org.easydata.model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.easydata.util.EasyObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EasyModel extends EasyObj {

	public Set<EasyClass> classes = new HashSet<EasyClass>();
	
	public EasyModel() {}
	
	public EasyClass getClassByName(String clName) {
		
		for (EasyClass cl: this.classes) {			
			if (cl.targetClassName.equals(clName)) {
				return cl;
			}
		}
		
		return null;
		
	}
	
	public void fromJSON(File folder) throws IllegalArgumentException {
		
		if (!folder.isDirectory()) {
			throw new IllegalArgumentException("Input needs to be a folder!");
		}
		
		File[] jsonFiles = folder.listFiles(new FilenameFilter() {
			
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".json");
	        }
	    });
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			for (File json : jsonFiles) {
				
				EasyClass cl = mapper.readValue(json, EasyClass.class);
				this.classes.add(cl);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		return EasyObjectMapper.produceJSON(this);
	}
	
}
