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
    /*
     *@return true if student was actually removed 
     */
    boolean removeStudent(Lesson l,Student student);
    
    void addStudent(Lesson l,Student student);  
    
    /*
     * @return list of students signed at this lection
     */
    List<Student> findAllStudents(Lesson l);
	
	void addLesson(Lesson l);
	
	Set<Lesson> getAllLessons();
    
    
    //+ CRUD operace
}