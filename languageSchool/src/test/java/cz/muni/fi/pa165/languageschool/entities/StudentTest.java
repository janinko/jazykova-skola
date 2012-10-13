/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.entities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fivekeyem
 */
public class StudentTest {
	
	public StudentTest() {
	}

	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Student t1 = new Student(new Long(1), "Pepa", "Novak");
		Student t2 = new Student(new Long(1), "Pepa", "Novak");
		
		assertEquals(t1, t2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Student t1 = new Student(new Long(1), "Pepa", "Novak");
		Student t2 = new Student(new Long(2), "Pepa", "Novak");
		
		assertFalse(t1.equals(t2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Student t1 = new Student(null, "Pepa", "Novak");
		Student t2 = new Student(null, "Pepa", "Novak");
		
		assertFalse(t1.equals(t2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Student t1 = new Student();
		Student t2 = new Student();
		
		assertFalse(t1.equals(t2));
	}

}
