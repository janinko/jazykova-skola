package cz.muni.fi.pa165.languageschool.adapters;

import cz.muni.fi.pa165.languageschool.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.Set;

/**
 * CourseDtoAdapter is used for manipulation with the Course entity
 * via injected course service using adapter design pattern. 
 * It can create courses in the database, retrieve and delete them. 
 * It allows to add lesson to a course or retrieve a course by given
 * language
 * 
 * @author xschlem1
 */
public interface CourseDtoAdapter {

    /**
     * Adds lesson to a course
     * 
     * @param courseDto course to which the lesson will be added
     * @param lessonDto lesson which will be added to the given course
     */
    void addLessonToCourse(CourseDto courseDto, LessonDto lessonDto);

    /**
     * Creates a course
     * 
     * @param courseDto course to be created
     */
    void createCourse(CourseDto courseDto);

    /**
     * Deletes a course
     * 
     * @param courseDto course to be deleted
     */
    void deleteCourse(CourseDto courseDto);

    /**
     * Retrieves courses available in a database
     * 
     * @return set of all courses
     */
    Set<CourseDto> getAllCourses();

    /**
     * Retrieves all courses that focus on a given language
     * 
     * @param language language which courses focus on
     * @return set of all courses that focus on the given language
     */
    Set<CourseDto> getCourseByLanguage(Language language);
    
}