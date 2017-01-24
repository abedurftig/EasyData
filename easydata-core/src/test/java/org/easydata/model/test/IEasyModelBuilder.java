package org.easydata.model.test;

import org.easydata.model.EasyObj;

public interface IEasyModelBuilder<T extends EasyObj, B extends IEasyModelBuilder<T, B>> {

	public B create();
	
	public T get();
	
}
