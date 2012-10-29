package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 * CourseDAO does CRUD operations with the Course entity.
 * It can store courses to the database, update, retrieve and delete them.
 * It allows to retrieve courses by language.
 * 
 * @author xchrastk
 */
public interface CourseDAO {
    /*
     * Adds a new course to the database. The course must not be null.
     * 
     * @param course course to be added
     * @return course the new course (retrieved from the database)
     * @throws IllegalArgumentException if the course is null
     */    
    public Course create(Course course);
    
    /*
     * Retrieves a course from the database. 
     * 
     * @param id id of the claimed course
     * @return course from the database with the specified id
     */
    public Course read(long id);
    
    /*
     * Updates a course in the database. The course must not be null.
     * 
     * @param course updated version of the course
     * @return the updated course
     * @throws IllegalArgumentException if the course is null
     */
    public void update(Course course);
    
    /*
     * Deleted a course from the database.
     * 
     * @param course course to be deleted
     * @return the deleted course
     * @throws NullPointerException if the course is null
     */
    public void delete(Course course);
    
    /*
     * Retrieves all courses stored in the database.
     * 
     * @return all courses in the database
     */
    public List<Course> findAllCourses();
    
    /*
     * Retrieves courses where the language is tought.
     * 
     * @param language language whose courses we want to find
     * @return list of courses that teach the language
     */
    public List<Course> findCourseByLanguage(Language language);
}