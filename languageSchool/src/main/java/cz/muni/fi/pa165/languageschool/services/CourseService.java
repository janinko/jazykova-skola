package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.Set;

/**
 *
 * @author xchrastk
 */
public interface CourseService {
    
    void createCourse(Course course);
    
    void deleteCourse(Course course);
    
    void addLessonToCourse(Course course, Lesson lesson);   
    
    Set<Course> getCoursesByLanguage(Language language);
    
    Set<Course> getAllCourses();
}
