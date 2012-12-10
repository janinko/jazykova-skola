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
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
public class StudentDtoAdapterImpl implements StudentDtoAdapter{
	@Autowired
	private StudentService studentService;
	@Autowired
	private LessonDtoAdapterImpl lessonDtoAdapter;

	@Override
	public void createStudent(StudentDto student){
		studentService.createStudent(dto2e(student));
	}

	@Override
	public void update(StudentDto student){
		studentService.update(dto2e(student));
	}

	@Override
	public StudentDto read(long id){
		Student student = studentService.read(id);
		if(student == null) return null;
		return e2dto(student);
	}

	@Override
	public StudentDto read(String email){
		Student student = studentService.read(email);
		if(student == null) return null;
		return e2dto(student);
	}

    @Override
    public Set<StudentDto> getAllStudents() {
        Set<StudentDto> students = new HashSet<StudentDto>();
        for(Student s : studentService.getAllStudents()){
			students.add(e2dto(s));
		}
        return students;
    }

	@Override
	public Set<LessonDto> getAllLessons(StudentDto student){
		Set<LessonDto> lessons = new HashSet<LessonDto>();
		for(Lesson l : studentService.getAllLessons(dto2e(student))){
			lessons.add(lessonDtoAdapter.e2dto(l));
		}
		return lessons;
	}

	@Override
	public void lessonEnroll(StudentDto student, LessonDto lesson){
		studentService.lessonEnroll(dto2e(student), lessonDtoAdapter.dto2e(lesson));
	}

	@Override
	public void lessonCancel(StudentDto student, LessonDto lesson){
		studentService.lessonCancel(dto2e(student), lessonDtoAdapter.dto2e(lesson));
	}

	@Override
	public void removeStudent(StudentDto student){
		studentService.removeStudent(dto2e(student));
	}

	@Override
	public void setPassword(StudentDto student, String password) {
		studentService.setPassword(dto2e(student), password);
	}

	@Override
	public StudentDto authentize(String email, String password) {
		return e2dto(studentService.authentize(email, password));
	}

	StudentDto e2dto(Student entity) {
		if(entity == null) return null;
		StudentDto dto = new StudentDto();

		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
		dto.setAge(entity.getAge());

		return dto;
	}

	Student dto2e(StudentDto dto) {
		if(dto == null) return null;
		Student entity = new Student();

		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
		entity.setAge(dto.getAge());

		return entity;
	}
}
