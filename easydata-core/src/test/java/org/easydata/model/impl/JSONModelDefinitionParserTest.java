package org.easydata.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyRelation;
import org.easydata.model.EasyRelation.RelationType;
import org.easydata.model.test.EasyClassBuilder;
import org.easydata.model.test.EasyFieldBuilder;
import org.easydata.model.test.EasyModelBuilder;
import org.junit.Test;

public class JSONModelDefinitionParserTest {

	@Test
	public void addRelationshipsOneToMany() {
		
		EasyFieldBuilder fb1 = new EasyFieldBuilder().create()
				.withTargetName("Id")
				.makeKey();
		
		EasyClassBuilder c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(fb1);
		
		EasyFieldBuilder fb2 = new EasyFieldBuilder().create()
				.withTargetName("Id")
				.makeKey();
		
		EasyFieldBuilder fb3 = new EasyFieldBuilder().create()
				.withTargetName("EmployeeId")
				.withRef("Employee");
		
		EasyClassBuilder c2 = new EasyClassBuilder().create()
				.withClassName("Address")
				.withEasyField(fb2)
				.withEasyField(fb3);
		
		EasyModel em = new EasyModelBuilder().create()
				.withEasyClass(c1)
				.withEasyClass(c2)
				.get();
		
		// want to test this one later
		EasyClass emp = c1.get();
		EasyClass add = c2.get();
		
		// act
		JSONModelDefinitionParser parser = new JSONModelDefinitionParser();
		parser.addRelationships(em);
		
		assertEquals("should have relation", 1, add.relations.size());
		
		EasyRelation r1 = add.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.ONE_TO_MANY, r1.getType());
		assertEquals("should point to class", "Employee", r1.getTo());
		
		assertTrue("should be empty", emp.relations.isEmpty());
		
	}
	
	@Test
	public void addRelationshipsOneToOne() {
		
		EasyFieldBuilder fb1 = new EasyFieldBuilder().create()
				.withTargetName("Id")
				.makeKey();
		
		EasyFieldBuilder fb2 = new EasyFieldBuilder().create()
				.withTargetName("AddressId")
				.withRef("Address");
		
		EasyClassBuilder c1 = new EasyClassBuilder().create()
				.withClassName("Employee")
				.withEasyField(fb1)
				.withEasyField(fb2);
		
		EasyFieldBuilder fb3 = new EasyFieldBuilder().create()
				.withTargetName("Id")
				.makeKey();
		
		EasyFieldBuilder fb4 = new EasyFieldBuilder().create()
				.withTargetName("EmployeeId")
				.withRef("Employee");
		
		EasyClassBuilder c2 = new EasyClassBuilder().create()
				.withClassName("Address")
				.withEasyField(fb3)
				.withEasyField(fb4);
		
		EasyModel em = new EasyModelBuilder().create()
				.withEasyClass(c1)
				.withEasyClass(c2)
				.get();
		
		// want to test this one later
		EasyClass emp = c1.get();
		EasyClass add = c2.get();
		
		// act
		JSONModelDefinitionParser parser = new JSONModelDefinitionParser();
		parser.addRelationships(em);
		
		assertEquals("should have relation", 1, add.relations.size());
		
		EasyRelation r1 = add.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.BI_DI_ONE_TO_ONE, r1.getType());
		assertEquals("should point to class", "Employee", r1.getTo());
		
		assertEquals("should have relation", 1, emp.relations.size());
		
		EasyRelation r2 = add.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.BI_DI_ONE_TO_ONE, r2.getType());
		assertEquals("should point to class", "Address", r2.getTo());
		
	}
	
}
