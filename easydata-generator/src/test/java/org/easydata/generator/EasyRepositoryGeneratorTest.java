package org.easydata.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.Test;

import your.domain.data.Addresses;
import your.domain.data.Boss;
import your.domain.data.Bosses;
import your.domain.data.Employee;
import your.domain.data.Employees;
import your.domain.data.Repositories;

public class EasyRepositoryGeneratorTest {

	@Test
	public void readCSV() throws IOException {
		
		String in = Paths.get("src/test/resources/employees-csv/").toAbsolutePath().toString();
		Employees emps = new Employees();
		emps.init(in);
		
		assertEquals("firstname of emp with id emp:1", "Peter", emps.getById("emp:1").getFirstName());
		assertEquals("size should match", 3588, emps.size());
		
		for (int i = 1; i <= 3588; i++) {
			assertNotNull("should contain employee with id", emps.getById("emp:" + i));
		}
		
	}
	
	@Test
	public void readCSVwithRelations() throws IOException {
		
		String in = Paths.get("src/test/resources/repository-test-csv/").toAbsolutePath().toString();
		Repositories.init(in);
		
		Employees empRepo = Repositories.get(Employees.class);
		Addresses addRepo = Repositories.get(Addresses.class);
		Bosses bossRepo = Repositories.get(Bosses.class);
		
		assertNotNull("employee repository should exists", empRepo);
		assertEquals("size should match", 3, empRepo.size());
		
		assertNotNull("address repository should exists", addRepo);
		assertEquals("size should match", 4, addRepo.size());
		
		assertNotNull("boss repository should exists", bossRepo);
		assertEquals("size should match", 2, bossRepo.size());
		
		Boss boss = empRepo.getBoss(empRepo.getById("emp:1"));
		String bossFirstName = boss.getFirstName();
		assertEquals("boss first name should match", "Arne", bossFirstName);
		
		Set<Employee> emps = empRepo.getEmployeesByBossId(boss.getKeyValue());
		assertEquals("number of employees should match", 2, emps.size());
		
	}
	
}
