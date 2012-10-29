package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author fivekeyem
 */
public class CourseDAOTest {

	
    private CourseDAO courseDao;

    public CourseDAOTest() {
    }


	
	

    @Before
    public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		courseDao = context.getBean(CourseDAO.class);
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
        c1.setLanguage(Teacher.Language.AJ);
        Course c2 = new Course("Francouzstina pro zacatecniky");
        c2.setLanguage(Teacher.Language.FJ);
        Course c3 = new Course("Nemcina pro pokrocile");
        c3.setLanguage(Teacher.Language.NJ);

        courseDao.create(c1);
        courseDao.create(c2);
        courseDao.create(c3);

        List<Course> ls = courseDao.findCourseByLanguage(Teacher.Language.FJ);
        Course c4 = ls.get(0);
        
        assertTrue(ls.size()==1);
        assertTrue(c4.getName().equals("Francouzstina pro zacatecniky"));

        courseDao.delete(c1);
        courseDao.delete(c2);
        courseDao.delete(c3);
    }

    @Test
    public void testCreateAndDelete() {
        courseDao.create(new Course("Anglictina pro pokrocile"));
        courseDao.create(new Course("Afrikanstina pro pokrocile"));
        courseDao.create(new Course("Cestina pro pokrocile"));

        List<Course> ls = courseDao.findAllCourses();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Course c2 = ls.get(1);
        courseDao.delete(c2);

        ls = courseDao.findAllCourses();
        assertTrue(ls.size() == 2);

        Course c4 = ls.get(1);
        assertTrue(c4.getName().equals("Cestina pro pokrocile"));
    }
}
