package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.ArrayList;
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
 * @author xchrastk
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, 
		locations = {"classpath:applicationContext.xml"})
public class TeacherServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private TeacherService teacherService;
	
	@ReplaceWithMock
	@Autowired
	private TeacherDAO teacherDao;
		
    @ReplaceWithMock
	@Autowired
	private LessonDAO lessonDao;
    
    @Before
    public void setUp() {
    }
	
	@Test
	public void testCreateTeacher(){
		Teacher teacher = mock(Teacher.class);
        teacherService.createTeacher(teacher);
        
        verify(teacherDao).create(teacher);
	}
    
    @Test
    public void testReadTeacher() {             
        Teacher teacher = teacherService.readTeacher("email");
        
        verify(teacherDao).findTeacherByEmail("email");
    }
    
    @Test
    public void testUpdateTeacher() {
        Teacher teacher = mock(Teacher.class);
        teacherService.updateTeacher(teacher);
        
        verify(teacherDao).update(teacher);
    }
    
    @Test
    public void testDeleteTeacher() {
        Teacher teacher = mock(Teacher.class);
        teacherService.deleteTeacher(teacher);
        
        verify(teacherDao).delete(teacher);
    }
    
    @Test
    public void testGetAllTeachers() {
        Teacher teacher = mock(Teacher.class);
        teacherService.getAllTeachers();
        
        verify(teacherDao).findAllTeachers();
    }
    
    @Test
    public void testGetTeachersLessons() {
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        Lesson lesson1 = mock(Lesson.class);
        Lesson lesson2 = mock(Lesson.class);
        when(lesson1.getTeacher()).thenReturn(teacher1);
        when(lesson2.getTeacher()).thenReturn(teacher2);        
        List<Lesson> list = new ArrayList<Lesson>();
        list.add(lesson1);
        list.add(lesson2);
        when(lessonDao.findAllLessons()).thenReturn(list);
        
        Set<Lesson> lessons = teacherService.getTeachersLessons(teacher1);
        
        assert(lessons.size() == 1);
        assert(lessons.contains(lesson1));
    }
}
