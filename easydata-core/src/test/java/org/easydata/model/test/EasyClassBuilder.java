package org.easydata.model.test;

import org.easydata.model.EasyClass;

public class EasyClassBuilder implements IEasyModelBuilder<EasyClass, EasyClassBuilder> {

	private EasyClass _clazz = null;
	
	public EasyClassBuilder withClassName(String name) {
		
		this._clazz.targetClassName = name;
		return this;
		
	}
	
	public EasyClassBuilder withEasyField(EasyFieldBuilder fieldBuilder) {
		
		this._clazz.fields.add(fieldBuilder.get());
		return this;
		
	}
	
	public EasyClassBuilder withEasyRelation(EasyRelationBuilder relationBuilder) {
		
		this._clazz.relations.add(relationBuilder.get());
		return this;
		
	}
	
	@Override
	public EasyClass get() {
		return _clazz;
	}

	@Override
	public EasyClassBuilder create() {
		this._clazz = new EasyClass();
		return this;
	}

}
