/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.api.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class LessonTest {
	
	public LessonTest() {
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Lesson l1 = new Lesson();
                l1.setId(new Long(1));
		Lesson l2 = new Lesson();
                l2.setId(new Long(1));
		
		assertEquals(l1, l2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Lesson l1 = new Lesson();
                l1.setId(new Long(1));
		Lesson l2 = new Lesson();
                l2.setId(new Long(2));
		
		assertFalse(l1.equals(l2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Lesson l1 = new Lesson();
                l1.setId(null);
		Lesson l2 = new Lesson();
                l2.setId(null);
		
		assertFalse(l1.equals(l2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Lesson l1 = new Lesson();
		Lesson l2 = new Lesson();
		
		assertFalse(l1.equals(l2));
	}
}
