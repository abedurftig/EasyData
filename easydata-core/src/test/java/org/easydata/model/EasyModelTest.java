package org.easydata.model;

import static org.junit.Assert.assertEquals;

import org.easydata.builder.EasyClassBuilder;
import org.easydata.builder.EasyModelBuilder;
import org.junit.Test;

public class EasyModelTest {
	
	@Test
	public void numberOfClasses() {
		
		EasyClassBuilder c1 = new EasyClassBuilder().create()
				.withClassName("Employee");
		
		EasyClassBuilder c2 = new EasyClassBuilder().create()
				.withClassName("Address");
		
		EasyModel em = new EasyModelBuilder().create()
				.withEasyClass(c1)
				.withEasyClass(c2)
				.get();
		
		assertEquals("Expect two classes", 2, em.classes.size());
		
	}
	
	@Test
	public void getClassByName() {
		
		EasyClassBuilder c1 = new EasyClassBuilder().create()
				.withClassName("Employee");
		
		EasyClassBuilder c2 = new EasyClassBuilder().create()
				.withClassName("Address");
		
		EasyModel em = new EasyModelBuilder().create()
				.withEasyClass(c1)
				.withEasyClass(c2)
				.get();
		
		EasyClass clAddress = em.getClassByName("Address");
		assertEquals("targetClassName should be different", "Address", clAddress.targetClassName);
		
		EasyClass clEmployee = em.getClassByName("Employee");
		assertEquals("targetClassName should be different", "Employee", clEmployee.targetClassName);
		
	}

}
