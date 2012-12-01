package cz.muni.fi.pa165.languageschool.api.adapters;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import java.util.Set;

/**
 * StudentDtoAdapter is used for manipulation with the Student entity
 * via injected student service using adapter design pattern. 
 * It can create studentss in the database, update, retrieve and delete them. 
 * It allows to retrieve all lessons of a given student and to enroll or
 * cancel lesson of a given student.
 * 
 * @author
 */
public interface StudentDtoAdapter {

    /**
     * Creates a new student. The new student must not be null
     * 
     * @param student student to be created
     * @throws IllegalArgumentException if the student parameter is null
     */
    void createStudent(StudentDto student);

    /**
     * Retrieves set of all lessons of a given student
     * 
     * @param student student whose lessons will be retrieved
     * @return set of all lessons of a given student
     */
    Set<LessonDto> getAllLessons(StudentDto student);

    /**
     * Calcels lesson for a given student
     * 
     * @param student student for which lesson will be cancelled
     * @param lesson lesson to be calcelled
     */
    void lessonCancel(StudentDto student, LessonDto lesson);

    /**
     * Enrolls students to lesson
     * 
     * @param student student to be enrolled
     * @param lesson lesson to enroll
     */
    void lessonEnroll(StudentDto student, LessonDto lesson);

    /**
     * Retrieves a one specific student from a database
     * 
     * @param id id of a desired student
     * @return student with a given id
     */
    StudentDto read(long id);
    
     /**
     * Retrieves a one specific student from a database
     * 
     * @param email email of a desired student
     * @return student with a given id
     */
    StudentDto read(String email);

    /**
     * Removes a one specific student from a database
     * 
     * @param id id of a student to be removed
     */
    void removeStudent(StudentDto student);

    /**
     * Updates student
     * 
     * @param student student to be updated
     */
    void update(StudentDto student);

	void setPassword(StudentDto student, String password);

	StudentDto authentize(String email, String password);
}
