package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * HibernateLessonDao does CRUD operations with the Lesson entity
 * using an injected session factory. It can store lessons to the database, 
 * update, retrieve and delete them. It allows to retrieve lessons by course.
 * 
 * @author xschlem1
 */
public interface HibernateLessonDao {

    /**
     * Creates a new lesson. The new lesson must not be null
     * 
     * @param lesson lesson to be created
     * @return the created lesson
     * @throws IllegalArgumentException if the lesson parameter is null
     */
    Lesson create(Lesson lesson);

    /**
     * Deletes a given lesson. The lesson to be deleted must not be null
     * 
     * @param lesson lesson to be deleted
     * @return the deleted lesson
     * @throws NullPointerException if the lesson parameter is null
     */
    Lesson delete(Lesson lesson);

    /**
     * Retrieves all lessons available in the database
     * 
     * @return list of all available lessons
     */
    List<Lesson> findAllLessons();

    /**
     * Retrieves all lessons by a given course
     * 
     * @param course course the lessons of which should be retrieved
     * @return list of all available lessons of a given course
     */
    List<Lesson> findLessonByCourse(Course course);

    /**
     * Retrieves a one specific lesson from a database
     * 
     * @param id id of a desired lesson
     * @return lesson with a given id
     */
    Lesson read(long id);

    /**
     * Sets a given session factory that is then used for 
     * manipulation with data. The session factory is injected
     * 
     * @param sessionFactory sessionFactory to be set
     */
    @Autowired
    void setSessionFactory(SessionFactory sessionFactory);

    /**
     * Updates a given lesson. The lesson must not be null
     * 
     * @param lesson lesson to be updated
     * @return updated lesson
     * @throws IllegalArgumentException if the lesson is null
     */
    Lesson update(Lesson lesson);
    
}
