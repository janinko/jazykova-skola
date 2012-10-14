/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lection;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fivekeyem
 */
public class LectionDAOTest {
	
	private LectionDAO lections;
	
	public LectionDAOTest() {
	}
	
	@Before
	public void setUp() {
		lections = new LectionDAOImpl();
	}

	@Test
	public void testCreate() {
	}

}
