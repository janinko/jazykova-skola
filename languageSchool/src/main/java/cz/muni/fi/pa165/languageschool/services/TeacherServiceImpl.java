package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xchrastk
 */
@Service
public class TeacherServiceImpl implements TeacherService{
    
    private TeacherDAO teacherDao;
    private LessonDAO lessonDao;
	
    @Autowired
    public void setTeacherDao(TeacherDAO teacherDao) {
        this.teacherDao = teacherDao;
    }
	
    @Autowired
    public void setLessonDao(LessonDAO lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Transactional
    public void createTeacher(Teacher teacher) {
        teacherDao.create(teacher);
    }

    @Transactional
    public Teacher readTeacher(String email) {
        return teacherDao.findTeacherByEmail(email);
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Transactional
    public void deleteTeacher(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    @Transactional
    public Set<Lesson> getTeachersLessons(Teacher teacher) {
        Set<Lesson> teachersLessons = new HashSet<Lesson>();
        List<Lesson> lessons = lessonDao.findAllLessons();  
        for(Lesson l: lessons) {
            if(l.getTeacher().equals(teacher)) teachersLessons.add(l);
        }
        return teachersLessons;
    }

    @Transactional
    public Set<Course> getTeachersCourses(Teacher teacher) {
        Set<Lesson> lessons = getTeachersLessons(teacher);        
        Set<Course> courses = new HashSet<Course>();
        for(Lesson l: lessons) {
            courses.add(l.getCourse());
        }
        return courses;
    }

    @Transactional
    public Set<Teacher> getAllTeachers() {
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.addAll(teacherDao.findAllTeachers());
        return teachers;
    }
}