package org.easydata.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class EasyModelTest extends EasyTestBase {
	
	@Test
	public void numberOfClasses() {
		
		assertEquals("Expect two classes", 2, getModel().classes.size());
		
	}
	
	@Test
	public void getClassByName() {
		
		EasyClass addCl = getModel().getClassByName("Address");
		assertNotNull("EasyClass instance for 'Address' should exist", addCl);
		
		EasyClass empCl = getModel().getClassByName("Employee");
		assertNotNull("EasyClass instance for 'Employee' should exist", empCl);
		
		EasyClass noCl = getModel().getClassByName("NotExisting");
		assertNull("EasyClass instance for 'NotExisting' should not exist", noCl);
		
	}

}
