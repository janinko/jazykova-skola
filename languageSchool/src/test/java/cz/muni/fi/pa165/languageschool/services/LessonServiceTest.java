package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author jbrazdil
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, 
		locations = {"classpath:applicationContext.xml"})
public class LessonServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private LessonService lessonService;
	
	@ReplaceWithMock
	@Autowired
	private LessonDAO lessonDao;
	
	@ReplaceWithMock
	@Autowired
	private StudentDAO studentDao;
	
	
    @Before
    public void setUp() {
    }
	
	@Test
	public void testRemoveLesson(){
		Lesson lesson = mock(Lesson.class);
		Lesson lesson2 = mock(Lesson.class);
		when(lesson.getId()).thenReturn(Long.valueOf(1));
		when(lessonDao.read(1)).thenReturn(lesson2);
		
		lessonService.removeLesson(lesson);
		
		verify(lessonDao).delete(lesson2);
	}
	
	@Test
	public void removeStudent(){
		// STUB
		Lesson lesson = when(mock(Lesson.class).getId()).thenReturn(Long.valueOf(876)).getMock();
		Lesson lesson2 = when(lessonDao.read(876)).thenReturn(mock(Lesson.class)).getMock();
		List<Student> students = when(lesson2.getStudents()).thenReturn(mock(List.class)).getMock();
		Student student = mock(Student.class);
		
		// RUN
		lessonService.removeStudent(lesson,student);
		
		// ASSERT
		verify(students).remove(student);
		verify(lessonDao).update(lesson2);
	}
	
	@Test
	public void testAddStudent(){
		// STUB		
		Lesson lesson = when(mock(Lesson.class).getId()).thenReturn(Long.valueOf(8696)).getMock();
		Lesson lesson2 = when(lessonDao.read(8696)).thenReturn(mock(Lesson.class)).getMock();
		List<Student> students = when(lesson2.getStudents()).thenReturn(mock(List.class)).getMock();
		Student student = mock(Student.class);
		
		// RUN
		lessonService.addStudent(lesson, student);
		
		// ASSERT
		verify(students).add(student);
		verify(lessonDao).update(lesson2);
	}
	
	@Test
	public void testFindStudentsByLesson(){
		// STUB
		Lesson lesson = mock(Lesson.class);
		
		// RUN
		lessonService.findStudentsByLesson(lesson);
		
		// ASSERT
		verify(studentDao).findStudentByLesson(lesson);
	}
	
	@Test
	public void testGetAllLessons(){
		// STUB
		
		// RUN
		lessonService.getAllLessons();
		
		// ASSERT
		verify(lessonDao).findAllLessons();
	}
}
