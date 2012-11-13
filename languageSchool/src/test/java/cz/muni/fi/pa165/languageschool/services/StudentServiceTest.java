package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.category.Mocked;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;


/**
 *
 * @author fivekeyem
 */
@RunWith(MockitoJUnitRunner.class)
@Category(Mocked.class)
public class StudentServiceTest {
	
	private Student student;
	
	@Mock
    private StudentDAO studentDao;
    
    @Mock
	private LessonDAO lessonDao;
	
	
    @InjectMocks
    private StudentService studentService = new StudentServiceImpl();
	
	
	@Before
    public void setUp() throws Exception {
		student = createMockStudent();
	}
	
	@After
	public void tearDown(){
		reset(studentDao, lessonDao);
		/*
		for(Course c : courseService.getAllCourses()){
			courseService.deleteCourse(c);
		}
		*/
	}
	
	
    
    @Test
    public void testCreateStudent() {
		studentService.createStudent(student);
		
		verify(studentDao).create(student);
    }
	
	
	@Test
    public void testUpdateStudent() {
		studentService.update(student);
		
		verify(studentDao).update(student);
    }
	
	@Test
    public void testReadStudent() {
		studentService.read(Long.valueOf(1));
		
		verify(studentDao).read(Long.valueOf(1));
    }
	
		
	@Test
    public void testGetAllLessons() {
		Student s1 = createMockStudent(333L);
		Student s2 = createMockStudent(334L);
		Lesson lesson1 = new Lesson();
		Lesson lesson2 = new Lesson();
		Lesson lesson3 = new Lesson();
		Lesson lesson4 = new Lesson();
		lesson1.getStudents().add(s1);
		lesson1.getStudents().add(s2);
		lesson2.getStudents().add(s1);
		lesson3.getStudents().add(s2);
		lesson4.getStudents().add(s2);
		
		List<Lesson> lessons = new ArrayList<Lesson>();
		lessons.add(lesson1);
		lessons.add(lesson2);
		lessons.add(lesson3);
		lessons.add(lesson4);
		
		doReturn(lessons).when(lessonDao).findAllLessons();
		
		Set<Lesson> ls1 = studentService.getAllLessons(s1);
		Set<Lesson> ls2 = studentService.getAllLessons(s2);
		
		assertTrue(ls1.size() == 2);
		assertTrue(ls2.size() == 3);
		assertTrue(ls1.contains(lesson1));
		assertTrue(ls1.contains(lesson2));
		assertTrue(ls2.contains(lesson1));
		assertTrue(ls2.contains(lesson3));
		assertTrue(ls2.contains(lesson4));
		
    }
	
	
	@Test
    public void lessonEnroll() {
		studentService.lessonEnroll(student, createMockLesson(1L));
		
		verify(lessonDao).update(createMockLesson(1L));
    }
	 
	
	@Test
    public void lessonCancel() {
		studentService.lessonCancel(student, createMockLesson(1L));
		
		verify(lessonDao).update(createMockLesson(1L));
    }
	
	@Test
    public void removeStudent() {
		studentService.removeStudent(student);
		
		verify(studentDao).delete(student);
    }
	
	
	private Student createMockStudent() {
		return createMockStudent(546L);
	}
	
	
	private Student createMockStudent(Long id) {
		Student student = new Student("Martin", "Nitram");
		student.setId(id);
		return student;
	}
	
	
	private Lesson createMockLesson(Long id) {
		Lesson lesson = new Lesson();
		lesson.setId(id);
		
		return lesson;
	}

}
