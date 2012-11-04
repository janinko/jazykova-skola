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
    /*
     * Create teacher.
     * @param teacher teacher to be created
     */
    void createTeacher(Teacher teacher);
    
    /*
     * Read teacher with given email.
     * @param email
     * @return teacher teacher who has the email
     */
    Teacher readTeacher(String email);
    
    /*
     * Updates the teacher.
     * @param teacher updated teacher
     */
    void updateTeacher(Teacher teacher);    
    
    /*
     * Deletes the teacher.
     */
    void deleteTeacher(Teacher teacher);
    
    /*
     * Returns all teachers from the school.
     * @return all teachers
     */
    Set<Teacher> getAllTeachers();
    
     /*
     * Finds all lessons the teacher teaches.
     * @param teacher
     * @return lessons the teacher teaches
     */
    Set<Lesson> getTeachersLessons(Teacher teacher);
    
    /*
     * Finds all courses the teacher teaches.
     * @param teacher
     * @return courses the teacher teaches
     */
    Set<Course> getTeachersCourses(Teacher teacher);
}
