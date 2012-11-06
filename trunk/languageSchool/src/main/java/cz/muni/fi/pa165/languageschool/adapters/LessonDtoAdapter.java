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
		lessonService.removeLesson(lesson.adaptToEntity());
	}
    
    public void removeStudent(LessonDto lesson,StudentDto student){
		lessonService.removeStudent(lesson.adaptToEntity(),student.adaptToEntity());
	}
    
    public void addStudent(LessonDto lesson,StudentDto student){
		lessonService.addStudent(lesson.adaptToEntity(), student.adaptToEntity());
	}  
    
    public Set<StudentDto> findStudentsByLesson(LessonDto lesson){
		Set<StudentDto> studentTOs = new HashSet<StudentDto>();
		for(Student s : lessonService.findStudentsByLesson(lesson.adaptToEntity())){
			studentTOs.add(new StudentDto(s));
		}
		return studentTOs; 
	}
		
	public Set<LessonDto> getAllLessons(){
		Set<LessonDto> lessonTOs = new HashSet<LessonDto>();
		for(Lesson l : lessonService.getAllLessons()){
			lessonTOs.add(new LessonDto(l));
		}
		return lessonTOs;
	}
	
}
