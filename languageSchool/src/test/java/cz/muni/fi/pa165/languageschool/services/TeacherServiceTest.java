package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import cz.muni.fi.pa165.languageschool.category.Mocked;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
 * @author xbrazdi1, xchrastk, xkelnar
 */
@RunWith(MockitoJUnitRunner.class)
@Category(Mocked.class)
public class TeacherServiceTest{
	Teacher teacher;


	@Mock
	private TeacherDAO teacherDao;
    @Mock
	private LessonDAO lessonDao;
	@InjectMocks
	private TeacherService teacherService = new TeacherServiceImpl();
    
    @Before
    public void setUp() {
		teacher = createMockTeacher();
    }

    @After
    public void tearDown() {
		reset(lessonDao, teacherDao);
    }
	
	@Test
	public void testCreateTeacher(){
        teacherService.createTeacher(teacher);
        verify(teacherDao).create(teacher);
	}
    
    @Test
    public void testReadTeacher() {             
        teacherService.readTeacher("email");
        verify(teacherDao).findTeacherByEmail("email");
    }
    
    @Test
    public void testUpdateTeacher() {
        teacherService.updateTeacher(teacher);
        verify(teacherDao).update(teacher);
    }
    
    @Test
    public void testDeleteTeacher() {
        teacherService.deleteTeacher(teacher);
        verify(teacherDao).delete(teacher);
    }
    
    @Test
    public void testGetAllTeachers() {
        teacherService.getAllTeachers();
        verify(teacherDao).findAllTeachers();
    }
    
    @Test
    public void testGetTeachersLessons() {
        Teacher teacher1 = new Teacher("Radek", "Nevidím", "rnevidim@example.com");
		teacher1.setId(4677L);
        Teacher teacher2 = new Teacher("Pavel", "Nepovím", "pnepovim@example.com");
		teacher1.setId(8742L);
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

	private Teacher createMockTeacher() {
		Teacher teacher = new Teacher("Radek", "Nevidím", "rnevidim@example.com");
		teacher.setId(Long.valueOf(98786));
		return teacher;
	}
}
