package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.CourseDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.category.Mocked;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;


/**
 *
 * @author fivekeyem
 */
@RunWith(MockitoJUnitRunner.class)
@Category(Mocked.class)
public class CourseServiceTest {
	
	private Course course;
	private Lesson lesson;
	
	@Mock
    private CourseDAO courseDao;
    
    @Mock
	private LessonDAO lessonDao;
	
	@Mock
	private Lesson lesson1;
	
    @InjectMocks
    private CourseService courseService = new CourseServiceImpl();
	
	
	@Before
    public void setUp() throws Exception {
		course = createMockCourse();
		lesson = createMockLesson(Long.valueOf(1));
	}
	
	@After
	public void tearDown(){
		reset(courseDao, lessonDao, lesson1);
		for(Course c : courseService.getAllCourses()){
			courseService.deleteCourse(c);
		}
	}
    
    @Test
    public void testCreateCourse() {
		courseService.createCourse(course);
		
		verify(courseDao).create(course);
    }
	
	
	@Test
    public void testDeleteCourse() {
		when(lessonDao.findLessonByCourse(course)).thenReturn(createMockLessons());
		courseService.deleteCourse(course);
		
		verify(courseDao).delete(course);
		verify(lessonDao, times(3)).delete((Lesson)any());
    }

	
	@Test
    public void addLessonToCourse() {
		courseService.addLessonToCourse(course, lesson1);
		
		verify(lesson1).setCourse(course);
		verify(lessonDao).create(lesson1);
    }
	
	
	@Test
    public void getCoursesByLanguage() {
		courseService.getCoursesByLanguage(Teacher.Language.AJ);
		
		verify(courseDao).findCourseByLanguage(Teacher.Language.AJ);
    }
	
	@Test
    public void getAllCourses() {
		courseService.getAllCourses();
		
		verify(courseDao).findAllCourses();
    }
	
	 
	private Course createMockCourse() {
		Course course = new Course("Anglicita A1");
		course.setId(Long.valueOf(546));
		return course;
	}
	
	private Lesson createMockLesson(Long id) {
		Lesson lesson = new Lesson();
		lesson.setId(id);
		lesson.setCourse(createMockCourse());
		
		return lesson;
	}
	
	private List<Lesson> createMockLessons() {
		List<Lesson> lessons = new ArrayList<Lesson>();
		lessons.add(createMockLesson(1L));
		lessons.add(createMockLesson(2L));
		lessons.add(createMockLesson(3L));
		
		return lessons;
	}
}
