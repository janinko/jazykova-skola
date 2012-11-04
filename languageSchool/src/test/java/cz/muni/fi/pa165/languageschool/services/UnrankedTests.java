package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import org.junit.Test;
import org.kubek2k.springockito.annotations.WrapWithSpy;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jbrazdil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, 
		locations = {"classpath:applicationContext.xml"})
public class UnrankedTests {//extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private LessonService lessonService;
	@Autowired
	private CourseService courseService;
	
	@WrapWithSpy
	@Autowired
	private LessonDAO lessonDao;
	
    @Before
    public void setUp() {
    }
	
	/**
	 * Tests if rollback is performed correctly when Exception is thrown
	 * and then if transaction is performed correctly when isn't.
	 */
	@Test
	public void testCourseDeleteRollback(){
		// setup
		Course c = new Course("Angličtina pro začátečníky");
		courseService.createCourse(c);
		courseService.addLessonToCourse(c, new Lesson());
		courseService.addLessonToCourse(c, new Lesson());
		courseService.addLessonToCourse(c, new Lesson());
		
		// test setup
		assertTrue(courseService.getAllCourses().size() == 1);
		assertTrue(lessonService.getAllLessons().size() == 3);
		
		// stub
		doCallRealMethod()
		  .doCallRealMethod()
		  .doThrow(new UnsupportedOperationException("Test ex"))
		    .when(lessonDao).delete(any(Lesson.class));
		
		// run
		try{
			courseService.deleteCourse(c);
			fail("UnsupportedOperationException was expected");
		}catch(UnsupportedOperationException ex){
			assertEquals("Test ex", ex.getMessage());
		}
		
		// assert
		assertTrue(courseService.getAllCourses().size() == 1);
		assertTrue(lessonService.getAllLessons().size() == 3);
		
		// check
		reset(lessonDao);
		
		courseService.deleteCourse(c);
		assertTrue(courseService.getAllCourses().isEmpty());
		assertTrue(lessonService.getAllLessons().isEmpty());
	}
}
