package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fivekeyem
 */
@Service
public class CourseDtoAdapterImpl implements CourseDtoAdapter {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private LessonDtoAdapterImpl lessonDtoAdapter;
	
    @Override
    public void createCourse(CourseDto courseDto) {
		courseService.createCourse(dto2e(courseDto));
	}
	
    @Override
	public void deleteCourse(CourseDto courseDto) {
		courseService.deleteCourse(dto2e(courseDto));
	}
	
    @Override
	public void addLessonToCourse(CourseDto courseDto, LessonDto lessonDto) {
		Teacher teacher = teacherService.readTeacher(lessonDto.getTeacherEmail());
		courseService.addLessonToCourse(dto2e(courseDto), lessonDtoAdapter.dto2e(lessonDto));
	}
	
    @Override
	public Set<CourseDto> getCourseByLanguage(Language language) {
		Set<Course> courses = courseService.getCoursesByLanguage(language);
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(e2dto(c));
		}
		
		return courseDTOs;
	}

	@Override
	public CourseDto read(long id) {
		return e2dto(courseService.read(id));
	}
	
    @Override
	public Set<CourseDto> getAllCourses() {
		Set<Course> courses = courseService.getAllCourses();
		Set<CourseDto> courseDTOs = new HashSet<CourseDto>();
		
		for (Course c : courses) {
			courseDTOs.add(e2dto(c));
		}
		
		return courseDTOs;
	}

	CourseDto e2dto(Course entity){
		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setLanguage(entity.getLanguage().toString());
		dto.setName(entity.getName());
		dto.setLevel(entity.getLevel());

		return dto;
	}
	
	Course dto2e(CourseDto dto){
		Course entity = new Course();

		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}	
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}
		if (dto.getName() != null) {
			entity.setLanguage(Teacher.Language.valueOf(dto.getLanguage().toString()));
		}
		entity.setLevel(dto.getLevel());

		return entity;
	}
}
