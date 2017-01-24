package org.easydata.model;

public class EasyRelation extends EasyObj {

	public static enum RelationType {
		ONE_TO_ONE, ONE_TO_MANY, BI_DI_ONE_TO_ONE
	}
	
	private RelationType _type = null;
	private String _to = null;
	
	public EasyRelation(RelationType type, String to) {
		this._type = type;
		this._to = to;
	}
	
	public RelationType getType() {
		return this._type;
	}
	
	public String getTo() {
		return this._to;
	}

}
