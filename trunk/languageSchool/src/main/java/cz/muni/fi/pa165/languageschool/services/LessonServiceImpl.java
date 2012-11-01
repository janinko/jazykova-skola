package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
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
	
	@Autowired
	private LessonDAO lessonDao;

	public boolean removeStudent(Lesson l,Student student) {
		Lesson lesson = lessonDao.read(l.getId());
		List<Student> students = lesson.getStudents();
		students.remove(student);
		lesson.setStudents(students);
		lessonDao.update(lesson);
		return true;
	}

	public void addStudent(Lesson l,Student student) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public List<Student> findAllStudents(Lesson l) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void addLesson(Lesson l) {
		//throw new UnsupportedOperationException("Not supported yet.");
		System.out.println(l);
		System.out.println(lessonDao);
		lessonDao.create(l);
	}

	public Set<Lesson> getAllLessons() {
		return new HashSet<Lesson>(lessonDao.findAllLessons());
	}
	
}
