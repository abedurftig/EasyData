package org.easydata.model.test;

import org.easydata.model.EasyField;

public class EasyFieldBuilder implements IEasyModelBuilder<EasyField, EasyFieldBuilder> {

	private EasyField _field = null;
	
	public EasyFieldBuilder makeKey() {
		
		this._field.key = true;
		return this;
		
	}
	
	public EasyFieldBuilder notKey() {
		
		this._field.key = false;
		return this;
		
	}
	
	public EasyFieldBuilder withTargetName(String name) {
		
		this._field.targetFieldName = name;
		return this;
		
	}
	
	public EasyFieldBuilder withRef(String ref) {
		
		this._field.ref = ref;
		return this;
		
	}
	
	public EasyFieldBuilder removeRef() {
		
		this._field.ref = null;
		return this;
		
	}
	
	@Override
	public EasyFieldBuilder create() {
		
		this._field = new EasyField();
		return this;
		
	}
	
	@Override
	public EasyField get() {
		return _field;
	}

}
