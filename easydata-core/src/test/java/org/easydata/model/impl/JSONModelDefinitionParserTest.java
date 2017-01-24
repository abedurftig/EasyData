package org.easydata.model.impl;

import static org.junit.Assert.assertEquals;

import org.easydata.builder.EasyClassBuilder;
import org.easydata.builder.EasyFieldBuilder;
import org.easydata.builder.EasyModelBuilder;
import org.easydata.model.EasyClass;
import org.easydata.model.EasyModel;
import org.easydata.model.EasyRelation;
import org.easydata.model.EasyRelation.RelationType;
import org.junit.Test;

public class JSONModelDefinitionParserTest {

	@Test
	public void addRelationshipsOneToMany() {
		
		// arrange
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
		
		EasyClass emp = c1.get();
		EasyClass add = c2.get();
		
		// act
		new JSONModelDefinitionParser().addRelationships(em);
		
		// assert
		assertEquals("should have relation", 1, add.relations.size());
		assertEquals("should have relation", 1, emp.relations.size());
		
		EasyRelation r1 = add.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.MANY_TO_ONE, r1.getType());
		assertEquals("should point to class", "Employee", r1.getTo());
		
		EasyRelation r2 = emp.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.ONE_TO_MANY, r2.getType());
		assertEquals("should point to class", "Address", r2.getTo());
		
	}
	
	@Test
	public void addRelationshipsOneToOne() {
		
		// arrange
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
		
		EasyClass emp = c1.get();
		EasyClass add = c2.get();
		
		// act
		new JSONModelDefinitionParser().addRelationships(em);
		
		// assert
		assertEquals("should have relation", 1, add.relations.size());
		
		EasyRelation r1 = add.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.BI_DI_ONE_TO_ONE, r1.getType());
		assertEquals("should point to class", "Employee", r1.getTo());
		
		assertEquals("should have relation", 1, emp.relations.size());
		
		EasyRelation r2 = emp.relations.iterator().next();
		
		assertEquals("should be one to many", RelationType.BI_DI_ONE_TO_ONE, r2.getType());
		assertEquals("should point to class", "Address", r2.getTo());
		
	}
	
}
