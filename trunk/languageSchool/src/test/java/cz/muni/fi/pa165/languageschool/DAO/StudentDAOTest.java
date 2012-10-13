/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fivekeyem
 */
public class StudentDAOTest {
	
	StudentDAO students;
	
	public StudentDAOTest() {
	}
	
	@Before
	public void setUp() {
		students = new StudentDAOImpl();
	}

	@Test
	public void testSomeMethod1() {
		Student s1 = new Student("Radek", "Capek");
		Student s2 = students.create(s1);
		
		assertEquals(s1, s2);
		assertEquals(s1.getFirstName(), s2.getFirstName());
		assertEquals(s1.getLastName(), s2.getLastName());
		assertEquals(s1.getId(), s2.getId());
	}
	
	
	
	@Test
	public void testSomeMethod2() {
		Student s1 = new Student("Radek", "Capek");
		assertTrue(s1.getId() == null);
		students.create(s1);
		assertFalse(s1.getId() == null);
	}
	
	
	
	@Test
	public void testSomeMethod3() {
		Student s1 = new Student("Radek", "Capek");
		Student s2;
		students.create(s1);
		
		s2 = students.read(s1.getId());
		
		assertTrue(s2.getId().equals(s2.getId()));
		
	}
	
	
}
