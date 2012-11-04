package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


/**
 *
 * @author fivekeyem
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void getAllLessons() {
		
    }
	
	
	@Test
    public void lessonEnroll() {
		studentService.lessonEnroll(student, createMockLesson(1L));
		
		verify(lessonDao).update(createMockLesson(1L));
    }
	 
	
	
	private Student createMockStudent() {
		Student student = new Student("Martin", "Nitram");
		student.setId(Long.valueOf(546));
		return student;
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
	
	
	private List<Lesson> createMockLessons() {
		List<Student> ls = new ArrayList<Student>();
		ls.add(createMockStudent(333L));
		ls.add(createMockStudent(334L));
		
		Lesson lesson1 = new Lesson();
		lesson1.setStudents(ls);
		Lesson lesson2 = new Lesson();
		lesson2.setStudents(ls);
		
		List<Lesson> lessons = new ArrayList<Lesson>();
		lessons.add(lesson1);
		lessons.add(lesson2);
		
		return lessons;
	}
	

}
