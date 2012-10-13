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
public class CourseTest {
	
	public CourseTest() {
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Course t1 = new Course(new Long(1), "Anglictina pro zacatecniky");
		Course t2 = new Course(new Long(1), "Anglictina pro zacatecniky");
		
		assertEquals(t1, t2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Course t1 = new Course(new Long(1), "Anglictina pro zacatecniky");
		Course t2 = new Course(new Long(2), "Anglictina pro zacatecniky");
		
		assertFalse(t1.equals(t2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Course t1 = new Course(null, "Anglictina pro zacatecniky");
		Course t2 = new Course(null, "Anglictina pro zacatecniky");
		
		assertFalse(t1.equals(t2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Course t1 = new Course();
		Course t2 = new Course();
		
		assertFalse(t1.equals(t2));
	}
}
