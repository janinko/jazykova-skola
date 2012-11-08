package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * HibernateTeacherDao does CRUD operations with the Teacher entity
 * using an injected session factory. It can store teachers to the database, 
 * update, retrieve and delete them. It allows to retrieve teachers by name and
 * by email.
 * 
 * @author xschlem1
 */
public interface HibernateTeacherDao {

    /**
     * Creates a new teacher. The new teacher must not be null
     * 
     * @param teacher teacher to be created
     * @return the created teacher
     * @throws IllegalArgumentException if the teacher parameter is null
     */
    Teacher create(Teacher teacher);

    /**
     * Deletes a given teacher. The teacher to be deleted must not be null
     * 
     * @param teacher teacher to be deleted
     * @return the deleted teacher
     * @throws NullPointerException if the teacher parameter is null
     */
    Teacher delete(Teacher teacher);

    /**
     * Retrieves all teachers available in the database
     * 
     * @return list of all available teachers
     */
    List<Teacher> findAllTeachers();

    /**
     * Retrieves teacher by a given email
     * 
     * @param email email the teacher of which should be retrieved
     * @return teacher which has the given email address
     */
    Teacher findTeacherByEmail(String email);

    /**
     * Retrieves all teachers by a given first name and last name
     * 
     * @param firstName first name of the teachers to be retrieved
     * @param lastName last name of the teachers to be retrieved
     * @return list of all available teachers by a given first name 
     * and last name
     */
    List<Teacher> findTeacherByName(String firstName, String lastName);

    /**
     * Retrieves a one specific teacher from a database
     * 
     * @param id id of a desired teacher
     * @return teacher with a given id
     */
    Teacher read(long id);

    /**
     * Sets a given session factory that is then used for 
     * manipulation with data. The session factory is injected
     * 
     * @param sessionFactory sessionFactory to be set
     */
    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);

    /**
     * Updates a given teacher. The teacher must not be null
     * 
     * @param teacher teacher to be updated
     * @return updated teacher
     * @throws IllegalArgumentException if the teacher is null
     */
    Teacher update(Teacher teacher);
    
}
