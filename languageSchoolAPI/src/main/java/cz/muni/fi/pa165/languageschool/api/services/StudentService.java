package cz.muni.fi.pa165.languageschool.api.services;

import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import java.util.Set;

/**
 * StudentService provides methods for manipulation with Student entity.
 * It allows to create, update, delete or retrieve students, retrieve
 * lessons of a student, sign a student in a lesson or sign him out of it.
 * It uses injected LessonDAO and StudentDAO object.
 * 
 * @author xschlem1
 */
public interface StudentService {

    /*
     * Adds a new student to the database. The student must not be null.
     * 
     * @param student student to be added
     * @throws IllegalArgumentException if the student is null
     */ 
    void createStudent(Student student);
    
    /**
     * Updates a student
     * 
     * @param student student to be updated
     */
    void update(Student student);
	
    /*
     * Retrieves a student with a given id
     * 
     * @param id id of a student
     * @return student with a given id
     */
    Student read(long id);
    
    /*
     * Retrieves a student with a given email
     * 
     * @param email email of a student
     * @return student with a given email
     */
    Student read(String email);
	
    /*
     * Retrieves all lessons that a given student has enrolled
     * 
     * @param student student whose lessons are retrieved
     * @return all lessons of a given student
     */
    Set<Lesson> getAllLessons(Student student);
	
    /**
     * Enrolls a student to a lesson
     * 
     * @param student student to be enrolled
     * @param lesson lesson to which the student will be enrolled
     */
    void lessonEnroll(Student student, Lesson lesson);
	
    /**
     * Signs a student out of a lesson
     * 
     * @param student student to be signed out
     * @param lesson lesson from which the student will be signed out
     */
    void lessonCancel(Student student, Lesson lesson);
    
    /**
     * Removes a student from a database
     * 
     * @param student student to be removed
     */
    void removeStudent(Student student);
	
}
