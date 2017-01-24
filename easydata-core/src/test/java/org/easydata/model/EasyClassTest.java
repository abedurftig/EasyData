package org.easydata.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EasyClassTest extends EasyTestBase {

	@Test
	public void checkAddressClass() {
		
		EasyClass cl = getModel().getClassByName("Address");
		assertEquals("targetClassName should be different", "Address", cl.targetClassName);
		
	}
	
	@Test
	public void checkEmployeeClass() {
		
		EasyClass cl = getModel().getClassByName("Employee");
		assertEquals("targetClassName should be different", "Employee", cl.targetClassName);
		
	}

}
