package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import java.util.Set;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jbrazdil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CourseService2Test {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private CourseService courseService;
	
	
    @Before
    public void setUp() {
    }
	
	@After
	public void tearDown(){
		for(Course c: courseService.getAllCourses()){
			courseService.deleteCourse(c);
		}
		for(Lesson l: lessonService.getAllLessons()){
			lessonService.removeLesson(l);
		}
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
	
	
    @Test
    public void testCoursesByLanguage() {
		Course c1 = new Course("Anglictina pro zacatecniky"); c1.setLanguage(Language.AJ);
		Course c2 = new Course("Anglictina pro pokrocile"); c2.setLanguage(Language.AJ);
		Course c3 = new Course("Francouzstina pro zacatecniky"); c3.setLanguage(Language.FJ);
		
		courseService.createCourse(c1);
		courseService.createCourse(c2);
		courseService.createCourse(c3);
		
		Set<Course> coursesAJ = courseService.getCoursesByLanguage(Language.AJ);
		Set<Course> coursesFJ = courseService.getCoursesByLanguage(Language.FJ);
		Set<Course> coursesNJ = courseService.getCoursesByLanguage(Language.NJ);
		
		assertTrue(coursesAJ.size() == 2);
		assertTrue(coursesFJ.size() == 1);
		assertTrue(coursesNJ.isEmpty());
		
		assertTrue(coursesAJ.contains(c1));
		assertTrue(coursesAJ.contains(c2));
		assertTrue(coursesFJ.contains(c3));
		
		
		courseService.deleteCourse(c1);
		courseService.deleteCourse(c2);
		courseService.deleteCourse(c3);
	}
	
    @Test
    public void testAddLessonToCourse() {
		Course c1 = new Course("Anglictina pro zacatecniky");
		Lesson l1 = new Lesson();
		Lesson l2 = new Lesson();
		
		courseService.createCourse(c1);
		courseService.addLessonToCourse(c1, l1);
		
		Set<Lesson> lessons = lessonService.getAllLessons();
		assertTrue(lessons.size() == 1);
		assertEquals(l1, lessons.iterator().next());
		
		courseService.addLessonToCourse(c1, l2);
		lessons = lessonService.getAllLessons();
		assertTrue(lessons.size() == 2);
		assertEquals(c1,l1.getCourse());
		
		courseService.deleteCourse(c1);
	}
	
	// When course is deleted, lessons binded to it must be deleted too
    @Test
    public void testDeleteCourseWithLessons() {
		Course c1 = new Course("Anglictina pro zacatecniky");
		Course c2 = new Course("Anglictina pro pokrocile");
		Lesson l1 = new Lesson();
		Lesson l2 = new Lesson();
		Set<Lesson> lessons;
		
		courseService.createCourse(c1);
		courseService.addLessonToCourse(c1, l1);
		lessons = lessonService.getAllLessons();
		assertTrue(lessons.size() == 1);
		assertTrue(lessons.contains(l1));
		assertFalse(lessons.contains(l2));
		
		
		courseService.createCourse(c2);
		courseService.addLessonToCourse(c2, l2);
		lessons = lessonService.getAllLessons();
		assertTrue(lessons.size() == 2);
		assertTrue(lessons.contains(l1));
		assertTrue(lessons.contains(l2));
		
		
		courseService.deleteCourse(c1);
		lessons = lessonService.getAllLessons();
		assertTrue(lessons.size() == 1);
		assertFalse(lessons.contains(l1));
		assertTrue(lessons.contains(l2));
		
		
		courseService.deleteCourse(c2);
		lessons = lessonService.getAllLessons();
		assertTrue(lessons.isEmpty());
		assertFalse(lessons.contains(l1));
		assertFalse(lessons.contains(l2));
	}
	
}
