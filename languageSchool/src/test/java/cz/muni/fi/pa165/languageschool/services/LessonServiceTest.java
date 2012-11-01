package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.Date;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author jbrazdil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LessonServiceTest {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private CourseService courseService;
	
	
	
    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
		Course c1 = new Course("Anglictina pro zacatecniky.");
		courseService.createCourse(c1);
		
		Lesson l1 = new Lesson();
		l1.setTime(new Date());
		
		courseService.addLessonToCourse(c1, l1);
		
		Set<Lesson> lessons = lessonService.getAllLessons();
		System.out.println(lessons);
		assertTrue(lessons.size() == 1);
		
		Lesson l2 = lessons.iterator().next();
		System.out.println(l2);
		
		assertEquals(l1.getId(), l2.getId());		
	}
	
	/*@Test
    public void test1() {
		Student s1 = studentDao.create(new Student("Franta", "Novák"));
		Student s2 = studentDao.create(new Student("Pepa", "Novotný"));
		
		Lesson l1 = new Lesson();
		l1.getStudents().add(s1);
		
		lessonService.addLesson(l1);
		Lesson l2 = lessonService.getAllLessons().iterator().next();
		
		System.out.println("Lesson l1" + l1 + " ~ " + l1.getStudents());
		System.out.println("Lesson l2" + l2 + " ~ " + l2.getStudents());
		
	}*/
}
