package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class StudentDAOTest {

    private StudentDAO students;
    private CourseDAO courses;

    public StudentDAOTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
        students = new StudentDAOImpl(emf);
        courses = new CourseDAOImpl(emf);
    }
	
	@After
	public void tearDown() {
	}
	

    @Test
    public void testUpdate() {
        Student s1 = new Student("Radek", "Capek");
        students.create(s1);

        s1.setFirstName("Radek po uprave");
        students.update(s1);

        students.delete(s1);
    }

    @Test
    public void testCreate1() {
        Student s1 = new Student("Radek", "Capek");
        Student s2 = students.create(s1);

        assertEquals(s1, s2);
        assertEquals(s1.getFirstName(), s2.getFirstName());
        assertEquals(s1.getLastName(), s2.getLastName());
        assertEquals(s1.getId(), s2.getId());

        students.delete(s1);
    }

    @Test
    public void testCreate2() {
        Student s1 = new Student("Radek", "Capek");
        assertTrue(s1.getId() == null);
        students.create(s1);
        assertFalse(s1.getId() == null);

        students.delete(s1);
    }

    @Test
    public void testRead1() {
        Student s1 = new Student("Radek", "Capek");
        students.create(s1);

        Student s2 = students.read(s1.getId());

        assertTrue(s2.getId().equals(s2.getId()));

        students.delete(s1);
    }

    @Test
    public void testFindStudentByName1() {
        Student s1 = new Student("Honza1", "Siroky1");
        Student s2 = new Student("Honza2", "Siroky2");
        Student s3 = new Student("Honza3", "Siroky3");

        students.create(s1);
        students.create(s2);
        students.create(s3);

        List<Student> ls = students.findStudentByName("Honza2", "Siroky2");
        Student t4 = ls.get(0);

        assertTrue(ls.size()==1);
        assertTrue(t4.getFirstName().equals("Honza2"));
        assertTrue(t4.getLastName().equals("Siroky2"));

        students.delete(s1);
        students.delete(s2);
        students.delete(s3);
    }

    @Test
    public void testFindStudentByName2() {
        List<Student> ls = students.findStudentByName("Honza", "Siroky");

        assertTrue(ls.isEmpty());
    }

    @Test
    public void testFindAllStudents() {
        Student s1 = new Student("Honza1", "Siroky1");
        Student s2 = new Student("Honza2", "Siroky2");
        Student s3 = new Student("Honza3", "Siroky3");

        students.create(s1);
        students.create(s2);
        students.create(s3);

        List<Student> ls = students.findAllStudents();
        Student s4 = ls.get(0);
        Student s5 = ls.get(1);
        Student s6 = ls.get(2);

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        assertTrue(s4.getFirstName().equals("Honza1"));
        assertTrue(s4.getLastName().equals("Siroky1"));
        assertTrue(s5.getFirstName().equals("Honza2"));
        assertTrue(s5.getLastName().equals("Siroky2"));
        assertTrue(s6.getFirstName().equals("Honza3"));
        assertTrue(s6.getLastName().equals("Siroky3"));
        
        students.delete(s1);
        students.delete(s2);
        students.delete(s3);
    }

    @Test
    public void testFindStudentByLection() {
        Course c1 = new Course();
        Course c2 = new Course();
        Course c3 = new Course();
        
        Student s1 = new Student("Honza1", "Siroky1");
        
        Student s2 = new Student("Honza2", "Siroky2");
        Student s3 = new Student("Honza3", "Siroky3");

        students.create(s1);
        students.create(s2);
        students.create(s3);

        List<Student> ls = students.findAllStudents();
        Student s4 = ls.get(0);
        Student s5 = ls.get(1);
        Student s6 = ls.get(2);

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        assertTrue(s4.getFirstName().equals("Honza1"));
        assertTrue(s4.getLastName().equals("Siroky1"));
        assertTrue(s5.getFirstName().equals("Honza2"));
        assertTrue(s5.getLastName().equals("Siroky2"));
        assertTrue(s6.getFirstName().equals("Honza3"));
        assertTrue(s6.getLastName().equals("Siroky3"));
        
        students.delete(s1);
        students.delete(s2);
        students.delete(s3);
    }
    
    @Test
    public void testCreateAndDelete() {
        Student s1 = new Student("Honza1", "Siroky1");
        Student s2 = new Student("Honza2", "Siroky2");
        Student s3 = new Student("Honza3", "Siroky3");

        students.create(s1);
        students.create(s2);
        students.create(s3);

        List<Student> ls = students.findAllStudents();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Student s4 = ls.get(1);
        students.delete(s4);

        ls = students.findAllStudents();
        assertTrue(ls.size() == 2);

        Student s5 = ls.get(1);
        assertTrue(s5.getFirstName().equals("Honza3"));
        assertTrue(s5.getLastName().equals("Siroky3"));

        List<Student> ls2 = students.findStudentByName("Honza2", "Siroky2");
        assertTrue(ls2.isEmpty());    
        
        students.delete(s1);
        students.delete(s3);
    }
}
