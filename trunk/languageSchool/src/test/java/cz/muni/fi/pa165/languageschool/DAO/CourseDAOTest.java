package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
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
public class CourseDAOTest {

    private CourseDAO courses;

    public CourseDAOTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
        courses = new CourseDAOImpl(emf);
    }

    @Test
    public void testCreate1() {
        Course c1 = new Course("Anglictina pro pokrocile");
        Course c2 = courses.create(c1);

        assertEquals(c1, c2);
        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courses.delete(c1);
    }

    @Test
    public void testCreate2() {
        Course c1 = new Course("Anglictina pro pokrocile");

        assertTrue(c1.getId() == null);
        Course c2 = courses.create(c1);

        assertFalse(c1.getId() == null);
        assertFalse(c2.getId() == null);

        courses.delete(c1);
    }

    @Test
    public void testRead() {
        Course c1 = new Course("Anglictina pro pokrocile");

        courses.create(c1);

        Course c2 = courses.read(c1.getId());

        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courses.delete(c1);
    }

    @Test
    public void testFindAllCourses() {
        Course c1 = new Course("Anglictina pro pokrocile");

        courses.create(c1);

        List<Course> ls = courses.findAllCourses();
        Course c2 = ls.get(0);

        assertFalse(ls.isEmpty());

        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courses.delete(c1);
    }

    @Test
    public void testFindCourseByLanguage() {
        Course c1 = new Course("Anglictina pro pokrocile");
        c1.setLanguage(Teacher.Language.AJ);
        Course c2 = new Course("Francouzstina pro zacatecniky");
        c2.setLanguage(Teacher.Language.FJ);
        Course c3 = new Course("Nemcina pro pokrocile");
        c3.setLanguage(Teacher.Language.NJ);

        courses.create(c1);
        courses.create(c2);
        courses.create(c3);

        List<Course> ls = courses.findCourseByLanguage(Teacher.Language.FJ);
        Course c4 = ls.get(0);
        
        assertTrue(ls.size()==1);
        assertTrue(c4.getName().equals("Francouzstina pro zacatecniky"));

        courses.delete(c1);
        courses.delete(c2);
        courses.delete(c3);
    }

    @Test
    public void testCreateAndDelete() {
        courses.create(new Course("Anglictina pro pokrocile"));
        courses.create(new Course("Afrikanstina pro pokrocile"));
        courses.create(new Course("Cestina pro pokrocile"));

        List<Course> ls = courses.findAllCourses();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Course c2 = ls.get(1);
        courses.delete(c2);

        ls = courses.findAllCourses();
        assertTrue(ls.size() == 2);

        Course c4 = ls.get(1);
        assertTrue(c4.getName().equals("Cestina pro pokrocile"));
    }
}
