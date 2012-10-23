package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface LessonService {
    /*
     *@return true if student was actually removed 
     */
    boolean removeStudent(Student student);
    
    void addStudent(Student student);  
    
    /*
     * @return list of students signed at this lection
     */
    List<Student> findAllStudents();
    
    
    //+ CRUD operace
}
