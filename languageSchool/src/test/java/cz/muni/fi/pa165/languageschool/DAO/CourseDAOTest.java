package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.AbstractSpringTest;
import cz.muni.fi.pa165.languageschool.api.DAO.CourseDAO;
import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class CourseDAOTest extends AbstractSpringTest{
	@Autowired
    private CourseDAO courseDao;

    public CourseDAOTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testCreate1() {
        Course c1 = new Course("Anglictina pro pokrocile");
        Course c2 = courseDao.create(c1);

        assertEquals(c1, c2);
        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courseDao.delete(c1);
    }

    @Test
    public void testCreate2() {
        Course c1 = new Course("Anglictina pro pokrocile");

        assertNull(c1.getId());
        Course c2 = courseDao.create(c1);

        assertNotNull(c1.getId());
        assertNotNull(c2.getId());

        courseDao.delete(c1);
    }

    @Test
    public void testRead() {
        Course c1 = new Course("Anglictina pro pokrocile");

        courseDao.create(c1);

        Course c2 = courseDao.read(c1.getId());

        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courseDao.delete(c1);
    }

    @Test
    public void testFindAllCourses() {
        Course c1 = new Course("Anglictina pro pokrocile");

        courseDao.create(c1);

        List<Course> ls = courseDao.findAllCourses();
        Course c2 = ls.get(0);

        assertFalse(ls.isEmpty());

        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getName(), c2.getName());

        courseDao.delete(c1);
    }

    @Test
    public void testFindCourseByLanguage() {
        Course c1 = new Course("Anglictina pro pokrocile");
        c1.setLanguage(Language.AJ);
        Course c2 = new Course("Francouzstina pro zacatecniky");
        c2.setLanguage(Language.FJ);
        Course c3 = new Course("Nemcina pro pokrocile");
        c3.setLanguage(Language.NJ);

        courseDao.create(c1);
        courseDao.create(c2);
        courseDao.create(c3);

        List<Course> ls = courseDao.findCourseByLanguage(Language.FJ);
        Course c4 = ls.get(0);
        
        assertTrue(ls.size()==1);
        assertTrue(c4.getName().equals("Francouzstina pro zacatecniky"));

        courseDao.delete(c1);
        courseDao.delete(c2);
        courseDao.delete(c3);
    }

    @Test
    public void testCreateAndDelete() {
		List<Course> ls;
		Course course;
        Course c1 = courseDao.create(new Course("Anglictina pro pokrocile"));
        Course c2 = courseDao.create(new Course("Afrikanstina pro pokrocile"));
        Course c3 = courseDao.create(new Course("Cestina pro pokrocile"));

		ls = courseDao.findAllCourses();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        course = ls.get(1);
        courseDao.delete(course);

        ls = courseDao.findAllCourses();
        assertTrue(ls.size() == 2);


        course = ls.get(1);
		assertEquals("Cestina pro pokrocile", course.getName());
		
		courseDao.delete(c1);
		// c2 already deleted
		courseDao.delete(c3);
    }
}
