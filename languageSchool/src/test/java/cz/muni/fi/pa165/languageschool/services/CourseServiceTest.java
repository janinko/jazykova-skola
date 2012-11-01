package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
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
public class CourseServiceTest {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private CourseService courseService;
	
	
    @Before
    public void setUp() {
    }

    @Test
    public void testCreateCourse() {
		Course c1 = new Course("Anglictina pro mirne zacatecniky");
		c1.setLanguage(Teacher.Language.AJ);
		c1.setLevel(2);
		
		courseService.createCourse(c1);
		assertNotNull(c1.getId());
		
		Set<Course> courses = courseService.getAllCourses();
		
		assertTrue(courses.size() == 1);
		
		Course c2 = courses.iterator().next();
		
		assertEquals(c1.getId(), c2.getId());
		assertEquals(c1.getLanguage(), c2.getLanguage());
		assertEquals(c1.getLevel(), c2.getLevel());
		assertEquals(c1.getName(), c2.getName());
		assertEquals(c1, c2);
		
		courseService.deleteCourse(c2);
	}
	
	

    @Test
    public void testDeleteCourse() {
		Course c1 = new Course("Anglictina pro uplne zacatecniky");
		Course c2 = new Course("Anglictina pro mirne zacatecniky");
		Course c3 = new Course("Anglictina pro stredni zacatecniky");
		Set<Course> courses;
		
		courseService.createCourse(c1);
		courseService.createCourse(c2);
		courseService.createCourse(c3);
		
		courses = courseService.getAllCourses();
		assertTrue(courses.size() == 3);
		
		
		assertTrue(courses.contains(c2));
		courseService.deleteCourse(c2);
		courses = courseService.getAllCourses();
		assertTrue(courses.size() == 2);
		assertFalse(courses.contains(c2));
		for(Course c : courses){
			assertFalse(c2.getName().equals(c.getName()));
		}
		
		
		assertTrue(courses.contains(c1));
		courseService.deleteCourse(c1);
		courses = courseService.getAllCourses();
		assertTrue(courses.size() == 1);
		assertFalse(courses.contains(c1));
		
		assertTrue(courses.contains(c3));
		courseService.deleteCourse(c3);
		courses = courseService.getAllCourses();
		assertTrue(courses.isEmpty());
		assertFalse(courses.contains(c1));
	}
	
}
