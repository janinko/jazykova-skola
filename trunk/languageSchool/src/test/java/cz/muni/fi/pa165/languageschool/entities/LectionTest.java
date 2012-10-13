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
public class LectionTest {
	
	public LectionTest() {
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals1() {
		Lection t1 = new Lection(new Long(1));
		Lection t2 = new Lection(new Long(1));
		
		assertEquals(t1, t2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Lection t1 = new Lection(new Long(1));
		Lection t2 = new Lection(new Long(2));
		
		assertFalse(t1.equals(t2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Lection t1 = new Lection(null);
		Lection t2 = new Lection(null);
		
		assertFalse(t1.equals(t2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Lection t1 = new Lection();
		Lection t2 = new Lection();
		
		assertFalse(t1.equals(t2));
	}
}
