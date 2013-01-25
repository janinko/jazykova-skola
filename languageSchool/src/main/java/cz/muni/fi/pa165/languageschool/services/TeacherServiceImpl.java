package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDao;
    @Autowired
    private LessonDAO lessonDao;

    @Override
    public void createTeacher(Teacher teacher) {
		teacher.setPassword("deffpass");
        teacherDao.create(teacher);
    }

    @Override
    public Teacher readTeacher(String email) {
        return teacherDao.findTeacherByEmail(email);
    }

    @Override
    public Teacher readTeacher(long id) {
        return teacherDao.read(id);
    }

    @Override
	@Secured("ROLE_TEACHER")
    public void updateTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
	@Secured("ROLE_TEACHER")
    public void deleteTeacher(Teacher teacher) {
        teacherDao.delete(teacher);
        for (Lesson l : getTeachersLessons(teacher)) {
            lessonDao.delete(l);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Lesson> getTeachersLessons(Teacher teacher) {
        Set<Lesson> teachersLessons = new HashSet<Lesson>();
        List<Lesson> lessons = lessonDao.findAllLessons();
        for (Lesson l : lessons) {
            if (l.getTeacher().equals(teacher)) {
                teachersLessons.add(l);
            }
        }
        return teachersLessons;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Course> getTeachersCourses(Teacher teacher) {
        Set<Lesson> lessons = getTeachersLessons(teacher);
        Set<Course> courses = new HashSet<Course>();
        for (Lesson l : lessons) {
            courses.add(l.getCourse());
        }
        return courses;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Teacher> getAllTeachers() {
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(teacherDao.findAllTeachers());
        return teachers;
    }

    @Override
	@Secured("ROLE_TEACHER")
    public void setPassword(Teacher teacher, String password) {
        Teacher t = teacherDao.read(teacher.getId());
		if(t == null) throw new IllegalArgumentException("Provided teacher doesn't exist");
        t.setPassword(PasswordEncoder.encode(password));
        teacherDao.update(t);
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher authentize(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        Teacher s = teacherDao.findTeacherByEmail(email);
        if (s == null) {
            return null;
        }
        if (!PasswordEncoder.encode(password).equals(s.getPassword())) {
            return null;
        }
        return s;
    }
}