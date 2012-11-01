package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.Set;

/**
 *
 * @author xchrastk
 */
public interface TeacherService {
    
    void createTeacher(Teacher teacher);
    
    Teacher readTeacher(String email);
    
    void updateTeacher(Teacher teacher);    
    
    void deleteTeacher(Teacher teacher);
    
    Set<Lesson> getTeachersLessons(Teacher teacher);
    
    Set<Course> getTeachersCourses(Teacher teacher);
    
    Set<Teacher> getAllTeachers();
}
