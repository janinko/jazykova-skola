package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xchrastk
 */
public interface LessonService {
    
    void createLesson(Lesson lesson);
    
    void removeLesson(Lesson lesson);
    
    void removeStudent(Lesson lesson,Student student);
    
    void addStudent(Lesson lesson,Student student);  
    
    List<Student> findStudentsByLesson(Lesson lesson);
		
	Set<Lesson> getAllLessons();
}
