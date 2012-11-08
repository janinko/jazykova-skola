package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;

/**
 * HibernateStudentDao does CRUD operations with the Student entity
 * using an injected session factory. It can store students to the database, 
 * update, retrieve and delete them. It allows to retrieve students by lesson
 * or by first name and last name
 * 
 * @author xschlem1
 */
public interface HibernateStudentDao {

    /**
     * Creates a new student. The new student must not be null
     * 
     * @param student student to be created
     * @return the created student
     * @throws IllegalArgumentException if the student parameter is null
     */
    Student create(Student student);

    /**
     * Deletes a given student. The student to be deleted must not be null
     * 
     * @param student student to be deleted
     * @return the deleted student
     * @throws NullPointerException if the student parameter is null
     */
    Student delete(Student student);

    /**
     * Retrieves all students available in the database
     * 
     * @return list of all available students
     */
    List<Student> findAllStudents();

    /**
     * Retrieves all students by a given lesson
     * 
     * @param lesson lesson the students of which should be retrieved
     * @return list of all available students by a given lesson
     */
    List<Student> findStudentByLesson(Lesson lesson);

    /**
     * Retrieves all students by a given first name and last name
     * 
     * @param firstName first name of the students to be retrieved
     * @param lastName last name of the students to be retrieved
     * @return list of all available students by a given first name 
     * and last name
     */
    List<Student> findStudentByName(String firstName, String lastName);

    /**
     * Retrieves a one specific student from a database
     * 
     * @param id id of a desired student
     * @return student with a given id
     */
    Student read(long id);

    /**
     * Updates a given student. The student must not be null
     * 
     * @param student student to be updated
     * @return updated student
     * @throws IllegalArgumentException if the student is null
     */
    Student update(Student student);
    
}
