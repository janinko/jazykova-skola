/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xschlem1
 */
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDao;
    private LessonDAO lessonDao;
    
    @Autowired
    public void setStudentDao(StudentDAO studentDao) {
            this.studentDao = studentDao;
    }
    
    @Autowired
    public void setLessonDao(LessonDAO lessonDao) {
            this.lessonDao = lessonDao;
    }
    
    @Transactional
    public void createStudent(Student student){
        studentDao.create(student);
    }
    
    public void update(Student student) {
        studentDao.update(student);
    }

    public Student read(long id) {
        return studentDao.read(id);
    }

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

    public void lessonEnroll(Student student, Lesson lesson) {
	lesson.getStudents().add(student);
	lessonDao.update(lesson);
    }

    public void lessonCancel(Student student, Lesson lesson) {
        lesson.getStudents().remove(student);
	lessonDao.update(lesson);
    }

    public void removeStudent(Student s){
        studentDao.delete(s);
    }
}
