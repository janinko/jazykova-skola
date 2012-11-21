package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.AbstractSpringTest;
import cz.muni.fi.pa165.languageschool.category.Smoke;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jbrazdil
 */
public class DtoAdapterImplTest extends AbstractSpringTest{

	@Autowired private TeacherService teacherService;

	@Autowired private  CourseDtoAdapterImpl courseDtoAdapter;
	@Autowired private StudentDtoAdapterImpl studentDtoAdapter;
	@Autowired private  LessonDtoAdapterImpl lessonDtoAdapter;
	@Autowired private TeacherDtoAdapterImpl teacherDtoAdapter;

	private Course course;
	private Student student;
	private Lesson lesson;
	private Teacher teacher;

	@Before
	public void setUp(){
        course = new Course();
		course.setId(4653L);
		course.setLanguage(Teacher.Language.AJ);
		course.setLevel(45);
		course.setName("Anglictina pro pokrocile");

		student = new Student();
		student.setId(6546545L);
		student.setAge(65);
		student.setFirstName("Franta");
		student.setLastName("Petrželka");

		ArrayList<Student> students = new ArrayList<Student>();
		students.add(student);

		teacher = new Teacher();
		teacher.setEmail("ucitel@skola.cz");
		teacher.setFirstName("Josef");
		HashSet<Teacher.Language> languages = new HashSet<Teacher.Language>();
		languages.add(Teacher.Language.AJ);
		languages.add(Teacher.Language.FJ);
		teacher.setLanguages(languages);
		teacher.setLastName("Zkusil");
		teacher.setNativeLanguage(Teacher.Language.AJ);
		teacherService.createTeacher(teacher);

		lesson = new Lesson();
		lesson.setId(46835789L);
		lesson.setCourse(course);
		lesson.setDate(new Date(1296000000)); // 15 days
		lesson.setStudents(students);
		lesson.setTeacher(teacher);
		lesson.setTime(new Time(900000));// 15 minutes
	}
	
    @Test
	@Category(Smoke.class)
    public void testTransformCourse() {
		CourseDto dto = courseDtoAdapter.e2dto(course);
		assertNotNull(dto);

		Course entity = courseDtoAdapter.dto2e(dto);
		assertNotNull(entity);

		assertEquals(course.getId(), entity.getId());
		assertEquals(course.getLanguage(), entity.getLanguage());
		assertEquals(course.getLevel(), entity.getLevel());
		assertEquals(course.getName(), entity.getName());

    }

    @Test
	@Category(Smoke.class)
    public void testTransformStudent() {
		StudentDto dto = studentDtoAdapter.e2dto(student);
		assertNotNull(dto);

		Student entity = studentDtoAdapter.dto2e(dto);
		assertNotNull(entity);

		assertEquals(student.getId(), entity.getId());
		assertEquals(student.getAge(), entity.getAge());
		assertEquals(student.getFirstName(), entity.getFirstName());
		assertEquals(student.getLastName(), entity.getLastName());

    }

    @Test
	@Category(Smoke.class)
    public void testTransformLesson() {
		LessonDto dto = lessonDtoAdapter.e2dto(lesson);
		assertNotNull(dto);

		Lesson entity = lessonDtoAdapter.dto2e(dto);
		assertNotNull(entity);

		assertEquals(lesson.getId(), entity.getId());
		assertEquals(lesson.getCourse(), entity.getCourse());
		assertEquals(lesson.getDate(), entity.getDate());
		assertEquals(lesson.getTeacher(), entity.getTeacher());
		assertEquals(lesson.getTime(), entity.getTime());

		// LessonDTO don't transfer students
		assertTrue(entity.getStudents().isEmpty());
    }

    @Test
	@Category(Smoke.class)
    public void testTransformTeacher() {
		TeacherDto dto = teacherDtoAdapter.e2dto(teacher);
		assertNotNull(dto);

		Teacher entity = teacherDtoAdapter.dto2e(dto);
		assertNotNull(entity);

		assertEquals(teacher.getId(), entity.getId());
		assertEquals(teacher.getEmail(), entity.getEmail());
		assertEquals(teacher.getFirstName(), entity.getFirstName());
		assertEquals(teacher.getLanguages(), entity.getLanguages());
		assertEquals(teacher.getLastName(), entity.getLastName());
		assertEquals(teacher.getNativeLanguage(), entity.getNativeLanguage());
    }
}
