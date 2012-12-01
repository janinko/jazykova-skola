package cz.muni.fi.pa165.languageschool.api.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author
 */
public class StudentTest {
	
	public StudentTest() {
	}

	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Student s1 = new Student("Pepa", "Novak", "pnovak@example.com");
                s1.setId(new Long(1));
		Student s2 = new Student("Pepa", "Novak", "pnovak@example.com");
                s2.setId(new Long(1));
		
		assertEquals(s1, s2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Student s1 = new Student("Pepa", "Novak", "pnovak@example.com");
                s1.setId(new Long(1));
		Student s2 = new Student("Pepa", "Novak", "pnovak@example.com");
                s2.setId(new Long(2));
		
		assertFalse(s1.equals(s2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Student s1 = new Student("Pepa", "Novak", "pnovak@example.com");
                s1.setId(null);
		Student s2 = new Student("Pepa", "Novak", "pnovak@example.com");
                s2.setId(null);
		
		assertFalse(s1.equals(s2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Student s1 = new Student();
		Student s2 = new Student();
		
		assertFalse(s1.equals(s2));
	}

}
