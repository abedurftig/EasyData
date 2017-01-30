package org.easydata.model;

public class EasyRelation extends EasyObj {

	public static enum RelationType {
		ONE_TO_ONE, ONE_TO_MANY, BI_DI_ONE_TO_ONE, MANY_TO_ONE
	}
	
	private RelationType _type = null;
	private String _to = null;
	private String _refName = null;
	
	public EasyRelation(RelationType type, String to) {
		this(type, to, null);
	}
	
	public EasyRelation(RelationType type, String to, String refName) {
		this._type = type;
		this._to = to;
		this._refName = refName;
	}
	
	public RelationType getType() {
		return this._type;
	}
	
	public String getTo() {
		return this._to;
	}
	
	public String getRefName() {
		return this._refName;
	}

}
