package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class StudentDAOTest {

	private StudentDAOLocal students;
	
    public StudentDAOTest() {
    }

    @Before
    public void setUp() {
        try {
        InitialContext ctx = new InitialContext();
        students = (StudentDAOLocal) ctx.lookup("StudentDAOImpl");
        } catch (NamingException ex) {
            System.err.println("StudentDAOImpl not found.");
        }
    }
	
	@After
	public void tearDown() {
	}
	

    @Test
    public void testCreate1() {
        Student s1 = new Student("Radek", "Capek");
        Student s2 = students.create(s1);

        assertEquals(s1, s2);
        assertEquals(s1.getFirstName(), s2.getFirstName());
        assertEquals(s1.getLastName(), s2.getLastName());
        assertEquals(s1.getId(), s2.getId());

    }

    @Test
    public void testCreate2() {
        Student s1 = new Student("Radek", "Capek");
        assertNull(s1.getId());
        students.create(s1);
        assertNotNull(s1.getId());

    }

    @Test
    public void testRead1() {
        Student s1 = new Student("Radek", "Capek");
        students.create(s1);

        Student s2 = students.read(s1.getId());

        assertTrue(s2.getId().equals(s2.getId()));

    }
}
