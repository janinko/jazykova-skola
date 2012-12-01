package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author jbrazdil
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService {
	
	private LessonDAO lessonDao;
	private	StudentDAO studentDao;

	@Autowired
	public void setLessonDao(LessonDAO lessonDao) {
		this.lessonDao = lessonDao;
	}

	@Autowired
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	

    @Override
    public Lesson read(Long lessonId) {
        return lessonDao.read(lessonId);
    }	
    
    public void removeLesson(Lesson l) {
		lessonDao.delete(l);
	}

	public void removeStudent(Lesson l, Student student) {
		Lesson lesson = lessonDao.read(l.getId());
		lesson.getStudents().remove(student);
		lessonDao.update(lesson);
	}

	public void addStudent(Lesson l,Student student) {
		Lesson lesson = lessonDao.read(l.getId());
		lesson.getStudents().add(student);
		lessonDao.update(lesson);
	}
	
	@Transactional(readOnly=true)
	public Set<Student> findStudentsByLesson(Lesson lesson) {
		return new HashSet<Student>(studentDao.findStudentByLesson(lesson));
	}

	@Transactional(readOnly=true)
	public Set<Lesson> getAllLessons() {
		return new HashSet<Lesson>(lessonDao.findAllLessons());
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Lesson> getUpcomingLessons(Date date) {
		return new HashSet<Lesson>(lessonDao.findUpcomingLessons(date));
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Lesson> getLessonsByCourse(Course course) {
		return new HashSet<Lesson>(lessonDao.findLessonByCourse(course));
	}
}