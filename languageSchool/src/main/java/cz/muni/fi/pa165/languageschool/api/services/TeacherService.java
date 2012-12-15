package cz.muni.fi.pa165.languageschool.api.services;

import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import java.util.Set;

/**
 * TeacherService provides methods for manipulation with Teacher entity.
 * It allows to create, update, delete or retrieve teachers and retrieve
 * lessons or courses of a teacher. It uses injected TeacherDAO 
 * and LessonDAO object.
 * 
 * @author xbrazdi1, xchrastk, xkelnar
 */
public interface TeacherService {
    /*
     * Creates a teacher.
     * 
     * @param teacher teacher to be created
     */
    void createTeacher(Teacher teacher);
    
    /*
     * Retrieves a teacher with a given id.
     * 
     * @param id id of a retrieved teacher
     * @return teacher teacher who has the given id
     */
    Teacher readTeacher(long id);
    
    /*
     * Retrieves a teacher with a given email.
     * 
     * @param email email of a retrieved teacher
     * @return teacher teacher who has the given email
     */
    Teacher readTeacher(String email);
    
    /*
     * Updates a teacher.
     * 
     * @param teacher teacher to be updated
     */
    void updateTeacher(Teacher teacher);    
    
    /*
     * Deletes the teacher.
     * 
     * @param teacher teacher to be deleted
     */
    void deleteTeacher(Teacher teacher);
    
    /*
     * Retrieves all teachers from the database
     * .
     * @return set of all teachers
     */
    Set<Teacher> getAllTeachers();
    
     /*
     * Retrieves all lessons taught by the given teacher.
     * 
     * @param teacher teacher the lessons of which will be retrieved
     * @return set of lessons the teacher teaches
     */
    Set<Lesson> getTeachersLessons(Teacher teacher);
    
    /*
     * Retrieves all courses taught by the given teacher.
     * 
     * @param teacher teacher the courses of which will be retrieved
     * @return courses the teacher teaches
     */
    Set<Course> getTeachersCourses(Teacher teacher);

    /**
     * Sets teacher's password
     *
     * @param teacher teacher to be updated
     * @param password new password
     */
	void setPassword(Teacher teacher, String password);

    /**
     * Try to authentize user.
     *
     * @param email teacher's email
     * @param password teacher's password
	 * @return teacher instace if authentizazion is sucessfull, null otherwise
     */
	Teacher authentize(String email, String password);
}
