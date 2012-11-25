package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.CourseDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher.Language;
import cz.muni.fi.pa165.languageschool.api.services.CourseService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fivekeyem
 */
@Service
@Transactional
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

	public void createCourse(Course course) {
		courseDao.create(course);
	}

	public void deleteCourse(Course course) {
		courseDao.delete(course);
		List<Lesson> l = lessonDao.findLessonByCourse(course);
		
		for (Lesson lesson : l) {
			lessonDao.delete(lesson);
		}
	}

	public void addLessonToCourse(Course course, Lesson lesson) {
		lesson.setCourse(course);
		lessonDao.create(lesson);
	}

	@Transactional(readOnly=true)
	public Set<Course> getCoursesByLanguage(Language language) {
		return new HashSet<Course>(courseDao.findCourseByLanguage(language));
	}

	@Transactional(readOnly=true)
	public Set<Course> getAllCourses() {
		return new HashSet<Course>(courseDao.findAllCourses());
	}

	@Override
	@Transactional(readOnly=true)
	public Course read(long id) {
		return courseDao.read(id);
	}
	
	
	
}
