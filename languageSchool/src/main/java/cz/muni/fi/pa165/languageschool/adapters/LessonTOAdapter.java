package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import cz.muni.fi.pa165.languageschool.services.LessonService;
import cz.muni.fi.pa165.languageschool.transfer.LessonTO;
import cz.muni.fi.pa165.languageschool.transfer.StudentTO;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jbrazdil
 */
public class LessonTOAdapter {
	
	@Autowired
	LessonService lessonService;
	
	
    public void removeLesson(LessonTO lesson){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		lessonService.removeLesson(l);
	}
    
    public void removeStudent(LessonTO lesson,StudentTO student){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Student s = new Student();
		s.setId(student.getId());
		lessonService.removeStudent(l,s);
	}
    
    public void addStudent(LessonTO lesson,StudentTO student){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Student s = new Student();
		s.setAge(student.getAge());
		throw new UnsupportedOperationException("StudentTO is not yet implemented");
		//s.setFirstName();
	}  
    
    public Set<StudentTO> findStudentsByLesson(LessonTO lesson){
		Lesson l = new Lesson();
		l.setId(lesson.getId());
		Set<Student> students = lessonService.findStudentsByLesson(l);
		Set<StudentTO> studentTOs = new HashSet<StudentTO>();
		for(Student s : students){
			studentTOs.add(new StudentTO(s));
		}
		
		return studentTOs; 
	}
		
	public Set<LessonTO> getAllLessons(){
		Set<Lesson> lessons = lessonService.getAllLessons();
		Set<LessonTO> lessonTOs = new HashSet<LessonTO>();
		for(Lesson l : lessons){
			lessonTOs.add(new LessonTO(l));
		}
		
		return lessonTOs;
	}
	
}
