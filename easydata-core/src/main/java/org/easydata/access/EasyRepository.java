package org.easydata.access;

import java.io.IOException;

import org.easydata.model.EasyPojo;

public abstract class EasyRepository<P extends EasyPojo> {
	
	public abstract P getById(String id);
	
	public abstract boolean add(P item) throws IllegalArgumentException;
	
	public abstract int size();
	
	public abstract void init(String path) throws IOException;
	
	protected abstract void populateReferenceIndices();
	
}
