package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author jbrazdil
 */
@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {
	
	@Mock
	private LessonDAO lessonDao;
	
	@Mock
	private StudentDAO studentDao;
	
	@InjectMocks
	private LessonService lessonService = new LessonServiceImpl();
	
    @Before
    public void setUp() {
    }
	
    @After
    public void tearDown() {
		reset(lessonDao, studentDao);
    }
	
	@Test
	public void testRemoveLesson(){
		Long id = Long.valueOf(1);
		Lesson lesson = new Lesson(); lesson.setId(id);
		Lesson lesson2 = new Lesson(); lesson2.setId(id);
		when(lessonDao.read(id)).thenReturn(lesson2);
		
		lessonService.removeLesson(lesson);
		
		verify(lessonDao).delete(lesson2);
	}
	
	@Test
	public void removeStudent(){
		// STUB
		Long id = Long.valueOf(9868);
		Lesson lesson = new Lesson(); lesson.setId(id);		
		Lesson lesson2 = mock(Lesson.class);
		List<Student> students = mock(List.class);
		Student student = mock(Student.class);
		
		when(lessonDao.read(id)).thenReturn(lesson2);
		when(lesson2.getStudents()).thenReturn(students);
		
		// RUN
		lessonService.removeStudent(lesson,student);
		
		// ASSERT
		verify(students).remove(student);
		verify(lessonDao).update(lesson2);
	}
	
	@Test
	public void testAddStudent(){
		// STUB
		Long id = Long.valueOf(4684);
		Lesson lesson = new Lesson(); lesson.setId(id);		
		Lesson lesson2 = mock(Lesson.class);
		List<Student> students = mock(List.class);
		Student student = mock(Student.class);
		
		when(lessonDao.read(id)).thenReturn(lesson2);
		when(lesson2.getStudents()).thenReturn(students);
		
		// RUN
		lessonService.addStudent(lesson, student);
		
		// ASSERT
		verify(students).add(student);
		verify(lessonDao).update(lesson2);
	}
	
	@Test
	public void testFindStudentsByLesson(){
		// STUB
		Long id = Long.valueOf(6546);
		Lesson lesson = new Lesson(); lesson.setId(id);
		
		// RUN
		lessonService.findStudentsByLesson(lesson);
		
		// ASSERT
		verify(studentDao).findStudentByLesson(lesson);
	}
	
	@Test
	public void testGetAllLessons(){		
		// RUN
		lessonService.getAllLessons();
		
		// ASSERT
		verify(lessonDao).findAllLessons();
	}
}
