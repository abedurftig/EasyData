package org.easydata.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easydata.builder.EasyClassBuilder;
import org.easydata.builder.EasyFieldBuilder;
import org.easydata.builder.EasyRelationBuilder;
import org.junit.Test;

public class EasyClassTest {

	@Test
	public void getManyToOneRelations() {
		
		EasyRelationBuilder r1 = new EasyRelationBuilder()
				.withManyToOne("Address");
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyRelation(r1)
				.get();
		
		assertEquals("Expected different number of many-to-one relations", 1, c1.getManyToOneRelations().size());
		
	}
	
	@Test
	public void getKeyField() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("ID")
				.makeKey();
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.get();
		
		assertEquals("Expected different key field", f1.get(), c1.getKeyField());
		
	}

	@Test
	public void getRefs() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("ID")
				.makeKey();
		
		EasyFieldBuilder f2 = new EasyFieldBuilder()
				.create()
				.withTargetName("ContactID")
				.withRef("Contact");
		
		EasyFieldBuilder f3 = new EasyFieldBuilder()
				.create()
				.withTargetName("AddressID")
				.withRef("Address");
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.withEasyField(f2)
				.withEasyField(f3)
				.get();
		
		assertEquals("Expected different number of refs", 2, c1.getRefs().size());
		
	}
	
	@Test
	public void hasDateFieldPositive() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("DateOfBirth")
				.withType(EasyType.DATE);
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.get();
		
		assertTrue("Expected to have date field", c1.hasDateField());
		
	}
	
	@Test
	public void hasDateFieldNegative() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("Name")
				.withType(EasyType.TEXT);
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.get();
		
		assertFalse("Expected not to have date field", c1.hasDateField());
		
	}
	
	@Test
	public void hasRefToPositive() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("ContactID")
				.withRef("Contact");
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.get();
		
		assertTrue("Expected to ref Contact", c1.hasRefTo("Contact"));
		
	}
	
	@Test
	public void hasRefToNegative() {
		
		EasyFieldBuilder f1 = new EasyFieldBuilder()
				.create()
				.withTargetName("ContactID")
				.withRef("Contact");
		
		EasyClass c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(f1)
				.get();
		
		assertFalse("Expected not to ref Address", c1.hasRefTo("Address"));
		
	}
	
}
