package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
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
public class LessonDAOTest {

    private LessonDAO lessons;
    private CourseDAO courses;

    public LessonDAOTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
        lessons = new LessonDAOImpl(emf);
        courses = new CourseDAOImpl(emf);
    }

    @Test
    public void testCreate1() {
        Lesson l1 = new Lesson();
        Lesson l2 = lessons.create(l1);

        assertEquals(l1, l2);
        assertEquals(l1.getId(), l2.getId());

        lessons.delete(l1);
    }

    @Test
    public void testCreate2() {
        Lesson l1 = new Lesson();

        assertNull(l1.getId());

        Lesson l2 = lessons.create(l1);

        assertNotNull(l1.getId());
        assertNotNull(l2.getId());

        lessons.delete(l1);
    }

    @Test
    public void testRead() {
        Lesson l1 = new Lesson();

        lessons.create(l1);

        Lesson l2 = lessons.read(l1.getId());

        assertEquals(l1.getId(), l2.getId());

        lessons.delete(l1);
    }

    @Test
    public void testFindAllLessons() {
        Lesson l1 = new Lesson();

        lessons.create(l1);

        List<Lesson> ls = lessons.findAllLessons();
        Lesson l2 = ls.get(0);

        assertFalse(ls.isEmpty());
        assertEquals(l1.getId(), l2.getId());

        lessons.delete(l1);
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

        courses.create(c1);
        courses.create(c2);
        lessons.create(l1);
        lessons.create(l2);
        lessons.create(l3);

        List<Lesson> list = lessons.findLessonByCourse(c1);

        assertTrue(list.contains(l1));
        assertTrue(list.contains(l3));
        assertFalse(list.contains(l2));
        
        lessons.delete(l1);
        lessons.delete(l2);
        lessons.delete(l3);
        courses.delete(c1);
        courses.delete(c2);
    }

    @Test
    public void testCreateAndDelete() {
        Lesson l1 = new Lesson();
        Lesson l2 = new Lesson();
        Lesson l3 = new Lesson();

        lessons.create(l1);
        lessons.create(l2);
        lessons.create(l3);

        List<Lesson> ls = lessons.findAllLessons();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Lesson l4 = ls.get(1);
        lessons.delete(l4);

        ls = lessons.findAllLessons();
        assertTrue(ls.size() == 2);
        
        lessons.delete(l1);
        lessons.delete(l3);
    }
}
