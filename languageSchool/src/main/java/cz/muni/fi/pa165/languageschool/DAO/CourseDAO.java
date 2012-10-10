package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface CourseDAO {
    public Course create(Course course);
    public Course read(long id);
    public Course update(Course course);
    public Course delete(Course course);
    
    public List<Course> findAllCourses();
    public List<Course> findCourseByLanguage(Language language);
}