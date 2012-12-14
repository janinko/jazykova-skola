package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
public class CourseDtoAdapterImpl implements CourseDtoAdapter{
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LessonDtoAdapterImpl lessonDtoAdapter;

	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		Course c = courseService.createCourse(dto2e(courseDto));
		return e2dto(c);
	}

	@Override
	public void deleteCourse(CourseDto courseDto) {
		courseService.deleteCourse(dto2e(courseDto));
	}
	
	@Override
	public void updateCourse(CourseDto courseDto) {
		courseService.updateCourse(dto2e(courseDto));
	}

	@Override
	public void addLessonToCourse(CourseDto courseDto, LessonDto lessonDto) {
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
		if(entity == null) return null;
		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setLanguage(entity.getLanguage());
		dto.setName(entity.getName());
		dto.setLevel(entity.getLevel());

		return dto;
	}

	Course dto2e(CourseDto dto){
		if(dto == null) return null;
		Course entity = new Course();

		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}
		if (dto.getName() != null) {
			entity.setLanguage(Language.valueOf(dto.getLanguage().toString()));
		}
		entity.setLevel(dto.getLevel());

		return entity;
	}
}
