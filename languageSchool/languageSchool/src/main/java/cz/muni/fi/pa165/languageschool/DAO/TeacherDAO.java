package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 * TeacherDAO does CRUD operations with the Teacher entity.
 * It can store teachers to the database, update, retrieve and delete them.
 * It allows to retrieve teachers by their name and the language they teach.
 * 
 * @author xchrastk
 */
public interface TeacherDAO {    
    /*
     * Adds a new teacher to the database. The teacher must not be null.
     * 
     * @param teacher teacher to be added
     * @return teacher the new teacher (retrieved from the database)
     * @throws IllegalArgumentException if the teacher is null
     */   
    public Teacher create(Teacher teacher);
    
    /*
     * Retrieves a teacher from the database. 
     * 
     * @param id id of the claimed teacher
     * @return teacher from the database with the specified id
     */
    public Teacher read(long id);
    
    /*
     * Updates a teacher in the database. The teacher must not be null.
     * 
     * @param teacher updated version of the teacher
     * @return the updated teacher
     * @throws IllegalArgumentException if the teacher is null
     */
    public Teacher update(Teacher teacher);
    
    /*
     * Deleted a teacher from the database.
     * 
     * @param teacher teacher to be deleted
     * @return the deleted teacher
     * @throws NullPointerException if the teacher is null
     */
    public Teacher delete(Teacher teacher);
    
    /*
     * Retrieves all teachers stored in the database.
     * 
     * @return all teachers in the database
     */
    public List<Teacher> findAllTeachers();
    
    /*
     * Retrieves teachers of the given first and last name.
     * 
     * @param firstName first name of the teacher
     * @param lastName last name of the teacher
     * @return list of teachers with the first and last name
     */
    public List<Teacher> findTeacherByName(String firstName, String lastName);
    
    /*
     * Retrieves teachers who teach the language.
     * 
     * @param language the language to find teachers who teach it
     * @return list of teachers who teach the language
     */
    public List<Teacher> findTeacherByLanguage(Language language);
}
