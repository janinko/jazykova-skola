package cz.muni.fi.pa165.languageschool.api.DAO;

import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import java.util.List;

/**
 * TeacherDAO does CRUD operations with the Teacher entity.
 * It can store teachers to the database, update, retrieve and delete them.
 * It allows to retrieve teachers by their name and the language they teach.
 * 
 * @author xbrazdi1, xchrastk, xkelnar
 */
public interface TeacherDAO {    
    /*
     * Adds a new teacher to the database. The teacher must not be null.
     * 
     * @param teacher teacher to be added
     * @return teacher the new teacher (retrieved from the database)
     * @throws IllegalArgumentException if the teacher is null
     */   
    Teacher create(Teacher teacher);
    
    /*
     * Retrieves a teacher from the database. 
     * 
     * @param id id of the claimed teacher
     * @return teacher from the database with the specified id
     */
    Teacher read(long id);
    
    /*
     * Updates a teacher in the database. The teacher must not be null.
     * 
     * @param teacher updated version of the teacher
     * @return the updated teacher
     * @throws IllegalArgumentException if the teacher is null
     */
    Teacher update(Teacher teacher);
    
    /*
     * Deleted a teacher from the database.
     * 
     * @param teacher teacher to be deleted
     * @return the deleted teacher
     * @throws NullPointerException if the teacher is null
     */
    Teacher delete(Teacher teacher);
    
    /*
     * Retrieves all teachers stored in the database.
     * 
     * @return all teachers in the database
     */
    List<Teacher> findAllTeachers();
    
    /*
     * Retrieves teachers of the given first and last name.
     * 
     * @param firstName first name of the teacher
     * @param lastName last name of the teacher
     * @return list of teachers with the first and last name
     */
    List<Teacher> findTeacherByName(String firstName, String lastName);
    
    /*
     * Retrieves teachers of the given email.
     *      
     * @param email email of the teacher
     * @return list of teachers with the first and last name
     */
    Teacher findTeacherByEmail(String email);
}
