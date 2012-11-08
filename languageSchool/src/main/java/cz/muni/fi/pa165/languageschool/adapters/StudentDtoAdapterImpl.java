package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.services.StudentService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jbrazdil
 */
public class StudentDtoAdapterImpl implements StudentDtoAdapter {
	
    @Autowired
    StudentService studentService;

    public void createStudent(StudentDto student){
            studentService.createStudent(student.adaptToEntity());
    }
    
    public void update(StudentDto student){
		studentService.update(student.adaptToEntity());
	}
	
    public StudentDto read(long id){
		return new StudentDto(studentService.read(id));
	}
	
    public Set<LessonDto> getAllLessons(StudentDto student){
		Set lessons = new HashSet<LessonDto>();
		for(Lesson l : studentService.getAllLessons(student.adaptToEntity())){
			lessons.add(new LessonDto(l));
		}
		return lessons;
	}
	
    public void lessonEnroll(StudentDto student, LessonDto lesson){
		studentService.lessonEnroll(student.adaptToEntity(), lesson.adaptToEntity());
	}
	
    public void lessonCancel(StudentDto student, LessonDto lesson){
		studentService.lessonCancel(student.adaptToEntity(), lesson.adaptToEntity());
	}
    
    public void removeStudent(StudentDto student){
		studentService.removeStudent(student.adaptToEntity());
	}
}
