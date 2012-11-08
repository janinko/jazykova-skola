package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 * HibernateCourseDao does CRUD operations with the Course entity
 * using an injected session factory. It can store courses to the database, 
 * update, retrieve and delete them. It allows to retrieve courses by language.
 * 
 * @author xschlem1
 */
public interface HibernateCourseDao {

    /**
     * Creates a new course. The new course must not be null
     * 
     * @param course course to be created
     * @return the created course
     * @throws IllegalArgumentException if the course parameter is null
     */
    Course create(Course course);

    /**
     * Deletes a given course. The course to be deleted must not be null
     * 
     * @param course course to be deleted
     * @return the deleted course
     * @throws NullPointerException if the course parameter is null
     */
    Course delete(Course course);

    /**
     * Retrieves all courses available in the database
     * 
     * @return list of all available courses
     */
    List<Course> findAllCourses();

    /**
     * Retrieves all courses by a given language
     * 
     * @param language language the courses of which should be retrieved
     * @return list of all available courses by a given language
     */
    List<Course> findCourseByLanguage(Language language);

    /**
     * Retrieves a one specific course from a database
     * 
     * @param id id of a desired course
     * @return course with a given id
     */
    Course read(long id);

    /**
     * Updates a given course. The course must not be null
     * 
     * @param course course to be updated
     * @return updated course
     * @throws IllegalArgumentException if the course is null
     */
    Course update(Course course);
    
}
