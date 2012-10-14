/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fivekeyem
 */
public class TeacherDAOTest {
	
	private TeacherDAO teachers;
	
	public TeacherDAOTest() {
	}

	@Before
	public void setUp() {
		teachers = new TeacherDAOImpl();
	}
	
	@Test
	public void testSomeMethod() {
	}
}
