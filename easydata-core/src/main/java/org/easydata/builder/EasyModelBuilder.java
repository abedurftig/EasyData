package org.easydata.builder;

import org.easydata.model.EasyModel;

public class EasyModelBuilder implements IEasyModelBuilder<EasyModel, EasyModelBuilder> {

	private EasyModel _model = null;
	
	public EasyModelBuilder withEasyClass(EasyClassBuilder classBuilder) {
		this._model.classes.add(classBuilder.get());
		return this;
	}

	@Override
	public EasyModel get() {
		return _model;
	}
	
	@Override
	public EasyModelBuilder create() {
		
		this._model = new EasyModel();
		return this;
	}
	
}
