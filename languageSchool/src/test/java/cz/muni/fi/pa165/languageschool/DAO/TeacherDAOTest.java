package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.AbstractSpringTest;
import cz.muni.fi.pa165.languageschool.api.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fivekeyem
 */
public class TeacherDAOTest extends AbstractSpringTest{

	@Autowired
    private TeacherDAO teacherDao;

    public TeacherDAOTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testCreate1() {
        Teacher t1 = new Teacher("Pavel", "Hanak","email");
        Teacher t2 = teacherDao.create(t1);

        assertEquals(t1, t2);
        assertEquals(t1.getFirstName(), t2.getFirstName());
        assertEquals(t1.getLastName(), t2.getLastName());
        assertEquals(t1.getEmail(), t2.getEmail());
        assertEquals(t1.getId(), t2.getId());

        teacherDao.delete(t1);
    }

    @Test
    public void testCreate2() {
        Teacher t1 = new Teacher("Pavel", "Hanak", "email");
        assertNull(t1.getId());
        teacherDao.create(t1);
        assertNotNull(t1.getId());

        teacherDao.delete(t1);
    }

    @Test
    public void testRead() {
        Teacher t1 = new Teacher("Pavel", "Hanak", "email");
        Teacher t2;
        teacherDao.create(t1);

        t2 = teacherDao.read(t1.getId());

        assertTrue(t2.getId().equals(t2.getId()));

        teacherDao.delete(t1);
    }

    @Test
    public void testFindTeacherByName1() {
        Teacher t1 = new Teacher("Honza1", "Siroky1", "email1");
        Teacher t2 = new Teacher("Honza2", "Siroky2", "email2");
        Teacher t3 = new Teacher("Honza3", "Siroky3", "email3");

        teacherDao.create(t1);
        teacherDao.create(t2);
        teacherDao.create(t3);

        List<Teacher> ls = teacherDao.findTeacherByName("Honza2", "Siroky2");
        Teacher t4 = ls.get(0);

        assertTrue(ls.size()==1);
        assertTrue(t4.getFirstName().equals("Honza2"));
        assertTrue(t4.getLastName().equals("Siroky2"));
        assertTrue(t4.getEmail().equals("email2"));

        teacherDao.delete(t1);
        teacherDao.delete(t2);
        teacherDao.delete(t3);
    }

    @Test
    public void testFindTeacherByName2() {
        List<Teacher> ls = teacherDao.findTeacherByName("Honza", "Siroky");

        assertTrue(ls.isEmpty());
    }

    @Test
    public void testFindAllTeacher() {
        Teacher t1 = new Teacher("Honza1", "Siroky1", "email1");
        Teacher t2 = new Teacher("Honza2", "Siroky2", "email2");
        Teacher t3 = new Teacher("Honza3", "Siroky3", "email3");

        teacherDao.create(t1);
        teacherDao.create(t2);
        teacherDao.create(t3);
        
        List<Teacher> ls = teacherDao.findAllTeachers();
        Teacher t4 = ls.get(0);
        Teacher t5 = ls.get(1);
        Teacher t6 = ls.get(2);

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        assertTrue(t4.getFirstName().equals("Honza1"));
        assertTrue(t4.getLastName().equals("Siroky1"));
        assertTrue(t5.getFirstName().equals("Honza2"));
        assertTrue(t5.getLastName().equals("Siroky2"));
        assertTrue(t6.getFirstName().equals("Honza3"));
        assertTrue(t6.getLastName().equals("Siroky3"));

        teacherDao.delete(t1);
        teacherDao.delete(t2);
        teacherDao.delete(t3);
    }

    
    @Test
    public void testCreateAndDelete() {
        Teacher t1 = new Teacher("Honza1", "Siroky1", "email1");
        Teacher t2 = new Teacher("Honza2", "Siroky2", "email2");
        Teacher t3 = new Teacher("Honza3", "Siroky3", "email3");

        teacherDao.create(t1);
        teacherDao.create(t2);
        teacherDao.create(t3);

        List<Teacher> ls = teacherDao.findAllTeachers();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Teacher t4 = ls.get(1);
        teacherDao.delete(t4);

        ls = teacherDao.findAllTeachers();
        assertTrue(ls.size() == 2);

        Teacher t5 = ls.get(1);
        assertTrue(t5.getFirstName().equals("Honza3"));
        assertTrue(t5.getLastName().equals("Siroky3"));

        List<Teacher> lt2 = teacherDao.findTeacherByName("Honza2", "Siroky2");
        assertTrue(lt2.isEmpty());
        
        teacherDao.delete(t1);
        teacherDao.delete(t3);
    }
}
