package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lection;
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
public class LectionDAOTest {

    private LectionDAO lections;
    private CourseDAO courses;

    public LectionDAOTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
        lections = new LectionDAOImpl(emf);
        courses = new CourseDAOImpl(emf);
    }

    @Test
    public void testCreate1() {
        Lection l1 = new Lection();
        Lection l2 = lections.create(l1);

        assertEquals(l1, l2);
        assertEquals(l1.getId(), l2.getId());

        lections.delete(l1);
    }

    @Test
    public void testCreate2() {
        Lection l1 = new Lection();

        assertNull(l1.getId());

        Lection l2 = lections.create(l1);

        assertNotNull(l1.getId());
        assertNotNull(l2.getId());

        lections.delete(l1);
    }

    @Test
    public void testRead() {
        Lection l1 = new Lection();

        lections.create(l1);

        Lection l2 = lections.read(l1.getId());

        assertEquals(l1.getId(), l2.getId());

        lections.delete(l1);
    }

    @Test
    public void testFindAllLections() {
        Lection l1 = new Lection();

        lections.create(l1);

        List<Lection> ls = lections.findAllLections();
        Lection l2 = ls.get(0);

        assertFalse(ls.isEmpty());
        assertEquals(l1.getId(), l2.getId());

        lections.delete(l1);
    }

    @Test
    public void testFindLectionByCourse() {
        Lection l1 = new Lection();
        Lection l2 = new Lection();
        Lection l3 = new Lection();
        Course c1 = new Course();
        Course c2 = new Course();
        l1.setCourse(c1);
        l2.setCourse(c2);
        l3.setCourse(c1);

        courses.create(c1);
        courses.create(c2);
        lections.create(l1);
        lections.create(l2);
        lections.create(l3);

        List<Lection> list = lections.findLectionByCourse(c1);

        assertTrue(list.contains(l1));
        assertTrue(list.contains(l3));
        assertFalse(list.contains(l2));
        
        lections.delete(l1);
        lections.delete(l2);
        lections.delete(l3);
        courses.delete(c1);
        courses.delete(c2);
    }

    @Test
    public void testCreateAndDelete() {
        Lection l1 = new Lection();
        Lection l2 = new Lection();
        Lection l3 = new Lection();

        lections.create(l1);
        lections.create(l2);
        lections.create(l3);

        List<Lection> ls = lections.findAllLections();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Lection l4 = ls.get(1);
        lections.delete(l4);

        ls = lections.findAllLections();
        assertTrue(ls.size() == 2);
        
        lections.delete(l1);
        lections.delete(l3);
    }
}
