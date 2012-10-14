/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
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
	public void testCreate1() {
		Teacher t1 = new Teacher("Pavel", "Hanak");
		Teacher t2 = teachers.create(t1);
		
		assertEquals(t1, t2);
		assertEquals(t1.getFirstName(), t2.getFirstName());
		assertEquals(t1.getLastName(), t2.getLastName());
		assertEquals(t1.getId(), t2.getId());
	}
	
	@Test
	public void testCreate2() {
		Teacher t1 = new Teacher("Pavel", "Hanak");
		assertTrue(t1.getId() == null);
		teachers.create(t1);
		assertFalse(t1.getId() == null);
	}
	
	
	
	
	
	
	@Test
	public void testRead() {
		Teacher t1 = new Teacher("Pavel", "Hanak");
		Teacher t2;
		teachers.create(t1);
		
		t2 = teachers.read(t1.getId());
		
		assertTrue(t2.getId().equals(t2.getId()));
		
	}
	
	
	@Test
	public void testFindTeacherByName1() {
		teachers.create(new Teacher("Honza", "Siroky"));

		List<Teacher> ls = teachers.findTeacherByName("Honza", "Siroky");
		Teacher t2 = ls.get(0);
		
		assertFalse(ls.isEmpty());
		assertTrue(t2.getFirstName().equals("Honza"));
		assertTrue(t2.getLastName().equals("Siroky"));
	}
	
	
	@Test
	public void testFindTeacherByName2() {
		List<Teacher> ls = teachers.findTeacherByName("Honza", "Siroky");
		
		assertTrue(ls.isEmpty());
	}
	
	
	@Test
	public void testFindAllTeacher1() {
		teachers.create(new Teacher("Honza1", "Siroky1"));
		teachers.create(new Teacher("Honza2", "Siroky2"));
		teachers.create(new Teacher("Honza3", "Siroky3"));

		List<Teacher> ls = teachers.findAllTeachers();
		Teacher t1 = ls.get(0);
		Teacher t2 = ls.get(1);
		Teacher t3 = ls.get(2);
		
		assertFalse(ls.isEmpty());
		assertTrue(ls.size() == 3);
		
		assertTrue(t1.getFirstName().equals("Honza1"));
		assertTrue(t1.getLastName().equals("Siroky1"));
		assertTrue(t2.getFirstName().equals("Honza2"));
		assertTrue(t2.getLastName().equals("Siroky2"));
		assertTrue(t3.getFirstName().equals("Honza3"));
		assertTrue(t3.getLastName().equals("Siroky3"));
	}
	

	
	@Test
	public void testCreateAndDelete() {
		teachers.create(new Teacher("Honza1", "Siroky1"));
		teachers.create(new Teacher("Honza2", "Siroky2"));
		teachers.create(new Teacher("Honza3", "Siroky3"));

		List<Teacher> ls = teachers.findAllTeachers();
		
		assertFalse(ls.isEmpty());
		assertTrue(ls.size() == 3);
		
		Teacher t2 = ls.get(1);
		System.out.println(t2);
		teachers.delete(t2);
		
		ls = teachers.findAllTeachers();
		assertTrue(ls.size() == 2);
		
		Teacher t4 = ls.get(1);
		assertTrue(t4.getFirstName().equals("Honza3"));
		assertTrue(t4.getLastName().equals("Siroky3"));
		
		List<Teacher> lt2 = teachers.findTeacherByName("Honza2", "Siroky2");
		assertTrue(lt2.isEmpty());
	}
}
