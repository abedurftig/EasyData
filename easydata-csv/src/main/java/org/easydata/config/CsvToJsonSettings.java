package org.easydata.config;

public class CsvToJsonSettings extends EasySettings {

	private String _idColumnName = "ID";
	private String _referenceSuffix = "ID";

	public CsvToJsonSettings(String inputPath, String outputPath) {
		super(inputPath, outputPath);
	}
	
	public String getIdColumnName() {
		return _idColumnName;
	}
	
	public void setIdColumnName(String idColumnName) {
		this._idColumnName = idColumnName;
	}

	public String getReferenceSuffix() {
		return _referenceSuffix;
	}

	public void setReferenceSuffix(String referenceSuffix) {
		this._referenceSuffix = referenceSuffix;
	}

}
