package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface LessonDAO {
	
	/**
	 * Save new lesson. One course is composed of several lessons.
	 * 
	 * @param lesson which will be saved
	 * @return saved lesson
	 * @throws IllegalArgumentException if lesson is null
	 */
    public Lesson create(Lesson lesson);
	
	
	/**
	 * Returns lesson by id.
	 *
	 * @param id is unique number of lesson.
	 * @return one lesson with the id
	 * @throws IllegalArgumentException id is null or is < 1
	 */
    public Lesson read(long id);
	
	/**
	 * Change lesson which was saved previously.
	 * 
	 * @param lesson which will be changed
	 * @return changed lesson
	 * @throws IllegalArgumentException if lesson is null
	 */
    public Lesson update(Lesson lesson);
	
	/**
	 * Delete of lesson
	 * 
	 * @param lesson which will be deleted
	 * @return deleted lesson
	 * @throws IllegalArgumentException if lesson is null
	 */
    public Lesson delete(Lesson lesson);
    
	
	/**
	 * Return all lessons.
	 * 
	 * @return List of all lessons.
	 */
    public List<Lesson> findAllLessons();
	
	/**
	 * Return list of lessons depending on course.
	 * 
	 * @param course for which lesson will be returned
	 * @return list of lessons
	 */
    public List<Lesson> findLessonByCourse(Course course);
}
