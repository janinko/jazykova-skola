package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.services.CourseService;
import cz.muni.fi.pa165.languageschool.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fivekeyem
 */
public class CourseDtoAdapterImpl implements CourseDtoAdapter {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TeacherService teacherService;
	
	
    @Override
    public void createCourse(CourseDto courseDto) {
		courseService.createCourse(courseDto.adaptToEntity());
	}
	
    @Override
	public void deleteCourse(CourseDto courseDto) {
		courseService.deleteCourse(courseDto.adaptToEntity());
	}
	
    @Override
	public void addLessonToCourse(CourseDto courseDto, LessonDto lessonDto) {
		Teacher teacher = teacherService.readTeacher(lessonDto.getTeacherEmail());
		courseService.addLessonToCourse(courseDto.adaptToEntity(), lessonDto.adaptToEntity(teacher));
	}
	
    @Override
	public Set<CourseDto> getCourseByLanguage(Language language) {
		Set<Course> courses = courseService.getCoursesByLanguage(language);
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(new CourseDto(c));
		}
		
		return courseDTOs;
	}
	
    @Override
	public Set<CourseDto> getAllCourses() {
		Set<Course> courses = courseService.getAllCourses();
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(new CourseDto(c));
		}
		
		return courseDTOs;
	}

	
}
