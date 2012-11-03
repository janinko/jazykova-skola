package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.services.CourseService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fivekeyem
 */
public class CourseDtoAdapter {
	
	@Autowired
	CourseService courseService;
	
	
    public void createCourse(CourseDto courseDto) {
		courseService.createCourse(courseDto.adaptToEntity(courseDto));
	}
	
	public void deleteCourse(CourseDto courseDto) {
		courseService.deleteCourse(courseDto.adaptToEntity(courseDto));
	}
	
	public void addLessonToCourse(CourseDto courseDto, LessonDto lessonDto) {
		courseService.addLessonToCourse(courseDto.adaptToEntity(courseDto), lessonDto.adaptToEntity(lessonDto));
	}
	
	public Set<CourseDto> getCourseByLanguage(Language language) {
		Set<Course> courses = courseService.getCoursesByLanguage(language);
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(new CourseDto(c));
		}
		
		return courseDTOs;
	}
	
	public Set<CourseDto> getAllCourses() {
		Set<Course> courses = courseService.getAllCourses();
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(new CourseDto(c));
		}
		
		return courseDTOs;
	}

	
}
