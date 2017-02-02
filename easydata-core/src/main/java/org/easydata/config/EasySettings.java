package org.easydata.config;

public class EasySettings {

	private String _inputPath = null;
	private String _ouputPath = null;
	
	public EasySettings(String inputPath, String outputPath) {
		
		if (inputPath == null) {
			throw new IllegalArgumentException("inputPath arg cannot not be null.");
		}
		
		if (outputPath == null) {
			throw new IllegalArgumentException("outputPath arg cannot not be null.");
		}
		
		this._inputPath = inputPath;
		this._ouputPath = outputPath;
		
	}
	
	public String getInputPath() {
		return _inputPath;
	}

	public void setInputPath(String inputPath) {
		this._inputPath = inputPath;
	}

	public String getOuputPath() {
		return _ouputPath;
	}

	public void setOuputPath(String ouputPath) {
		this._ouputPath = ouputPath;
	}
	
	
}
