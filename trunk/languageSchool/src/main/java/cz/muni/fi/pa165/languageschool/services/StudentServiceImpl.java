package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import cz.muni.fi.pa165.languageschool.api.services.StudentService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
    private StudentDAO studentDao;
	@Autowired
    private LessonDAO lessonDao;

	
	@Override
    public void createStudent(Student student){
        studentDao.create(student);
    }
    
	@Override
    public void update(Student student) {
        studentDao.update(student);
    }

	@Override
	@Transactional(readOnly=true)
    public Student read(long id) {
        return studentDao.read(id);
    }

	@Override
	@Transactional(readOnly=true)
    public Set<Lesson> getAllLessons(Student student) {
        Set<Lesson> studentsLessons = new HashSet<Lesson>();
        List<Lesson> lessons = lessonDao.findAllLessons();  
        for(Lesson l: lessons) {
            if(l.getStudents().contains(student)){
                studentsLessons.add(l);
            }
        }
        return studentsLessons;
    }

	@Override
    public void lessonEnroll(Student student, Lesson lesson) {
	lesson.getStudents().add(student);
	lessonDao.update(lesson);
    }

	@Override
    public void lessonCancel(Student student, Lesson lesson) {
        lesson.getStudents().remove(student);
	lessonDao.update(lesson);
    }

	@Override
    public void removeStudent(Student s){
        studentDao.delete(s);
    }

	@Override
	@Transactional(readOnly=true)
    public Student read(String email) {
        return studentDao.findStudentByEmail(email);
    }

	@Override
	public void setPassword(Student student, String password) {
		Student s = studentDao.read(student.getId());
		s.setPassword(PasswordEncoder.encode(password));
		studentDao.update(s);
	}

	@Override
	@Transactional(readOnly=true)
	public Student authentize(String email, String password) {
		if(email == null || password == null) return null;
		Student s = studentDao.findStudentByEmail(email);
		if(s == null) return null;
		if(!PasswordEncoder.encode(password).equals(s.getPassword())){
			return null;
		}
		return s;
	}
}
