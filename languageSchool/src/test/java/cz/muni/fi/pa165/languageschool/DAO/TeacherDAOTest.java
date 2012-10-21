package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.ArrayList;
import java.util.Collection;
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
public class TeacherDAOTest {

    private TeacherDAO teachers;

    public TeacherDAOTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
        teachers = new TeacherDAOImpl(emf);

    }

    @Test
    public void testCreate1() {
        Teacher t1 = new Teacher("Pavel", "Hanak");
        Teacher t2 = teachers.create(t1);

        assertEquals(t1, t2);
        assertEquals(t1.getFirstName(), t2.getFirstName());
        assertEquals(t1.getLastName(), t2.getLastName());
        assertEquals(t1.getId(), t2.getId());

        teachers.delete(t1);
    }

    @Test
    public void testCreate2() {
        Teacher t1 = new Teacher("Pavel", "Hanak");
        assertNull(t1.getId());
        teachers.create(t1);
        assertNotNull(t1.getId());

        teachers.delete(t1);
    }

    @Test
    public void testRead() {
        Teacher t1 = new Teacher("Pavel", "Hanak");
        Teacher t2;
        teachers.create(t1);

        t2 = teachers.read(t1.getId());

        assertTrue(t2.getId().equals(t2.getId()));

        teachers.delete(t1);
    }

    @Test
    public void testFindTeacherByName1() {
        Teacher t1 = new Teacher("Honza1", "Siroky1");
        Teacher t2 = new Teacher("Honza2", "Siroky2");
        Teacher t3 = new Teacher("Honza3", "Siroky3");

        teachers.create(t1);
        teachers.create(t2);
        teachers.create(t3);

        List<Teacher> ls = teachers.findTeacherByName("Honza2", "Siroky2");
        Teacher t4 = ls.get(0);

        assertTrue(ls.size()==1);
        assertTrue(t4.getFirstName().equals("Honza2"));
        assertTrue(t4.getLastName().equals("Siroky2"));

        teachers.delete(t1);
        teachers.delete(t2);
        teachers.delete(t3);
    }

    @Test
    public void testFindTeacherByName2() {
        List<Teacher> ls = teachers.findTeacherByName("Honza", "Siroky");

        assertTrue(ls.isEmpty());
    }

    @Test
    public void testFindAllTeacher() {
        Teacher t1 = new Teacher("Honza1", "Siroky1");
        Teacher t2 = new Teacher("Honza2", "Siroky2");
        Teacher t3 = new Teacher("Honza3", "Siroky3");

        teachers.create(t1);
        teachers.create(t2);
        teachers.create(t3);
        
        List<Teacher> ls = teachers.findAllTeachers();
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

        teachers.delete(t1);
        teachers.delete(t2);
        teachers.delete(t3);
    }

    @Test
    public void testFindTeacherByLanguage() {            
        Teacher t1 = new Teacher("Honza1", "Siroky1");                
        Teacher t2 = new Teacher("Honza2", "Siroky2");
        Teacher t3 = new Teacher("Honza3", "Siroky3");
        
        Collection<Language> languages = new ArrayList<Language>();
        languages.add(Language.AJ);
        languages.add(Language.NJ);
        t1.setLanguages(languages);
        teachers.create(t1);        
        
        languages.clear();
        languages.add(Language.AJ);
        languages.add(Language.RU);
        languages.add(Language.FJ);
        t2.setLanguages(languages);
        teachers.create(t2); 
        
        languages.clear();
        languages.add(Language.AJ);
        languages.add(Language.NJ);
        languages.add(Language.RU);
        t3.setLanguages(languages);
        teachers.create(t3);

        List<Teacher> list = teachers.findTeacherByLanguage(Language.NJ);
        
        assertTrue(list.contains(t1));
        assertTrue(list.contains(t3));
        assertFalse(list.contains(t2));
        
        teachers.delete(t1);
        teachers.delete(t2);
        teachers.delete(t3);
    }  
    
    @Test
    public void testCreateAndDelete() {
        Teacher t1 = new Teacher("Honza1", "Siroky1");
        Teacher t2 = new Teacher("Honza2", "Siroky2");
        Teacher t3 = new Teacher("Honza3", "Siroky3");

        teachers.create(t1);
        teachers.create(t2);
        teachers.create(t3);

        List<Teacher> ls = teachers.findAllTeachers();

        assertFalse(ls.isEmpty());
        assertTrue(ls.size() == 3);

        Teacher t4 = ls.get(1);
        teachers.delete(t4);

        ls = teachers.findAllTeachers();
        assertTrue(ls.size() == 2);

        Teacher t5 = ls.get(1);
        assertTrue(t5.getFirstName().equals("Honza3"));
        assertTrue(t5.getLastName().equals("Siroky3"));

        List<Teacher> lt2 = teachers.findTeacherByName("Honza2", "Siroky2");
        assertTrue(lt2.isEmpty());
        
        teachers.delete(t1);
        teachers.delete(t3);
    }
}
