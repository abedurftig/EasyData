package org.easydata.builder;

import org.easydata.model.EasyRelation;
import org.easydata.model.EasyRelation.RelationType;

public class EasyRelationBuilder implements IEasyModelBuilder<EasyRelation, EasyRelationBuilder> {

	private EasyRelation _relation = null;
	
	public EasyRelationBuilder withOneToMany(String to) {
		
		this._relation = new EasyRelation(RelationType.ONE_TO_MANY, to);
		return this;
		
	}
	
	public EasyRelationBuilder withOneToOne(String to) {
		
		this._relation = new EasyRelation(RelationType.BI_DI_ONE_TO_ONE, to);
		return this;
		
	}
	
	public EasyRelationBuilder withManyToOne(String to) {
		
		this._relation = new EasyRelation(RelationType.MANY_TO_ONE, to);
		return this;
		
	}
	
	@Override
	public EasyRelation get() {
		return this._relation;
	}

	@Override
	public EasyRelationBuilder create() {
		
		this._relation = new EasyRelation(RelationType.ONE_TO_ONE, "nowhere");
		return this;
		
	}

}
