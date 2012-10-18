/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class LectionTest {
	
	public LectionTest() {
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Lection l1 = new Lection();
                l1.setId(new Long(1));
		Lection l2 = new Lection();
                l2.setId(new Long(1));
		
		assertEquals(l1, l2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Lection l1 = new Lection();
                l1.setId(new Long(1));
		Lection l2 = new Lection();
                l2.setId(new Long(2));
		
		assertFalse(l1.equals(l2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Lection l1 = new Lection();
                l1.setId(null);
		Lection l2 = new Lection();
                l2.setId(null);
		
		assertFalse(l1.equals(l2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Lection l1 = new Lection();
		Lection l2 = new Lection();
		
		assertFalse(l1.equals(l2));
	}
}
