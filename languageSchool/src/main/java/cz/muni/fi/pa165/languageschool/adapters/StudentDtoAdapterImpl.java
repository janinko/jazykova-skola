package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.services.StudentService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbrazdil
 */
@Service
public class StudentDtoAdapterImpl implements StudentDtoAdapter {
	
    @Autowired
    private StudentService studentService;

	@Autowired
	private LessonDtoAdapterImpl lessonDtoAdapter;

    public void createStudent(StudentDto student){
		studentService.createStudent(dto2e(student));
    }
    
    public void update(StudentDto student){
		studentService.update(dto2e(student));
	}
	
    public StudentDto read(long id){
		Student student = studentService.read(id);
		if(student == null) return null;
		return e2dto(student);
	}
    
    public StudentDto read(String email){
		Student student = studentService.read(email);
		if(student == null) return null;
		return e2dto(student);
	}
	
    public Set<LessonDto> getAllLessons(StudentDto student){
		Set<LessonDto> lessons = new HashSet<LessonDto>();
		for(Lesson l : studentService.getAllLessons(dto2e(student))){
			lessons.add(lessonDtoAdapter.e2dto(l));
		}
		return lessons;
	}
	
    public void lessonEnroll(StudentDto student, LessonDto lesson){
		studentService.lessonEnroll(dto2e(student), lessonDtoAdapter.dto2e(lesson));
	}
	
    public void lessonCancel(StudentDto student, LessonDto lesson){
		studentService.lessonCancel(dto2e(student), lessonDtoAdapter.dto2e(lesson));
	}
    
    public void removeStudent(StudentDto student){
		studentService.removeStudent(dto2e(student));
	}

	StudentDto e2dto(Student entity) {
		StudentDto dto = new StudentDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
		return dto;
	}

	Student dto2e(StudentDto dto) {
		Student entity = new Student();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());

		return entity;
	}
}
