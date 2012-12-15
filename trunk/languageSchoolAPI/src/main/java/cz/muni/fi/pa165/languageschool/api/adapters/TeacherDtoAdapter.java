package cz.muni.fi.pa165.languageschool.api.adapters;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.Set;

/**
 * TeacherDtoAdapter is used for manipulation with the Teacher entity
 * via injected teacher service using adapter design pattern. 
 * It can create teachers in the database, update, retrieve and delete them. 
 * It allows to retrieve lessons and courses of the teacher.
 * 
 * @author xbrazdi1, xchrastk, xkelnar
 */
public interface TeacherDtoAdapter {

    /**
     * Creates a new teacher. The new teacher must not be null
     * 
     * @param teacher teacher to be created
     * @throws IllegalArgumentException if the teacher parameter is null
     */
    void createTeacher(TeacherDto teacher);
    
    /**
     * Retrieves a one specific teacher from a database
     * 
     * @param email email of a desired teacher
     * @return teacher with a given email
     */
    TeacherDto readTeacher(String email);
    
    /**
     * Retrieves a one specific teacher from a database
     * 
     * @param id id of a desired teacher
     * @return teacher with a given id
     */
    TeacherDto readTeacher(long id);
    
    /**
     * Updates a given teacher. The teacher must not be null
     * 
     * @param teacher teacher to be updated
     * @throws IllegalArgumentException if the teacher is null
     */
    void updateTeacher(TeacherDto teacher);    
    
    /**
     * Deletes a given teacher. The teacher to be deleted must not be null
     * 
     * @param teacher teacher to be deleted
     * @throws NullPointerException if the teacher parameter is null
     */
    void deleteTeacher(TeacherDto teacher);
    
    /**
     * Retrieves all lessons of a given teacher
     * 
     * @param teacher teacher the lessons of which will be returned
     * @return set of all lessons of a given teacher
     */
    Set<LessonDto> getTeachersLessons(TeacherDto teacher);
    
    /**
     * Retrieves all teachers available in the database
     * 
     * @return set of all available teachers
     */
    Set<TeacherDto> getAllTeachers();

    /**
     * Sets teacher's password
     *
     * @param teacher teacher to be updated
     * @param password new password
     */
	void setPassword(TeacherDto teacher, String password);

    /**
     * Try to authentize user.
     *
     * @param email teacher's email
     * @param password teacher's password
	 * @return teacher instace if authentizazion is sucessfull, null otherwise
     */
	TeacherDto authentize(String email, String password);
}
