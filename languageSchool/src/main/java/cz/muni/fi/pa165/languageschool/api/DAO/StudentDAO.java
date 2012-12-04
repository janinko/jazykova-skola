package cz.muni.fi.pa165.languageschool.api.DAO;

import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import java.util.List;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public interface StudentDAO {
	
	/**
	 * This method save new student.
	 * 
	 * @param new student
	 * @return new student who is in database now
	 * @throws IllegalArgumentException if student is null
	 */
    Student create(Student student);
	
	/**
	 * This method return student.
	 * 
	 * @param id unique number of student
	 * @return Student by id
	 * @throws IllegalArgumentException if there is id null or id <= 0
	 */
    Student read(long id);
	
	/**
	 * This method change student who was saved previously
	 * 
	 * @param student new changed student
	 * @return Student who was updated
	 * @throws IllegalArgumentException if student is null
	 */
    Student update(Student student);
	
	/**
	 * This method delete student.
	 * 
	 * @param student who will be deleted.
	 * @return deleted student
	 * @throws IllegalArgumentException if student is null
	 */
    Student delete(Student student);
    
	/**
	 * This method returns all save students.
	 * 
	 * @return all students
	 */
    List<Student> findAllStudents();
    
    /**
	 * This method returns students depending on combination first name and last name
	 * 
	 * @param email email of student
	 * @return student who have the email
	 * @throws IllegalArgumentException if email is Null
	 */
    Student findStudentByEmail(String email);
	
	/**
	 * This method returns students depending on combination first name and last name
	 * 
	 * @param firstName First name of student
	 * @param lastName Last name of student
	 * @return One or more students who have appropriate name
	 * @throws IllegalArgumentException if firstName or lastName is null
	 */
    List<Student> findStudentByName(String firstName, String lastName);
	
	/**
	 * This method return list of students for the lesson.
	 * 
	 * @param lesson which students have registered
	 * @return one or more students
	 * @throws IllegalArgumentException if lesson is null
	 */
    List<Student> findStudentByLesson(Lesson lesson);
}
