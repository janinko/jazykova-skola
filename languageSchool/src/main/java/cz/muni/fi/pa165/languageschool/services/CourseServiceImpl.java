package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.CourseDAO;
import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fivekeyem
 */
@Service
public class CourseServiceImpl implements CourseService {

	private	CourseDAO courseDao;
	private LessonDAO lessonDao;

	@Autowired
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	@Autowired
	public void setLessonDao(LessonDAO lessonDao) {
		this.lessonDao = lessonDao;
	}

	
	@Transactional
	public void createCourse(Course course) {
		courseDao.create(course);
	}

	@Transactional
	public void deleteCourse(Course course) {
		courseDao.delete(course);
	}

	@Transactional
	public void addLessonToCourse(Course course, Lesson lesson) {
		lesson.setCourse(course);
		lessonDao.create(lesson);
	}

	@Transactional
	public Set<Course> getCoursesByLanguage(Language language) {
		return new HashSet<Course>(courseDao.findCourseByLanguage(language));
	}

	@Transactional
	public Set<Course> getAllCourses() {
		return new HashSet<Course>(courseDao.findAllCourses());
	}
	
	
	
}
