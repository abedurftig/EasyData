package org.easydata.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import your.domain.data.Employees;

public class EasyRepositoryGeneratorTest {

	@Test
	public void firstTest() throws IOException {
		
		String in = Paths.get("src/test/resources/csv/").toAbsolutePath().toString();
		Employees emps = new Employees();
		emps.init(in);
		
		assertEquals("firstname of emp with id emp:1", "Peter", emps.getById("emp:1").getFirstName());
		assertEquals("size should match", 3588, emps.size());
		
		for (int i = 1; i <= 3588; i++) {
			assertNotNull("should contains employee with id", emps.getById("emp:" + i));
		}
		
	}
	
}
