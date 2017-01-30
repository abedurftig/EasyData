package org.easydata.access;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.easydata.model.EasyPojo;

public abstract class EasyCSVRepository<P extends EasyPojo> extends EasyRepository<P> {

	public void init(String path) throws IOException {
		
		Reader in = null;
		try {
			
			String filePath = path + "/" + getInputFileName();
			in = new FileReader(filePath);
			CSVFormat format = CSVFormat.RFC4180.withFirstRecordAsHeader().withSkipHeaderRecord(true);
			Iterable<CSVRecord> records = format.parse(in);
			Iterator<CSVRecord> iter = records.iterator();
			
			P pojo = null;
			
			while (iter.hasNext()) {
				
				pojo = createFrom(iter.next());
				add(pojo);
				
			}
			
			populateReferenceIndices();
			
			
		} catch (ParseException pe) {
			
			pe.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			in.close();
		}
		
	}
	
	public abstract P createFrom(CSVRecord record) throws ParseException;
	
	public abstract String getInputFileName();
	
}
