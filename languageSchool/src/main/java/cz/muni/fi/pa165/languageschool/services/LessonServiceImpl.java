package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author jbrazdil
 */
@Service
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
	
	@Transactional
	public void removeLesson(Lesson l) {
		Lesson lesson = lessonDao.read(l.getId());
		lessonDao.delete(lesson);
	}

	@Transactional
	public void removeStudent(Lesson l, Student student) {
		Lesson lesson = lessonDao.read(l.getId());
		List<Student> students = lesson.getStudents();
		students.remove(student);
		lesson.setStudents(students);
		lessonDao.update(lesson);
	}

	@Transactional
	public void addStudent(Lesson l,Student student) {
		Lesson lesson = lessonDao.read(l.getId());
		lessonDao.delete(lesson);
	}
	
	@Transactional
	public Set<Student> findStudentsByLesson(Lesson lesson) {
		return new HashSet<Student>(studentDao.findStudentByLesson(lesson));
	}

	@Transactional
	public Set<Lesson> getAllLessons() {
		return new HashSet<Lesson>(lessonDao.findAllLessons());
	}


	
}