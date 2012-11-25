package cz.muni.fi.pa165.languageschool.api.adapters;

import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import java.sql.Date;
import java.util.Set;

/**
 * LessonDtoAdapter is used for manipulation with the Lesson entity
 * via injected lesson service using adapter design pattern. 
 * It can retrieve and delete lessons, retrieve all lessons of a given student 
 * and enroll or cancel lesson of a given student.
 * 
 * @author xschlem1
 */
public interface LessonDtoAdapter {

    /**
     * Adds student to lesson
     * 
     * @param lesson lesson to which student will be added
     * @param student student to be added
     */
    void addStudent(LessonDto lesson, StudentDto student);

    /**
     * Retrieves all students enrolled in a given lesson
     * 
     * @param lesson lesson the students of which will be retrieved
     * @return set of all students enrolled in the given lesson
     */
    Set<StudentDto> findStudentsByLesson(LessonDto lesson);

    /**
     * Retrieves lessons available in a database
     * 
     * @return set of all lessons
     */
    Set<LessonDto> getAllLessons();

    /**
     * Removes a one specific lesson from a database
     * 
     * @param lesson lesson to be removed
     */
    void removeLesson(LessonDto lesson);

    /**
     * Removes student from a specific lesson
     * 
     * @param lesson lesson from which student will be removed
     * @param student student to be removed
     */
    void removeStudent(LessonDto lesson, StudentDto student);
    
    /*
     * Retrieves all upcoming lessons till specified date.
     * @param date Date in future
     * @return all upcoming lessons till date
     */
    Set<LessonDto> getUpcomingLessons(Date date);
}
