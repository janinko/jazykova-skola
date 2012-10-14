package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Lection;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;

/**
 * StudentDAO does CRUD operations with the Student entity.
 * It can store students to the database, update, retrieve and delete them.
 * It allows to retrieve students by name and lection.
 * @author xchrastk
 */
public interface StudentDAO {
	
	/**
	 * This method save new student.
	 * 
	 * @param new student
	 * @return new student who is in database now
	 * @throws IllegalArgumentException if student is null
	 */
    public Student create(Student student);
	
	/**
	 * This method return student.
	 * 
	 * @param id unique number of student
	 * @return Student by id
	 * @throws IllegalArgumentException if there is id null or id <= 0
	 */
    public Student read(long id);
	
	/**
	 * This method change student who was saved previously
	 * 
	 * @param student new changed student
	 * @return Student who was updated
	 * @throws IllegalArgumentException if student is null
	 */
    public Student update(Student student);
	
	
	/**
	 * This method delete student.
	 * 
	 * @param student who will be deleted.
	 * @return deleted student
	 * @throws IllegalArgumentException if student is null
	 */
    public Student delete(Student student);
    
	/**
	 * This method returns all save students.
	 * 
	 * @return all students
	 */
    public List<Student> findAllStudents();
	
	/**
	 * This method returns students depending on combination first name and last name
	 * 
	 * @param firstName First name of student
	 * @param lastName Last name of student
	 * @return One or more students who have appropriate name
	 * @throws IllegalArgumentException if firstName or lastName is null
	 */
    public List<Student> findStudentByName(String firstName, String lastName);
	
	/**
	 * This method return list of students for the lection.
	 * 
	 * @param lection which students have registered
	 * @return one or more students
	 * @throws IllegalArgumentException if lection is null
	 */
    public List<Student> findStudentByLection(Lection lection);
}
