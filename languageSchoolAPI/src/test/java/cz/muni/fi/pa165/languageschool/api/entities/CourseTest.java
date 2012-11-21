package cz.muni.fi.pa165.languageschool.api.entities;

import static org.junit.Assert.*;
import org.junit.Test;

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
		Course c1 = new Course("Anglictina pro zacatecniky");
                c1.setId(new Long(1));
		Course c2 = new Course("Anglictina pro zacatecniky");
                c2.setId(new Long(1));
			
		
		assertEquals(c1, c2);
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals2() {
		Course c1 = new Course("Anglictina pro zacatecniky");
                c1.setId(new Long(1));
		Course c2 = new Course("Anglictina pro zacatecniky");
                c2.setId(new Long(2));
		
		assertFalse(c1.equals(c2));
	}
	
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals3() {
		Course c1 = new Course("Anglictina pro zacatecniky");
                c1.setId(null);
		Course c2 = new Course("Anglictina pro zacatecniky");
                c2.setId(null);
		
		assertFalse(c1.equals(c2));
	}

	
	/**
	 * Test equals method
	 */
	@Test
	public void testEquals4() {
		Course c1 = new Course();
		Course c2 = new Course();
		
		assertFalse(c1.equals(c2));
	}
}
