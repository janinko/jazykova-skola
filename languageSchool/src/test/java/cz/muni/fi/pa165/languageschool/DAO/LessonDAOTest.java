package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.AbstractSpringTest;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fivekeyem
 */
public class LessonDAOTest extends AbstractSpringTest{

	@Autowired
    private LessonDAO lessonDao;
	
	@Autowired
    private CourseDAO courseDao;

    public LessonDAOTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testCreate1() {
        Lesson l1 = new Lesson();
        Lesson l2 = lessonDao.create(l1);

        assertEquals(l1, l2);
        assertEquals(l1.getId(), l2.getId());

        lessonDao.delete(l1);
    }

    @Test
    public void testCreate2() {
        Lesson l1 = new Lesson();

        assertNull(l1.getId());

        Lesson l2 = lessonDao.create(l1);

        assertNotNull(l1.getId());
        assertNotNull(l2.getId());

        lessonDao.delete(l1);
    }

    @Test
    public void testRead() {
        Lesson l1 = new Lesson();

        lessonDao.create(l1);

        Lesson l2 = lessonDao.read(l1.getId());

        assertEquals(l1.getId(), l2.getId());

        lessonDao.delete(l1);
    }

    @Test
    public void testFindAllLessons() {
        Lesson l1 = new Lesson();

        lessonDao.create(l1);

        List<Lesson> ls = lessonDao.findAllLessons();
        Lesson l2 = ls.get(0);

        assertFalse(ls.isEmpty());
        assertEquals(l1.getId(), l2.getId());

        lessonDao.delete(l1);
    }

	
    @Test
    public void testFindLessonByCourse() {
        Lesson l1 = new Lesson();
        Lesson l2 = new Lesson();
        Lesson l3 = new Lesson();
        Course c1 = new Course();
        Course c2 = new Course();
        l1.setCourse(c1);
        l2.setCourse(c2);
        l3.setCourse(c1);

        courseDao.create(c1);
        courseDao.create(c2);
        lessonDao.create(l1);
        lessonDao.create(l2);
        lessonDao.create(l3);

        List<Lesson> list = lessonDao.findLessonByCourse(c1);

        assertTrue(list.contains(l1));
        assertTrue(list.contains(l3));
        assertFalse(list.contains(l2));
        
        lessonDao.delete(l1);
        lessonDao.delete(l2);
        lessonDao.delete(l3);
        courseDao.delete(c1);
        courseDao.delete(c2);
    }

    @Test
    public void testCreateAndDelete() {
        Lesson l1 = new Lesson();
        Lesson l2 = new Lesson();
        Lesson l3 = new Lesson();

        lessonDao.create(l1);
        lessonDao.create(l2);
        lessonDao.create(l3);

        List<Lesson> ls = lessonDao.findAllLessons();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Lesson l4 = ls.get(1);
        lessonDao.delete(l4);

        ls = lessonDao.findAllLessons();
        assertTrue(ls.size() == 2);
        
        lessonDao.delete(l1);
        lessonDao.delete(l3);
    }
	
	@Test
	public void testDataAccessExceptionThrowning(){
		Lesson l = new Lesson();l.setId(Long.valueOf(-1));
		try{
			lessonDao.create(l);
			fail("DataAccessException was expected.");
		}catch(DataAccessException ex){
			//ok
		}
	}
}
