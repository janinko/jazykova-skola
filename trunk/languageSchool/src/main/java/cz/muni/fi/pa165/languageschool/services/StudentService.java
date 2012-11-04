package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.Set;

/**
 *
 * @author xschlem1
 */
public interface StudentService {
    
    void createStudent(Student student);
    
    void update(Student student);
	
    Student read(long id);
	
    Set<Lesson> getAllLessons(Student student);
	
    void lessonEnroll(Student student, Lesson lesson);
	
    void lessonCancel(Student student, Lesson lesson);
    
    void removeStudent(Student student);
	
}
