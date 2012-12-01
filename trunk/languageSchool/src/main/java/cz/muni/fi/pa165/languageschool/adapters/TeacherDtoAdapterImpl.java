package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class TeacherDtoAdapterImpl implements TeacherDtoAdapter {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LessonDtoAdapterImpl lessonDtoAdapter;
	@Autowired
	private CourseDtoAdapterImpl courseDtoAdapter;

	@Override
    public void createTeacher(TeacherDto teacher) {
        teacherService.createTeacher(dto2e(teacher));
    }

	@Override
    public TeacherDto readTeacher(String email) {
        return e2dto(teacherService.readTeacher(email));
    }

	@Override
    public void updateTeacher(TeacherDto teacher) {
        teacherService.updateTeacher(dto2e(teacher));
    }

	@Override
    public void deleteTeacher(TeacherDto teacher) {
        teacherService.deleteTeacher(dto2e(teacher));
    }

	@Override
    public Set<LessonDto> getTeachersLessons(TeacherDto teacher) {
        Set<Lesson> lessons = teacherService.getTeachersLessons(dto2e(teacher));
        Set<LessonDto> lessonsDto = new HashSet<LessonDto>();
        for(Lesson l : lessons) {
            lessonsDto.add(lessonDtoAdapter.e2dto(l));
        }
        return lessonsDto;
    }

	@Override
    public Set<CourseDto> getTeachersCourses(TeacherDto teacher) {
        Set <Course> courses = teacherService.getTeachersCourses(dto2e(teacher));
        Set <CourseDto> coursesDto = new HashSet<CourseDto>();
        for(Course c: courses) {
            coursesDto.add(courseDtoAdapter.e2dto(c));
        }
        return coursesDto;
    }

	@Override
    public Set<TeacherDto> getAllTeachers() {
        Set<Teacher> teachers = teacherService.getAllTeachers();
        Set<TeacherDto> teachersDto = new HashSet<TeacherDto>();
        for(Teacher t: teachers) {
            teachersDto.add(e2dto(t));
        }
        return teachersDto;
    }

	@Override
	public void setPassword(TeacherDto teacher, String password) {
		teacherService.setPassword(dto2e(teacher), password);
	}

	@Override
	public TeacherDto authentize(String email, String password) {
		return e2dto(teacherService.authentize(email, password));
	}

	TeacherDto e2dto(Teacher entity) {
		if (entity == null) return null;

		TeacherDto dto = new TeacherDto();

		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		if (entity.getNativeLanguage() != null) {
			dto.setNativeLanguage(entity.getNativeLanguage().toString());
		}
		HashSet<String> languages = new HashSet<String>();
		for (Teacher.Language l : entity.getLanguages()) {
			languages.add(l.toString());
		}
		dto.setLanguages(languages);

		return dto;
	}

    Teacher dto2e(TeacherDto dto) {
		if(dto == null) return null;
        Teacher entity = new Teacher();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
		if(dto.getNativeLanguage() != null){
			entity.setNativeLanguage(Teacher.Language.valueOf(dto.getNativeLanguage()));
		}
        Set<Teacher.Language> lang = new HashSet<Teacher.Language>();
        for (String l : dto.getLanguages()) {
            lang.add(Teacher.Language.valueOf(l));
        }
        entity.setLanguages(lang);
        return entity;
    }
}
