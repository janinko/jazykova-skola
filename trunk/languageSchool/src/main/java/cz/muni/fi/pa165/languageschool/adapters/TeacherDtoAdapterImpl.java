package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xbrazdi1, xchrastk, xkelnar
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
		Teacher t = dto2e(teacher);
        teacherService.createTeacher(t);
		teacher.setId(t.getId());
    }

	@Override
    public TeacherDto readTeacher(String email) {
        return e2dto(teacherService.readTeacher(email));
    }

	@Override
    public TeacherDto readTeacher(long id) {
        return e2dto(teacherService.readTeacher(id));
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
			dto.setNativeLanguage(entity.getNativeLanguage());
		}

		dto.setLanguages(new HashSet<Language>(entity.getLanguages()));

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
			entity.setNativeLanguage(dto.getNativeLanguage());
		}
        entity.setLanguages(new HashSet<Language>(dto.getLanguages()));
        return entity;
    }
}
