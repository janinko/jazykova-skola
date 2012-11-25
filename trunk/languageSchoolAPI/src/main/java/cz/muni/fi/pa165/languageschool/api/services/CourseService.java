package cz.muni.fi.pa165.languageschool.api.services;

import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher.Language;
import java.util.Set;

/**
 * CourseService provides methods for manipulation with Course entity.
 * It allows to store courses to the database, retrieve, delete, add a custom 
 * lesson to a course or retrieve courses by language. It uses injected
 * CourseDAO and LessonDAO object 
 * 
 * @author xchrastk
 */
public interface CourseService {

    /*
     * Adds a new course to the database. The course must not be null.
     * 
     * @param course course to be added
     * @throws IllegalArgumentException if the course is null
     */ 
    void createCourse(Course course);
    
    /*
     * Deletes a course from the database.
     * 
     * @param course course to be deleted
     * @throws NullPointerException if the course is null
     */ 
    void deleteCourse(Course course);
    
    /*
     * Adds a lesson to a course.
     * 
     * @param course course to which a lesson will be added
     * @param lesson lesson which will be added to a course
     * @throws NullPointerException if the course is null
     */ 
    void addLessonToCourse(Course course, Lesson lesson);   
    
    /*
     * Retrieves courses with a given language.
     * 
     * @param language language whose courses we want to find
     * @return set of courses that focus on the language
     */
    Set<Course> getCoursesByLanguage(Language language);
    
    /*
     * Retrieves all courses stored in the database.
     * 
     * @return all courses in the database
     */
    Set<Course> getAllCourses();

	/*
     * Retrieves a course with a given id
     *
     * @param id id of a course
     * @return course with a given id
     */
    Course read(long id);
}
