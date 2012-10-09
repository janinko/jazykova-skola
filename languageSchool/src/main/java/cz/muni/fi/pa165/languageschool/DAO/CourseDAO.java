/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import java.util.Collection;

/**
 *
 * @author xchrastk
 */
public interface CourseDAO {
    public Course create(Course course);
    public Course read(long id);
    public Course update(Course course);
    public Course delete(Course course);
    
    public Collection<Course> findAllCourses();
}
