/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.api.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class TeacherTest {
	
	public TeacherTest() {
	}
	
	@Before
	public void setUp() {
	}
	
	

	/**
	 * Test equals method, of class Teacher.
	 */
	@Test
	public void testEquals1() {
		Teacher t1 = new Teacher("Martin", "Novak", "email");
                t1.setId(new Long(1));
		Teacher t2 = new Teacher("Martin", "Novak", "email");
                t2.setId(new Long(1));
		
		assertEquals(t1, t2);
	}
	
	
	/**
	 * Test equals method, of class Teacher.
	 */
	@Test
	public void testEquals2() {
		Teacher t1 = new Teacher("Martin", "Novak", "email");
                t1.setId(new Long(1));
		Teacher t2 = new Teacher("Martin", "Novak", "email");
                t2.setId(new Long(2));
		
		assertFalse(t1.equals(t2));
	}
	
	
	/**
	 * Test equals method, of class Teacher.
	 */
	@Test
	public void testEquals3() {
		Teacher t1 = new Teacher("Martin", "Novak", "email");
                t1.setId(null);
		Teacher t2 = new Teacher("Martin", "Novak", "email");
                t2.setId(null);
		
		assertFalse(t1.equals(t2));
	}

	
	/**
	 * Test equals method, of class Teacher.
	 */
	@Test
	public void testEquals4() {
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		
		assertFalse(t1.equals(t2));
	}

}
