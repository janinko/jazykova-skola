package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class StudentDAOTest {
	
	private StudentDAO students;
	
	public StudentDAOTest() {
	}
	
	@Before
	public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
		students = new StudentDAOImpl(emf);
	}
	
	
	@Test
	public void testUpdate(){
		Student s1 = new Student("Radek", "Capek");
		students.create(s1);
		
		s1.setFirstName("Radek po uprave");
		students.update(s1);
	}
	
	
	
	@Test
	public void testSomeMethod1() {
		Student s1 = new Student("Radek", "Capek");
		Student s2 = students.create(s1);
		
		assertEquals(s1, s2);
		assertEquals(s1.getFirstName(), s2.getFirstName());
		assertEquals(s1.getLastName(), s2.getLastName());
		assertEquals(s1.getId(), s2.getId());
	}
	
	
	
	@Test
	public void testSomeMethod2() {
		Student s1 = new Student("Radek", "Capek");
		assertTrue(s1.getId() == null);
		students.create(s1);
		assertFalse(s1.getId() == null);
	}
	
	
	
	@Test
	public void testSomeMethod3() {
		Student s1 = new Student("Radek", "Capek");
		Student s2;
		students.create(s1);
		
		s2 = students.read(s1.getId());
		
		assertTrue(s2.getId().equals(s2.getId()));
		
	}
	
	
	@Test
	public void testFindStudentByName1() {
		students.create(new Student("Honza", "Siroky"));

		List<Student> ls = students.findStudentByName("Honza", "Siroky");
		Student s2 = ls.get(0);
		
		assertFalse(ls.isEmpty());
		assertTrue(s2.getFirstName().equals("Honza"));
		assertTrue(s2.getLastName().equals("Siroky"));
	}
	
	
	@Test
	public void testFindStudentByName2() {
		List<Student> ls = students.findStudentByName("Honza", "Siroky");
		
		assertTrue(ls.isEmpty());
	}
	
	
	@Test
	public void testFindAllStudent1() {
		students.create(new Student("Honza1", "Siroky1"));
		students.create(new Student("Honza2", "Siroky2"));
		students.create(new Student("Honza3", "Siroky3"));

		List<Student> ls = students.findAllStudents();
		Student s1 = ls.get(0);
		Student s2 = ls.get(1);
		Student s3 = ls.get(2);
		
		assertFalse(ls.isEmpty());
		assertTrue(ls.size() == 3);
		
		assertTrue(s1.getFirstName().equals("Honza1"));
		assertTrue(s1.getLastName().equals("Siroky1"));
		assertTrue(s2.getFirstName().equals("Honza2"));
		assertTrue(s2.getLastName().equals("Siroky2"));
		assertTrue(s3.getFirstName().equals("Honza3"));
		assertTrue(s3.getLastName().equals("Siroky3"));
	}
	

	
	@Test
	public void testCreateAndDelete() {
		students.create(new Student("Honza1", "Siroky1"));
		students.create(new Student("Honza2", "Siroky2"));
		students.create(new Student("Honza3", "Siroky3"));

		List<Student> ls = students.findAllStudents();
		
		assertFalse(ls.isEmpty());
		assertTrue(ls.size() == 3);
		
		Student s2 = ls.get(1);
		System.out.println(s2);
		students.delete(s2);
		
		ls = students.findAllStudents();
		assertTrue(ls.size() == 2);
		
		Student s4 = ls.get(1);
		assertTrue(s4.getFirstName().equals("Honza3"));
		assertTrue(s4.getLastName().equals("Siroky3"));
		
		List<Student> ls2 = students.findStudentByName("Honza2", "Siroky2");
		assertTrue(ls2.isEmpty());
	}
	
	
}
