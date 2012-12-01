package cz.muni.fi.pa165.languageschool.api.services;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import java.sql.Date;
import java.util.Set;

/**
 * LessonService provides methods for manipulation with Lesson entity.
 * It allows to retrieve or delete lessons, add/remove a custom student
 * to a lesson or retrieve students by lesson. It uses injected
 * LessonDAO and StudentDAO object 
 * 
 * @author xchrastk
 */
public interface LessonService {

    /*
     * Reads a lesson eith specified id.
     * @param id lesson's id
     * @return lesson with specified id
     */
    public Lesson read(Long lessonId);
    
    /*
     * Removes a lesson from the database.
     * 
     * @param lesson lesson to be deleted
     * @throws NullPointerException if the lesson is null
     */ 
    void removeLesson(Lesson lesson);
    
    /*
     * Removes a student from a lesson.
     * 
     * @param lesson lesson from which student will be removed
     * @param student student to be removed
     * @throws NullPointerException if the lesson or student is null
     */ 
    void removeStudent(Lesson lesson,Student student);
    
    /*
     * Adds a student to a lesson.
     * 
     * @param lesson lesson to which a student will be added
     * @param student student which will be added to a lesson
     * @throws NullPointerException if the lesson or student is null
     */ 
    void addStudent(Lesson lesson,Student student);  
    
    /*
     * Retrieves students that are signed to a given lesson.
     * 
     * @param lesson lesson whose students we want to find
     * @return set of students that are signed to the lesson
     */
    Set<Student> findStudentsByLesson(Lesson lesson);

    /*
     * Retrieves all lessons stored in the database.
     * 
     * @return all lessons in the database
     */
    Set<Lesson> getAllLessons();

    /*
     * Retrieves all upcoming lessons till specified date.
     * @param date Date in future
     * @return all upcoming lessons till date
     */
    Set<Lesson> getUpcomingLessons(Date date);
    /*
     * Retrieves all lessons from specified course.
     * @param coure coure
     * @return all lessons from specified coures
     */
    Set<Lesson> getLessonsByCourse(Course course);
}
