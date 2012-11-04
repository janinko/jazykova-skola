package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import cz.muni.fi.pa165.languageschool.services.LessonService;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.dto.StudentDto;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jbrazdil
 */
public class LessonDtoAdapter {
	
	@Autowired
	LessonService lessonService;
	
	
    public void removeLesson(LessonDto lesson){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		lessonService.removeLesson(l);
	}
    
    public void removeStudent(LessonDto lesson,StudentDto student){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Student s = student.adaptToEntity();
		lessonService.removeStudent(l,s);
	}
    
    public void addStudent(LessonDto lesson,StudentDto student){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Student s = new Student();
		s.setAge(student.getAge());
		throw new UnsupportedOperationException("StudentTO is not yet implemented");
		//s.setFirstName();
	}  
    
    public Set<StudentDto> findStudentsByLesson(LessonDto lesson){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Set<Student> students = lessonService.findStudentsByLesson(l);
		Set<StudentDto> studentTOs = new HashSet<StudentDto>();
		for(Student s : students){
			studentTOs.add(new StudentDto(s));
		}
		
		return studentTOs; 
	}
		
	public Set<LessonDto> getAllLessons(){
		Set<Lesson> lessons = lessonService.getAllLessons();
		Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
		for(Lesson l : lessons){
			lessonTOs.add(new LessonDto(l));
		}
		
		return lessonTOs;
	}
	
}
