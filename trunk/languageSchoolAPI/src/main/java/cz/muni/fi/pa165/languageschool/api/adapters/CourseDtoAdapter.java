package cz.muni.fi.pa165.languageschool.api.adapters;

import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import java.util.Set;

/**
 * CourseDtoAdapter is used for manipulation with the Course entity
 * via injected course service using adapter design pattern. 
 * It can create courses in the database, retrieve and delete them. 
 * It allows to add lesson to a course or retrieve a course by given
 * language
 * 
 * @author xbrazdi1, xchrastk, xkelnar
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
    Set<CourseDto> getCourseByLanguage(String language);

	/*
     * Retrieves a course with a given id
     *
     * @param id id of a course
     * @return course with a given id
     */
    CourseDto read(long id);
}
